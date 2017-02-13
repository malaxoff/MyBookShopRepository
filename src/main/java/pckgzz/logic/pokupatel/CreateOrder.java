package pckgzz.logic.pokupatel;

// Создание заказа

import dao.*;
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

        // генерим дату заказа
        java.util.Date order_date = new java.util.Date();

        // стоимость заказа. нужно было бы поставить этот атрибут в таблицу "список заказа", поэтому на
        // этапе создания заказа это поле будет равно 1. (т.к. не может быть равно нулю). В процессе добавления книг общитаю стоимость заказа
        // с четом количества книг и по окончанию добавления книг в заказ запишу результат в таблицу.
        int orderPrice = 1;


        //  order_status при создании заказа = 0   - заказан и ждет оплаты
        int orderStatus = 0;



        // ***********  запускаем сессию *****************************************

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();


        // ****************** получаем объект статуса заказа по orderStatus = 0 ************




        StatusOfOrderEntity statusOfOrder  = new  StatusOfOrderEntity();     // создаем объект статуса заказа

        Criteria statusCriteria = session.createCriteria(StatusOfOrderEntity.class);
// далее выбираем строчку из таблицы StatusOfOrderEntity со значением поля (столбца) id_status равным  int orderStatus = 0;
        statusCriteria.add(Restrictions.eq("idStatus", orderStatus));


        // если такого статуса нет пишем : Нет такого статуса
        if ( statusCriteria.uniqueResult()== null)
        {
            System.out.println("Нет такого статуса! Попробуйте еще раз. ");
            System.exit(0);
            // Доработать. Отправить на повторную попытку  ввода издательства
        }

        else     // в противном случае получаем наш объект
        {

            statusOfOrder = (StatusOfOrderEntity) statusCriteria.uniqueResult();

        }



     // ***************   добавляем сам заказ (без списка книг) *****************************


        // создаем объект заказа
        OrdersEntity newOrder = new   OrdersEntity();


        // заполняем поля класса заказ
        newOrder.setUsersByUserId(u);
        newOrder.setOrderDate(order_date);
        newOrder.setOrderPrice(orderPrice);    // не рассчитал, нужно было запихнуть это поле в список заказа , пока ноль
        newOrder.setStatusOfOrderByIdStatus(statusOfOrder);



        session.save( newOrder );
        session.getTransaction().commit();


// ***************** добавление списка  книг в заказ ***********************************************



        boolean SoglasieNaZakazKnigi = true;
        orderPrice = 0;    // обнуляем стоимость заказа

         // добавление книг в цикле, потому что за ранее не знаем сколько их будет в заказе



        do {



            // ***********  запускаем сессию *****************************************

            session.beginTransaction();


                 // id списка заказа генериться автоматически  благодаря seq_cont

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

                   //  выдергиваем остаток книг
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

                   // необходимо реализовать что бы  заказанные книги уходили из остатка на 3 дня в резерв (ждали оплаты)
                   // а затем возвращались через 3 дня обратно на остатки, если оплата не поступила.
                   // именно поэтому введен пункт меню у продавца "изменение статуса заказа".


                  //  сохранение книги в таблицу *************************************************************************
                   // создаем объект "список"
                   ContentOfOrderEntity content  = new ContentOfOrderEntity();

                   // id генериться автоматом
                   content.setOrdersByOrderId(newOrder);
                   content.setBooksByBookId(book);
                   content.setAmountOfEachBook(bookAmount);


                   //  ***** рассчитываем и сохраняем стоимость заказа ***************************************************

                   orderPrice = orderPrice + (bookAmount * book.getBookPrice());

                   session.save( content );
                   session.getTransaction().commit();




                   //  ********   спрашиваем будут ли еще книги в заказе  *******************************************

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




        session.beginTransaction();

        newOrder.setOrderPrice(orderPrice);    // не рассчитал, нужно было запихнуть это поле в список заказа

        session.save( newOrder );      // сохраняем новую цену заказа

        session.getTransaction().commit();
        session.close();


        System.out.println("Новый заказ зарегистрирован успешно ... ");
        System.exit(0);



















    }
}
