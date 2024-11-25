package application.Model;

import javax.persistence.*;

/*
DROP TABLE IF EXISTS `gestionpartes`.`profesores`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`profesores` (
  `id_profesor` INT NOT NULL AUTO_INCREMENT,
  `contrasena` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `numero_asignado` VARCHAR(255) NULL DEFAULT NULL,
  `tipo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_profesor`),
  UNIQUE INDEX `UK_p6ltb4s5eu3ymeanq6rdw944v` (`numero_asignado` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;
 */

@Entity
@Table(name = "profesores")
public class Profesores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private int id_profesor;

    @Column(name = "contrasena")
    private String contrasena;// recordar sha

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "numero_asignado")
    private String numero_asignado;

    @Column(name = "tipo")
    private String tipo;

    public Profesores() {
    }

    public Profesores(String contrasena, String nombre, String numero_asignado, String tipo) {
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.numero_asignado = numero_asignado;
        this.tipo = tipo;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero_asignado() {
        return numero_asignado;
    }

    public void setNumero_asignado(String numero_asignado) {
        this.numero_asignado = numero_asignado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Profesores{" +
                "contrasena='" + contrasena + '\'' +
                ", nombre='" + nombre + '\'' +
                ", numero_asignado='" + numero_asignado + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
