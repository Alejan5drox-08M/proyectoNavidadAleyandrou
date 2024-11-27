package application.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
DROP TABLE IF EXISTS `gestionpartes`.`puntuacion_partes`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`puntuacion_partes` (
  `id_punt_partes` INT NOT NULL AUTO_INCREMENT,
  `puntos` INT NOT NULL,
  `tipo_parte` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_punt_partes`),
  UNIQUE INDEX `UK_6m0adpsgoo0hnptrfpdvcx8vm` (`tipo_parte` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;
 */
@Entity
@Table(name = "puntuacion_partes")
public class Puntuacion_partes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_punt_partes")
    private int id_punt_partes;

    @Column(name = "puntos")
    private int puntos;

    @Column(name = "tipo_parte")
    private String tipo_parte;

    @OneToMany(mappedBy = "id_parte", cascade = CascadeType.ALL)
    Set<Partes_incidencia> partesIncidencias = new HashSet<>();

    public Puntuacion_partes() {
    }

    public Puntuacion_partes(int puntos, String tipo_partes) {
        this.puntos = puntos;
        this.tipo_parte = tipo_partes;
    }

    public int getId_punt_partes() {
        return id_punt_partes;
    }

    public void setId_punt_partes(int id_punt_partes) {
        this.id_punt_partes = id_punt_partes;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getTipo_parte() {
        return tipo_parte;
    }

    public void setTipo_parte(String tipo_parte) {
        this.tipo_parte = tipo_parte;
    }

    public Set<Partes_incidencia> getPartesIncidencias() {
        return partesIncidencias;
    }

    public void setPartesIncidencias(Set<Partes_incidencia> partesIncidencias) {
        this.partesIncidencias = partesIncidencias;
    }

    @Override
    public String toString() {
        return "Puntuacion_partes{" +
                "puntos=" + puntos +
                ", tipo_partes='" + tipo_parte + '\'' +
                '}';
    }
}
