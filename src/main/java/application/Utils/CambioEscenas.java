package application.Utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CambioEscenas {

    // metodo estatico para cambiar de escena, recibe tanto el nombre de la nueva escena y el pane inicial de la escena vieja
    static public void cambioEscena(String fxmlnName, AnchorPane rootPane) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI(fxmlnName));

            // aqui abro la escena nueva
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.sizeToScene();
            newStage.show();

            // aqui cierro la escena antigua
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.close();

        } catch (IOException IOex) {
            AlertUtils.mostrarError("Error al cargar el FXML");
        }
    }

    // metodo estatico para cambiar de escena, recibe tanto el nombre de la nueva escena y el pane inicial de la escena vieja y el vehiculo para modificarlo
    /*static public void cambioEscena(String fxmlnName, AnchorPane rootPane, Coche coche) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI(fxmlnName));

            // aqui abro la escena nueva
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));

            //El unico cambio respecto al cambio pantallas normal es que le paso el controller para poder pasarle parametros
            MultasController controller = loader.getController();
            controller.setCoche(coche);

            newStage.sizeToScene();
            newStage.show();

            // aqui cierro la escena antigua
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.close();

        } catch (IOException IOex) {
            AlertUtils.mostrarError("Error al cargar el FXML");
        }
    }*/

    public static SuperController cambioEscenaController(String fxmlnName, AnchorPane rootPane) { //Función que introduce un nuevo stage con una escena
        SuperController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI(fxmlnName));

            // aqui abro la escena nueva
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.sizeToScene();
            controller = loader.getController();
            newStage.show();

            // aqui cierro la escena antigua
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            System.out.println("Error al cargar el FXML");
        }
        return controller;
    }
}
