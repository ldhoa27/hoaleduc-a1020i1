package vku.udn.DAO.Impl;

import com.laptrinhjavaweb.DAO.GenericDAO;
import com.laptrinhjavaweb.Mapper.DBMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {

	@Override
	public Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/ptpm-huongdichvu";
			String url = "jdbc:mysql://localhost/ptpm-huongdichvu?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String userName = "root";
			String passWord = "";
			return DriverManager.getConnection(url, userName, passWord);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void closeConncettion(Connection conn, PreparedStatement ps, ResultSet rs) {

		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setParameters(PreparedStatement ps, Object... parametters) {
		for (int i = 0; i < parametters.length; i++) {
			Object param = parametters[i];
			int index = i + 1;
			try {
				if (param instanceof Double) {
					ps.setDouble(index, (Double)param);
				}else if(param == null) {
					ps.setNull(index, Types.NULL);
				}else if(param instanceof Long){
					ps.setLong(index, (Long) param);
				}else if(param instanceof Timestamp) {
					ps.setTimestamp(index,(Timestamp) param);
				}else if(param instanceof Integer) {
					ps.setInt(index,(Integer)param);
				}else {
					ps.setString(index, (String) param);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public T query(String sql, DBMapper<T> mapper, Object... parametters) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = openConnection();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			setParameters(ps, parametters);
			rs = ps.executeQuery();
			rs.next();
			T t = mapper.mapRow(rs);
			conn.commit();
			return t;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		} finally {
			closeConncettion(conn, ps, rs);
		}

	}
	
	public List<T> queryList(String sql, DBMapper<T> mapper, Object... parametters ){
		List<T> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = openConnection();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			setParameters(ps, parametters);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(mapper.mapRow(rs) != null) {
					result.add(mapper.mapRow(rs));
				}
			}
			return result;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}finally {
			closeConncettion(conn, ps, rs);
		}
	}
	
	public Integer save(String sql, Object... parametters) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = openConnection();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(ps, parametters);;
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			conn.commit();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}finally {
			closeConncettion(conn, ps, rs);
		}
	}
	
	public void update(String sql, Object... parametters ) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = openConnection();
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			setParameters(ps, parametters);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
 
}
