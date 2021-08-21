package vku.udn.model;

import java.sql.Timestamp;

public class BookModel extends abstractModel {
	private int categoryID;
	private int authorID;
	private String bookName;
	private Double price;
	private String title;
	private String thumbnail;
	private String shortdescription;
	private String content;
	private categoryModel categoryModel;
	
	public BookModel() {
	}
	public BookModel(int iD, String createBy, String modifiedBy, Timestamp createDate, Timestamp modifiedDate) {
		super(iD, createBy, modifiedBy, createDate, modifiedDate);
	}
	public BookModel(int categoryID, int authorID, String bookName, Double price, String title, String thumbnail,
			String shortdescription, String content) {
		this.categoryID = categoryID;
		this.authorID = authorID;
		this.bookName = bookName;
		this.price = price;
		this.title = title;
		this.thumbnail = thumbnail;
		this.shortdescription = shortdescription;
		this.content = content;
	}
	
	
	public categoryModel getCategoryModel() {
		return categoryModel;
	}
	public void setCategoryModel(categoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortdescription() {
		return shortdescription;
	}
	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
