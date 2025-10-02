package co.edu.uniquindio.fx10.controllers;

import co.edu.uniquindio.fx10.App;
import co.edu.uniquindio.fx10.models.Producto;
import co.edu.uniquindio.fx10.repositories.ProductoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class DashboardController {


    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public VBox getContenedorPrincipal() {
        return contenedorPrincipal;
    }

    @FXML
    private VBox contenedorPrincipal;

    @FXML
    private Button editButton;

    @FXML
    private Label lblTitulo;

    @FXML
    void onEditButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/ListadoProducto.fxml"));
            Parent listado = loader.load();

            ListadoProductoController controller = loader.getController();
            controller.setDashboardController(this);

            // Reemplazar el contenido del contenedor principal
            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(listado);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el listado", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }
}

