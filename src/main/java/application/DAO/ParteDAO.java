package application.DAO;

import application.Model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParteDAO extends ConexionDAO {


    private final Session session = getSession();


    public Alumnos buscarAlumnoByExp(String expediente) {
        Alumnos alumno = null;
        try {
            session.beginTransaction();
            alumno = session.createQuery("from Alumnos where numero_expediente=:numero_expediente", Alumnos.class)
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
            partesIncidencias = session.createQuery("from Partes_incidencia where id_alum=:alumno_id", Partes_incidencia.class)
                    .setParameter("alumno_id", alumno)
                    .stream().toList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return partesIncidencias;
    }

    public Boolean insertarParte(Partes_incidencia parte) {
        boolean annadido = false;
        try {
            session.beginTransaction();
            session.save(parte);
            session.getTransaction().commit();
            annadido = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return annadido;
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

    public Partes_incidencia[] buscarPorFecha(LocalDate fechaEmpieza, LocalDate fechaAcaba) {
        List<Partes_incidencia> listaPartes = listarPartes();
        if (fechaEmpieza == null) {
            listaPartes = listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isBefore(fechaAcaba))
                    .toList();
        }
        if (fechaAcaba == null) {
            listaPartes = listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isAfter(fechaEmpieza))
                    .toList();
        }
        if (fechaAcaba != null && fechaEmpieza != null) {
            listaPartes = listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isAfter(fechaEmpieza) &&
                                    partesIncidencia.getFecha().isBefore(fechaAcaba))
                    .toList();

        }
        return listaPartes.toArray(new Partes_incidencia[0]);

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

    public Partes_incidencia[] buscarPartes() {
        List<Partes_incidencia> partesList = null;

        try {
            session.beginTransaction();
            partesList = session.createQuery("from Partes_incidencia", Partes_incidencia.class).list();
            session.beginTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();

        } finally {
            session.close();
        }

        return partesList != null ? partesList.toArray(new Partes_incidencia[0]) : new Partes_incidencia[0];
    }

    public Partes_incidencia[] buscarPartesPorId(Alumnos alumno) {
        List<Partes_incidencia> partesList = null;

        try {
            session.beginTransaction();
            partesList = session.createQuery("from Partes_incidencia where id_alum=:id_alum", Partes_incidencia.class)
                    .setParameter("id_alum", alumno).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return partesList != null ? partesList.toArray(new Partes_incidencia[0]) : new Partes_incidencia[0];
    }

    public void eliminarParte(Partes_incidencia parte) {
        try {
            session.beginTransaction();
            session.delete(parte);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

}
