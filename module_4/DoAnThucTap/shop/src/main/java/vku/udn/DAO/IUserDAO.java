package vku.udn.DAO;

import vku.udn.model.UserModel;

import java.util.List;

public interface IUserDAO extends GenericDAO<UserModel>{
	UserModel findOneByUserNameAndPassWord(String userName, String passWord);
	UserModel finOne(Integer id);
	List<UserModel> findAll();
	Integer insert(UserModel userModel);
	Integer update(UserModel userModel);
	void delete(Integer id);
}
