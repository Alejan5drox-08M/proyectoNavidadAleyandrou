package application.DAO;

import application.Conexion.Conexion;
import application.Model.Profesores;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.commons.codec.digest.DigestUtils;


public class InicioSesionDAO {
    private SessionFactory factory;
    private Session session;

    public InicioSesionDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    public Profesores buscarProfesor(String numero_asignado, String contrasenna) {
        Profesores profesor = null;
        String contrasena = DigestUtils.sha256Hex(contrasenna);
        try {
            session.beginTransaction();
           /* equipo = session.createQuery("from Equipo where idEquipo=:idEquipo", Equipo.class)
                    .setParameter("idEquipo", idEquipo)
                    .stream().findFirst().orElse(null);*/
            profesor = session.createQuery("from Profesores where numero_asignado:numero_asignado and contrasena:contrasena", Profesores.class)
                    .setParameter("numero_asignado", numero_asignado)
                    .setParameter("contrasena", contrasena)
                    .stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (
                Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return profesor;
    }

}
