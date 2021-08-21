package vku.udn.model;

import java.sql.Timestamp;

public class authorModel extends abstractModel {
	private String fullName;
	private String shortDescription;
	
	public authorModel() {
	}
	public authorModel(int iD, String createBy, String modifiedBy, Timestamp createDate, Timestamp modifiedDate) {
		super(iD, createBy, modifiedBy, createDate, modifiedDate);
	}
	public authorModel(String fullName, String shortDescription) {
		this.fullName = fullName;
		this.shortDescription = shortDescription;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
}
