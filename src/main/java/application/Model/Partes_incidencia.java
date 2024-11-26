package application.Model;

/*
DROP TABLE IF EXISTS `gestionpartes`.`partes_incidencia`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`partes_incidencia` (
  `id_alum` INT NULL DEFAULT NULL,
  `id_grupo` INT NULL DEFAULT NULL,
  `id_parte` INT NOT NULL AUTO_INCREMENT,
  `id_profesor` INT NULL DEFAULT NULL,
  `id_punt_partes` INT NULL DEFAULT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `fecha` VARCHAR(255) NULL DEFAULT NULL,
  `hora` VARCHAR(255) NULL DEFAULT NULL,
  `sancion` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_parte`),
  INDEX `FKqrx661g5lij25bl2plx6cb2pl` (`id_alum` ASC),
  INDEX `FKckq2ajm1w9wbi3kunm8q3ldp3` (`id_grupo` ASC),
  INDEX `FKniytl2x2lvm632ic1904a1bhb` (`id_profesor` ASC),
  INDEX `FK75ruupml2w0bpugnsojl56g05` (`id_punt_partes` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FK75ruupml2w0bpugnsojl56g05`
  FOREIGN KEY (`id_punt_partes`)
  REFERENCES `gestionpartes`.`puntuacion_partes` (`id_punt_partes`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKckq2ajm1w9wbi3kunm8q3ldp3`
  FOREIGN KEY (`id_grupo`)
  REFERENCES `gestionpartes`.`grupos` (`id_grupo`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKniytl2x2lvm632ic1904a1bhb`
  FOREIGN KEY (`id_profesor`)
  REFERENCES `gestionpartes`.`profesores` (`id_profesor`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKqrx661g5lij25bl2plx6cb2pl`
  FOREIGN KEY (`id_alum`)
  REFERENCES `gestionpartes`.`alumnos` (`id_alum`);
 */

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "partes_incidencia")
public class Partes_incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parte")
    private int id_parte;

    @ManyToOne
    @JoinColumn(name = "id_alum", referencedColumnName = "id_alum")
    private Alumnos id_alum;

    @ManyToOne
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    private Grupos id_grupo;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    private Profesores id_profesor;

    @ManyToOne
    @JoinColumn(name = "punt_partes", referencedColumnName = "punt_partes")
    private Puntuacion_partes punt_partes;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "sancion")
    private String sancion;

    public Partes_incidencia() {
    }

    public Partes_incidencia(Alumnos id_alum, Grupos id_grupo, Profesores id_profesor, Puntuacion_partes punt_partes, String descripcion, String fecha, String hora, String sancion) {
        this.id_alum = id_alum;
        this.id_grupo = id_grupo;
        this.id_profesor = id_profesor;
        this.punt_partes = punt_partes;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.sancion = sancion;
    }

    public int getId_parte() {
        return id_parte;
    }

    public void setId_parte(int id_parte) {
        this.id_parte = id_parte;
    }

    public Alumnos getId_alum() {
        return id_alum;
    }

    public void setId_alum(Alumnos id_alum) {
        this.id_alum = id_alum;
    }

    public Grupos getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Grupos id_grupo) {
        this.id_grupo = id_grupo;
    }

    public Profesores getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(Profesores id_profesor) {
        this.id_profesor = id_profesor;
    }

    public Puntuacion_partes getPunt_partes() {
        return punt_partes;
    }

    public void setPunt_partes(Puntuacion_partes punt_partes) {
        this.punt_partes = punt_partes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }

    @Override
    public String toString() {
        return "Partes_incidencia{" +
                "id_alum=" + id_alum +
                ", id_grupo=" + id_grupo +
                ", id_profesor=" + id_profesor +
                ", punt_partes=" + punt_partes +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", sancion='" + sancion + '\'' +
                '}';
    }
}
