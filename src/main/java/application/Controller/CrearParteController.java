package application.Controller;

import application.DAO.ParteDAO;
import application.Model.Alumnos;
import application.Model.Grupos;
import application.Model.Partes_incidencia;
import application.Model.Profesores;
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

    @FXML
    private Button CrearButton;

    @FXML
    private Button EditarButton;

    private String tipoParte = "Advertencia";

    private int punt_partes = 1;

    ParteDAO parteDAO = new ParteDAO();

    private String[] horas = {"08:30", "09:25", "10:20", "11:40", "12:35", "13:30", "16:00", "16:55", "17:50", "19:00", "19:55", "20:50"};
    private String[] sanciones = {"Incoación de expediente o expediente abreviado", "Reunion con la Comisión de Convivencia", "Obligatorio pedir disculpas a los afectados y reparar los daños causados", "✎ Rellenar a mano"};

    Partes_incidencia parte1;
    Alumnos alumno1;
    Grupos grupo1;

    int puntos = 1;

    @FXML
    void OnCrearClic(ActionEvent event) throws IOException {
        if (camposVacios()) {
            String sancion = "";
            if (puntos < 12) {
                sancion = SancionText.getText();
            } else if (Objects.equals(ComboSancion.getValue().toString(), "✎ Rellenar a mano")) {
                sancion = MiniSancionText.getText();
            } else {
                sancion = ComboSancion.getValue().toString();
            }
            Partes_incidencia partesIncidencia = new Partes_incidencia(alumno1, alumno1.getId_grupo(), profesor, FechaPicker.getValue(), HoraCombo.getValue().toString(), DescripcionText.getText(), sancion, puntos);
            if (parteDAO.insertarParte(partesIncidencia)) {
                AlertUtils.mostrarConfirmacion("Parte creado");
                vaciarCampos();
            } else {
                AlertUtils.mostrarError("Error al crear el parte");
            }
        }
    }

    @FXML
    void OnParteVerdeClic(ActionEvent event) {
        SetParteVerde();
    }

    @FXML
    void OnParteNaranjaClic(ActionEvent event) {
        SetParteNaranja();
    }

    @FXML
    void OnParteRojoClic(ActionEvent event) {
        SetParteRojo();
    }

    public void SetParteVerde() {
        fondoParte.setStyle("-fx-background-color: #befc77;");
        tituloPagina.setText("PARTE VERDE DE ADVERTENCIA");
        tipoParte = "Advertencia";
        SancionAMano.setVisible(true);
        SancionComboBox.setVisible(false);
        puntos = 1;
    }

    public void SetParteNaranja() {
        fondoParte.setStyle("-fx-background-color: #fa9746;");
        tituloPagina.setText("PARTE NARANJA  DE NOTA NEGATIVA");
        tipoParte = "Suspensión";
        SancionAMano.setVisible(true);
        SancionComboBox.setVisible(false);
        puntos = 6;
    }

    public void SetParteRojo() {
        fondoParte.setStyle("-fx-background-color: #ff616c;");
        tituloPagina.setText("PARTE ROJO  DE NOTA NEGATIVA");
        tipoParte = "Expulsión";
        SancionAMano.setVisible(false);
        SancionComboBox.setVisible(true);
        puntos = 12;
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        if (getParte() == null) {
            if (Objects.equals(getProfesor().getTipo(), "profesor")) {
                CambioEscenas.cambioEscena("InicioProfesor.fxml", fondoParte);
            } else {
                CambioEscenas.cambioEscena("InicioJefeEstudios.fxml", fondoParte);
            }
        } else {
            CambioEscenas.cambioEscena("VistaParte.fxml", fondoParte);
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
            alumno1 = parteDAO.buscarAlumnoByExp(NumExpedienteAlumnoText.getText());
            if (alumno1 != null) {
                grupo1 = alumno1.getId_grupo();
                GrupoText.setText(grupo1.getNombre_grupo());
            } else {
                AlertUtils.mostrarError("Ese alumno no existe");
            }
        } else {
            GrupoText.setText("");
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
        //Introducimos los valores a los ComboBox
        HoraCombo.getItems().addAll(horas);
        ComboSancion.getItems().addAll(sanciones);

        //Dependiendode la opcion sleccionada en el combobox mostramos o escondemos el campo para rellenar a mano
        ComboSancion.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && Objects.equals(newValue.toString(), "✎ Rellenar a mano")) {
                RellenarAAmano.setVisible(true);
            } else {
                RellenarAAmano.setVisible(false);
            }
        });

        if (getParte() == null) {
            CrearButton.setVisible(true);
            EditarButton.setVisible(false);
            NombreProfesor.setText(getProfesor().getNombre());
        } else {
            CrearButton.setVisible(false);
            EditarButton.setVisible(true);
            parte1 = getParte();
            cargarParte();
        }
    }

    public void cargarParte() {
        NumExpedienteAlumnoText.setText(parte1.getId_alum().getNumero_expediente());
        GrupoText.setText(parte1.getId_alum().getId_grupo().getNombre_grupo());
        NombreProfesor.setText(parte1.getId_profesor().getNombre());
        FechaPicker.setValue(parte1.getFecha());
        HoraCombo.setValue(parte1.getHora());
        DescripcionText.setText(parte1.getDescripcion());
        if (parte1.getPuntos() < 12) {
            if (parte1.getPuntos() == 1) {
                SetParteVerde();
            } else {
                SetParteNaranja();
            }
            SancionText.setText(parte1.getSancion());
        } else {
            SetParteRojo();
            if (!Objects.equals(parte1.getSancion(), "Incoación de expediente o expediente abreviado") &&
                    !Objects.equals(parte1.getSancion(), "Reunion con la Comisión de Convivencia") &&
                    !Objects.equals(parte1.getSancion(), "Obligatorio pedir disculpas a los afectados y reparar los daños causados")) {
                RellenarAAmano.setVisible(true);
                ComboSancion.setValue("✎ Rellenar a mano");
                MiniSancionText.setText(parte1.getSancion());
            } else {
                ComboSancion.setValue(parte1.getSancion());
            }
        }
    }

    @FXML
    public void OnEditarClic(ActionEvent actionEvent) throws IOException {
        if (camposVacios()) {
            String sancion = "";
            if (puntos < 12) {
                sancion = SancionText.getText();
            } else if (Objects.equals(ComboSancion.getValue().toString(), "✎ Rellenar a mano")) {
                sancion = MiniSancionText.getText();
            } else {
                sancion = ComboSancion.getValue().toString();
            }
            parte.setId_alum(alumno);
            parte.setId_grupo(alumno.getId_grupo());
            parte.setId_profesor(profesor);
            parte.setFecha(FechaPicker.getValue());
            parte.setHora(HoraCombo.getValue().toString());
            parte.setDescripcion(DescripcionText.getText());
            parte.setSancion(sancion);
            parte.setPuntos(puntos);
            if (parteDAO.modificarParte(parte)) {
                AlertUtils.mostrarConfirmacion("Parte modificado");
                parte = null;
                alumno = null;
                CambioEscenas.cambioEscena("ListaPartes.fxml", fondoParte);
            } else {
                AlertUtils.mostrarError("Error al modificar el parte");
            }
        }
    }
}