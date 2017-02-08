package pckgzz.logic.pokupatel;

// меню покупателя


import dao.UsersEntity;
import pckgzz.logic.autorisation.Autorisation;
import pckgzz.logic.registration.Registration;


public class UserMenu {

    public static void userMenu (UsersEntity u) {


        char choice = 5;    // по умолчанию выход
        char ignore;

        try{
          do {

            System.out.println("Пожалуйства выберете необходимый пункт меню :");
            System.out.println("1) Редактирование учетной записи ");
            System.out.println("2) Просмотр заказов");
            System.out.println("3) Просмотр книг");
            System.out.println("4) Создание заказа");
            System.out.println("5) Выход");


            choice = (char) System.in.read();

            do {                                                  // исключаем служебн символы
                ignore = (char) System.in.read();
            } while(ignore != '\n');

          } while (choice < '1' || choice > '5') ;
        } catch (Exception e) {
            System.out.println(" Исключение в меню пользователя");
            e.printStackTrace();
        }



     //   System.out.println("Вы выбрали редактирование учетной записи    " + u);




        switch (choice){
            case '1':
                System.out.println("Вы выбрали редактирование учетной записи");
               // Autorisation.autorisation();
                break;
            case '2':
                System.out.println("Вы выбрали просмотр заказов");
              //  Registration.registration();
                break;
            case '3':
                System.out.println("Вы выбрали просмотр книг");
             //   Registration.registration();
                break;
            case '4':
                System.out.println("Вы выбрали создание заказа");
              //  Registration.registration();
                break;
            case '5':
                System.out.println("Вы выбрали выход");
                System.exit(0);
                break;
            default:
                System.out.print("Что то пошло не так в меню пользователя ... ");
        }






    }


}
