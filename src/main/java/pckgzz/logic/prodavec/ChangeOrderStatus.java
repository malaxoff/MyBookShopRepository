package pckgzz.logic.prodavec;

// Изменение статуса заказа продавцом

import dao.OrdersEntity;
import dao.StatusOfOrderEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pckgzz.utilz.HibernateSessionFactory;
import java.util.Scanner;

public class ChangeOrderStatus {
    public static void changeOrderStatus(){


    // ********************    вводим id заказа        ************************************************

        Scanner scan = new Scanner(System.in);


        System.out.print("Введите id заказа        :  ");
        String idStr = scan.next();


        int id=0;    //  id издательства после "оцифровки"
        try {
            id = Integer.valueOf(idStr);

        }catch (NumberFormatException e) {

            System.out.println("Неверный формат id издательства   !  ");
            e.printStackTrace();
        }         // добавить возврат к повторному введению id



   // *********************** проверяем наличие такого заказа *********************


        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();


        Criteria orderCriteria = session.createCriteria(OrdersEntity.class);
// далее выбираем строчку из таблицы BooksEntity со значением поля (столбца) idPublisher равным id
        orderCriteria.add(Restrictions.eq("orderId", id));


        // если такого id заказа нет пишем : Нет такого заказа
        if ( orderCriteria.uniqueResult()== null)
        {
            System.out.println("Нет такого заказа! Попробуйте еще раз. ");
            System.exit(0);
            // Доработать. Отправить на повторную попытку  ввода id
        }

             // в противном случае считываем этот заказ


        OrdersEntity orderEntity = (OrdersEntity) orderCriteria.uniqueResult();


        // **************** вводим новый статус заказа ********************

        System.out.print("Введите новый статус заказа 0, 1, 2 или 3        :  ");
        String statusStr = scan.next();


        Integer status = 0;    //  id издательства после "оцифровки"
        try {
            status = Integer.valueOf(statusStr);

        }catch (NumberFormatException e) {

            System.out.println("Неверный формат id заказа   !  ");
            e.printStackTrace();
        }         // добавить возврат к повторному введению id



     //   ********************** проверяем наличие такого статуса  *********************


        Criteria statusOfOrderCriteria = session.createCriteria(StatusOfOrderEntity.class);
// далее выбираем строчку из таблицы StatusOfOrderEntity со значением поля (столбца) status равным status
        statusOfOrderCriteria.add(Restrictions.eq("idStatus", status));

        // если такого id заказа нет пишем : Нет такого заказа
        if ( statusOfOrderCriteria.uniqueResult()== null)
        {

            System.out.println("Нет такого статуса! Попробуйте еще раз. ");
            System.exit(0);
            // Доработать. Отправить на повторную попытку  ввода id
        }

        // в противном случае считаем этот заказ

        StatusOfOrderEntity statusOfOrderEntity = (StatusOfOrderEntity) statusOfOrderCriteria.uniqueResult();



     //  **********************   устанавливаем новый статус ************************

        orderEntity.setStatusOfOrderByIdStatus(statusOfOrderEntity);


    // ****************** сохраняемся и закрываем сессию работы с базой данных ***************


        session.save(orderEntity);
        session.getTransaction().commit();
        session.close();


        System.out.println("Изменение статуса заказа прошло успешно ... ");
        System.exit(0);
    }
}
