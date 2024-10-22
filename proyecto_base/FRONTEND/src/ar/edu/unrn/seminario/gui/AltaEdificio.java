package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import ar.edu.unrn.seminario.exception.DataEmptyException;


import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.EdificioDTO;
//import ar.edu.unrn.seminario.dto.RecursoDTO;
//import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.RolDTO;

public class AltaEdificio {
	private JFrame frame;	// Ventana principal
	private JTextField textFieldNombreEdificio;// aca quizas conviene usar un jcomboBox
	private JTextField textFieldDireccionEdificio;
	private JButton btnAceptar; 
	private JButton btnCancelar; 
	//private JComboBox<String> edificioComboBox;
	private JComboBox edificioComboBox;
	
	private List<EdificioDTO> edificios = new ArrayList<>();
	/**
	 * Create the application.
	 */
	public AltaEdificio(IApi api) {
		
		this.edificios = api.obtenerEdificiosDTO();
		
		frame = new JFrame("Alta de edificio");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        
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
        
     
 	 
        
		
		btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(111, 160, 85, 30);
        frame.getContentPane().add(btnAceptar);
	      
	        btnAceptar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String nombre = textFieldNombreEdificio.getText();
	                String direccion = textFieldDireccionEdificio.getText();
	                
	                if (nombre.isEmpty() || direccion.isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Ambos campos deben estar llenos", "Error de validación", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                
	                try {
	                    // Intenta cargar el edificio a través de la API
	                    api.cargarEdificio(nombre, direccion);
	                    JOptionPane.showMessageDialog(null, "Edificio cargado con éxito");
	                    edificios = api.obtenerEdificiosDTO();  // Refrescar la lista con la API
	                    actualizarComboBox(); // Método que actualizará
	                  

	                    frame.setVisible(false);
	                    frame.dispose();
	                } catch (DataEmptyException ex) {
	                   
	                    JOptionPane.showMessageDialog(null, "Error: No se encontraron datos", "Error", JOptionPane.ERROR_MESSAGE);
	                } catch (Exception ex) {
	                    
	                    JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                } finally {
	                    
	                    System.out.println("Operación de carga de edificio completada.");
	                }
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
	          
	         edificioComboBox = new JComboBox();
	         edificioComboBox.setBounds(148, 151, 160, 22);
	         frame.getContentPane().add(edificioComboBox);

	         for (RolDTO rol : this.edificios) {
	        	 	edificioComboBox.addItem(rol.getNombre());
	         }
	}
}

	

