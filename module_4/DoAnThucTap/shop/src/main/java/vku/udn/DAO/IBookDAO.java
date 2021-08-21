package vku.udn.DAO;

import vku.udn.model.BookModel;

import java.util.List;

public interface IBookDAO extends GenericDAO<BookModel>{
	List<BookModel> findAll();
	BookModel findOne(Integer id);
	Integer addBook(BookModel bookModel);
	Integer updateBook(BookModel bookModel);
	void delete(int id);
	List<BookModel> findByKey(String key);
	List<BookModel> findByCategoryID(Integer categoryID);
}
