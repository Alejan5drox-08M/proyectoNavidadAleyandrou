package application.DAO;

import application.Conexion.Conexion;
import application.Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
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

    public Set<Partes_incidencia> filtarByAlumno(Alumnos alumno) {
        Set<Partes_incidencia> partesIncidencias = new HashSet<>();
        try {
            session.beginTransaction();
            partesIncidencias = session.createQuery("from Partes_incidencia where alumno_id:=alumno_id", Partes_incidencia.class)
                    .setParameter("alumno_id", alumno)
                    .stream().collect(Collectors.toSet());
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return partesIncidencias;
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

    public Set<Partes_incidencia> buscarPorFecha(LocalDate fechaEmpieza, LocalDate fechaAcaba) {
        List<Partes_incidencia> listaPartes = listarPartes().stream().toList();
        if (fechaEmpieza == null) {
            return listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isBefore(fechaAcaba))
                    .collect(Collectors.toSet());
        }
        if (fechaAcaba == null) {
            return listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isAfter(fechaEmpieza))
                    .collect(Collectors.toSet());
        }
        return listaPartes.stream()
                .filter(partesIncidencia ->
                        partesIncidencia.getFecha().isAfter(fechaEmpieza) &&
                                partesIncidencia.getFecha().isBefore(fechaAcaba))
                .collect(Collectors.toSet());
    }
//aqui consigo las 3 puntuaciones
    public Set<Puntuacion_partes> getPuntuaciones(){
        Set<Puntuacion_partes> puntuaciones = new HashSet<>();
        try {
            session.beginTransaction();
            puntuaciones = session.createQuery("from Puntuacion_partes", Puntuacion_partes.class)
                    .stream().collect(Collectors.toSet());
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return puntuaciones;
    }
}
