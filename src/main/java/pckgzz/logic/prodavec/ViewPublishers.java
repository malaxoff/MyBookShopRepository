package pckgzz.logic.prodavec;

//    Вываливает весь список издательств

import dao.PublisherEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import pckgzz.utilz.HibernateSessionFactory;
import java.util.ArrayList;
import java.util.List;

public class ViewPublishers {
    public static void viewPublishers() {






        // работа с базой данных
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // Выводим все издательства в список
        Criteria publCriteria = session.createCriteria(PublisherEntity.class);
        List <PublisherEntity> publisher = publCriteria.addOrder(Order.asc("idPublisher")).list();

         // закрываем сессию
        session.getTransaction().commit();
        session.close();

        //печатаем список
        System.out.println("id |  Название ");
         for (PublisherEntity pbl: publisher) {
             System.out.println (pbl.getIdPublisher() + " | " + pbl.getNameOfPublisher());

         }

        System.exit(0);




    }
}
