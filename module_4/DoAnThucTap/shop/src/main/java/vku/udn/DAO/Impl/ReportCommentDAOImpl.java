package vku.udn.DAO.Impl;

import com.laptrinhjavaweb.DAO.IReportCommentDAO;
import com.laptrinhjavaweb.Mapper.ReportCommentMapper;
import com.laptrinhjavaweb.model.ReportCommentModel;

import java.util.List;

public class ReportCommentDAOImpl extends AbstractDAO<ReportCommentModel> implements IReportCommentDAO{
	
	@Override
	public List<ReportCommentModel> findAll() {
//		String sql = "SELECT * FROM `reportcomment` as rp \r\n" + 
//				"	INNER JOIN `users` as u on rp.usersID = u.id\r\n" + 
//				"    INNER JOIN `comment` as c on rp.commentID = c.id\r\n" + 
//				"    INNER JOIN `books` as b on rp.bookID = b.id\r\n" + 
//				"ORDER BY rp.createDate desc;";
		
		StringBuilder sql = new StringBuilder("SELECT * FROM `reportcomment` as rp ");
		sql.append("INNER JOIN `users` as u on rp.usersID = u.id ");
		sql.append("INNER JOIN `comment` as c on rp.commentID = c.id ");
		sql.append("INNER JOIN `books` as b on rp.bookID = b.id ");
		sql.append("ORDER BY rp.createDate desc");
		return queryList(sql.toString(), new ReportCommentMapper());
	}
	
	@Override
	public ReportCommentModel findOne(int id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM `reportcomment` as rp inner join `comment`");
		sql.append(" as c on rp.CommentID = c.id inner join `users` as u on rp.usersID = u.id ");
		sql.append("inner join `books` as b on rp.bookID = b.id WHERE rp.id = ? ");
		return query(sql.toString(), new ReportCommentMapper(), id);
	}

	@Override
	public Integer addReport(ReportCommentModel reportComment) {
		StringBuilder sql = new StringBuilder("INSERT INTO `reportcomment`(`CommentID`, ");
		sql.append("`bookID`, `usersID`, `reportcontent`, `createDate`)");
		sql.append(" VALUES (?,?,?,?,?)");
		return save(sql.toString(), reportComment.getCommentID(), reportComment.getBookID(),
				reportComment.getUsersID(), reportComment.getContent(), reportComment.getCreateDate());
	}

	@Override
	public Integer updateReport(ReportCommentModel reportComment) {
		StringBuilder sql = new StringBuilder("UPDATE `reportcomment` SET `CommentID`=?,`bookID`=?,`usersID`=?,");
		sql.append("`reportcontent`=?,`createDate`=? WHERE id = ?");
		update(sql.toString(), reportComment.getCommentID(), reportComment.getBookID(), reportComment.getUsersID(),
				reportComment.getContent(), reportComment.getCreateDate(), reportComment.getID());
		return reportComment.getID();
	}

	@Override
	public Integer delete(int id) {
		String sql = "DELETE FROM `reportcomment` WHERE id = ?";
		update(sql, id);
		return id;
	}
	
}
