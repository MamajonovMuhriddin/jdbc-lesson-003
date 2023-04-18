package main.java;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<UserEntity> getUsers() {
        List<UserEntity> userList = new ArrayList<>();


        SessionFactory factory = SessionFactoryMaker.getFactory();

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query<UserEntity> query = session.createQuery("from UserEntity", UserEntity.class);
            userList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userList;
    }

    public List<UserEntity> saveUser(String n, String i, String st) {
        UserEntity b = new UserEntity();
        b.setName(n);
        b.setPassNum(n);
        b.getBalance();
        SessionFactory factory = SessionFactoryMaker.getFactory();

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(b);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return getUsers();
    }
}

