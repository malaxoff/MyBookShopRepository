package pckgzz.logic.pokupatel;

// Создание заказа

import dao.BooksEntity;
import dao.PublisherEntity;
import dao.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pckgzz.logic.prodavec.ViewBooks;
import pckgzz.utilz.HibernateSessionFactory;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CreateOrder {
    public static void createOrder(UsersEntity u) {



        Scanner scan = new Scanner(System.in);



        //*************  Сбор данных о новом заказе  ************************************************

        // id заказа генериться автоматически  благодаря seq_ord
        // id заказа генериться автоматически  благодаря seq_cont
        // id пользователя берем из объекта u

          int userId = u.getUserId();


        // генерим дату заказа
        java.util.Date order_date = new java.util.Date();
        //  id_status при создании заказа = 0   - ждет оплаты

        boolean SoglasieNaZakazKnigi = true;

         // добавление книг в цикле, потому что за ранее не знаем сколько их будет в заказе

        do {



                 //    вводим id книги
                 System.out.print("Введите id книги      :  ");
                 String idBookStr = scan.nextLine();


                 int idBook=0;    //  id издательства после "оцифровки"
                 try {
                        idBook = Integer.valueOf(idBookStr);

                 }catch (NumberFormatException e) {

                        System.out.println("Неверный формат id книги   !  ");
                        e.printStackTrace();
                 }         // добавить возврат к повторному введению id


                 // ***********  запускаем сессию *****************************************

                 Session session = HibernateSessionFactory.getSessionFactory().openSession();
                 session.beginTransaction();

                 // проверяем существование такого id в базе
                 // тут же выдергиваем остаток книг

                  Criteria bookIdCriteria = session.createCriteria(BooksEntity.class);
                  // далее выбираем строчку из таблицы BooksEntity со значением поля (столбца)  id
                  bookIdCriteria.add(Restrictions.eq("bookId", idBook));


                   // если такого id книги нет, пишем : Нет такой книги
                  if ( bookIdCriteria.uniqueResult()== null){
                          System.out.println("Нет такой книги! Попробуйте еще раз. ");
                          System.exit(0);
                    // Доработать. Отправить на повторную попытку  ввода издательства
                  }

                   // создаем объект книги
                   BooksEntity book = (BooksEntity) bookIdCriteria.uniqueResult();

                   // тут же выдергиваем остаток книг
                   int bookAmountOnBase = book.getBookAmount();

                   //проверяем остаток на адекватность
                   if (bookAmountOnBase == 0) {
                           System.out.println(" На складе не осталось таких книг   !  ");
                           System.exit(0);
                   }


                   // вводим количество книг, которое хотим приобрести
                   System.out.print("Введите количество книг     :  ");
                   String bookAmountStr = scan.nextLine();


                   int bookAmount=0;    //  id издательства после "оцифровки"
                   try {
                           bookAmount = Integer.valueOf(bookAmountStr);

                   }catch (NumberFormatException e) {
                           System.out.println("Неверный формат количества книг   !  ");
                           e.printStackTrace();
                   }         // добавить возврат к повторному введению id


                   // проверяем чтобы количество книг в заказе было не больше чем на остатках

                   if (bookAmount > bookAmountOnBase) {
                           System.out.println("На складе нет такого количества книг   ! Введите другое число ! ");
                           System.exit(0);
                   }

                   // реализовать что бы  заказанные книги уходили из остатка на 3 дня в резерв (ждали оплаты)
                   // а затем возвращались через 3 дня обратно, если оплата не поступили
                   // именно поэтому введен пункт меню у продавца "изменение статуса заказа".


                   // спрашиваем будут ли еще книги в заказе

                   char choice = 'n';    // по умолчанию выход
                   char ignore;

                   try{
                         do {

                                System.out.println("Добавить книгу в заказ ?    y - да,    n - Нет ");
                                choice = (char) System.in.read();

                                do {                                                  // исключаем служебн символы
                                       ignore = (char) System.in.read();
                                } while(ignore != '\n');

                         } while (choice != 'y' && choice > 'n') ;

                   } catch (Exception e) {
                         System.out.println(" Исключение в меню пользователя");
                         e.printStackTrace();
                   }



                    switch (choice){
                          case 'y':
                              SoglasieNaZakazKnigi = true;
                              break;
                          case 'n':
                              SoglasieNaZakazKnigi = false;
                              break;
                         default:
                              System.out.print("Что то пошло не так в меню (добавлять книгу или нет) ");
                              break;
                    }




        } while (SoglasieNaZakazKnigi);







        //****************************************************************************************************
        // работа с базой данных


      /*

        PublisherEntity publisherEntity  = new PublisherEntity();     // создаем объект издательства



        // создаем объект книги
        BooksEntity newBook = new   BooksEntity();


        // заполняем поля класса книга
        newBook.setAutors(autors);
        newBook.setBookTitle(title);
        newBook.setPublisherByIdPublisher(publisherEntity);     //   важно что добавляем id издательства по выдернутому из главной таблицы объекту по введенному id
        newBook.setBookDescription(descript);
        newBook.setBookPrice(price);
        newBook.setBookAmount(amount);


        // сохраняемся и закрываем сессию
        session.save( newBook );
        session.getTransaction().commit();
        session.close();


        System.out.println("Новая книга зарегистрирована успешно ... ");
        System.exit(0);



           */















    }
}
