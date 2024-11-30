package application.DAO;

import application.Conexion.Conexion;
import application.Model.Alumnos;
import application.Model.Grupos;
import application.Model.Partes_incidencia;
import application.Model.Profesores;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ParteDAO extends ConexionDAO {


    private final Session session = getSession();


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

    public Set<Partes_incidencia> listarPartes() {
        Set<Partes_incidencia> partesIncidencias = new HashSet<>();
        try {
            session.beginTransaction();
            partesIncidencias = session.createQuery("from Partes_incidencia", Partes_incidencia.class)
                    .stream().collect(Collectors.toSet());
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return partesIncidencias;
    }

    public Set<Partes_incidencia> buscarPorFecha(LocalDate fecha) {
        Set<Partes_incidencia> partesIncidencias = listarPartes();
        partesIncidencias.stream()
                .filter(partesIncidencia -> partesIncidencia.getFecha().equalsIgnoreCase(fecha.toString()));
        return partesIncidencias;
    }


}
