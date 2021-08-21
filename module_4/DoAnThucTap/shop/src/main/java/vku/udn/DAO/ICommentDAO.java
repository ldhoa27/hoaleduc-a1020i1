package vku.udn.DAO;

import vku.udn.model.CommentModel;

import java.util.List;

public interface ICommentDAO {
	CommentModel findOne(int id);
	List<CommentModel> findAll();
	List<CommentModel> findByBookID(Integer bookID);
	Integer addComment(CommentModel commentModel);
	Integer updateComment(CommentModel commentModel);
	int delete(int id);
}
