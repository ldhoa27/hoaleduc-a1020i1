package vku.udn.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class abstractModel<T> {
	private Integer ID;
	private String createBy;
	private String modifiedBy;
	private Timestamp createDate;
	private Timestamp modifiedDate;
	private Integer amount;
	private List<T> listResult = new ArrayList<>();
	private String type;
	private int[] ids;

	public abstractModel() {
	}
	public abstractModel(Integer iD, String createBy, String modifiedBy, Timestamp createDate, Timestamp modifiedDate) {
		ID = iD;
		this.createBy = createBy;
		this.modifiedBy = modifiedBy;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}
	
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<T> getListResult() {
		return listResult;
	}
	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}	
}
