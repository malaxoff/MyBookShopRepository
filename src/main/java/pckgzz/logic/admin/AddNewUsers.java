package pckgzz.logic.admin;

//   добавление новых пользователей. отличается от регистрации нового покупателя только тем
//   что статус пользователя не зафиксирован (  = 0), а возможно его выбрать (0, 1 или 2).
//   А так же производится его проверка ...

import pckgzz.logic.registration.Registration;
import java.util.Scanner;

public class AddNewUsers {
    public static void addNewUsers(){


        Scanner scan = new Scanner(System.in);

        System.out.print("Введите статус новго пользователя ( 0 - покупатель, - 1 продавец, 2 - админ ) :  ");
        String buyerStatusStr = scan.next();

        int buyerStatus=0;    //
        try {
            buyerStatus = Integer.valueOf(buyerStatusStr);

        }catch (NumberFormatException e) {

            System.out.println("Неверный формат статуса пользователя  ");
            e.printStackTrace();
        }


        if ( buyerStatus!= 0 && buyerStatus!= 1 && buyerStatus!= 2)
               {
                   System.out.println("Нет такого статуса ....   ");
                   System.exit(0);

               }
        else   Registration.registration(buyerStatus);




    }
}
