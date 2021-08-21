package vku.udn.DAO;

import vku.udn.model.categoryModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO<categoryModel>{
	List<categoryModel> findAll();
	categoryModel findOne(Integer id);
}
