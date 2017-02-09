package pckgzz.main;


import pckgzz.logic.wellcome.WellCome;


//   Начало прораммы.  WellCome.wellCome() запускает процесс авторизации или регистрации ...



public class AppMain {

    public static void main(String[] args) {

        try {
            // при запуске программы вываливаемся в меню где предложено авторизоваться или зарегистрироваться
            WellCome.wellCome();

        } catch (Exception e){

            System.out.println("Исключение при регистрации  ");

            e.printStackTrace();
        }


    }
}
