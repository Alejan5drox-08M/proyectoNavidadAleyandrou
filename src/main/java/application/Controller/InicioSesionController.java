package application.Controller;

import application.DAO.ProfesorDAO;
import application.Model.Profesores;
import application.Utils.AlertUtils;
import application.Utils.CambioEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class InicioSesionController extends SuperController {


    public AnchorPane inicioSesion;

    @FXML
    private TextField ContraseniaText;

    @FXML
    private TextField NumeroAsignadoText;

    ProfesorDAO profesorDAO;

    public InicioSesionController() {
        profesorDAO = new ProfesorDAO();
    }

    @FXML
    void OnIniciarSesionClic(ActionEvent event) {
        if (camposVacios()) {
            String numAsignado = NumeroAsignadoText.getText();
            String contra = ContraseniaText.getText();
            Profesores profesor = profesorDAO.buscarProfesor(numAsignado, contra);
            if (profesor == null) {
                AlertUtils.mostrarError("No existe dicho profesor");
            } else {
                if (profesor.getTipo().equalsIgnoreCase("profesor")) {
                    CambioEscenas.cambioEscenaController("InicioProfesor.fxml", inicioSesion);
                } else {
                    CambioEscenas.cambioEscenaController("InicioJefeEstudios.fxml", inicioSesion);
                }
            }
        }
    }

    public boolean camposVacios() {
        if (Objects.equals(ContraseniaText.getText(), "") || Objects.equals(NumeroAsignadoText.getText(), "")) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Todos los campos deben estar rellenos");
            alerta.show();
            return false;
        }
        return true;
    }
}
