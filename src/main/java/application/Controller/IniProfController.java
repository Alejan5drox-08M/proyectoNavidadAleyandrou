package application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class IniProfController {

    @FXML
    private AnchorPane AreaCrear;

    @FXML
    void OnCrearParteClic(MouseEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Pantalla Crear Parte");
        alerta.show();
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
    public void OnVolverClic(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Ir a InicioSesion.fxml");
        alerta.show();
    }

    public void cambiarFondoAzul(AnchorPane areaAzul){
        areaAzul.setStyle("-fx-background-color: #A3CEEF;");
    }

    public void cambiarFondoGris(AnchorPane areaGris){
        areaGris.setStyle("-fx-background-color: #E4E4E5;");
    }
}
