package vn.codegym.repository;

import org.springframework.stereotype.Repository;
import vn.codegym.model.Blog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BlogRepositoryImpl implements BlogRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT s FROM Blog as s", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(int id) {
        return entityManager.find(Blog.class, id);
    }

    @Override
    public void save(Blog blog) {
        entityManager.persist(blog);
    }

    @Override
    public void remove(Blog blog) {
        entityManager.remove(entityManager.merge(blog));
    }

    @Override
    public List<Blog> searchByName(String kq) {
        TypedQuery<Blog> query =
                entityManager.createQuery("SELECT d FROM Blog as d where d.title like :keyword", Blog.class);
        query.setParameter("keyword", kq + "%");
        return query.getResultList();
    }

    @Override
    public void update(Blog blog) {
        entityManager.merge(blog);
    }
}
