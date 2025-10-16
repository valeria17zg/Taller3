package co.edu.uniquindio.fx10.controllers;

import co.edu.uniquindio.fx10.App;
import co.edu.uniquindio.fx10.models.Inmueble;
import co.edu.uniquindio.fx10.repositories.InmuebleRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para el formulario de creación de productos
 */
public class FormularioProductoController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtHabitaciones;
    @FXML
    private TextField txtPisos;
    @FXML
    private TextField txtPrecio;



    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private InmuebleRepository inmuebleRepository;
    private DashboardController dashboardController;
    private VBox contenedorPrincipal;


    @FXML
    public void initialize() {
        inmuebleRepository = InmuebleRepository.getInstancia();
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
        this.contenedorPrincipal = dashboardController.getContenedorPrincipal();
    }


    @FXML
    private void onGuardarMoto(ActionEvent event) throws IOException {


        if (!validarCampos()) {
            return;
        }

        try {
            String tipo = txtTipo.getText().trim();
            String ciudad = txtCiudad.getText().trim();
            String habitaciones = txtHabitaciones.getText().trim();
            String pisos = txtPisos.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());



            Inmueble nuevoInmueble = new Inmueble(tipo, ciudad, habitaciones, pisos, precio);
            inmuebleRepository.agregarInmueble(nuevoInmueble);

            mostrarAlerta("Éxito", "Inmueble registrado correctamente", Alert.AlertType.INFORMATION);

            volverAlDashboard();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio debe ser valores numéricos válidos", Alert.AlertType.ERROR);
        }


    }

    /**
     * Maneja el evento de cancelar
     */
    @FXML
    private void onCancelar(ActionEvent event) throws IOException {
        volverAlDashboard();
    }

    private void volverAlDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/Dashboard.fxml"));
            Parent dashboard = loader.load();

            DashboardController controller = loader.getController();
            controller.setDashboardController(dashboardController);

            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(dashboard);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo volver al listado", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    private boolean validarCampos() {
        if (txtTipo.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El tipo es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtCiudad.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La ciudad es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        if (txtHabitaciones.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La cantidad de habitaciones es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        if (txtPisos.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La cantidad de pisos es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        if (txtPrecio.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El precio es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    /**
     * Muestra una alerta al usuario
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

