package application.DAO;

import application.Model.Alumnos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO extends ConexionDAO {
    // SessionFactory factory = getFactory();
    Session session = getSession();

    public List<Alumnos> listarAlumnos() {
        List<Alumnos> alumnos = new ArrayList<>();
        try {
            session.beginTransaction();
            alumnos = session.createQuery("from Alumnos", Alumnos.class)
                    .stream().toList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return alumnos;
    }
}
