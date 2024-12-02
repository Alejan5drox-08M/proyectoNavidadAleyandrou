package application.Controller;

import application.Model.Profesores;
import application.Utils.CambioEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IniProfController extends SuperController  implements Initializable {

    Profesores profesor = getProfesor();
    @FXML
    private AnchorPane AreaCrear;

    @FXML
    private AnchorPane fondoProfe;

    @FXML
    void OnCrearParteClic(MouseEvent event) throws IOException {
        CambioEscenas.cambioEscena("CrearParte.fxml", fondoProfe);
    }

    @FXML
    void OnMouseEnter(MouseEvent event) {
        cambiarFondoAzul(AreaCrear);
    }

    @FXML
    void OnMouseExit(MouseEvent event) {
        cambiarFondoGris(AreaCrear);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(profesor);
    }
}
