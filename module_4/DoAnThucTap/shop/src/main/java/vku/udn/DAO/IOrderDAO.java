package vku.udn.DAO;

import vku.udn.model.OrderModel;

import java.util.List;

public interface IOrderDAO {
	OrderModel findOne(Integer id);
	List<OrderModel> findAllOrderByCreateDate();
	Integer add(OrderModel orderModel);
	Integer update(OrderModel orderModel);
	List<OrderModel> findByStatus(String status);
	List<OrderModel> findByUserID(int id);
	void delete(int id);
}
