package vku.udn.DAO.Impl;

import com.laptrinhjavaweb.DAO.IOrderDAO;
import com.laptrinhjavaweb.Mapper.OrderMapper;
import com.laptrinhjavaweb.model.OrderModel;

import java.util.List;

public class OrderDAOImpl extends AbstractDAO<OrderModel> implements IOrderDAO{
	
	@Override
	public OrderModel findOne(Integer id) {
		String sql = "SELECT * FROM `orders` WHERE id = ?";
		return query(sql, new OrderMapper(), id);
	}

	
	@Override
	public Integer add(OrderModel orderModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO `orders`(`userID`, `totalCash`, `status`, `createDate`) VALUES (?,?,?,?);");
		return save(sql.toString(), orderModel.getUserId(), orderModel.getTotalCash(),
								orderModel.getStatus(), orderModel.getCreateDate());
	}


	@Override
	public Integer update(OrderModel orderModel) {
		StringBuilder sql = new StringBuilder("UPDATE `orders` SET `status`= ? WHERE id = ?;");
		update(sql.toString(), orderModel.getStatus(), orderModel.getID());
		return orderModel.getID();
	}


	@Override
	public List<OrderModel> findAllOrderByCreateDate() {
		StringBuilder sql = new StringBuilder("SELECT * FROM `orders` as od INNER JOIN `users` us on od.userID = us.id order BY od.createDate desc;");
		return queryList(sql.toString(), new OrderMapper());
	}


	@Override
	public List<OrderModel> findByStatus(String status) {
		StringBuilder sql = new StringBuilder("select * from `orders` as od inner join `users` as us on ");
		sql.append("od.userID = us.id where od.status = ?");
		return queryList(sql.toString(), new OrderMapper(), status);
	}


	@Override
	public List<OrderModel> findByUserID(int id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM `orders` WHERE userID = ? order by createDate desc");
		return queryList(sql.toString(), new OrderMapper(), id);
	}


	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `orders` WHERE id = ?";
		update(sql, id);
	}

}
