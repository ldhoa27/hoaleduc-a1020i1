package vn.codegym.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import vn.codegym.model.Student;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
//    private static List<Student> studentList;
//
//    static {
//        studentList = new ArrayList<>();
//        studentList.add(new Student(1, "Nguyen Van A", 1, new String[]{"PHP", "JAVA"}));
//        studentList.add(new Student(2, "Nguyen Van B", 0, new String[]{"C#", "JAVA"}));
//        studentList.add(new Student(3, "Nguyen Van C", 2, new String[]{"C#", "PHP", "JAVA"}));
//    }

    @Override
    public List<Student> findAll() {
        Session session = null;
        List studentList = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            studentList = session.createQuery("FROM Student").getResultList();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return studentList;
    }


    @Override
    public Student finById(int id) {

        TypedQuery<Student> query = ConnectionUtil.entityManager.createQuery("SELECT c FROM Student AS c WHERE c.id = :id", Student.class);
        query.setParameter("id", id);
        return query.getSingleResult();


    }

    @Override
    public void save(Student student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(student);

            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public void deleteStudent(int id) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }


    @Override
    public Student updateStudent(Student student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = ConnectionUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student student1 = finById(student.getId());
            student1.setName(student.getName());
            student1.setGender(student.getGender());
            student1.setLanguage(student.getLanguage());
            session.update(student1);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return student;
    }
}
