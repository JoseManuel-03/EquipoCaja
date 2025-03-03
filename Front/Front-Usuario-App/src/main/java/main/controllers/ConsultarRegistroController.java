package main.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.apiService.ApiService;
import org.openapitools.client.model.FechaPractica;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class ConsultarRegistroController extends AppController {

    private static final Logger log = LoggerFactory.getLogger(ConsultarRegistroController.class); // Logger para esta clase

    private Stage stage; // Necesario para controlar la ventana

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
        columnaFechas.setCellValueFactory(cellData -> {
            FechaPractica fechaPractica = cellData.getValue().getFecha();
            String fecha = (fechaPractica != null && fechaPractica.getFecha() != null)
                    ? fechaPractica.getFecha().toString()
                    : "Sin fecha";
            return new SimpleStringProperty(fecha);
        });

        columnaHorasRegistradas.setCellValueFactory(new PropertyValueFactory<>("cantidadHoras"));
        columnaDescripcion.setCellValueFactory(cellData -> {
            String descripcion = cellData.getValue().getDescripcion();
            return new SimpleStringProperty(
                    descripcion != null && descripcion.length() > 20 ? descripcion.substring(0, 20) + "..."
                            : descripcion);
        });

        // Configura el ComboBox para los filtros
        comboBoxFiltro.getItems().addAll("Todas", "Sólo fechas completas", "Sólo fechas sin completar");
        comboBoxFiltro.setValue("Todas"); // Valor por defecto

        // Configura el manejo de doble clic en la tabla
        tabla.setRowFactory(new Callback<TableView<RegistroPractica>, TableRow<RegistroPractica>>() {
            @Override
            public TableRow<RegistroPractica> call(TableView<RegistroPractica> tableView) {
                TableRow<RegistroPractica> row = new TableRow<RegistroPractica>() {
                    @Override
                    protected void updateItem(RegistroPractica registro, boolean empty) {
                        super.updateItem(registro, empty);
                        if (registro == null || empty) {
                            setStyle(""); // Restablece el estilo en filas vacías
                        } else {
                            if (registro.getCantidadHoras() > 0) {
                                setStyle("-fx-background-color: #c8e6c9;"); // Verde para registros con horas
                            } else {
                                setStyle("-fx-background-color: #ffcdd2;"); // Rojo para registros sin horas
                            }
                        }
                    }
                };

                // Manejo del doble clic en la fila
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && !row.isEmpty()) {
                        RegistroPractica registro = row.getItem();
                        mostrarDetalleRegistro(registro);
                        log.debug("Registro seleccionado: {}", registro.getDescripcion()); // Log de selección de registro
                    }
                });

                return row;
            }
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
        log.debug("Cambiando a la pantalla de detalle del registro: {}", registro.getDescripcion()); // Log de cambio de escena
    }

    /**
     * Carga los registros aplicando los filtros seleccionados.
     */
    private void cargarRegistros() {
        UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");
        if (usuario == null) {
            mostrarAlerta("Error", "No se ha iniciado sesión.");
            log.warn("Intento de cargar registros sin usuario logueado"); // Log de advertencia
            return;
        }

        // Obtener filtros de fecha
        LocalDate fechaDesde = datePickerDesde.getValue();
        LocalDate fechaHasta = datePickerHasta.getValue();

        // Llamar a la API para obtener registros
        List<RegistroPractica> registrosDesdeAPI = apiService.consultarDetalles(usuario.getId(), fechaDesde,
                fechaHasta);

        if (registrosDesdeAPI == null) {
            mostrarAlerta("Error", "No existen registros.");
            log.warn("No existen registros para el usuario: {}", usuario.getId()); // Log de advertencia
            return;
        }

        // Aplicar filtro de fechas
        registros.clear();
        for (RegistroPractica registro : registrosDesdeAPI) {
            LocalDate fechaRegistro = registro.getFecha().getFecha();

            // Validar que la fecha esté en el rango seleccionado (si hay filtros aplicados)
            boolean dentroDelRango = (fechaDesde == null || !fechaRegistro.isBefore(fechaDesde))
                    && (fechaHasta == null || !fechaRegistro.isAfter(fechaHasta));

            // Aplicar filtro de tipo de registro
            String filtro = comboBoxFiltro.getValue();
            boolean coincideFiltro = filtro.equals("Todas")
                    || (filtro.equals("Sólo fechas completas") && registro.getCantidadHoras() > 0)
                    || (filtro.equals("Sólo fechas sin completar") && registro.getCantidadHoras() == 0);

            if (dentroDelRango && coincideFiltro) {
                registros.add(registro);
                log.debug("Registro añadido: {}", registro.getDescripcion()); // Log de registro añadido
            }
        }

        // Asignar registros a la tabla
        tabla.setItems(registros);
        log.info("Total de registros cargados: {}", registros.size()); // Log de total de registros
    }

    @FXML
    void altaRegistro(ActionEvent event) {
        changeScene(FXML_ALTAREGISTRO);
        log.debug("Navegando a la pantalla de alta de registro"); // Log de navegación
    }

    /**
     * Método para filtrar los registros cuando se seleccionan fechas en el
     * DatePicker.
     */
    @FXML
    void consultarEntreFechas(ActionEvent event) {
        cargarRegistros();
        log.debug("Consultando registros entre fechas: {} y {}", datePickerDesde.getValue(), datePickerHasta.getValue()); // Log de consulta de fechas
    }

    @FXML
    void irMenu(ActionEvent event) {
        changeScene(FXML_MENU);
        log.debug("Navegando al menú"); // Log de navegación
    }

    @FXML
    void minimizar(ActionEvent event) {
        if (stage != null) {
            stage.setIconified(true); // Minimiza la ventana
            log.debug("Ventana minimizada"); // Log de acción
        }
    }

    @FXML
    void cerrar(ActionEvent event) {
        if (stage != null) {
            stage.close(); // Cierra la ventana
            log.debug("Ventana cerrada"); // Log de acción
        }
    }
}
