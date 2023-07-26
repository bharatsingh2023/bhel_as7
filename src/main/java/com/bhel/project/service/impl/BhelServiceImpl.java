package com.bhel.project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhel.project.dtoImpl.ChildrenDto;
import com.bhel.project.dtoImpl.GrandChildrenDto;
import com.bhel.project.dtoImpl.GreatGrandChildrenDto;
import com.bhel.project.dtoImpl.ParentChildDto;
import com.bhel.project.dtoImpl.ParentDto;
import com.bhel.project.dtoImpl.StatusDto;
import com.bhel.project.dtoImpl.ViewEditProject;
import com.bhel.project.entityImpl.BbuToWam;
import com.bhel.project.entityImpl.BhelBbuData;
import com.bhel.project.entityImpl.BhelVendorBbuData;
import com.bhel.project.entityImpl.CustomerVendorData;
import com.bhel.project.entityImpl.ProjectVendorData;
import com.bhel.project.jpa.repo.BhelRepo;
import com.bhel.project.jpa.repo.BhelWamRepo;
import com.bhel.project.jpa.repo.ProjectVendorRepository;
import com.bhel.project.repo.GetWamData;
import com.bhel.project.service.BhelService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BhelServiceImpl implements BhelService {
	static final Logger logger = Logger.getLogger(BhelServiceImpl.class);
	@Autowired
	BhelRepo bhelRepo;
	@Autowired
	BhelWamRepo wamRepo;
	@Autowired
	GetWamData wamdata;

	@Autowired
	private SessionFactory wamSessionFactory;
	@Autowired
	ProjectVendorRepository pvRepository;

	@SuppressWarnings("unchecked")
	@Override
	public StatusDto insertConnections(List<Map<String, Map<String, String>>> mapList,
			ViewEditProject viewEditProject) {
		ObjectMapper mapper = new ObjectMapper();
		BbuToWam eachConnection = null;
		BbuToWam returnValue = null;
		long ccn = Long.parseLong(viewEditProject.getCustomer_CCN());
		long cpc = Long.parseLong(viewEditProject.getProject_Code());
		int keyCount = 0;
		StatusDto insertConnectionServiceStatus = new StatusDto();
		List<BbuToWam> returnValueList = new ArrayList<BbuToWam>();
		insertConnectionServiceStatus.setSaveStatus(false);
		insertConnectionServiceStatus.setSaveMessage("Unable to save");
		int count = 0;
		try {
			if (ccn != 0 && cpc != 0) {
				count = bhelRepo.isDataExists(cpc, ccn);
				if (count > 0) {
					bhelRepo.deletePrevData(cpc, ccn);
				}
			}
			if (mapList != null) {

				for (Map<String, Map<String, String>> map : mapList) {

					for (String key : map.keySet()) {
						Map<String, String> dataMap = mapper.convertValue(map.get(key), HashMap.class);
						// logger.info(" dataMap key :: " + dataMap.keySet());
						eachConnection = mapper.convertValue(dataMap, BbuToWam.class);
						eachConnection.setCcn(ccn);
						eachConnection.setCpc(cpc);
						eachConnection.setPkgID(viewEditProject.getPackages());
						eachConnection.setWorkArea(viewEditProject.getWork_area());
						returnValue = bhelRepo.save(eachConnection);
						returnValueList.add(returnValue);
						keyCount++;

					}
				}
				logger.info(returnValueList.size() + " " + keyCount);
				if (returnValueList.size() == keyCount) {
					insertConnectionServiceStatus.setSaveStatus(true);
					insertConnectionServiceStatus.setSaveMessage("Saved successfully");
					insertConnectionServiceStatus.setBbuWamMapingData(mapList);
				}
				return insertConnectionServiceStatus;
			}

		} catch (Exception e) {
			e.printStackTrace();
			insertConnectionServiceStatus.setSaveMessage("Something went wrong");
			return insertConnectionServiceStatus;
		}
		return insertConnectionServiceStatus;
	}

	@Override
	public Map<String, List<ParentDto>> getBbuData(ViewEditProject viewEditProject) {

		LinkedList<ParentChildDto> allLeftChildList = null;
		LinkedList<ParentChildDto> allRightChildList = null;
		Session session = null;
		Map<String, List<ParentDto>> returnListMap = new HashMap<>();

		try {
			logger.info("project code :: " + viewEditProject.getProject_Code());
			// get all parents for left part
			List<String> allLeftParentList = bhelRepo.getAllStNoWithOutDot(viewEditProject.getProject_Code());
			// get all child list for left part
			allLeftChildList = bhelRepo.getAllStNoWithDotByParent(allLeftParentList, viewEditProject.getProject_Code());
			session = wamSessionFactory.openSession();
			session.beginTransaction();

			// get all parents for right part
			// logger.info("viewEditProject data == " + viewEditProject.getProject_Code() +
			// ", "
			// + viewEditProject.getPackages() + ", " + viewEditProject.getWork_area());
			System.out.println("right vpcode -->>> "+viewEditProject.getProject_Code());
			System.out.println("right vpPkg -->>> "+viewEditProject.getPackages());
			System.out.println("right vparea -->>> "+viewEditProject.getWork_area());
			List<String> allRightParentList = wamdata.getAllRightParents(session, viewEditProject.getProject_Code().trim(),
					viewEditProject.getPackages().trim(), viewEditProject.getWork_area().trim());
			// logger.info("all Right Prent List :: " + allRightParentList.size());
			// get all child list for right part
			System.out.println("all Right -- >> "+allRightParentList);
			allRightChildList = wamRepo.getAllRightChildsByParent(allRightParentList);
			
			// logger.info("allRightChildList " + allRightParentList.size());

			if (allLeftChildList != null && allRightChildList != null) {
				List<ParentDto> parentListLeft = createLeftSideList(allLeftParentList, allLeftChildList);
				List<ParentDto> parentListRight = createRigthSideList(allRightParentList, allRightChildList);
				// logger.info("parentList :: " + parentListLeft.size());

				returnListMap.put("left", parentListLeft);
				returnListMap.put("right", parentListRight);

			}

		} catch (Exception e) {
			logger.error("error in " + e.getClass(), e);
		} finally {
			if (session.isOpen() && session.isConnected()) {

				session.disconnect();
				session.close();

			}

		}

		return returnListMap;
	}

	private List<ParentDto> createRigthSideList(List<String> allRightParentList,
			LinkedList<ParentChildDto> allRightChildList) {
		List<ChildrenDto> childrenlistRight = null;

		String prevRightParentid = "";
		List<ParentDto> parentListRight = new ArrayList<ParentDto>();
		// logger.info("Right Parentid ");
		Session session = null;
		BhelVendorBbuData childWamData = null;
		BhelVendorBbuData parentWamData = null;
		try {
			ListIterator<String> parent_ids = allRightParentList.listIterator();
			logger.info(" Parentid " + parent_ids.nextIndex());
			session = wamSessionFactory.openSession();
			session.beginTransaction();

			while (parent_ids.hasNext()) {
				logger.info("prevRightParentid :: " + prevRightParentid);
				ParentDto allParentRight = new ParentDto();
				String nxtParentid = parent_ids.next();
				// logger.info("nxtrightParentid :: " + nxtParentid);
				if (nxtParentid != null && !nxtParentid.equalsIgnoreCase(prevRightParentid)) {
					parentWamData = wamdata.getWamParentDataByStNo(session, nxtParentid);

					allParentRight.setName(nxtParentid);
					allParentRight.setValue(nxtParentid);
					// logger.info("parentWamData :: " + parentWamData);
					if (parentWamData != null) {
						// logger.info("parentWamData.getQUANTITY() :: " + parentWamData.getQuentity());
						allParentRight.setDescription(parentWamData.getItemDescription());
						if (parentWamData.getQuentity() != null)
							allParentRight.setQuantity(Double.parseDouble(parentWamData.getQuentity()));
						else
							allParentRight.setQuantity(0.0);
						if (parentWamData.getRATE() != null)
							allParentRight.setRate(Double.parseDouble(parentWamData.getRATE()));
						else
							allParentRight.setRate(0.0);
						if (parentWamData.getCumAmmount() != null)
							allParentRight.setAmmount(Double.parseDouble(parentWamData.getCumAmmount()));
						else
							allParentRight.setAmmount(0.0);

					}
					// parentWamData = null;
					// Child
					String prevChildId = "";
					childrenlistRight = new ArrayList<ChildrenDto>();
					ListIterator<ParentChildDto> childObjectsRight = allRightChildList.listIterator();

					while (childObjectsRight.hasNext()) {
						ChildrenDto children = new ChildrenDto();
						ParentChildDto ent = childObjectsRight.next();
						String nxtChildId = ent.getChildId();

						childWamData = wamdata.getWamChildDataByStNo(session, nxtChildId, nxtParentid);

						if (nxtChildId != null && !nxtChildId.equalsIgnoreCase(prevChildId)
								&& ent.getParentId().equalsIgnoreCase(nxtParentid)) {

							// logger.info("next child id :: " + nxtChildId);

							children.setName(nxtChildId);
							children.setValue(nxtChildId);
							if (childWamData != null) {
								// logger.info("ITEM_DESCRIPTION child :: " +
								// childWamData.getItemDescription());
								children.setDescription(childWamData.getItemDescription());
								if (childWamData.getQuentity() != null)
									children.setQuantity(Double.parseDouble(childWamData.getQuentity()));
								else
									children.setQuantity(0.0);
								if (childWamData.getRATE() != null)
									children.setRate(Double.parseDouble(childWamData.getRATE()));
								else
									children.setRate(0.0);
								if (childWamData.getCumAmmount() != null)
									children.setAmmount(Double.parseDouble(childWamData.getCumAmmount()));
								else
									children.setAmmount(0.0);

							}
							// grand child
							List<GrandChildrenDto> grandChildrensRight = new ArrayList<GrandChildrenDto>();
							logger.info("prev child id :: " + nxtChildId);
							logger.info("next parent id :: " + nxtParentid);
							int grandChildExists = wamRepo.getGrandChildCount(nxtChildId, nxtParentid);
							logger.info("grand Child Exists :: " + grandChildExists);
							if (grandChildExists > 0) {
								// List<String> getTypeByParent = wamRepo.getType(nxtParentid, nxtChildId);
								ListIterator<ParentChildDto> grandChildObjectsRight = allRightChildList.listIterator();

								String prevGrandChildId = "";
								while (grandChildObjectsRight.hasNext()) {

									GrandChildrenDto grandChildren = new GrandChildrenDto();
									ParentChildDto grandChildrenEnt = grandChildObjectsRight.next();
									String nxtGrandChildId = grandChildrenEnt.getGrandchildId();
									if (nxtGrandChildId != null && !nxtGrandChildId.equalsIgnoreCase(prevGrandChildId)
											&& grandChildrenEnt.getChildId().equalsIgnoreCase(nxtChildId)) {

										logger.info("prev child id :: " + nxtChildId);
										logger.info("next grand child id :: " + nxtGrandChildId);
										BhelVendorBbuData grandChildWamData = wamdata.getGrandChildDataByStNo(session,
												nxtGrandChildId, nxtChildId);
										// BhelWamData grandChildWamData =
										// wamRepo.getGrandChildDataByStNo(nxtGrandChildId,
										// nxtChildId);

										grandChildren.setName(nxtGrandChildId);
										grandChildren.setValue(nxtGrandChildId);
										if (grandChildWamData != null) {
											// logger.info("ITEM_DESCRIPTION gchild:: "
											// + grandChildWamData.getItemDescription());
											grandChildren.setDescription(grandChildWamData.getItemDescription());
											if (grandChildWamData.getQuentity() != null)
												grandChildren.setQuantity(
														Double.parseDouble(grandChildWamData.getQuentity()));
											else
												grandChildren.setQuantity(0);
											if (grandChildWamData.getRATE() != null)
												grandChildren.setRate(Double.parseDouble(grandChildWamData.getRATE()));
											else
												grandChildren.setRate(0);
											if (grandChildWamData.getCumAmmount() != null)
												grandChildren.setAmmount(
														Double.parseDouble(grandChildWamData.getCumAmmount()));
											else
												grandChildren.setAmmount(0.0);
											// new Children().setParent(eachStNo.getParent());
										}
										grandChildrensRight.add(grandChildren);
									}
									prevGrandChildId = nxtGrandChildId;
								}
								children.setGrandChild(grandChildrensRight);
							}

							childrenlistRight.add(children);

						}
						allParentRight.setChildList(childrenlistRight);
						prevChildId = nxtChildId;
					}

					parentListRight.add(allParentRight);
					prevRightParentid = nxtParentid;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session.isOpen() && session.isConnected()) {

				session.disconnect();
				session.close();

			}

		}
		return parentListRight;
	}

	private List<ParentDto> createLeftSideList(List<String> allLeftParentList,
			LinkedList<ParentChildDto> allLeftChildList) {

		List<ChildrenDto> childrenlist = null;

		String prevParentid = "";
		List<ParentDto> parentList = new ArrayList<ParentDto>();
		try {
			ListIterator<String> parent_ids = allLeftParentList.listIterator();

			while (parent_ids.hasNext()) {
				ParentDto allParent = new ParentDto();

				String nxtParentid = parent_ids.next();

				logger.info("prevParentid :: " + prevParentid);
				// logger.info("parent Id :: " + nxtParentid);

				if (nxtParentid != null && !nxtParentid.equalsIgnoreCase(prevParentid)) {
					logger.info("nxtParentid <-->:: " + nxtParentid);
					BhelBbuData parentBoqData = bhelRepo.getParentDataByStNo(nxtParentid);
					allParent.setName(nxtParentid);
					allParent.setValue(nxtParentid);
					logger.info("parentBoqData <-->:: " + parentBoqData);
					if (parentBoqData != null) {
						allParent.setQuantity(Double.parseDouble(Integer.toString(parentBoqData.getCUM_QTY())));
						allParent.setDescription(parentBoqData.getITEM_DESCRIPTION());
						allParent.setRate(Double.parseDouble(parentBoqData.getRATE()));
						allParent.setAmmount(Double.parseDouble(Integer.toString(parentBoqData.getCUM_AMT())));
					}

					ListIterator<ParentChildDto> childObjects = allLeftChildList.listIterator();
					int childcount = 0;

					String prevChildId = "";
					childrenlist = new ArrayList<ChildrenDto>();
					while (childObjects.hasNext()) {

						childcount++;

						ChildrenDto children = new ChildrenDto();
						ParentChildDto ent = childObjects.next();
						String nxtChildId = ent.getChildId();
						if (nxtChildId != null && !nxtChildId.equalsIgnoreCase(prevChildId.toString())
								&& ent.getParentId().equalsIgnoreCase(nxtParentid)) {
							// logger.info("prev child id :: " + prevChildId.toString());
							// logger.info("next child id :: " + nxtChildId);
							BhelBbuData childBoqData = bhelRepo.getChildDataByStNo(nxtChildId, nxtParentid);

							children.setName(nxtChildId);
							children.setValue(nxtChildId);
							if (childBoqData != null) {
								children.setDescription(childBoqData.getITEM_DESCRIPTION());
								children.setQuantity(Double.parseDouble(Integer.toString(childBoqData.getQUANTITY())));
								children.setRate(Double.parseDouble(childBoqData.getRATE()));
								children.setAmmount(Double.parseDouble(Integer.toString(childBoqData.getCUM_AMT())));
							}
							// grand child
							List<GrandChildrenDto> grandChildrens = new ArrayList<GrandChildrenDto>();

							int grandChildExists = bhelRepo.getGrandChildCount(nxtChildId, nxtParentid);
							if (grandChildExists > 0) {
								ListIterator<ParentChildDto> grandChildObjects = allLeftChildList.listIterator();

								String prevGrandChildId = "";
								while (grandChildObjects.hasNext()) {

									GrandChildrenDto grandChildren = new GrandChildrenDto();
									ParentChildDto grandChildrenEnt = grandChildObjects.next();
									String nxtGrandChildId = grandChildrenEnt.getGrandchildId();
									if (nxtGrandChildId != null && !nxtGrandChildId.equalsIgnoreCase(prevGrandChildId)
											&& grandChildrenEnt.getChildId().equalsIgnoreCase(nxtChildId)) {

										BhelBbuData grandChildBoqData = bhelRepo
												.getGrandChildDataByStNo(nxtGrandChildId, nxtChildId);

										grandChildren.setName(nxtGrandChildId);
										grandChildren.setValue(nxtGrandChildId);
										if (grandChildBoqData != null) {
											grandChildren.setDescription(grandChildBoqData.getITEM_DESCRIPTION());
											grandChildren.setQuantity(grandChildBoqData.getCUM_QTY());
											grandChildren.setRate(Double.parseDouble(grandChildBoqData.getRATE()));
											grandChildren.setAmmount(Double
													.parseDouble(Integer.toString(grandChildBoqData.getCUM_AMT())));
											// new Children().setParent(eachStNo.getParent());
										}
										// great grand child
										List<GreatGrandChildrenDto> grandGrandChildren = new ArrayList<GreatGrandChildrenDto>();
										int greatGrandChildExists = bhelRepo.getGreatGrandcChildCount(nxtGrandChildId,
												nxtChildId);
										if (greatGrandChildExists > 0) {
											// logger.info(nxtChildId + "::" + nxtGrandChildId + " has "
											// + greatGrandChildExists + "great grand childs");

											ListIterator<ParentChildDto> greatGrandChildObjects = allLeftChildList
													.listIterator();

											String prevGreatGrandChildId = "";
											while (greatGrandChildObjects.hasNext()) {

												GreatGrandChildrenDto gGrandChildren = new GreatGrandChildrenDto();
												ParentChildDto gGrandChildrenEnt = greatGrandChildObjects.next();
												String nxtGreatGrandChildId = gGrandChildrenEnt.getGrandgrandchildId();

												if (nxtGreatGrandChildId != null
														&& !nxtGreatGrandChildId.equalsIgnoreCase(prevGreatGrandChildId)
														&& gGrandChildrenEnt.getGrandchildId()
																.equalsIgnoreCase(nxtGrandChildId)) {
													// logger.info("prev grand child id :: " +
													// prevGreatGrandChildId);
													// logger.info("next grand child id :: " +
													// nxtGreatGrandChildId);
													BhelBbuData greatGrandChildBoqData = bhelRepo
															.getGreatGrandChildrenDataByStNo(nxtGreatGrandChildId,
																	nxtGrandChildId);

													gGrandChildren.setName(nxtGreatGrandChildId);
													gGrandChildren.setValue(nxtGreatGrandChildId);
													if (greatGrandChildBoqData != null) {
														gGrandChildren.setDescription(
																greatGrandChildBoqData.getITEM_DESCRIPTION());
														gGrandChildren.setQuantity(Double.parseDouble(Integer
																.toString(greatGrandChildBoqData.getQUANTITY())));
														gGrandChildren.setRate(
																Double.parseDouble(greatGrandChildBoqData.getRATE()));
														gGrandChildren.setAmmount(Double.parseDouble(
																Integer.toString(greatGrandChildBoqData.getCUM_AMT())));
													}
													grandGrandChildren.add(gGrandChildren);
												}

												prevGreatGrandChildId = nxtGreatGrandChildId;

											}
											grandChildren.setgGrandchild(grandGrandChildren);
										} else {
											grandChildren.setgGrandchild(grandGrandChildren);
										}

										grandChildrens.add(grandChildren);
										logger.info("grandChildren.size :: " + grandChildrens.size());
									}

									prevGrandChildId = nxtGrandChildId;

								}
								logger.info(" nxtChildId before set :: " + nxtChildId);
								children.setGrandChild(grandChildrens);
							}

							childrenlist.add(children);
							logger.info("childrenlist for " + nxtChildId + ":: " + childrenlist.size());

						}
						allParent.setChildList(childrenlist);
						prevChildId = nxtChildId;

					}
					// elementlistMap.put("subcategory", childrenlist);

				}
				parentList.add(allParent);
				prevParentid = nxtParentid;
			}
		} catch (Exception e) {
			logger.info("createLeftSideList error ", e);
		}
		return parentList;

	}

	@Override
	public List<Map<String, Map<String, String>>> getBbuToWamDataByCpcCcn(long cpc, long ccn) {
		logger.info("cpc :: " + cpc);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Map<String, String>> pathMap = new HashMap<>();
		List<Map<String, Map<String, String>>> pathMapList = new ArrayList<>();
		try {
			List<BbuToWam> bbuToWam = bhelRepo.getDataByCpcAndCcn(cpc, ccn);
			logger.info("bbuToWam :: " + bbuToWam);
			if (bbuToWam != null) {
				for (BbuToWam eachBbuToWam : bbuToWam) {

					Map<String, String> workMap = mapper.convertValue(eachBbuToWam, HashMap.class);
					logger.info("workMap :: " + workMap);
					pathMap.put(eachBbuToWam.getLeftPathName(), workMap);
					logger.info("pathMap :: " + pathMap);

				}
				pathMapList.add(pathMap);
				logger.info("pathMapList :: " + pathMapList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathMapList;

	}

	@Override
	public Boolean savePackageWiseWorkArea(String projectCode, String projectPackage, String workAreas,
			String vendorName, String vendorId) {
		List<String> workAreaArrayList = null;
		List<ProjectVendorData> savedList = null;

		try {
			if (workAreas != null) {
				logger.info(workAreas.length());
				if (workAreas.contains(",")) {
					workAreaArrayList = Arrays.asList(workAreas.split(","));
				} else {
					workAreaArrayList = new ArrayList<>();
					workAreaArrayList.add(workAreas);
				}
				List<ProjectVendorData> pvDataList = new ArrayList<>();
				for (String workArea : workAreaArrayList) {
					ProjectVendorData pvData = new ProjectVendorData();
					pvData.setpKg(projectPackage);
					pvData.setProjectCode(projectCode);
					pvData.setWorkArea(workArea);
					pvData.setVendorName(vendorName);
					pvData.setVendorId(vendorId);
					pvDataList.add(pvData);
					// pvRepository.updateWorkArea(projectPackage, projectCode, workArea);
				}

				savedList = pvRepository.saveAll(pvDataList);
				if (workAreaArrayList.size() == savedList.size()) {
					return true;
				}

			}
		} catch (Exception e) {
			logger.error("savePackageWiseWorkArea error :: ", e);
			return false;
		}
		return false;
	}

	@Override
	public List<String> getWorkAreaById(String projectCode) {
		try {

			return pvRepository.getByProjectId(projectCode);
		} catch (Exception e) {
			logger.error("getWorkAreaById error ", e);
		}
		return null;
	}

	@Override
	public int getBbuToWamDataCountByCpcCcn(String project_Code, String customer_CCN) {
		int bbuToWam = 0;
		try {
			customer_CCN = "0";
			System.out.println("proj code "+ project_Code);
			//bbuToWam = bhelRepo.getDataCountByCpcAndCcn(Long.parseLong(project_Code), Long.parseLong(customer_CCN));
			bbuToWam = bhelRepo.getDataCountByCpcAndCcn(Long.parseLong(project_Code));

			
		} catch (Exception e) {
			logger.error("getBbuToWamDataCountByCpcCcn error ", e);
		}
		return bbuToWam;
	}

	@Override
	public CustomerVendorData getProjectData(String project_Code) {
		try {
			return bhelRepo.getByProjectCode(project_Code);
		} catch (Exception e) {
			logger.error("getProjectData error ", e);
		}
		return null;
	}

}
