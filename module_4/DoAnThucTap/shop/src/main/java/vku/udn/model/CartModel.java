package vku.udn.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CartModel extends abstractModel {
	private String user;
	private List<BookModel> listBooks = new ArrayList<>();
	private Double totalCash;
		
	public CartModel() {
		this.totalCash = 0.0;
	}

	public CartModel(String user, List<BookModel> listBooks, Double totalCash) {
		this.user = user;
		this.listBooks = listBooks;
		this.totalCash = totalCash;
	}

	public CartModel(Integer iD, String createBy, String modifiedBy, Timestamp createDate, Timestamp modifiedDate, String user, List<BookModel> listBooks, Double totalCash) {
		super(iD, createBy, modifiedBy, createDate, modifiedDate);
		this.user = user;
		this.listBooks = listBooks;
		this.totalCash = totalCash;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<BookModel> getListBooks() {
		return listBooks;
	}

	public void setListBooks(List<BookModel> listBooks) {
		this.listBooks = listBooks;
	}

	public Double getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(Double totalCash) {
		this.totalCash = totalCash;
	}

	public void totalCash() {
		this.totalCash = 0.0;
		this.listBooks.stream().forEach(book -> this.totalCash += book.getPrice()*book.getAmount());
	}
}
