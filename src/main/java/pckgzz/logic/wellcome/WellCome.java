package pckgzz.logic.wellcome;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 06.02.17
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class WellCome {

    public static void wellCome() throws java.io.IOException{




        char choice, ignore;

        do {

            System.out.println("Пожалуйства выберете необходимый пункт меню :");
            System.out.println("1) Авторизация ");
            System.out.println("2) Регистрация нового пользователя");
            System.out.println("3) Выход");

            choice = (char) System.in.read();

            do {
                ignore = (char) System.in.read();
            } while(ignore != '\n');

        } while (choice < '1' || choice > '3') ;

        switch (choice){
            case '1':
                System.out.println("Вы выбрали авторизацию");
                break;
            case '2':
                System.out.println("Вы выбрали регистрацию нового пользователя");
                break;
            case '3':
                System.exit(0);
                break;
            default:
                System.out.print("Что то пошло не так ...");
        }





    }
}
