package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.openapitools.client.model.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerDatosController extends AppController {
	private static final Logger log = LoggerFactory.getLogger(VerDatosController.class); // Logger para esta clase

	private Stage stage; // Necesario para controlar la ventana

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


	// Método para inicializar el Stage, pasarlo al controlador
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void irMenu(ActionEvent event) {
		changeScene(FXML_MENU);
		log.debug("Navegando al menú"); // Log de navegación
	}

	@FXML
	void irRegistroHoras(ActionEvent event) {
		changeScene(FXML_REGISTROHORAS);
		log.debug("Navegando a la pantalla de registro de horas"); // Log de navegación
	}

	@FXML
	void minimizar(ActionEvent event) {
		if (stage != null) {
			stage.setIconified(true); // Minimiza la ventana
			log.debug("Ventana minimizada"); // Log de la acción
		}
	}

	// Método para cerrar la ventana
	@FXML
	void cerrar(ActionEvent event) {
		if (stage != null) {
			stage.close(); // Cierra la ventana
			log.debug("Ventana cerrada"); // Log de la acción
		}
	}

	@FXML
	public void initialize() {
		// Obtén el usuario logueado desde el AppController
		UsuarioDTO usuario = (UsuarioDTO) getParam("usuario");

		if (usuario != null) {
			// Muestra los datos del alumno
			mostrarDatosAlumno(usuario);
			log.debug("Datos del alumno mostrados para el usuario: {}",
					usuario.getUsuarioAsociado().getNombreCompleto()); // Log de datos mostrados
		} else {
			mostrarAlerta("Error", "No se ha iniciado sesión.");
			log.warn("Intento de mostrar datos sin usuario logueado"); // Log de advertencia
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
		// labelPerfilUsuario.setText(usuario.getRol()); no está el método
		// labelActivoUsuario.setText(usuario.getActivo() ? "Activo" : "Inactivo"); no
		// está el método

		// Datos específicos del alumno
		labelAñoCursoAlumno.setText(String.valueOf(usuario.getUsuarioAsociado().getAnioCurso()));
		labelCicloAlumno.setText(usuario.getUsuarioAsociado().getCiclo());
		labelEmpresaAsignadaALumno.setText(usuario.getUsuarioAsociado().getEmpresa().getNombreEmpresa() != null
				? usuario.getUsuarioAsociado().getEmpresa().getNombreEmpresa()
				: "No asignado");
		labelTutorDocenteAlumno.setText(usuario.getUsuarioAsociado().getTutorDocente().getNombreCompleto() != null
				? usuario.getUsuarioAsociado().getTutorDocente().getNombreCompleto()
				: "No asignado");
		labelEvaluacionAlumno.setText(
				usuario.getUsuarioAsociado().getEvaluacion() != null ? usuario.getUsuarioAsociado().getEvaluacion()
						: "No asignado");

		log.debug("Datos del alumno: Año: {}, Ciclo: {}, Empresa: {}, Tutor: {}, Evaluación: {}",
				usuario.getUsuarioAsociado().getAnioCurso(), usuario.getUsuarioAsociado().getCiclo(),
				usuario.getUsuarioAsociado().getEmpresa().getNombreEmpresa(),
				usuario.getUsuarioAsociado().getTutorDocente().getNombreCompleto(),
				usuario.getUsuarioAsociado().getEvaluacion()); // Log de los datos del alumno
	}
}
