package com.bhel.project.entityImpl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ElementConnect {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sl_no;
	private String sourceId;
	private String sourceData;
	private String targetId;
	private String targetData;
	private String pathId;

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceData() {
		return sourceData;
	}

	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	
	public String getTargetData() {
		return targetData;
	}

	public void setTargetData(String targetData) {
		this.targetData = targetData;
	}

	public String getPathId() {
		return pathId;
	}

	public void setPathId(String pathId) {
		this.pathId = pathId;
	}

	public ElementConnect() {
	}

	public ElementConnect(int sl_no, String sourceId, String sourceData, String targetId, String targetData,
			String pathId) {
		super();
		this.sl_no = sl_no;
		this.sourceId = sourceId;
		this.sourceData = sourceData;
		this.targetId = targetId;
		this.targetData = targetData;
		this.pathId = pathId;
	}

}
