package vku.udn.DAO.Impl;

import com.laptrinhjavaweb.DAO.ICommentDAO;
import com.laptrinhjavaweb.Mapper.CommentMapper;
import com.laptrinhjavaweb.model.CommentModel;

import java.util.List;

public class CommentDAOImpl extends AbstractDAO<CommentModel> implements ICommentDAO {
	
	@Override
	public CommentModel findOne(int id) {
		String sql = "SELECT * FROM `comment` WHERE id = ?";
		return query(sql, new CommentMapper(), id);
	}
	
	@Override
	public List<CommentModel> findAll() {
		String sql = "SELECT * FROM `comment`";
		return queryList(sql, new CommentMapper());
	}

	@Override
	public List<CommentModel> findByBookID(Integer bookID) {
		String sql = "SELECT * FROM `comment` WHERE bookID = ?";
		return queryList(sql, new CommentMapper(), bookID);
	}

	@Override
	public Integer addComment(CommentModel commentModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO `comment`(`userID`, `bookID`,");
		sql.append(" `contentComment`, `createDate`, `name`)");
		sql.append(" VALUES (?,?,?,?,?)");
		return save(sql.toString(), commentModel.getUserID(), commentModel.getBookID(), commentModel.getCommentContent().trim(),
									commentModel.getCreateDate(), commentModel.getName());
	}

	@Override
	public Integer updateComment(CommentModel commentModel) {
		StringBuilder sql = new StringBuilder("UPDATE `comment` SET `userID`=?,`bookID`=?,`contentComment`=?,");
		sql.append("`contentComment`=?,`createDate`=?,`name`=? WHERE id = ?");
		update(sql.toString(), commentModel.getUserID(), commentModel.getBookID(), commentModel.getCommentContent().trim(),
								commentModel.getCreateDate(), commentModel.getName(), commentModel.getID());
		return commentModel.getID();
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `comment` WHERE id = ?";
		update(sql, id);
		return id;
	}

	
}
