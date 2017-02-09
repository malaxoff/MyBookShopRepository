package pckgzz.logic.prodavec;

// добавление карточки книги

import dao.BooksEntity;
import dao.PublisherEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pckgzz.utilz.HibernateSessionFactory;

import java.util.Scanner;

public class AddBook {
    public static void addBook () {


  //*************  Сбор данных о новой книге ************************************************

        Scanner scan = new Scanner(System.in);

        // id генериться автоматически  благодаря seq_book

        //авторы
        System.out.print("Введите авторов книги   ( пока на Английском :-) )      :  ");
        String autors = scan.nextLine();

        //название книги
        System.out.print("Введите название книги  ( пока на Английском :-) )      :  ");
        String title = scan.nextLine();

        //издательство
        // Добавить выбор издательства по имени, чтоб id заабивалось автоматом
        System.out.print("Введите id издательства                                 :  ");
        String idStr = scan.next();



        int id=0;    //  id издательства после "оцифровки"
        try {
            id = Integer.valueOf(idStr);

        }catch (NumberFormatException e) {

            System.out.println("Неверный формат id издательства   !  ");
            e.printStackTrace();
        }         // добавить возврат к повторному введению id





        // описание книги
        System.out.print("Введите описание книги   ( пока на Английском :-) )     :  ");
        String description = scan.next();


        //цена книги
        System.out.print("Введите цену книги                                      :  ");
        String priceStr = scan.next();


        int price=0;    //    цена книги  после "оцифровки"
        try {
            price = Integer.valueOf(priceStr);

        }catch (NumberFormatException e) {

            System.out.println("Неверный форматцены книги    !  ");
            e.printStackTrace();
        }         // добавить возврат к повторному введению цены





        //количество штук на складе
        System.out.print("Введите количество книг на складе                       :  ");
        String amountStr = scan.next();


        int amount=0;    //     количество книг   после "оцифровки"
        try {
            amount = Integer.valueOf(amountStr);

        }catch (NumberFormatException e) {

            System.out.println("Неверный формат количества книг    !  ");
            e.printStackTrace();
        }         // добавить возврат к повторному введению цены





     //****************************************************************************************************
        // работа с базой данных

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();



        PublisherEntity publisherEntity  = new PublisherEntity();     // создаем объект издательства

        Criteria publCriteria = session.createCriteria(PublisherEntity.class);
// далее выбираем строчку из таблицы BooksEntity со значением поля (столбца) idPublisher равным id
        publCriteria.add(Restrictions.eq("idPublisher", id));


        // если такого id издательства нет пишем : Нет такого пользователя
        if ( publCriteria.uniqueResult()== null)
        {
            System.out.println("Нет такого издательства! Попробуйте еще раз. ");
            System.exit(0);
            // Доработать. Отправить на повторную попытку  ввода издательства
        }

        else     // в противном случае считаем этого издателя
        {

            publisherEntity = (PublisherEntity) publCriteria.uniqueResult();

        }


        // создаем объект книги
        BooksEntity newBook = new   BooksEntity();


        // заполняем поля класса книга
        newBook.setAutors(autors);
        newBook.setBookTitle(title);
        newBook.setPublisherByIdPublisher(publisherEntity);     //   важно что добавляем id издательства по выдернутому из главной таблицы объекту по введенному id
        newBook.setBookDescription(description);
        newBook.setBookPrice(price);
        newBook.setBookAmount(amount);


          // сохраняемся и закрываем сессию
        session.save( newBook );
        session.getTransaction().commit();
        session.close();


        System.out.println("Новая книга зарегистрирована успешно ... ");
        System.exit(0);



    }
}
