package application.Utils;

import application.Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static SessionFactory factory = null;

    static {
        Configuration cfg = new Configuration();
        cfg.configure(R.getCfg("hibernate.cfg.xml"));
        cfg.addAnnotatedClass(Alumnos.class);
        cfg.addAnnotatedClass(Grupos.class);
        cfg.addAnnotatedClass(Partes_incidencia.class);
        cfg.addAnnotatedClass(Profesores.class);
        cfg.addAnnotatedClass(Puntuacion_partes.class);
        factory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
        return factory.openSession();
    }

}
