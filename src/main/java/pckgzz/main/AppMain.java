package pckgzz.main;

import dao.PublisherEntity;
import org.hibernate.Session;
import pckgzz.utilz.HibernateSessionFactory;

/**
 * Created by Nick on 05.09.2015.
 */
public class AppMain {

    public static void main(String[] args) {
        try {
        System.out.println("Hibernate tutorial");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

            PublisherEntity publisherEntity = new PublisherEntity();


            publisherEntity.setIdPublisher(8);
            publisherEntity.setNameOfPublisher("Luja");

        session.save(publisherEntity);
        session.getTransaction().commit();

        session.close();
        } catch (Exception e){System.out.println("Исключение   ");}


    }
}
