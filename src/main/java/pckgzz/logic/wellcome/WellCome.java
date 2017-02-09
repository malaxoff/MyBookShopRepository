package pckgzz.logic.wellcome;
import pckgzz.logic.autorisation.Autorisation;
import pckgzz.logic.registration.Registration;

// меню выбора Авторизация , Регистрация, Выход из порграммы.
public class WellCome {

    public static void wellCome() throws java.io.IOException{
        int buyerStatus = 0;




        char choice, ignore;

        do {

            System.out.println("Пожалуйства выберете необходимый пункт меню :");
            System.out.println("1) Авторизация ");
            System.out.println("2) Регистрация нового пользователя");
            System.out.println("3) Выход");

            choice = (char) System.in.read();

            do {                                                  // исключаем служебн символы
                ignore = (char) System.in.read();
            } while(ignore != '\n');

        } while (choice < '1' || choice > '3') ;



        switch (choice){
            case '1':
  //              System.out.println("Вы выбрали авторизацию");
                Autorisation.autorisation();
                break;
            case '2':
 //               System.out.println("Вы выбрали регистрацию нового пользователя");

                Registration.registration(buyerStatus);
                break;
            case '3':
                System.out.println("Вы выбрали выход");
                System.exit(0);
                break;
            default:
                System.out.print("Что то пошло не так в меню WellCome ...");
        }





    }
}
