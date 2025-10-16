package co.edu.uniquindio.fx10.controllers;

import co.edu.uniquindio.fx10.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {

    @FXML
    private VBox contenedorPrincipal;

    @FXML
    private Button editrButton;

    @FXML
    private StackPane stackPaneListado;
    private DashboardController dashboardController;

    public void initialize(){
        showListadoProducto();
    }

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

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
        this.contenedorPrincipal = dashboardController.getContenedorPrincipal();
    }
    
    @FXML
    void onEditButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/FormularioProducto.fxml"));
            Parent listado = loader.load();

            FormularioProductoController controller = loader.getController();
            controller.setDashboardController(this);

            // Reemplazar el contenido del contenedor principal
            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(listado);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el listado", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    public void showListadoProducto(){
        try{
            Parent listadoProducto = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/fx10/vista/ListadoProducto.fxml"));
            stackPaneListado.getChildren().setAll(listadoProducto);

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar el listado de motos", Alert.AlertType.ERROR);
        }
    }



}

