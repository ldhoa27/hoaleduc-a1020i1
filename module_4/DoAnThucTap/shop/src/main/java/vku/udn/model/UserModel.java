package vn.vku.model;

import java.sql.Timestamp;

public class UserModel extends abstractModel{
	private String userName;
	private String passWord;
	private Long facebookID = 0L;
	private int roleID;
	private String fullName= "";
	private roleModel role;

	public UserModel() {
	}
	public UserModel(int iD, String createBy, String modifiedBy, Timestamp createDate, Timestamp modifiedDate) {
		super(iD, createBy, modifiedBy, createDate, modifiedDate);
	}
	public UserModel(String userName, String passWord, Long facebookID, int roleID, String fullName, roleModel role) {
		this.userName = userName;
		this.passWord = passWord;
		this.facebookID = facebookID;
		this.roleID = roleID;
		this.fullName = fullName;
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Long getFacebookID() {
		return facebookID;
	}
	public void setFacebookID(Long facebookID) {
		this.facebookID = facebookID;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public roleModel getRole() {
		return role;
	}
	public void setRole(roleModel role) {
		this.role = role;
	}
}
