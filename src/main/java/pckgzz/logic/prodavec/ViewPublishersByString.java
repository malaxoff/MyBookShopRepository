package pckgzz.logic.prodavec;

// вываливает список издательств по первым буквам названия

import dao.PublisherEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
// import org.hibernate.criterion.Order;        для вывода всей базы
import org.hibernate.criterion.Restrictions;
import pckgzz.utilz.HibernateSessionFactory;
import java.util.List;
import java.util.Scanner;

public class ViewPublishersByString {
    public static void viewPublishersByString(){



        Scanner scan = new Scanner(System.in);


        System.out.print("Введите начальные буквы названия издательства      :  ");
        String str = scan.nextLine();



        // работа с базой данных
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();



        // Выводим все издательства в список
        Criteria publCriteria = session.createCriteria(PublisherEntity.class);
        List<PublisherEntity> publisher = publCriteria.add(Restrictions.like("nameOfPublisher", str+"%")).list();



  //     Вывод всей базы
  //      List <PublisherEntity> publisher = publCriteria.addOrder(Order.asc("idPublisher")).list();


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
