package pckgzz.logic.registration;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 06.02.17
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class Registration {
    public static void registration(){

        Scanner scan = new Scanner(System.in);


        // id генериться автоматически

        System.out.println("Введите Фамилию Имя Отчество :");
        String fio = scan.nextLine();

        System.out.println("Введите почтовый адрес :");
        String adress = scan.nextLine();

        System.out.println("Введите номер телефона :");
        String phoneStr = scan.next();


                   try {
                            int phone = Integer.valueOf(phoneStr);

                   }catch (NumberFormatException e) {

                         System.out.println("Неверный формат номера телефона!  ");
                   }



        System.out.println("Введите login :");
        String login = scan.next();

        System.out.println("Введите password :");
        String password = scan.next();

        System.out.println("Введите адрес электронной почты :");
        String email = scan.next();


        final int user_status = 0;         // статус покупателя по умолчанию 0 - ноль







    }
}
