package vku.udn.model;

import java.sql.Timestamp;

public class categoryModel extends abstractModel {
	private String name;
	private String code;
	
	public categoryModel() {
	}
	public categoryModel(int iD, String createBy, String modifiedBy, Timestamp createDate, Timestamp modifiedDate) {
		super(iD, createBy, modifiedBy, createDate, modifiedDate);
	}
	public categoryModel(String name, String code) {
		this.name = name;
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
