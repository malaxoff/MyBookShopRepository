package pckgzz.logic.prodavec;

//    Вываливает список издательств

import dao.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pckgzz.utilz.HibernateSessionFactory;

public class ViewPublishers {
    public static void viewPublishers() {






        // работа с базой данных
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // ищем пользователя

        Criteria userCriteria = session.createCriteria(UsersEntity.class);

        userCriteria.add(Restrictions.eq("userLogin", login));

            newUser = (UsersEntity) userCriteria.uniqueResult();



    }
}
