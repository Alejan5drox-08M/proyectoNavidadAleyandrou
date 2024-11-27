package application.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CrearUsuarioController extends SuperController implements Initializable{

    @FXML
    private PasswordField ContraseniaText;

    @FXML
    private TextField NombreText;

    @FXML
    private TextField NumeroAsignadoText;

    @FXML
    private ComboBox TipoChoice;

    @FXML
    private Label errorContrasenia;

    @FXML
    private Label errorNombre;

    @FXML
    private Label errorNumero;

    @FXML
    private Label errorTipo;

    private String[] tipoUsuario={"Profesor", "Jefe de Estudios"};

    @FXML
    void OnCrearUsuarioClic(ActionEvent event) {
        if (camposVacios()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Usuario Creado");
            alerta.show();
        }
    }

    public boolean camposVacios(){
        boolean todoBien = true;
        if (Objects.equals(ContraseniaText.getText(), "")){
            errorContrasenia.setText("La contaseña es un campo obligatorio");
            todoBien = false;
        } else {
            errorContrasenia.setText("");
        }
        if (Objects.equals(NumeroAsignadoText.getText(), "")){
            errorNumero.setText("El número asignado es un campo obligatorio");
            todoBien = false;
        } else {
            errorNumero.setText("");
        }
        if (Objects.equals(NombreText.getText(), "")){
            errorNombre.setText("El nombre es un campo obligatorio");
            todoBien = false;
        } else {
            errorNombre.setText("");
        }
        if (TipoChoice.getValue() == null){
            errorTipo.setText("El tipo es un campo obligatorio");
            todoBien = false;
        } else {
            errorTipo.setText("");
        }
        return todoBien;
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Ir a InicioJefeEstudios.fxml");
        alerta.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TipoChoice.getItems().addAll(tipoUsuario);
    }
}
