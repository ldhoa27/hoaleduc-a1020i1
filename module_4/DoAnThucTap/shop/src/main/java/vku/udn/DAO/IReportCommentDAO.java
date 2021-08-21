package vku.udn.DAO;

import vku.udn.model.ReportCommentModel;

import java.util.List;

public interface IReportCommentDAO {
	List<ReportCommentModel> findAll();
	ReportCommentModel findOne(int id);
	Integer addReport(ReportCommentModel reportComment);
	Integer updateReport(ReportCommentModel reportComment);
	Integer delete(int id);
}
