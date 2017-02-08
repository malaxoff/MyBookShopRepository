package pckgzz.logic.registration;

import dao.UserStatusesEntity;
import dao.UsersEntity;
import org.hibernate.Session;
import pckgzz.logic.pokupatel.UserMenu;
import pckgzz.utilz.HibernateSessionFactory;
import java.util.Scanner;


// процедура регистрации. по факту регистрации передаем созданного пользователя в меню пользователя.

public class Registration {
    public static void registration(){



        Scanner scan = new Scanner(System.in);

     //сбор данных о пользователе

        // id генериться автоматически  благодаря seq_user

        //ФИО
        System.out.println("Введите Фамилию Имя Отчество :");
        String fio = scan.nextLine();

        //адрес
        System.out.println("Введите почтовый адрес :");
        String adress = scan.nextLine();

        //phone      для упрощения пока без реакции на неправильный формат номера
        System.out.println("Введите номер телефона :");
        String phoneStr = scan.next();
        int phone=0;
                   try {
                            phone = Integer.valueOf(phoneStr);

                   }catch (NumberFormatException e) {

                         System.out.println("Неверный формат номера телефона!  ");
                         e.printStackTrace();
                   }


         // login    пока без проверки на уникальность
        System.out.println("Введите login :");    // здесь нужно проверить логин на уникальность чтобы не было откза Oracle
        String login = scan.next();

        //password
        System.out.println("Введите password :");
        String password = scan.next();

        //электронка
        System.out.println("Введите адрес электронной почты :");
        String email = scan.next();

        // статус покупателя по умолчанию 0 - ноль, 1 - продавец, 2 - админ. Продавцов и админов добавляет админ.
        final Integer user_status = 0;





         // работа с базой данных
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        //Загружаем объект статуса пользователя из базы со статусом 0 - покупатель
        UserStatusesEntity userStatusesEntity = session.load(UserStatusesEntity.class, user_status);


        // создаем объект пользователя
        UsersEntity newUser = new  UsersEntity();


        // заполняем поля пользователя
        newUser.setUserFio(fio);
        newUser.setUserAdress(adress);
        newUser.setUserPhone(phone);
        newUser.setUserLogin(login);
        newUser.setUserPassword(password);
        newUser.setUserMail(email);
        newUser.setUserStatusesByUserStatusId( userStatusesEntity );     // важно что добавляем status пользователя по выдернутому из главной таблицы объекту


        session.save(newUser);
        session.getTransaction().commit();
        session.close();


        System.out.println("Регистрация прошла успешно. ");

        UserMenu.userMenu(newUser);




    }
}
