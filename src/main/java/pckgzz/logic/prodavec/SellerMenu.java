package pckgzz.logic.prodavec;

// меню ghjlfdwf


import dao.UsersEntity;



public class SellerMenu {

    public static void sellerMenu (UsersEntity u) {


        char choice = 6;    // по умолчанию выход
        char ignore;

        try{
            do {

                System.out.println("Пожалуйства выберете необходимый пункт меню :");
                System.out.println("1) Просмотр списка издательств");
                System.out.println("2) Добавить издательство");
                System.out.println("3) Просмотр списка книг");
                System.out.println("4) Добавить карточку книги");
                System.out.println("5) Изменение статуса заказа");
                System.out.println("6) Выход");


                choice = (char) System.in.read();

                do {                                                  // исключаем служебн символы
                    ignore = (char) System.in.read();
                } while(ignore != '\n');

            } while (choice < '1' || choice > '6') ;
        } catch (Exception e) {
            System.out.println(" Исключение в меню пользователя");
            e.printStackTrace();
        }



        switch (choice){

            case '1':
  //              System.out.println("Вы выбрали просмотр списка издательств");
                ViewPublishersByString.viewPublishersByString();
                break;
            case '2':
 //               System.out.println("Вы выбрали добавить издательство");
                AddPublisher.addPublisher();
                break;
            case '3':
 //               System.out.println("Вы выбрали просмотр списка книг");
                ViewBooks.viewBooks();
                break;
            case '4':
 //               System.out.println("Вы выбрали добавить карточку книги");
                AddBook.addBook();
                break;
            case '5':
 //               System.out.println("Вы выбрали изменение статуса заказа");
                ChangeOrderStatus.changeOrderStatus();
                break;
            case '6':
                System.out.println("Вы выбрали выход");
                System.exit(0);
                break;
            default:
                System.out.print("Что то пошло не так в меню пользователя ... ");
        }






    }


}
