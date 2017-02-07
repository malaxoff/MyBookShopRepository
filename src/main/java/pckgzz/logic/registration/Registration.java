package pckgzz.logic.registration;

import dao.UsersEntity;
import org.hibernate.Session;
import pckgzz.utilz.HibernateSessionFactory;

import java.math.BigInteger;
import java.util.Scanner;


// процедура регистрации

public class Registration {
    public static void registration(){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Scanner scan = new Scanner(System.in);

        UsersEntity newUser = new  UsersEntity();     //создаем нового пользователя

        // id генериться автоматически  благодаря seq_user

        //ФИО
        System.out.println("Введите Фамилию Имя Отчество :");
        String fio = scan.nextLine();
        newUser.setUserFio(fio);

        //адрес
        System.out.println("Введите почтовый адрес :");
        String adress = scan.nextLine();
        newUser.setUserAdress(adress);

        //phone      для упрощения пока без реакции на неправильный формат номера
        System.out.println("Введите номер телефона :");
        String phoneStr = scan.next();
        int phone=0;
                   try {
                            phone = Integer.valueOf(phoneStr);

                   }catch (NumberFormatException e) {

                         System.out.println("Неверный формат номера телефона!  ");
                   }
        newUser.setUserPhone(phone);



         // login    пока без проверки на уникальность
        System.out.println("Введите login :");
        String login = scan.next();
        newUser.setUserLogin(login);

        //password
        System.out.println("Введите password :");
        String password = scan.next();
        newUser.setUserPassword(password);

        //электронка
        System.out.println("Введите адрес электронной почты :");
        String email = scan.next();
        newUser.setUserMail(email);

        // статус покупателя по умолчанию 0 - ноль, 1 - продавец, 2 - админ. Продавцов и админов добавляет админ.
       // final BigInteger user_status = 0;
        newUser.setUserStatusId(0);














        session.save(newUser);
        session.getTransaction().commit();
        session.close();



    }
}
