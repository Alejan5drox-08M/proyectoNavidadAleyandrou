package application.Model;

/*
DROP TABLE IF EXISTS `gestionpartes`.`alumnos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`alumnos` (
  `id_alum` INT NOT NULL AUTO_INCREMENT,
  `id_grupo` INT NOT NULL,
  `puntos_acumulados` INT NOT NULL,
  `nombre_alum` VARCHAR(255) NULL DEFAULT NULL,
  `numero_expediente` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_alum`),
  INDEX `FKoif6noujgnb1q4hfucdj3by8q` (`id_grupo` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

ALTER TABLE `gestionpartes`.`alumnos`
  ADD CONSTRAINT `FKoif6noujgnb1q4hfucdj3by8q`
  FOREIGN KEY (`id_grupo`)
  REFERENCES `gestionpartes`.`grupos` (`id_grupo`);
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alumnos")
public class Alumnos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alum")
    private int id_alum;

    @ManyToOne
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    private Grupos id_grupo;

    @Column(name = "puntos_acumulados")
    private int puntos_acumulados;
    @Column(name = "nombre_alum")
    private String nombre_alum;
    @Column(name = "numero_expediente")
    private String numero_expediente;

    public Alumnos() {
    }


    public Alumnos(Grupos id_grupo, int puntos_acumulados, String nombre_alum, String numero_expediente) {
        this.id_grupo = id_grupo;
        this.puntos_acumulados = puntos_acumulados;
        this.nombre_alum = nombre_alum;
        this.numero_expediente = numero_expediente;
    }

    public int getId_alum() {
        return id_alum;
    }

    public void setId_alum(int id_alum) {
        this.id_alum = id_alum;
    }

    public Grupos getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Grupos id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getPuntos_acumulados() {
        return puntos_acumulados;
    }

    public void setPuntos_acumulados(int puntos_acumulados) {
        this.puntos_acumulados = puntos_acumulados;
    }

    public String getNombre_alum() {
        return nombre_alum;
    }

    public void setNombre_alum(String nombre_alum) {
        this.nombre_alum = nombre_alum;
    }

    public String getNumero_expediente() {
        return numero_expediente;
    }

    public void setNumero_expediente(String numero_expediente) {
        this.numero_expediente = numero_expediente;
    }

    @Override
    public String toString() {
        return "Alumnos{" +
                "id_grupo=" + id_grupo +
                ", puntos_acumulados=" + puntos_acumulados +
                ", nombre_alum='" + nombre_alum + '\'' +
                ", numero_expediente='" + numero_expediente + '\'' +
                '}';
    }
}
