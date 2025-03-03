package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.apiService.ApiService;
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
    	String nombreUsuario = (String) getParam("nombre");
        labelNombreUsuario.setText(nombreUsuario);
        labelNombreALumno.setText(usuario.getUsuarioAsociado().getNombreCompleto());
       // labelPerfilUsuario.setText(usuario.getRol()); no esta el metodo
       // labelActivoUsuario.setText(usuario.getActivo() ? "Activo" : "Inactivo"); no esta el metodo

        // Datos específicos del alumno
        labelAñoCursoAlumno.setText(String.valueOf(usuario.getUsuarioAsociado().getAnioCurso()));
        labelCicloAlumno.setText(usuario.getUsuarioAsociado().getCiclo());
        labelEmpresaAsignadaALumno.setText(usuario.getUsuarioAsociado().getEmpresa().getNombreEmpresa() != null ? usuario.getUsuarioAsociado().getEmpresa().getNombreEmpresa() : "No asignado");
        labelTutorDocenteAlumno.setText(usuario.getUsuarioAsociado().getTutorDocente().getNombreCompleto() != null ? usuario.getUsuarioAsociado().getTutorDocente().getNombreCompleto() : "No asignado");
        labelEvaluacionAlumno.setText(usuario.getUsuarioAsociado().getEvaluacion() != null ? usuario.getUsuarioAsociado().getEvaluacion() : "No asignado");
       
    }


}
