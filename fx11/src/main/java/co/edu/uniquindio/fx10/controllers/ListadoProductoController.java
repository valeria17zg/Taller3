package co.edu.uniquindio.fx10.controllers;

import co.edu.uniquindio.fx10.models.Inmueble;
import co.edu.uniquindio.fx10.repositories.InmuebleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * Controlador para el Dashboard principal
 */
public class ListadoProductoController {

    @FXML
    private VBox contenedorPrincipal;

    @FXML
    private Label lblTitulo;

    @FXML
    private TableView<Inmueble> tablaInmuebles;

    @FXML
    private TableColumn<Inmueble, String> colTipo;

    @FXML
    private TableColumn<Inmueble, String> colCiudad;

    @FXML
    private TableColumn<Inmueble, String> colHabitaciones;
    @FXML
    private TableColumn<Inmueble, String> colPisos;
    @FXML
    private TableColumn<Inmueble, Double> colPrecio;



    @FXML
    private Button btnEliminar;

    private InmuebleRepository inmuebleRepository;
    private ObservableList<Inmueble> listaInmuebles;
    private DashboardController dashboardController;


    @FXML
    public void initialize() {
        inmuebleRepository = InmuebleRepository.getInstancia();
        
        // Configurar las columnas de la tabla
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colHabitaciones.setCellValueFactory(new PropertyValueFactory<>("habitaciones"));
        colPisos.setCellValueFactory(new PropertyValueFactory<>("pisos"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        // Cargar los productos
        cargarProductos();
    }


    public void cargarProductos() {
        listaInmuebles = FXCollections.observableArrayList(inmuebleRepository.getInmuebles());
        tablaInmuebles.setItems(listaInmuebles);
    }





    @FXML
    private void onEliminarInmueble() {
        Inmueble inmuebleSeleccionado = tablaInmuebles.getSelectionModel().getSelectedItem();
        
        if (inmuebleSeleccionado == null) {
            mostrarAlerta("Advertencia", "Por favor seleccione un inmueble para eliminar", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar el inmueble?");
        confirmacion.setContentText("Tipo: " + inmuebleSeleccionado.getTipo() + " - Ciudad: " + inmuebleSeleccionado.getCiudad());

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                inmuebleRepository.eliminarInmueble(inmuebleSeleccionado);
                cargarProductos();
                mostrarAlerta("Éxito", "Inmueble eliminado correctamente", Alert.AlertType.INFORMATION);
            }
        });
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

    public VBox getContenedorPrincipal() {
        return contenedorPrincipal;
    }


}

