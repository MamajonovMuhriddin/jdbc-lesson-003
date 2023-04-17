package main.java;

import org.hibernate.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PetService {
    public List<PetEntity> getPets() {
        List<PetEntity> petList = new ArrayList<>();


        SessionFactory factory = SessionFactoryMaker.getFactory();

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query<PetEntity> query = session.createQuery("from PetEntity", PetEntity.class);
            petList = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return petList;
    }

    public List<PetEntity> savePet(String n, String t) {
        PetEntity b = new PetEntity();
        b.setNickname(n);
        b.setType(t);
        SessionFactory factory = SessionFactoryMaker.getFactory();

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(b);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return getPets();
    }

}