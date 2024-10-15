package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.AbstractButton;


import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.EdificioDTO;
import ar.edu.unrn.seminario.dto.RecursoDTO;
import ar.edu.unrn.seminario.dto.RolDTO;

public class AltaEdificio {
	private JFrame frame;	// Ventana principal
	private JTextField textFieldNombreEdificio;// aca quizas conviene usar un jcomboBox
	private JTextField textFieldDireccionEdificio;
	private JButton btnAceptar; 
	private JButton btnCancelar; 
	//private JComboBox<String> edificioComboBox;
	
	
	/**
	 * Create the application.
	 */
	public AltaEdificio(IApi api) {
		frame = new JFrame("Alta de edificio");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel nombreEdificio = new JLabel("Nombre del edificio");
        nombreEdificio.setBounds(50, 30, 130, 20);
        frame.getContentPane().add(nombreEdificio);
        
        
        textFieldNombreEdificio = new JTextField();
        textFieldNombreEdificio.setBounds(200, 31, 180, 20);
        frame.getContentPane().add(textFieldNombreEdificio);
        
     // Etiqueta para la dirección del edificio
        JLabel direccionEdificioLabel = new JLabel("Dirección del edificio");
        direccionEdificioLabel.setBounds(50, 70, 130, 20);
        frame.getContentPane().add(direccionEdificioLabel);

        // Campo de texto para ingresar la dirección del edificio
        textFieldDireccionEdificio = new JTextField();
        textFieldDireccionEdificio.setBounds(200, 70, 180, 20);
        frame.getContentPane().add(textFieldDireccionEdificio);
        
        JLabel seleccionarEdificio = new JLabel("Seleccione el edificio");
        nombreEdificio.setBounds(50, 30, 130, 20);
        frame.getContentPane().add(nombreEdificio);
        
     
 	 // Combo Box para edificio
	/*	edificioComboBox = new JComboBox<String>(); 
		edificioComboBox.setBounds(141, 109, 134, 21); 
		frame.getContentPane().add(edificioComboBox);*/
        /*for (EdificioDTO edificio : this.edificios) {
			rolComboBox.addItem(edificio.getNombre()); // esta es la opcion para el menu desplegable
		}*/
        
		
		btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(111, 160, 85, 30);
        frame.getContentPane().add(btnAceptar);
	      
	        btnAceptar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String nombre = textFieldNombreEdificio.getText();
	                String direccion = textFieldDireccionEdificio.getText();
	                
	                api.cargarEdificio(nombre , direccion);// me da error, ver en la IApi como esta 
	                
					frame.setVisible(false);
					frame.dispose();
	            }
	        });
	
	                
	        // Botón para cancelar
	      btnCancelar = new JButton("Cancelar");
	      btnCancelar.setBounds(290, 160, 100, 30);
	      frame.getContentPane().add(btnCancelar);
	                
	                // Listener para cerrar la ventana
	      	btnCancelar.addActionListener(new ActionListener() {
	      		public void actionPerformed(ActionEvent e) {
	      				frame.dispose(); // Cierra la ventana
	      		}
	          });
	                
	             frame.setVisible(true);         
	          }
	  
	}
