package vku.udn.model;

import java.sql.Timestamp;

public class roleModel extends abstractModel{
	private String name;
	private String code;
		
	public roleModel() {
	}
	public roleModel(int iD, String createBy, String modifiedBy, Timestamp createDate, Timestamp modifiedDate) {
		super(iD, createBy, modifiedBy, createDate, modifiedDate);
	}
	public roleModel(String name, String code) {
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
