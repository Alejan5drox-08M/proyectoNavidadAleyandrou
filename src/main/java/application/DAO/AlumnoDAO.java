package application.DAO;

import application.Model.Alumnos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AlumnoDAO extends ConexionDAO {
    // SessionFactory factory = getFactory();
    Session session = getSession();

    public void listarAlumnos() {
        try {
            session.beginTransaction();
            session.createQuery("from Alumnos", Alumnos.class);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }
}
