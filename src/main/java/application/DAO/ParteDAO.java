package application.DAO;

import application.Conexion.Conexion;
import application.Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public List<Partes_incidencia> filtarByAlumno(Alumnos alumno) {
        List<Partes_incidencia> partesIncidencias = new ArrayList<>();
        try {
            session.beginTransaction();
            partesIncidencias = session.createQuery("from Partes_incidencia where alumno_id:=alumno_id", Partes_incidencia.class)
                    .setParameter("alumno_id", alumno)
                    .stream().toList();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return partesIncidencias;
    }

    public void insertarParte(Alumnos alumno, Profesores profesor, LocalDate fecha, String hora, String descripcion) {

    }

    public List<Partes_incidencia> listarPartes() {
        List<Partes_incidencia> partesIncidencias = new ArrayList<>();
        try {
            session.beginTransaction();
            partesIncidencias = session.createQuery("from Partes_incidencia", Partes_incidencia.class)
                    .stream().toList();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return partesIncidencias;
    }

    public List<Partes_incidencia> buscarPorFecha(LocalDate fechaEmpieza, LocalDate fechaAcaba) {
        List<Partes_incidencia> listaPartes = listarPartes();
        if (fechaEmpieza == null) {
            return listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isBefore(fechaAcaba))
                    .toList();
        }
        if (fechaAcaba == null) {
            return listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isAfter(fechaEmpieza)).toList();
        }
        return listaPartes.stream()
                .filter(partesIncidencia ->
                        partesIncidencia.getFecha().isAfter(fechaEmpieza) &&
                                partesIncidencia.getFecha().isBefore(fechaAcaba))
                .toList();
    }

    //aqui consigo las 3 puntuaciones
    public List<Puntuacion_partes> getPuntuaciones() {
        List<Puntuacion_partes> puntuaciones = new ArrayList<>();
        try {
            session.beginTransaction();
            puntuaciones = session.createQuery("from Puntuacion_partes", Puntuacion_partes.class)
                    .stream().toList();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return puntuaciones;
    }
}
