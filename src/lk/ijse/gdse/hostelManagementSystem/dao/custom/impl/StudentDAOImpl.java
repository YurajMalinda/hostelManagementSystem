package lk.ijse.gdse.hostelManagementSystem.dao.custom.impl;

import lk.ijse.gdse.hostelManagementSystem.dao.custom.StudentDAO;
import lk.ijse.gdse.hostelManagementSystem.entity.Student;
import lk.ijse.gdse.hostelManagementSystem.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<Student> allStudents;
        Query query = session.createQuery("FROM Student ");
        allStudents = (ArrayList<Student>) query.list();
        transaction.commit();
        session.close();
        return allStudents;
    }

    @Override
    public boolean delete(String id) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Student student = session.load(Student.class, id);
            session.delete(student);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean add(Student student) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(student);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(Student student) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(student);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public String getCurrentId() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // session.createQuery("FROM employee ORDER BY CAST(SUBSTRING(EmpID, 2) AS UNSIGNED) DESC LIMIT 1");
        try{
            String sql = "SELECT * FROM student ORDER BY CAST(SUBSTRING(id, 2) AS UNSIGNED) DESC LIMIT 1";
            NativeQuery sqlQuery = session.createSQLQuery(sql);

            sqlQuery.addEntity(Student.class);

            List <Student> studentList = sqlQuery.list();
            String id = null;
            for(Student student : studentList){
                id = student.getId();
            }
            transaction.commit();
            session.close();
            return id;

        }catch (Exception e) {
            transaction.commit();
            session.close();
            return null;
        }
    }
}
