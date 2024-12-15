package application.Controller;

import application.Utils.CambioEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class IniJefController extends SuperController {

    @FXML
    private AnchorPane CrearPartePane;

    @FXML
    private AnchorPane CrearProfesorPane;

    @FXML
    private AnchorPane ListaAlumnosPane;

    @FXML
    private AnchorPane ListaPartesPane;

    @FXML
    private AnchorPane fondoProfe;

    @FXML
    void OnCrearParteClic(MouseEvent event) throws IOException {
        CambioEscenas.cambioEscena("CrearParte.fxml", fondoProfe);
    }

    @FXML
    void OnCrearProfesorClic(MouseEvent event) throws IOException {
        CambioEscenas.cambioEscena("CrearUsuario.fxml", fondoProfe);
    }

    @FXML
    void OnListaAlumnosClic(MouseEvent event) {
        CambioEscenas.cambioEscena("ListaAlumnos.fxml", fondoProfe);
    }

    @FXML
    void OnListaPartesClic(MouseEvent event) throws IOException {
        CambioEscenas.cambioEscena("ListaPartes.fxml", fondoProfe);
    }

    @FXML
    void EnterCrearPartePane(MouseEvent event) {
        cambiarFondoAzul(CrearPartePane);
    }

    @FXML
    void EnterCrearProfesorPane(MouseEvent event) {
        cambiarFondoAzul(CrearProfesorPane);
    }

    @FXML
    void EnterListaAlumnosPane(MouseEvent event) {
        cambiarFondoAzul(ListaAlumnosPane);
    }

    @FXML
    void EnterListaPartesPane(MouseEvent event) {
        cambiarFondoAzul(ListaPartesPane);
    }

    @FXML
    void ExitCrearPartePane(MouseEvent event) {
        cambiarFondoGris(CrearPartePane);
    }

    @FXML
    void ExitCrearProfesorPane(MouseEvent event) {
        cambiarFondoGris(CrearProfesorPane);
    }

    @FXML
    void ExitListaAlumnosPane(MouseEvent event) {
        cambiarFondoGris(ListaAlumnosPane);
    }

    @FXML
    void ExitListaPartesPane(MouseEvent event) {
        cambiarFondoGris(ListaPartesPane);
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        CambioEscenas.cambioEscena("InicioSesion.fxml", fondoProfe);
    }

    public void cambiarFondoAzul(AnchorPane areaAzul) {
        areaAzul.setStyle("-fx-background-color: #A3CEEF;");
    }

    public void cambiarFondoGris(AnchorPane areaGris) {
        areaGris.setStyle("-fx-background-color: #E4E4E5;");
    }

}
