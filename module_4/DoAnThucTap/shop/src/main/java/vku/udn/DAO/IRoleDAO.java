package vku.udn.DAO;

import vku.udn.model.roleModel;

import java.util.List;

public interface IRoleDAO extends GenericDAO<roleModel>{
	List<roleModel> findAll();
	roleModel findOne(Integer id);
}
