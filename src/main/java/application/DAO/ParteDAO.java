package application.DAO;

import application.Conexion.Conexion;
import application.Model.Alumnos;
import application.Model.Grupos;
import application.Model.Partes_incidencia;
import application.Model.Profesores;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class ParteDAO {

    private SessionFactory factory;
    private Session session;

    public ParteDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    public Alumnos buscarAlumnoByExp(int expediente) {
        Alumnos alumno = null;
        try {
            session.beginTransaction();
            alumno = session.createQuery("from Alumnos where numero_expediente:numero_expediente", Alumnos.class)
                    .setParameter("numero_expediente", expediente).stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return alumno;
    }

    public void insertarParte(Alumnos alumno, Profesores profesor, LocalDate fecha, String hora, String descripcion) {

    }

}
