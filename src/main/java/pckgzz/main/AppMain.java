package pckgzz.main;

import dao.PublisherEntity;
import org.hibernate.Session;
import pckgzz.utilz.HibernateSessionFactory;
import pckgzz.logic.wellcome.WellCome;

import java.util.Scanner;

/**
 * Created by Nick on 05.09.2015.
 */
public class AppMain {

    public static void main(String[] args) {
                /*
        try {
            WellCome.wellCome();
        } catch (Exception e){
            System.out.println("Исключение при регистрации  ");
            e.printStackTrace();
        }


                          */


        try {


        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();


            PublisherEntity publisherEntity = new PublisherEntity();


           //  id генериться автоматом.
            publisherEntity.setNameOfPublisher("1111");

        session.save(publisherEntity);
        session.getTransaction().commit();



        session.close();
        } catch (Exception e){
            System.out.println("Исключение по publisher   ");
            e.printStackTrace();
        }


    }
}
