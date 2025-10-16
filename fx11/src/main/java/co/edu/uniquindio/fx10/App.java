package co.edu.uniquindio.fx10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/Dashboard.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 700, 550);
        
        primaryStage.setTitle("Sistema de Gestión de Inmuebles");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(560);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

