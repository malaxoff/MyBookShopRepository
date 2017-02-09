package pckgzz.logic.autorisation;

import dao.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pckgzz.logic.admin.AdminMenu;
import pckgzz.logic.pokupatel.UserMenu;
import pckgzz.logic.prodavec.SellerMenu;
import pckgzz.utilz.HibernateSessionFactory;

import java.util.Scanner;

// меню авторизации пользователя
// маловат тип для номера телефона нужно исправить, поэтому вываливает исключение при выдергивании пользователя из базы с номером больше чем int

public class Autorisation {
    public static void autorisation(){

        int userstatus = 4;    // статус пользователя выдернутый из базы после авторизации.  По умолчанию не существующий, что бы поймать ошибку

        Scanner scan = new Scanner(System.in);

        UsersEntity newUser = new UsersEntity();


        System.out.print("Введите свой login       :   ");
        String login = scan.next();


        System.out.print("Введите свой password    :   ");
        String password = scan.next();




        // работа с базой данных
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // ищем пользователя

        Criteria userCriteria = session.createCriteria(UsersEntity.class);

        userCriteria.add(Restrictions.eq("userLogin", login ));      // выбираем строчку из таблицы со значением столбца равному login


            // если такого пользователя нет то пишем : Нет аткого пользователя
        if ( userCriteria.uniqueResult()== null)
               {
                   System.out.println("Нет такого пользователя! Попробуйте еще раз. ");
                   System.exit(0);

               }    // Доработать. Отправить на повторную попытку

        else     // в противном случае проверяем пароль на соответствие
          {

              newUser = (UsersEntity) userCriteria.uniqueResult();

              if (    password.equals(newUser.getUserPassword())   )     {

                       System.out.println("Авторизация прошла успешно ... ");
                       userstatus = newUser.getUserStatusId();      // не плохо бы здесь проверять статус пользователя на адекватность

              }

              else    {
                  System.out.println("Пароль не верный! Попробуйте еще раз.  ");       // Доработать. Отправить на повторную попытку
                  System.exit(0);
              }


          }


        session.close();     // закрытие сессии, база нам больше не нужна

        // проверим статус пользователя и в зависимости от него передадим объект пользователя соответствующему методу
        // так же можно передать в в метод id пользователя

        if (userstatus == 0)    {  UserMenu.userMenu(newUser); }
        if (userstatus == 1)    {  SellerMenu.sellerMenu(newUser); }
        if (userstatus == 2)    {  AdminMenu.adminMenu(newUser); }











    }
}
