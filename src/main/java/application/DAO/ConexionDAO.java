package application.DAO;

import application.Conexion.Conexion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class ConexionDAO {
    private SessionFactory factory;
    private Session session;

    public ConexionDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public Session getSession() {
        return session;
    }
}
