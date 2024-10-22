package ar.edu.unrn.seminario.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.exception.DataEmptyException;

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
	private JTextField txtNumeroAula; // Campo de texto para el número de aula
	private JTextField txtCapacidad; //Campo de texto para la capacidad del aula
	private JTextField txtNombreRecurso; //Campo de texto para el nombre del recurso
	private JTextArea txtDescripcionRecurso; //TextArea para la descripcion del recurso
	private JComboBox <String>  edificioComboBox; // ComboBox para obtener el nombre de los edificios
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
		txtNumeroAula = new JTextField();
		txtNumeroAula.setBounds(204, 41, 134, 19);
		frame.getContentPane().add(txtNumeroAula);
		txtNumeroAula.setColumns(10);
		
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
		txtCapacidad = new JTextField();
		txtCapacidad.setColumns(10);
		txtCapacidad.setBounds(204, 74, 134, 19);
		frame.getContentPane().add(txtCapacidad);
		
		// Label para Recurso
		JLabel lblRecurso = new JLabel("Nombre del recurso");
		lblRecurso.setBounds(65, 111, 112, 13);
		frame.getContentPane().add(lblRecurso);	
		
		// Campo de texto para el nombre del recurso
		txtNombreRecurso = new JTextField();
		txtNombreRecurso.setColumns(10);
		txtNombreRecurso.setBounds(206, 108, 132, 19);
		frame.getContentPane().add(txtNombreRecurso);
		
		// Label para descripcion del recurso
		JLabel lblDescripcinDelRecurso = new JLabel("Descripción del recurso");
		lblDescripcinDelRecurso.setBounds(65, 155, 112, 13);
		frame.getContentPane().add(lblDescripcinDelRecurso);
		
		// Text Area para descripcion del recurso
		txtDescripcionRecurso = new JTextArea();
		txtDescripcionRecurso.setBounds(206, 137, 132, 43);
		frame.getContentPane().add();
		
		// Scroll Bar para Recurso
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(340, 137, 17, 43);
		frame.getContentPane().add(scrollBar);
		
		// Boton para añadir Recursos
		JButton btnAadirRecursos = new JButton("Añadir recurso");
		btnAadirRecursos.setBounds(206, 201, 133, 21);
		frame.getContentPane().add(btnAadirRecursos);
		
		// Listener para el boton añadir más recursos
		btnAadirRecursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreDelRecurso = txtNombreRecurso.getText();
				String descripcionDelRecurso = txtDescripcionRecurso.getText();
				
				try {		
				nombreRecursos.add(nombreDelRecurso);
				descripcionRecursos.add(descripcionDelRecurso);
				
				txtNombreRecurso.setText("");
				txtDescripcionRecurso.setText("");
				
				JOptionPane.showMessageDialog(null, 
						"El recurso ha sido añadido con éxito", 
						"Información", 
						JOptionPane.INFORMATION_MESSAGE);
				
				}
				catch (DataEmptyException dataException) {
					JOptionPane.showMessageDialog(null, 
							"Por favor, complete todos los campos", 
							"Precaución", 
							JOptionPane.WARNING_MESSAGE);
				}				
				
				
			}
		});
		
		// Creación del botón aceptar y del listener
		btnAceptar = new JButton("Aceptar");
		
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {			
				
				EdificioDTO unEdificio = edificios.get(edificioComboBox.getSelectedIndex());
				
				try {
				int numAula = Integer.parseInt(txtNumeroAula.getText());
				int capacAula = Integer.parseInt((txtCapacidad.getText()));
				
				//
				
				api.cargarAula(nombreRecursos, descripcionRecursos,  unEdificio.getNombre(),numAula, capacAula);
				
				JOptionPane.showMessageDialog(null, 
						"Aula ingresada con exito!", 
						"Información", 
						JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				
				}
				
				catch (DataEmptyException dataException) {
					JOptionPane.showMessageDialog(null, 
							"Por favor, complete todos los campos", 
							"Precaución", 
							JOptionPane.WARNING_MESSAGE);
				}
				catch (NumberFormatException numberException) {
					
					JOptionPane.showMessageDialog(null, 
							"El dato ingresado en el número de aula o la capacidad debe ser un número", 
							"Información", 
							JOptionPane.WARNING_MESSAGE);
					
				}			
				
				
				
				
			}
		});
		btnAceptar.setBounds(111, 232, 85, 21);
		frame.getContentPane().add(btnAceptar);
		
		// Creación del boton cancelar y del listener
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		btnCancelar.setBounds(253, 232, 85, 21);
		frame.getContentPane().add(btnCancelar);
		
	
		frame.setVisible(true);
		
	}
	


	
}
