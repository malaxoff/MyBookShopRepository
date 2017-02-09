package pckgzz.logic.prodavec;

// Добавление издательства продавцом

import dao.PublisherEntity;
import org.hibernate.Session;
import pckgzz.utilz.HibernateSessionFactory;

import java.util.Scanner;

public class AddPublisher {
    public static void addPublisher () {

        Scanner scan = new Scanner(System.in);

        //сбор данных об издательстве

        // id генериться автоматически  благодаря seq_publ

        System.out.print("Введите название издательства   ( пока на Английском :-) )      :  ");
        String publisher = scan.nextLine();


             // работа с базой
        try {

                Session session = HibernateSessionFactory.getSessionFactory().openSession();
                session.beginTransaction();


                PublisherEntity publisherEntity = new PublisherEntity();


                   //  id генериться автоматом.
                publisherEntity.setNameOfPublisher(publisher);


                session.save(publisherEntity);
                session.getTransaction().commit();
                session.close();




        } catch (Exception e){
            System.out.println("Исключение по publisher   ");
            e.printStackTrace();
        }


        System.out.println("  Издательство добавлено успешно  ... ");

         // доработать. добавить повторное добавленеи издательства, возврат к меню продавца и выход.
        System.exit(0);


    }
}
