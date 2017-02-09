package pckgzz.logic.registration;

import dao.UserStatusesEntity;
import dao.UsersEntity;
import org.hibernate.Session;
import pckgzz.logic.pokupatel.UserMenu;
import pckgzz.utilz.HibernateSessionFactory;
import java.util.Scanner;


// процедура регистрации. по факту регистрации передаем созданного пользователя в меню пользователя.

public class Registration {
    public static void registration(int user_status){



        Scanner scan = new Scanner(System.in);

     //сбор данных о пользователе

        // id генериться автоматически  благодаря seq_user

        //ФИО
        System.out.print("Введите Фамилию Имя Отчество   ( пока на Английском :-) )      :  ");
        String fio = scan.nextLine();

        //адрес
        System.out.print("Введите почтовый адрес         ( пока на Английском :-) )      :  ");
        String adress = scan.nextLine();

        //phone      для упрощения пока без реакции на неправильный формат номера
        System.out.print("Введите номер телефона    ( пока меньше 10 символов :-) )      :  ");
        String phoneStr = scan.next();
        int phone=0;    // маловат тип для номера телефона нужно исправить, поэтому вываливает исключение при выдергивании пользователя из базы с номером больше чем int
                   try {
                            phone = Integer.valueOf(phoneStr);

                   }catch (NumberFormatException e) {

                         System.out.println("Неверный формат номера телефона!  ");
                         e.printStackTrace();
                   }


         // login    пока без проверки на уникальность
        System.out.print("Введите login                  ( пока на Английском :-) )      :  ");    // здесь нужно проверить логин на уникальность чтобы не было откза Oracle
        String login = scan.next();

        //password
        System.out.print("Введите password               ( пока на Английском :-) )      :  ");
        String password = scan.next();

        //электронка
        System.out.print("Введите адрес электронной почты                                :  ");
        String email = scan.next();

        // статус покупателя по умолчанию 0 - ноль, 1 - продавец, 2 - админ. Продавцов и админов добавляет админ.
       // final Integer user_status = 0;





         // работа с базой данных
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        //Загружаем объект статуса пользователя из базы со статусом 0 - покупатель
        // Хоро шо бы пере этой процедурой добавить проверку существования статуса пользователя в базе
        UserStatusesEntity userStatusesEntity = session.load(UserStatusesEntity.class, user_status);


        // создаем объект пользователя
        UsersEntity newUser = new  UsersEntity();


        // заполняем поля пользователя
        newUser.setUserFio(fio);
        newUser.setUserAdress(adress);
        newUser.setUserPhone(phone);     // маловат тип для номера телефона нужно исправить, поэтому вываливает исключение при выдергивании пользователя из базы с номером больше чем int
        newUser.setUserLogin(login);
        newUser.setUserPassword(password);
        newUser.setUserMail(email);
        newUser.setUserStatusesByUserStatusId( userStatusesEntity );     // важно что добавляем status пользователя по выдернутому из главной таблицы объекту


        session.save(newUser);
        session.getTransaction().commit();
        session.close();


        System.out.println("Регистрация нового покупателя прошла успешно ... ");

        UserMenu.userMenu(newUser);




    }
}
