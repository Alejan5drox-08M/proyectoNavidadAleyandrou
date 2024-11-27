package application.DAO;

import application.Conexion.Conexion;
import application.Model.Profesores;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class ProfesorDAO {
    private SessionFactory factory;
    private Session session;

    public ProfesorDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    public Profesores buscarProfesor(String numero_asignado, String contrasenna) {
        Profesores profesor = null;
        String contrasena = DigestUtils.sha256Hex(contrasenna);
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

    public void annadirProfesor(Profesores profesor) {
        profesor.setContrasena(DigestUtils.sha256Hex(profesor.getContrasena()));
        try {
            session.beginTransaction();
            session.save(profesor);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
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

}
