package main.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.apiService.ApiService;
import main.gui.AppController;

import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.UsuarioDTO;

import java.time.LocalDate;
import java.util.List;

public class ConsultarRegistroController extends AppController {

    private Stage stage;  // Necesario para controlar la ventana

    @FXML
    private TableColumn<RegistroPractica, String> columnaFechas;

    @FXML
    private TableColumn<RegistroPractica, String> columnaDescripcion;

    @FXML
    private TableColumn<RegistroPractica, String> columnaHorasRegistradas;

    @FXML
    private ComboBox<String> comboBoxFiltro;

    @FXML
    private DatePicker datePickerDesde;

    @FXML
    private DatePicker datePickerHasta;

    @FXML
    private TableView<RegistroPractica> tabla;

    private ObservableList<RegistroPractica> registros = FXCollections.observableArrayList();

    private ApiService apiService = new ApiService(); // Instancia del servicio de API

    // Método para inicializar el Stage, pasarlo al controlador
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        // Configura las columnas de la tabla
        columnaFechas.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaHorasRegistradas.setCellValueFactory(new PropertyValueFactory<>("horas"));
        columnaDescripcion.setCellValueFactory(cellData -> {
            String descripcion = cellData.getValue().getDescripcion();
            return new SimpleStringProperty(descripcion != null && descripcion.length() > 20 ?
                    descripcion.substring(0, 20) + "..." : descripcion);
        });

        // Configura el ComboBox para los filtros
        comboBoxFiltro.getItems().addAll("Todas", "Sólo fechas completas", "Sólo fechas sin completar");
        comboBoxFiltro.setValue("Todas"); // Valor por defecto

        // Carga los registros iniciales
        cargarRegistros();

        // Configura el manejo de doble clic en la tabla
        tabla.setRowFactory(tv -> {
            TableRow<RegistroPractica> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    RegistroPractica registro = row.getItem();
                    mostrarDetalleRegistro(registro);
                }
            });
            return row;
        });
    }
    /**
     * Muestra el detalle del registro seleccionado.
     *
     * @param registro El registro seleccionado.
     */
    private void mostrarDetalleRegistro(RegistroPractica registro) {
        // Almacena el registro seleccionado en el AppController
        addParam("registroSeleccionado", registro);

        // Cambia a la pantalla de detalle
        changeScene(FXML_DETALLEREGISTRO);
    }

    private void cargarRegistros() {
        // Obtén el ID del usuario logueado
        UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");
        if (usuario == null) {
            mostrarAlerta("Error", "No se ha iniciado sesión.");
            return;
        }

        // Obtén los filtros seleccionados
        LocalDate fechaDesde = datePickerDesde.getValue();
        LocalDate fechaHasta = datePickerHasta.getValue();
        String filtro = comboBoxFiltro.getValue();

        // Llama al servicio de la API para obtener los registros
        List<RegistroPractica> registrosDesdeAPI = apiService.consultarDetalles(usuario.getId(), fechaDesde, fechaHasta);

        if (registrosDesdeAPI == null) {
            mostrarAlerta("Error", "No se pudieron cargar los registros.");
            return;
        }

        // Filtra los registros según los criterios seleccionados
        registros.clear();
        for (RegistroPractica registro : registrosDesdeAPI) {
            if ((filtro.equals("Todas") ||
                (filtro.equals("Sólo fechas completas") && registro.getCantidadHoras() > 0) ||
                (filtro.equals("Sólo fechas sin completar") && registro.getCantidadHoras() == 0))) {
                registros.add(registro);
            }
        }

        // Asigna los registros filtrados a la tabla
        tabla.setItems(registros);
    }

    @FXML
    void altaRegistro(ActionEvent event) {
        changeScene(FXML_ALTAREGISTRO);
    }

    @FXML
    void consultarEntreFechas(ActionEvent event) {
        cargarRegistros();
    }

    @FXML
    void irMenu(ActionEvent event) {
        changeScene(FXML_MENU);
    }

    @FXML
    void minimizar(ActionEvent event) {
        if (stage != null) {
            stage.setIconified(true);  // Minimiza la ventana
        }
    }

    @FXML
    void cerrar(ActionEvent event) {
        if (stage != null) {
            stage.close();  // Cierra la ventana
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}