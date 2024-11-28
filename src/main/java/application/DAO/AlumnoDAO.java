package application.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AlumnoDAO extends ConexionDAO {
   // SessionFactory factory = getFactory();
    Session session = getSession();

    public void listarAlumnos() {

        session.beginTransaction();
    }
}
