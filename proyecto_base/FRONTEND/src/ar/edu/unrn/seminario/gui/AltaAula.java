package ar.edu.unrn.seminario.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.RolDTO;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

// Importo las librerias
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.EdificioDTO;
import ar.edu.unrn.seminario.dto.RecursoDTO;

// Creo la clase que representa la carga de las aulas

public class AltaAula {

	private JFrame frame;	// Ventana principal
	private JTextField textFieldNumeroAula; // Campo de texto para el número de aula
	private JTextField textFieldCapacidad; //Campo de texto para la capacidad del aula
	private JTextField textFieldNombreRecurso; //Campo de texto para el nombre del recurso
	private JTextArea textAreaRecurso; //TextArea para la descripcion del recurso
	private JComboBox edificioComboBox; // ComboBox para el nombre de los edificios
	private JButton btnAceptar; // Boton aceptar
	private JButton btnCancelar; // Boton cancelar
	
	private List<String> nombreRecursos = new ArrayList<>(); //Lista de nombres de Recursos
	private List<String> descripcionRecursos = new ArrayList<>(); //Lista de Recursos
	private List<EdificioDTO> edificios = new ArrayList<>(); // Lista de Edificios
	
	/**
	 * Create the application.
	 */
	public AltaAula(IApi api) {
		
		// Obtengo los edificios
		this.edificios = api.obtenerEdificiosDTO();
		
		// Centrar la ventana		
		
       
		// Creo la ventana principal
		frame = new JFrame("Alta de aula");		
		// Configuro las caracteristicas de la ventana principal
		frame.setBounds(100, 100, 450, 300);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Label para el número de aula
		JLabel lblNewLabel = new JLabel("Número de aula");
		lblNewLabel.setBounds(65, 44, 112, 13);
		frame.getContentPane().add(lblNewLabel);
		
		// Campo de texto para el número de aula
		textFieldNumeroAula = new JTextField();
		textFieldNumeroAula.setBounds(204, 41, 134, 19);
		frame.getContentPane().add(textFieldNumeroAula);
		textFieldNumeroAula.setColumns(10);
		
		// Label para Edificio
		
		JLabel lblEdificio = new JLabel("Edificio");
		lblEdificio.setBounds(65, 14, 112, 13);
		frame.getContentPane().add(lblEdificio);
		
		// Combo Box para edificio
		edificioComboBox = new JComboBox();
		edificioComboBox.setBounds(204, 10, 134, 21); 
		frame.getContentPane().add(edificioComboBox);
		
		for (EdificioDTO edificio : this.edificios) {
			edificioComboBox.addItem(edificio.getNombre());
			
		}
		
		// Label para capacidad
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(65, 77, 112, 13);
		frame.getContentPane().add(lblCapacidad);
		
		// Campo de texto para capacidad
		textFieldCapacidad = new JTextField();
		textFieldCapacidad.setColumns(10);
		textFieldCapacidad.setBounds(204, 74, 134, 19);
		frame.getContentPane().add(textFieldCapacidad);
		
		// Label para Recurso
		JLabel lblRecurso = new JLabel("Nombre del recurso");
		lblRecurso.setBounds(65, 111, 112, 13);
		frame.getContentPane().add(lblRecurso);	
		
		// Campo de texto para el nombre del recurso
		textFieldNombreRecurso = new JTextField();
		textFieldNombreRecurso.setColumns(10);
		textFieldNombreRecurso.setBounds(206, 108, 132, 19);
		frame.getContentPane().add(textFieldNombreRecurso);
		
		// Label para descripcion del recurso
		JLabel lblDescripcinDelRecurso = new JLabel("Descripción del recurso");
		lblDescripcinDelRecurso.setBounds(65, 155, 112, 13);
		frame.getContentPane().add(lblDescripcinDelRecurso);
		
		// Text Area para descripcion del recurso
		textAreaRecurso = new JTextArea();
		textAreaRecurso.setBounds(206, 137, 132, 43);
		frame.getContentPane().add(textAreaRecurso);
		
		// Scroll Bar para Recurso
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(340, 137, 17, 43);
		frame.getContentPane().add(scrollBar);
		
		// Boton para añadir Recursos
		JButton btnAadirRecursos = new JButton("Añadir más recursos");
		btnAadirRecursos.setBounds(206, 201, 133, 21);
		frame.getContentPane().add(btnAadirRecursos);
		
		// Listener para el boton añadir más recursos
		btnAadirRecursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreDelRecurso = textFieldNombreRecurso.getText();
				String descripcionDelRecurso = textAreaRecurso.getText();
				
				nombreRecursos.add(nombreDelRecurso);
				descripcionRecursos.add(descripcionDelRecurso);
				
						
				textFieldNombreRecurso.setText("");
				textAreaRecurso.setText("");
				
				JOptionPane.showMessageDialog(null, 
						"El recurso " + textFieldNombreRecurso.getText() + " ha sido añadido", 
						"Mensaje", 
						JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		// Creación del botón aceptar y del listener
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				
				EdificioDTO unEdificio = edificios.get(edificioComboBox.getSelectedIndex());
				int numAula = Integer.parseInt(textFieldNumeroAula.getText());
				int capacAula = Integer.parseInt((textFieldCapacidad.getText()));
				api.cargarAula(nombreRecursos, descripcionRecursos,  unEdificio.getNombre(),numAula, capacAula);
				JOptionPane.showMessageDialog(null, 
						"Aula ingresada con exito!", 
						"Información", 
						JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				frame.dispose();
				
			}
		});
		btnAceptar.setBounds(111, 232, 85, 21);
		frame.getContentPane().add(btnAceptar);
		
		// Creación del boton cancelar y del listener
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancelar.setBounds(253, 232, 85, 21);
		frame.getContentPane().add(btnCancelar);
		
	
		frame.setVisible(true);
		
	}
	


	
}
