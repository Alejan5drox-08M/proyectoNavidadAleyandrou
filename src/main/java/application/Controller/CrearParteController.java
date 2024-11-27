package application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CrearParteController extends SuperController implements Initializable {

    @FXML
    private AnchorPane fondoParte;

    @FXML
    private TextField NumExpedienteAlumnoText;

    @FXML
    private TextArea DescripcionText;

    @FXML
    private TextField NombreProfesor;

    @FXML
    private Label tituloPagina;

    @FXML
    private ComboBox GrupoCombo;

    @FXML
    private ComboBox HoraCombo;

    @FXML
    private DatePicker FechaPicker;

    private String tipoParte = "Advertencia";

    private String[] grupos = {"2º DAM", "1º DAM"};
    private String[] horas = {"08:30", "09:25", "10:20", "11:40", "12:35", "13:30", "16:00", "16:55", "17:50", "19:00", "19:55", "20:50"};

    @FXML
    void OnCrearClic(ActionEvent event) {
        if (camposVacios()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Pantalla Inicio Usuario");
            alerta.show();
        }
    }

    @FXML
    void OnParteNaranjaClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #fa9746;");
        tituloPagina.setText("PARTE NARANJA  DE NOTA NEGATIVA");
        tipoParte = "Suspensión";
    }

    @FXML
    void OnParteRojoClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #ff616c;");
        tituloPagina.setText("PARTE ROJO  DE NOTA NEGATIVA");
        tipoParte = "Expulsión";
    }

    @FXML
    void OnParteVerdeClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #befc77;");
        tituloPagina.setText("PARTE VERDE DE ADVERTENCIA");
        tipoParte = "Advertencia";
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Ir a InicioProfesor.fxml\no InicioJefeEstudios.fxml");
        alerta.show();
    }

    public boolean camposVacios() {
        if (Objects.equals(NumExpedienteAlumnoText.getText(), "") || Objects.equals(DescripcionText.getText(), "")
                || GrupoCombo.getValue() == null || HoraCombo.getValue() == null || FechaPicker.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Todos los campos deben estar rellenos");
            alerta.show();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GrupoCombo.getItems().addAll(grupos);
        HoraCombo.getItems().addAll(horas);
    }
}
