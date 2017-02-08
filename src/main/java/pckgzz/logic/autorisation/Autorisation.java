package pckgzz.logic.autorisation;

import dao.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pckgzz.utilz.HibernateSessionFactory;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 06.02.17
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class Autorisation {
    public static void autorisation(){

        Scanner scan = new Scanner(System.in);


        System.out.print("Введите свой login    :" + "\n");
        String login = scan.next();


        System.out.print("Введите свой password    :" + "\n");
        String password = scan.next();




        // работа с базой данных
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // ищем пользователя

        Criteria userCriteria = session.createCriteria(UsersEntity.class);

        userCriteria.add(Restrictions.eq("userLogin", login ));      // выбираем строчку из таблицы со значением столбца равному login


            // если такого пользователя нет то пишем : Нет аткого пользователя
        if ( userCriteria.uniqueResult()== null)
               {System.out.println("Нет такого пользователя. Попробуйте еще раз. ");}    // Доработать. Отправить на повторную попытку

        else     // в противном случае проверяем пароль на соответствие
          {
              UsersEntity newUser = (UsersEntity) userCriteria.uniqueResult();


              if (    password.equals(newUser.getUserPassword())   )     {

                       System.out.println("Авторизация прошла успешно ");

              }

              else    {
                  System.out.println("Пароль не верный. Попробуйте еще раз.  ");  // Доработать. Отправить на повторную попытку
              }


          }

        session.close();










    }
}
