package pckgzz.logic.pokupatel;

//     редактирование учетски пользователя

import dao.UsersEntity;
import org.hibernate.Session;
import pckgzz.utilz.HibernateSessionFactory;

import java.util.Scanner;

public class ChangeUserAccount {
    public static void changeUserAccount (UsersEntity u) {


        Scanner scan = new Scanner(System.in);

        //сбор новых данных о пользователе

        // id остается старым
        //адрес
        System.out.print("Введите новый почтовый адрес         ( пока на Английском :-) )      :  ");
        String adress = scan.nextLine();

        //phone      для упрощения пока без реакции на неправильный формат номера
        System.out.print("Введите новый номер телефона    ( пока меньше 10 символов :-) )      :  ");
        String phoneStr = scan.next();
        int phone=0;    // маловат тип для номера телефона нужно исправить, поэтому вываливает исключение при выдергивании пользователя из базы с номером больше чем int
        try {
            phone = Integer.valueOf(phoneStr);

        }catch (NumberFormatException e) {

            System.out.println("Неверный формат номера телефона!  ");
            e.printStackTrace();
        }


        // login    пока без проверки на уникальность
        System.out.print("Введите новый login                  ( пока на Английском :-) )      :  ");    // здесь нужно проверить логин на уникальность чтобы не было откза Oracle
        String login = scan.next();

        //password
        System.out.print("Введите новый password               ( пока на Английском :-) )      :  ");
        String password = scan.next();

        //электронка
        System.out.print("Введите новый адрес электронной почты                                :  ");
        String email = scan.next();







        // ******************    работа с базой данных   **************************************************
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();




        // заполняем поля пользователя
        u.setUserAdress(adress);
        u.setUserPhone(phone);     // маловат тип для номера телефона нужно исправить, поэтому вываливает исключение при выдергивании пользователя из базы с номером больше чем int
        u.setUserLogin(login);
        u.setUserPassword(password);
        u.setUserMail(email);




        // ******************    сохраняемся и закрываем сессию   **************************************************
        session.save(u);
        session.getTransaction().commit();
        session.close();


        System.out.println("Изменение данных покупателя прошло успешно ... ");

        System.exit(0);







    }
}
