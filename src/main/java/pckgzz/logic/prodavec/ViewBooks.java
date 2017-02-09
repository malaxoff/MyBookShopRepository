package pckgzz.logic.prodavec;

// просмотр списка книг

import dao.BooksEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
//  import org.hibernate.criterion.Order;         дял печати всей базы
import org.hibernate.criterion.Restrictions;
import pckgzz.utilz.HibernateSessionFactory;

import java.util.List;
import java.util.Scanner;

public class ViewBooks {
    public static void viewBooks () {




        Scanner scan = new Scanner(System.in);


        System.out.print("Введите начальные буквы названия книги с учетом регистра      :  ");
        String str = scan.nextLine();





        // работа с базой данных
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // Выводим все издательства в список
        Criteria bookCriteria = session.createCriteria(BooksEntity.class);
        List<BooksEntity> book = bookCriteria.add(Restrictions.like("bookTitle", str + "%")).list();



        //     Вывод всей базы
        // List<BooksEntity> book = bookCriteria.addOrder(Order.asc("bookId")).list();



        // закрываем сессию
        session.getTransaction().commit();
        session.close();

        //печатаем список
        System.out.println("id |  Название |    и пр.");
        for (BooksEntity bk: book) {
            System.out.println (" id :" +  bk.getBookId() + "  Авторы: " + bk.getAutors() + "  Название:  " + bk.getBookTitle() + "  Издательство:  " + bk.getPublisherByIdPublisher().getNameOfPublisher());

        }

        System.exit(0);




    }
}
