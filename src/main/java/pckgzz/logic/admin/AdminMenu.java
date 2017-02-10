package pckgzz.logic.admin;

import dao.UsersEntity;

// меню админа
public class AdminMenu {
    public static void adminMenu (UsersEntity u){




        char choice = 2, ignore;


        try{

        do {

            System.out.println("Пожалуйства выберете необходимый пункт меню :");
            System.out.println("1) Регистрация нового пользователя ");
            System.out.println("2) Выход");

            choice = (char) System.in.read();

            do {                                                  // исключаем служебн символы
                ignore = (char) System.in.read();
            } while(ignore != '\n');

        } while (choice < '1' || choice > '2') ;

        } catch (Exception e) { System.out.println("Исключение в меню админа");}






        switch (choice){
            case '1':
                AddNewUsers.addNewUsers();
                break;
            case '2':
                System.out.println("Вы выбрали выход");
                System.exit(0);
                break;
            default:
                System.out.print("Что то пошло не так в меню админа ...");
        }














        AddNewUsers.addNewUsers();
    }

}
