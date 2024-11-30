package application.DAO;

import application.Conexion.Conexion;
import application.Model.Profesores;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class ProfesorDAO extends ConexionDAO {
    private Session session = getSession();

    public Boolean buscarProfesor(Profesores profesorABuscar) {
        Profesores profesor = null;
        boolean encontrado = false;
        String contrasena = DigestUtils.sha256Hex(profesorABuscar.getContrasena());
        try {
            session.beginTransaction();
            profesor = session.createQuery("from Profesores where numero_asignado=:numero_asignado and contrasena=:contrasena", Profesores.class)
                    .setParameter("numero_asignado", profesorABuscar.getNumero_asignado())
                    .setParameter("contrasena", contrasena)
                    .stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        if (profesor != null) {
            encontrado = true;
        }
        return encontrado;
    }

    public Profesores comprobarProfesor(String numero_asignado, String password) {
        Profesores profesor = null;
        boolean encontrado = false;
        String contrasena = DigestUtils.sha256Hex(password);
        try {
            session.beginTransaction();
            profesor = session.createQuery("from Profesores where numero_asignado=:numero_asignado and contrasena=:contrasena", Profesores.class)
                    .setParameter("numero_asignado", numero_asignado)
                    .setParameter("contrasena", contrasena)
                    .stream().findFirst().orElse(null);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return profesor;
    }

    public Boolean annadirProfesor(Profesores profesor) {
        boolean annadido = false;
        profesor.setContrasena(DigestUtils.sha256Hex(profesor.getContrasena()));
        try {
            session.beginTransaction();
            session.save(profesor);
            session.getTransaction().commit();
            annadido = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return annadido;
    }

    public Set<Profesores> listarProfesores() {
        Set<Profesores> profesores = new HashSet<>();
        try {
            session.beginTransaction();
            profesores = session.createQuery("from Profesores", Profesores.class)
                    .stream().collect(Collectors.toSet());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return profesores;
    }

    public void eliminarProfesor(Profesores profesor) {
        try {
            session.beginTransaction();
            session.delete(profesor);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

    public void modificarProfesor(Profesores profesorNuevo, Profesores profesorViejo) {
        try {
            session.beginTransaction();
            profesorViejo.setNombre(profesorNuevo.getNombre());
            profesorViejo.setContrasena(DigestUtils.sha256Hex(profesorNuevo.getContrasena()));
            profesorViejo.setNumero_asignado(profesorNuevo.getNumero_asignado());
            profesorViejo.setTipo(profesorNuevo.getTipo());
            session.update(profesorViejo);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

}
