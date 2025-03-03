package main.controllers;

import java.time.LocalDate;
import java.util.List;

import org.openapitools.client.model.FechaPractica;
import org.openapitools.client.model.RegistroPractica;
import org.openapitools.client.model.UsuarioDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.apiService.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AltaRegistroController extends AppController {

    private static final Logger log = LoggerFactory.getLogger(AltaRegistroController.class); // Logger para esta clase

    private Stage stage; // Necesario para controlar la ventana

    @FXML
    private TextArea textAreaDetalleRegistro;

    @FXML
    private TextField textFieldHoras;

    @FXML
    private ComboBox<LocalDate> comboBoxFechasDisponibles;

    private ApiService apiService = new ApiService(); // Instancia del servicio de API

    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

    // Método para inicializar el Stage, pasarlo al controlador
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        // Obtén el usuario logueado desde el AppController
        UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");
        fechaDesde = (LocalDate) getParam("fechaDesde");
        fechaHasta = (LocalDate) getParam("fechaHasta");
        if (usuario != null) {
            // Obtén las fechas disponibles para el alumno
            List<FechaPractica> fechasDisponibles = apiService.obtenerFechasDisponibles(usuario.getId(),
                    usuario.getUsuarioAsociado().getAnioCurso(), usuario.getUsuarioAsociado().getEvaluacion(),
                    fechaDesde, fechaHasta);

            // Configura el ComboBox con solo las fechas
            if (fechasDisponibles != null && !fechasDisponibles.isEmpty()) {
                List<LocalDate> fechas = fechasDisponibles.stream().map(FechaPractica::getFecha) // Extrae solo la fecha
                        .toList();

                comboBoxFechasDisponibles.getItems().addAll(fechas);
                comboBoxFechasDisponibles.setValue(fechas.get(0)); // Selecciona la primera fecha por defecto
                log.info("Fechas disponibles cargadas: {}", fechas); // Log de fechas disponibles
            } else {
                mostrarAlerta("Error", "No hay fechas disponibles para registrar horas.");
                log.warn("No hay fechas disponibles para el usuario: {}", usuario.getId()); // Log de advertencia
            }
        } else {
            mostrarAlerta("Error", "No se ha iniciado sesión.");
            log.warn("Intento de inicialización sin usuario logueado."); // Log de advertencia
        }
    }

    @FXML
    void guardarRegistro(ActionEvent event) {
        // Obtén la fecha seleccionada en el ComboBox
        LocalDate fechaSeleccionada = comboBoxFechasDisponibles.getValue();
        if (fechaSeleccionada == null) {
            mostrarAlerta("Error", "Debes seleccionar una fecha.");
            log.warn("Intento de guardar registro sin fecha seleccionada."); // Log de advertencia
            return;
        }

        // Obtén el usuario logueado
        UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");
        if (usuario == null) {
            mostrarAlerta("Error", "No se ha iniciado sesión.");
            log.warn("Intento de guardar registro sin usuario logueado."); // Log de advertencia
            return;
        }

        // Buscar el objeto FechaPractica correspondiente a la fecha seleccionada
        FechaPractica fechaPracticaSeleccionada = apiService
                .obtenerFechasDisponibles(usuario.getId(), usuario.getUsuarioAsociado().getAnioCurso(),
                        usuario.getUsuarioAsociado().getEvaluacion(), fechaDesde, fechaHasta)
                .stream().filter(fp -> fp.getFecha().equals(fechaSeleccionada)).findFirst().orElse(null);

        if (fechaPracticaSeleccionada == null) {
            mostrarAlerta("Error", "No se encontró la fecha seleccionada.");
            log.warn("No se encontró la fecha seleccionada: {}", fechaSeleccionada); // Log de advertencia
            return;
        }

        // Obtener y validar la cantidad de horas
        String horasTexto = textFieldHoras.getText();
        if (horasTexto.isEmpty()) {
            mostrarAlerta("Error", "Debes ingresar la cantidad de horas.");
            log.warn("Intento de guardar registro sin horas ingresadas."); // Log de advertencia
            return;
        }

        double horas;
        try {
            horas = Double.parseDouble(horasTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Las horas deben ser un número decimal.");
            log.error("Error al convertir horas: {}", horasTexto, e); // Log de error
            return;
        }

        if (horas <= 0 || horas > 8 || (horas * 2) % 1 != 0) {
            mostrarAlerta("Error",
                    "Las horas deben ser un número decimal con saltos de 0.5, mayor a 0 y menor o igual a 8.");
            log.warn("Horas ingresadas fuera de rango o formato incorrecto: {}", horas); // Log de advertencia
            return;
        }

        // Obtener y validar el detalle
        String detalle = textAreaDetalleRegistro.getText();
        if (detalle.isEmpty()) {
            mostrarAlerta("Error", "Debes ingresar un detalle.");
            log.warn("Intento de guardar registro sin detalle ingresado."); // Log de advertencia
            return;
        }

        // Crear el objeto RegistroPractica con la fecha correcta
        RegistroPractica registro = new RegistroPractica();
        registro.setFecha(fechaPracticaSeleccionada); // Se asigna la fechaPractica correcta
        registro.setCantidadHoras(horas);
        registro.setDescripcion(detalle);
        registro.setAlumno(usuario.getUsuarioAsociado());

        // Llamar al servicio de la API para guardar el registro
        String resultado = apiService.crearRegistro(registro);

        if (resultado != null && resultado.contains("exitosamente")) {
            mostrarMensajeExito("Registro guardado correctamente.");
            log.info("Registro guardado exitosamente: {}", registro); // Log de éxito
            changeScene(FXML_CONSULTARREGISTRO); // Vuelve a la pantalla de consulta
        } else {
            mostrarAlerta("Error", "No se pudo guardar el registro.");
            log.error("Error al guardar el registro: {}", registro); // Log de error
        }
    }

    @FXML
    void irMenu(ActionEvent event) {
        changeScene(FXML_CONSULTARREGISTRO);
        log.debug("Navegando al menú de consulta de registros."); // Log de navegación
    }

    @FXML
    void minimizar(ActionEvent event) {
        if (stage != null) {
            stage.setIconified(true); // Minimiza la ventana
            log.debug("Ventana minimizada."); // Log de acción
        }
    }

    // Método para cerrar la ventana
    @FXML
    void cerrar(ActionEvent event) {
        if (stage != null) {
            stage.close(); // Cierra la ventana
            log.debug("Ventana cerrada."); // Log de acción
        }
    }

}
