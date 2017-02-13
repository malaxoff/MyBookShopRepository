package pckgzz.logic.pokupatel;

// Просмотр заказов покупателя


import dao.ContentOfOrderEntity;
import dao.OrdersEntity;
import dao.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pckgzz.utilz.HibernateSessionFactory;
import java.util.List;

public class ViewOrders {
    public static void viewOrders(UsersEntity u) {


        // ********** получаем id пользователя из объекта u, переданного в метод  *********************

        int id  = u.getUserId();
        System.out.println("id пользователя  : " + id);



        // ***********************  работа с базой данных ****************************
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();


        // Выводим все заказы по id пользователя  в список

        Criteria orderCriteria = session.createCriteria(OrdersEntity.class);
        List <OrdersEntity> order = orderCriteria.add(Restrictions.like("userId", id)).list();  // дописать упорядочивание


//*************** по полученному списку заказов ORDER для каждого заказа формируем список заказанных книг ***************


             Criteria contentCriteria = session.createCriteria(ContentOfOrderEntity.class);

        for (OrdersEntity ord: order) {


            List <ContentOfOrderEntity> content = contentCriteria.add(Restrictions.like("orderId", ord.getOrderId())).list();

            System.out.println (" Заказ № :" +  ord.getOrderId() + "  Дата : " + ord.getOrderDate() + "  Цена :  " + ord.getOrderPrice() + "  Статус :  " + ord.getIdStatus());

                               for (ContentOfOrderEntity cont: content) {

                                         System.out.println ("         id  книги  :" +  cont.getBookId() + "  количество : " + cont.getAmountOfEachBook());

                               }


        }



        // закрываем сессию
        session.getTransaction().commit();
        session.close();


        System.exit(0);
























    }
}
