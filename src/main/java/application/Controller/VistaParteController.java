package application.Controller;

import application.DAO.ProfesorDAO;
import application.Model.Partes_incidencia;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class VistaParteController {

    @FXML
    private Label AnioText;

    @FXML
    private Label DescripcionText;

    @FXML
    private Label DiaText;

    @FXML
    private Label FechaText;

    @FXML
    private AnchorPane FondoParte;

    @FXML
    private Label GrupoText;

    @FXML
    private Label HoraText;

    @FXML
    private Label MesText;

    @FXML
    private Label NombreAlumnoText;

    @FXML
    private Label NombreProfeText;

    @FXML
    private Label SancionText;

    @FXML
    private Label TextoParteVerde;

    @FXML
    private Label TituloParte;

    ProfesorDAO profesorDAO = new ProfesorDAO();

    public void rellenarParte(Partes_incidencia parte1) { //Mal implementado, necesito mas DAOs
        NombreAlumnoText.setText(parte1.getId_alum().toString());
        GrupoText.setText(parte1.getId_alum().toString());
        NombreProfeText.setText(parte1.getId_profesor().toString());
        FechaText.setText(parte1.getFecha().toString());
        HoraText.setText(parte1.getHora());
        DescripcionText.setText(parte1.getDescripcion());
        SancionText.setText(parte1.getSancion());
    }

}
