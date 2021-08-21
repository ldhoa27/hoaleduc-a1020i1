package com.teamvietdev.dao;

import com.teamvietdev.model.Member;
import com.teamvietdev.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TVD
 */
@Repository
public class MemberDAOImpl implements MemberDAO {

    @Override
    public Member login(String uname, String passwd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM member WHERE memberLogin = :uname AND memberPass = :passwd");
            query.setString("uname", uname);
            query.setString("passwd", passwd);
            Member obj = (Member) query.uniqueResult();
            transaction.commit();
            return obj;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }

}
