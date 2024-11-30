package application.Controller;

import application.DAO.ParteDAO;
import application.Model.*;
import application.Utils.AlertUtils;
import application.Utils.CambioEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CrearParteController implements Initializable {

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
    private TextField GrupoText;

    @FXML
    private ComboBox HoraCombo;

    @FXML
    private AnchorPane SancionAMano;

    @FXML
    private TextArea SancionText;

    @FXML
    private AnchorPane SancionComboBox;

    @FXML
    private ComboBox ComboSancion;

    @FXML
    private TextArea MiniSancionText;

    @FXML
    private AnchorPane RellenarAAmano;

    @FXML
    private DatePicker FechaPicker;

    private String tipoParte = "Advertencia";

    private int punt_partes = 1;

    ParteDAO parteDAO = new ParteDAO();

    private String[] horas = {"08:30", "09:25", "10:20", "11:40", "12:35", "13:30", "16:00", "16:55", "17:50", "19:00", "19:55", "20:50"};
    private String[] sanciones = {"Incoación de expediente o expediente abreviado", "Reunion con la Comisión de Convivencia", "Obligatorio pedir disculpas a los afectados y reparar los daños causados", "✎ Rellenar a mano"};

    Alumnos alumno1;
    Grupos grupo1;

    int puntos = 1;

    @FXML
    void OnCrearClic(ActionEvent event) throws IOException {
        if (camposVacios()) {
            String sancion = "";
//            if (alumno1)
            if (puntos < 12) {
                sancion = SancionText.getText();
            } else if (Objects.equals(ComboSancion.getValue().toString(), "✎ Rellenar a mano")) {
                sancion = MiniSancionText.getText();
            } else {
                sancion = ComboSancion.getValue().toString();
            }
            if (parteDAO.insertarParte(alumno1, ProfesorCompartido.getProfeIniciado(), puntos, FechaPicker.getValue(), HoraCombo.getValue().toString(), DescripcionText.getText(), sancion)) {
                AlertUtils.mostrarConfirmacion("Parte creado");
                vaciarCampos();
            } else {
                AlertUtils.mostrarError("Error al crear el parte");
            }
        }
    }

    @FXML
    void OnParteNaranjaClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #fa9746;");
        tituloPagina.setText("PARTE NARANJA  DE NOTA NEGATIVA");
        tipoParte = "Suspensión";
        SancionAMano.setVisible(true);
        SancionComboBox.setVisible(false);
        puntos = 6;
    }

    @FXML
    void OnParteRojoClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #ff616c;");
        tituloPagina.setText("PARTE ROJO  DE NOTA NEGATIVA");
        tipoParte = "Expulsión";
        SancionAMano.setVisible(false);
        SancionComboBox.setVisible(true);
        puntos = 12;
    }

    @FXML
    void OnParteVerdeClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #befc77;");
        tituloPagina.setText("PARTE VERDE DE ADVERTENCIA");
        tipoParte = "Advertencia";
        SancionAMano.setVisible(true);
        SancionComboBox.setVisible(false);
        puntos = 1;
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        if (Objects.equals(ProfesorCompartido.getProfeIniciado().getTipo(), "profesor")) {
            CambioEscenas.cambioEscena("InicioProfesor.fxml", fondoParte);
        } else {
            CambioEscenas.cambioEscena("InicioJefeEstudios.fxml", fondoParte);
        }
    }

    public boolean camposVacios() {

        if (Objects.equals(NumExpedienteAlumnoText.getText(), "") || Objects.equals(DescripcionText.getText(), "")
                || HoraCombo.getValue() == null || FechaPicker.getValue() == null || Objects.equals(GrupoText.getText(), "")) {
            AlertUtils.mostrarError("Todos los campos deben estar rellenos");
            return false;
        }

        if (puntos < 12) {
            if (Objects.equals(SancionText.getText(), "")) {
                AlertUtils.mostrarError("Todos los campos deben estar rellenos");
                return false;
            }
        } else {
            if (ComboSancion.getValue() == null) {
                AlertUtils.mostrarError("Todos los campos deben estar rellenos");
                return false;
            } else if (Objects.equals(ComboSancion.getValue().toString(), "✎ Rellenar a mano") && Objects.equals(MiniSancionText.getText(), "")) {
                AlertUtils.mostrarError("Todos los campos deben estar rellenos");
                return false;
            }
        }

        return true;
    }

    public void OnKeyPressed(KeyEvent keyEvent) {
        String teclaPulsada = keyEvent.getCode().toString();
        if (teclaPulsada.equals("TAB") || teclaPulsada.equals("ENTER")) {
            alumno1 = parteDAO.buscarAlumnoByExp(Integer.parseInt(NumExpedienteAlumnoText.getText()));
            if (alumno1 != null) {
                grupo1 = alumno1.getId_grupo();
                GrupoText.setText(grupo1.getNombre_grupo());
            } else {
                AlertUtils.mostrarError("Ese alumno no existe");
            }
        }
    }

    public void vaciarCampos() {
        NumExpedienteAlumnoText.setText("");
        GrupoText.setText("");
        FechaPicker.setValue(null);
        HoraCombo.setValue(null);
        DescripcionText.setText("");
        SancionText.setText("");
        ComboSancion.setValue(null);
        MiniSancionText.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HoraCombo.getItems().addAll(horas);
        ComboSancion.getItems().addAll(sanciones);

        ComboSancion.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && Objects.equals(newValue.toString(), "✎ Rellenar a mano")) {
                RellenarAAmano.setVisible(true);
            } else {
                RellenarAAmano.setVisible(false);
            }
        });

        NombreProfesor.setText(ProfesorCompartido.getProfeIniciado().getNombre());
    }
}