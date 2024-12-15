package application.Controller;

import application.DAO.AlumnoDAO;
import application.DAO.ParteDAO;
import application.Model.Alumnos;
import application.Utils.CambioEscenas;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ListaAlumnosController extends SuperController {

    @FXML
    private TextField BuscarNumeroExpediente;

    @FXML
    private TableColumn<Alumnos, String> ExpedienteColumn;

    @FXML
    private TableColumn<Alumnos, String> GrupoColumn;

    @FXML
    private TableView<Alumnos> LaTabla;

    @FXML
    private TableColumn<Alumnos, String> NombreAlumnoColumn;

    @FXML
    private TableColumn<Alumnos, String> PuntosColumn;

    @FXML
    private AnchorPane fondoAlumnos;

    AlumnoDAO alumnoDAO = new AlumnoDAO();
    List<Alumnos> alumnos;

    private ObservableList<Alumnos> alumnosList;

    @FXML
    void initialize() {
        // Inicializa las columnas de la tabla
        ExpedienteColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNumero_expediente()));
        NombreAlumnoColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre_alum()));
        GrupoColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId_grupo().getNombre_grupo()));
        PuntosColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPuntos_acumulados()).asString());

        // Cargar los partes en la tabla
        alumnos = alumnoDAO.listarAlumnos();
        cargarAlumnos();

        // Establecer la fábrica de filas
        LaTabla.setRowFactory(tv -> new TableRow<Alumnos>() {
            @Override
            protected void updateItem(Alumnos alumno1, boolean empty) {
                super.updateItem(alumno1, empty);
                if (empty || alumno1 == null) {
                    setStyle(""); // Restablecer el estilo si la fila está vacía
                } else {
                    // Cambiar el color según las condiciones
                    if (alumno1.getPuntos_acumulados() > 0 && alumno1.getPuntos_acumulados() < 6) {
                        setStyle("-fx-background-color: #befc77;");
                    } else if (alumno1.getPuntos_acumulados() > 5 && alumno1.getPuntos_acumulados() < 12) {
                        setStyle("-fx-background-color: #fa9746;");
                    } else if (alumno1.getPuntos_acumulados() > 11) {
                        setStyle("-fx-background-color: #ff616c;");
                    } else {
                        setStyle("-fx-background-color: #ffffff;");
                    }
                }
            }
        });
    }

    private void cargarAlumnos() {
        alumnosList = FXCollections.observableArrayList(alumnos);
        LaTabla.setItems(alumnosList);
    }

    @FXML
    void OnBuscarNumeroClic(ActionEvent event) {
        BuscarPorNumero();
    }

    public void BuscarPorNumero() {
        if (Objects.equals(BuscarNumeroExpediente.getText(), "")) {
            alumnos = alumnoDAO.listarAlumnos();
        } else {
            alumnos = alumnoDAO.listarAlumnosByExp(BuscarNumeroExpediente.getText());
        }
        cargarAlumnos();
        vaciarCampos();
    }

    public void vaciarCampos() {
        BuscarNumeroExpediente.setText("");
    }

    @FXML
    void OnVolverClic(ActionEvent event) throws IOException {
        CambioEscenas.cambioEscena("InicioJefeEstudios.fxml", fondoAlumnos);
    }

    public void OnNumeroPressed(KeyEvent keyEvent) {
        String teclaPulsada = keyEvent.getCode().toString();
        if (teclaPulsada.equals("ENTER")) {
            BuscarPorNumero();
        }
    }
}