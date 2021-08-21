package vku.udn.DAO.Impl;

import com.laptrinhjavaweb.DAO.IUserDAO;
import com.laptrinhjavaweb.Mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;

import java.util.List;

public class UserDAOImpl extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findOneByUserNameAndPassWord(String userName, String passWord) {
		String sql = "select * from users as u inner join roles as r on u.roleID=r.ID where u.userName=? and u.passWord=?";
		return query(sql, new UserMapper(), userName, passWord);
	}

	@Override
	public UserModel finOne(Integer id) {
		String sql = "SELECT * FROM `users` WHERE id = ?";
		return query(sql, new UserMapper(), id);
	}

	@Override
	public Integer insert(UserModel userModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO `users`(`userName`, `passWord`, `fullName`, `roleID`,");
		sql.append(" `facebookID`, `createBy`, `createDate`, `modifiedBy`, `modifiedDate`)");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?)");
		return save(sql.toString(), userModel.getUserName(), userModel.getPassWord(), userModel.getFullName(), userModel.getRoleID(),
					userModel.getFacebookID(), userModel.getCreateBy(), userModel.getCreateDate(),
					userModel.getModifiedBy(), userModel.getModifiedDate());
	}

	@Override
	public Integer update(UserModel userModel) {
		StringBuilder sql = new StringBuilder("UPDATE `users` SET `userName`=?,`passWord`=?,");
		sql.append("`fullName`=?,`roleID`=?,`facebookID`=?,");
		sql.append("`modifiedBy`=?,`modifiedDate`=? WHERE id = ?");
		update(sql.toString(), userModel.getUserName(), userModel.getPassWord(), userModel.getFullName(),
						userModel.getRoleID(), userModel.getFacebookID(),userModel.getModifiedBy(),
						userModel.getModifiedDate(),userModel.getID());
		return userModel.getID();
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM `users` WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * FROM `users` AS u INNER JOIN `roles` AS r ON u.roleID = r.ID WHERE u.id != 1";
		return queryList(sql, new UserMapper());
	}
}
