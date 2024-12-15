package application.Controller;

import application.DAO.ParteDAO;
import application.Model.Partes_incidencia;
import application.Utils.CambioEscenas;
import application.Utils.ParsearFecha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VistaParteController extends SuperController implements Initializable {


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
    private Label NombreProfeText2;

    @FXML
    private Label SancionText;

    @FXML
    private Label TextoParteVerde;

    @FXML
    private Label TituloParte;

    ParteDAO parteDAO = new ParteDAO();

    Partes_incidencia parte1;

    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        CambioEscenas.cambioEscena("ListaPartes.fxml", FondoParte);
        setParte(null);
    }

    public void OnEliminarClic(ActionEvent actionEvent) throws IOException {
        parteDAO.eliminarParte(parte1);
        CambioEscenas.cambioEscena("ListaPartes.fxml", FondoParte);
    }

    public void OnEditarClic(ActionEvent actionEvent) throws IOException {
        alumno = parte.getId_alum();
        CambioEscenas.cambioEscena("CrearParte.fxml", FondoParte);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parte1 = getParte();
        NombreAlumnoText.setText(parte1.getId_alum().getNombre_alum());
        GrupoText.setText(parte1.getId_alum().getId_grupo().getNombre_grupo());
        NombreProfeText.setText(parte1.getId_profesor().getNombre());
        NombreProfeText2.setText(parte1.getId_profesor().getNombre());
        FechaText.setText(parte1.getFecha().toString());
        HoraText.setText(parte1.getHora());
        DescripcionText.setText(parte1.getDescripcion());
        SancionText.setText(parte1.getSancion());
        AnioText.setText(String.valueOf(parte1.getFecha().getYear()));
        MesText.setText(ParsearFecha.fechaGetMonth(parte1.getFecha()));
        DiaText.setText(String.valueOf(parte1.getFecha().getDayOfMonth()));

        if (parte1.getPuntos() == 1) {
            TextoParteVerde.setText("Faltas leves de disciplina contrarias a las normas de convivencia");
            TituloParte.setText("PARTE VERDE DE ADVERTENCIA");
            FondoParte.setStyle("-fx-background-color: #befc77;");
        } else if (parte1.getPuntos() == 6) {
            TextoParteVerde.setText("");
            TituloParte.setText("PARTE NARANJA DE NOTA NEGATIVA");
            FondoParte.setStyle("-fx-background-color: #fa9746;");
        } else {
            TextoParteVerde.setText("");
            TituloParte.setText("PARTE ROJO DE NOTA NEGATIVA");
            FondoParte.setStyle("-fx-background-color: #ff616c;");
        }
    }
}
