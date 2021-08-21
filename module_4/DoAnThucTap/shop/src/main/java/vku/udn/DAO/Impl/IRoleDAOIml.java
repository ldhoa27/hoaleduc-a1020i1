package vku.udn.DAO.Impl;

import com.laptrinhjavaweb.DAO.IRoleDAO;
import com.laptrinhjavaweb.Mapper.RoleMapper;
import com.laptrinhjavaweb.model.roleModel;

import java.util.List;

public class IRoleDAOIml extends AbstractDAO<roleModel> implements IRoleDAO {

	@Override
	public List<roleModel> findAll() {
		String sql = "SELECT * FROM `roles`";
		return queryList(sql, new RoleMapper());
	}

	@Override
	public roleModel findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
