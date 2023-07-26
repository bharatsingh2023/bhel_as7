/**
 * 
 */
let leftBoxID;
let rightBoxID;
let workDone;
var joinPathMap_1 = new Map();
let pathName;
$(document).ready(function() {
	offsetHeightFooter = document.getElementById('footer').offsetHeight;
	offsetHeight = document.getElementById('header').offsetHeight + 5;
	//offsetHeight = 0;
	console.log("offsetHeight :: " + offsetHeight);
	var pageMode = document.getElementById("mode").innerHTML;
	console.log("mode :: " + pageMode);
	var spinner = $('#loader');
	if (pageMode == "viewupdate") {
		$("#savebutton").hide();
		$("#updatebutton").show();
	} else if (pageMode == "create") {
		$("#savebutton").show();
		$("#updatebutton").hide();
	}
	//console.log("pathMaps :: " + JSON.stringify(pathMapList));
	function fetchMappedDataAndRedraw(fdata) {
		console.log("form data :: ", typeof fdata);
		console.log("ccn data :: ", $('#ccn').html());
		if (fdata == undefined) {
			console.log("formdat is empty");
			fdata = new FormData();
			fdata.append('project_Code', $('#cpc').html());
			fdata.append('customer_CCN', $('#ccn').html());
			fdata.append('packages', $('#pkgs').html());
			fdata.append('work_area', $('#workarea').html());
		}
		console.log("form data new :: ", fdata.entries().next().value);
		spinner.show();
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "/bhel/getwork");
		xhr.onreadystatechange = function(success) {
			//{"status":true,"message":"Saved successfully.","data":null}

			//var responseVal = JSON.stringify(success);
			console.log(xhr)
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				spinner.hide();
				var successJson = JSON
					.stringify(xhr.responseText);
				console
					.log("success :: " + successJson);
				//create both side div
				let successData = JSON.parse(xhr.responseText);
				createWorkPage(successData.parentMap);
				// toogle all hidden div
				isFromViewWork = true;


				const pathArray = successData.bbuWamMapingData;

				const mapFromPathList = new Map(Object.entries(pathArray.at(0)));
				console
					.log("success :: "
						+ mapFromPathList.keys());
				console
					.log("success mapFromPathList :: "
						+ JSON.stringify(Object.fromEntries(mapFromPathList)));


				const keys = mapFromPathList.keys();
				console.log("key :: " + keys);
				//	console.log("pathMaps :: " + JSON.stringify(Object.fromEntries(pathMaps)));
				//const nxtPathKeys = keys.next();

				for (const key of mapFromPathList.keys()) {
					const joinHandleMap = new Map();
					//console.log("Map 1 :: " + JSON.stringify(Object.fromEntries( new Map(Object.entries(mapFromPathList.get(key))))));
					leftBoxID = new Map(Object.entries(mapFromPathList.get(key))).get("leftBoxId");
					rightBoxID = new Map(Object.entries(mapFromPathList.get(key))).get("rightBoxId");


					console.log(">>>--<<<leftBoxID :: " + document.getElementById(leftBoxID));
					console.log(">>>--<<<rightBoxID :: " + document.getElementById(rightBoxID));
					//console.log(">>>--<<<rightBoxID :: " + document.getElementById(document.getElementById(rightBoxID).parentNode.id).parentNode.id);
					document.getElementById(leftBoxID).style.backgroundColor = "#1a8965";
					document.getElementById(rightBoxID).style.backgroundColor = "#4cd1a6";

					let leftParentID_3 = document.getElementById(document.getElementById(leftBoxID).parentNode.id).parentNode.id;
					let rightParentID_3 = document.getElementById(document.getElementById(rightBoxID).parentNode.id).parentNode.id;

					console.log("leftParenID_3 :: ", leftParentID_3);
					console.log("rightParentID_3 :: ", rightParentID_3);


					if (String(leftParentID_3).startsWith("hiddenContainerDiv")) {
						toggleVisibilityForView(leftParentID_3, isFromViewWork);

					}
					if (String(rightParentID_3).startsWith("grandchildHiddenDiv") || String(rightParentID_3).startsWith("hiddenContainerDiv")) {
						console.log("rightParentID_3 toggle Visibility For View ");
						toggleVisibilityForView(rightParentID_3, isFromViewWork);
					}
					if (leftParentID_3 != "masterDiv") {
						console.log("leftParentID_2 :: ", document.getElementById(document.getElementById(leftParentID_3).parentNode.id));
						let leftParentID_2 = document.getElementById(document.getElementById(leftParentID_3).parentNode.id).parentNode.id


						if (leftParentID_2.startsWith("hiddenContainerDiv")) {
							toggleVisibilityForView(leftParentID_2, isFromViewWork);
						}
						if (leftParentID_2 != "masterDiv") {
							let leftParentID_1 = document.getElementById(document.getElementById(leftParentID_2).parentNode.id).parentNode.id

							if (leftParentID_1.startsWith("hiddenContainerDiv")) {
								toggleVisibilityForView(leftParentID_1, isFromViewWork);
							}
						}
					}
					if (rightParentID_3 != "masterDiv") {
						console.log("rightParentID_2 :: ", document.getElementById(document.getElementById(rightParentID_3).parentNode.id));
						let rightParentID_2 = document.getElementById(document.getElementById(rightParentID_3).parentNode.id).parentNode.id


						if (rightParentID_2.startsWith("hiddenContainerDiv")) {
							toggleVisibilityForView(rightParentID_2, isFromViewWork);

							let rightParentID_1 = document.getElementById(document.getElementById(rightParentID_2).parentNode.id).parentNode.id
							console.log("rightParentID_1 :: ", rightParentID_1);
							if (rightParentID_1.startsWith("hiddenContainerDiv")) {
								console.log("rightParentID_1 :: ", rightParentID_1);
								toggleVisibilityForView(rightParentID_1, isFromViewWork);
							}
						}
					}

					joinHandleMap.set(leftBoxID, rightBoxID);
					joinPathMap_1.set(new Map(Object.entries(mapFromPathList.get(key))).get("leftPathName"), Object.fromEntries(joinHandleMap));
					//createparentBoxDiv(joinPathMap);
					joinPathMap.set(new Map(Object.entries(mapFromPathList.get(key))).get("leftPathName"), joinHandleMap);
				}
				console.log("joinPathMap :: " + JSON.stringify(Object.fromEntries(joinPathMap_1)));

				reDrawPath(joinPathMap);

				for (const key of mapFromPathList.keys()) {
					workDone = new Map(Object.entries(mapFromPathList.get(key))).get("workDone");
					console.log("workDone :: ", workDone);
					if (workDone != null) {
						pathName = new Map(Object.entries(mapFromPathList.get(key))).get("leftPathName");
						let workDoneId = "wd_" + pathName;
						console.log("workDoneId  :: ", workDoneId);
						console.log("workDoneId  element :: ", document.getElementById(workDoneId).value);
						document.getElementById(workDoneId).value = workDone;
						document.getElementById(workDoneId).parentNode.style.display = "none";
						calculateAmount(document.getElementById(workDoneId));
					}

				}
			}
		};
		xhr.send(fdata);
	}

	if (pageMode == "viewupdate") {
		let fd = undefined;
		fetchMappedDataAndRedraw(fd);
	}
	if (pageMode == "create") {
		let fd = new FormData();
		fd.append('project_Code', $('#cpc').html());
		fd.append('project_CCN', $('#ccn').html());
		fd.append('packages', $('#pkgs').html());
		fd.append('work_area', $('#workarea').html());
		spinner.show();
		$.ajax({
			type: "POST",
			url: "/bhel/getBbuData",
			data: fd,
			processData: false,
			contentType: false,
			dataType: 'json',
			success: function(response) {
				console.log("response <===> :: " + JSON.stringify(response));

				createWorkPage(response);
				spinner.hide();
			}
		});
	}

	$('#savebutton').click(function() {
		spinner.show();
		console.log("pathMaps :: " + JSON.stringify(pathMapList));
		createData(pathMapList);

	});
	$('#updatebutton').click(function() {
		spinner.show();
		console.log("pathMaps :: " + JSON.stringify(pathMapList));
		createData(pathMapList);

	});
	function createData(pathMapList) {
		let saveFd = new FormData();
		console.log('project_Code ', $('#cpc').html());
		console.log('customer_CCN ', $('#ccn').html());
		console.log('packages ', $('#pkgs').html());
		console.log('work_area ', $('#workarea').html());

		saveFd.append('project_Code', $('#cpc').html());
		saveFd.append('customer_CCN', $('#ccn').html());
		saveFd.append('packages', $('#pkgs').html());
		saveFd.append('work_area', $('#workarea').html());
		saveFd.append('pathMapList', JSON.stringify(pathMapList));
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "/bhel/savework");
		xhr.onreadystatechange = function() {
			//{"status":true,"message":"Saved successfully.","data":null}

			//var responseVal = JSON.stringify(success);
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				spinner.hide();
				swal.fire({
					icon: 'success',
					title: 'Done',
					text: 'Your data has been saved successfully.',
					confirmButtonText: 'Ok',
				}).then((result) => {
					/* Read more about isConfirmed, isDenied below */
					if (result.isConfirmed) {

						if (pageMode == "create") {
							fetchMappedDataAndRedraw(saveFd);
						} else if (pageMode == "viewupdate") {
							window.location.reload();
						}
					}
				})

			}



		};
		xhr.send(saveFd);

	}
	$(".midRect input").click(function() {
		if ($(".midRect input").is(':visible')) {
			$(".midRect input").focus();
		}

	})

});

function newFunction() {
}
