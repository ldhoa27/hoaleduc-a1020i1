package vku.udn.DAO.Impl;

import com.laptrinhjavaweb.DAO.IBookDAO;
import com.laptrinhjavaweb.Mapper.BookMapper;
import com.laptrinhjavaweb.model.BookModel;

import java.util.List;

public class BookDAOImpl extends AbstractDAO<BookModel> implements IBookDAO{

	@Override
	public List<BookModel> findAll() {
		String sql = "select * from books";
		return queryList(sql, new BookMapper());
	}

	@Override
	public BookModel findOne(Integer id) {
		//String sql = "select * from books where id = ?";
		String sql = "SELECT * FROM `books` as b inner join `category` as c ON b.categoryid = c.id WHERE b.id = ?";
		return query(sql, new BookMapper(), id);
	}

	@Override
	public Integer addBook(BookModel bookModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO `books`(`categoryID`, `authorID`, `bookName`");
		sql.append(", `price`, `title`, `thumbnail`, `shortdescription`, `content`, `createBy`,");
		sql.append("`createDate`, `modifiedBy`, `modifiedDate`)");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
		return save(sql.toString(),bookModel.getCategoryID(), bookModel.getAuthorID(), bookModel.getBookName(),
				bookModel.getPrice(),bookModel.getTitle(),bookModel.getThumbnail(),bookModel.getShortdescription(),
				bookModel.getContent(), bookModel.getCreateBy(), bookModel.getCreateDate(), bookModel.getModifiedBy(),bookModel.getModifiedDate());
	}

	@Override
	public Integer updateBook(BookModel bookModel) {
		StringBuilder sql = new StringBuilder("UPDATE `books` SET `categoryID`=?,`authorID`=?,`bookName`=?,");
		sql.append("`price`=?,`title`=?,`thumbnail`=?,`shortdescription`=?,`content`=?,");
		sql.append("`modifiedBy`=?,`modifiedDate`=? WHERE id = ?");
		update(sql.toString(), bookModel.getCategoryID(), bookModel.getAuthorID(), bookModel.getBookName(),
				bookModel.getPrice(), bookModel.getTitle(), bookModel.getThumbnail(), bookModel.getShortdescription(),
				bookModel.getContent(), bookModel.getModifiedBy(), bookModel.getModifiedDate(), bookModel.getID());
		return bookModel.getID();
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `books` WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<BookModel> findByKey(String key) {
		String sql = "SELECT * FROM `books` WHERE bookname like '%"+ key +"%' or shortdescription like '%"+key+"%'";
		return queryList(sql, new BookMapper());
	}

	@Override
	public List<BookModel> findByCategoryID(Integer categoryID) {
		String sql = "SELECT * FROM `books` WHERE categoryID = ?";
		return queryList(sql, new BookMapper(), categoryID);
	}

}
