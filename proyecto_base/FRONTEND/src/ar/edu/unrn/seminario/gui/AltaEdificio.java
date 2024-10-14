package ar.edu.unrn.seminario.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.EdificioDTO;
import ar.edu.unrn.seminario.dto.RecursoDTO;

public class AltaEdificio {
	private JFrame frame;	// Ventana principal
	private JTextField textFieldNombreEdificio;// aca quizas conviene usar un jcomboBox
	private JTextField textFieldNumeroDeAula;
	private JTextField textFieldCpacidadDeAula;
	private JButton Aceptar; 
	private JButton Cancelar; 
	private JButton Modificar;
	
	private List<AulaDTO> recursos = new ArrayList<>(); //Lista de Recursos
	
	
	/**
	 * Create the application.
	 */
	public AltaEdificio(IApi api) {
		frame = new JFrame("Alta de edificio");
		
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblNombreEdificio = new JLabel("Nombre del edificio");
        lblNombreEdificio.setBounds(50, 30, 130, 20);
}
}