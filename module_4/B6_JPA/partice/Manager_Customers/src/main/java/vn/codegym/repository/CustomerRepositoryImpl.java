package vn.codegym.repository;

import org.springframework.stereotype.Repository;
import vn.codegym.model.Customer;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT s FROM Customer as s", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void remove(Customer customer) {
        entityManager.remove(entityManager.merge(customer));
    }


    @Override
    public List<Customer> searchByName(String kq) {
        TypedQuery<Customer> query =
                entityManager.createQuery("SELECT d FROM Customer as d where d.lastName like :keyword", Customer.class);
        query.setParameter("keyword", kq + "%");
        return query.getResultList();
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }
}
