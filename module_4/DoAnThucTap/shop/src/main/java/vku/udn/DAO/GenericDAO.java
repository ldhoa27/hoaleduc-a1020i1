package vku.udn.DAO;

import vku.udn.Mapper.DBMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface GenericDAO<T> {
	Connection openConnection();
	void closeConncettion(Connection conn, PreparedStatement ps, ResultSet rs);
	void setParameters(PreparedStatement ps, Object... parametters);
	T query(String sql,DBMapper<T> mapper,Object... parametters);
	List<T> queryList(String sql,DBMapper<T> mapper,Object... parametters);
}
