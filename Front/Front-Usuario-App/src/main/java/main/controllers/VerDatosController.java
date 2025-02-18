package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.apiService.ApiService;
import main.gui.AppController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.openapitools.client.model.UsuarioDTO;
import org.openapitools.client.model.RegistroPractica;

import java.util.List;

public class VerDatosController extends AppController {

    private Stage stage;  // Necesario para controlar la ventana

    @FXML
    private Label labelActivoUsuario;

    @FXML
    private Label labelAñoCursoAlumno;

    @FXML
    private Label labelCicloAlumno;

    @FXML
    private Label labelEmpresaAsignadaALumno;

    @FXML
    private Label labelEvaluacionAlumno;

    @FXML
    private Label labelNombreALumno;

    @FXML
    private Label labelNombreUsuario;

    @FXML
    private Label labelPerfilUsuario;

    @FXML
    private Label labelTutorDocenteAlumno;

    @FXML
    private Label labelTotalHoras;

    @FXML
    private Label labelHorasRealizadas;

    @FXML
    private Label labelPorcentajeHoras;

    @FXML
    private Label labelHorasPendientes;

    private ApiService apiService = new ApiService(); // Instancia del servicio de API

    // Método para inicializar el Stage, pasarlo al controlador
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void irMenu(ActionEvent event) {
        changeScene(FXML_MENU);
    }

    @FXML
    void irRegistroHoras(ActionEvent event) {
        changeScene(FXML_REGISTROHORAS);
    }

    @FXML
    void minimizar(ActionEvent event) {
        if (stage != null) {
            stage.setIconified(true);  // Minimiza la ventana
        }
    }

    // Método para cerrar la ventana
    @FXML
    void cerrar(ActionEvent event) {
        if (stage != null) {
            stage.close();  // Cierra la ventana
        }
    }

    @FXML
    public void initialize() {
        // Obtén el usuario logueado desde el AppController
        UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");

        if (usuario != null) {
            // Muestra los datos del alumno
            mostrarDatosAlumno(usuario);

            // Calcula y muestra el resumen de prácticas
            mostrarResumenPracticas(usuario.getId());
        } else {
            mostrarAlerta("Error", "No se ha iniciado sesión.");
        }
    }

    /**
     * Muestra los datos del alumno en los labels correspondientes.
     *
     * @param usuario El usuario logueado.
     */
    private void mostrarDatosAlumno(UsuarioDTO usuario) {
        labelNombreUsuario.setText(usuario.getNombreCompleto()); //debe ir el nombre de usuario);
        labelNombreALumno.setText(usuario.getNombreCompleto());
       // labelPerfilUsuario.setText(usuario.getRol()); no esta el metodo
       // labelActivoUsuario.setText(usuario.getActivo() ? "Activo" : "Inactivo"); no esta el metodo

        // Datos específicos del alumno
        labelAñoCursoAlumno.setText(String.valueOf(usuario.getAño()));
        labelCicloAlumno.setText(usuario.getCiclo());
        labelEmpresaAsignadaALumno.setText(usuario.getEmpresaAsignada() != null ? usuario.getEmpresaAsignada() : "No asignado");
        labelTutorDocenteAlumno.setText(usuario.getTutorDocente() != null ? usuario.getTutorDocente() : "No asignado");
        labelEvaluacionAlumno.setText(usuario.getEvaluacion() != null ? usuario.getEvaluacion() : "No asignado");
    }

    /**
     * Calcula y muestra el resumen de prácticas del alumno.
     *
     * @param idUsuario El ID del usuario.
     */
    private void mostrarResumenPracticas(Long idUsuario) {
        // Obtén los registros de prácticas del alumno
        List<RegistroPractica> registros = apiService.consultarDetalles(idUsuario, null, null);

        if (registros == null) {
            mostrarAlerta("Error", "No se pudieron cargar los registros de prácticas.");
            return;
        }

        // Calcula las horas totales, realizadas y pendientes
        double horasTotales = 350; // Supongamos que el total de horas requeridas es 350
        double horasRealizadas = 0;

        for (RegistroPractica registro : registros) {
            horasRealizadas += registro.getCantidadHoras();
        }

        double porcentajeHoras = (horasRealizadas / horasTotales) * 100;
        double horasPendientes = horasTotales - horasRealizadas;

        // Muestra el resumen en los labels
        /*
        labelTotalHoras.setText(String.format("%.2f horas", horasTotales));
        labelHorasRealizadas.setText(String.format("%.2f horas", horasRealizadas));
        labelPorcentajeHoras.setText(String.format("%.2f%%", porcentajeHoras));
        labelHorasPendientes.setText(String.format("%.2f horas", horasPendientes));
        */
    }

    /**
     * Muestra una alerta con el mensaje de error.
     *
     * @param titulo  El título de la alerta.
     * @param mensaje El mensaje de la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
