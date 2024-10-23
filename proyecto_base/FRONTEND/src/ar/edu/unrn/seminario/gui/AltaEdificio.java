package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.EdificioDTO;
import ar.edu.unrn.seminario.exception.DataEmptyException;

public class AltaEdificio {
    private JPanel contentPane;
    private JFrame frame;  // Ventana principal
    private JTextField textFieldNombreEdificio; // Campo de texto para el nombre del edificio
    private JTextField textFieldDireccionEdificio; // Campo de texto para la dirección del edificio
    private JButton btnAceptar; 
    private JButton btnCancelar; 
    //private JComboBox<String> edificioComboBox; 
    private JTable tableEdificios;
    private DefaultTableModel tableModel;
    private List<EdificioDTO> edificios = new ArrayList<>();
    
    public AltaEdificio(IApi api) {
        this.edificios = api.obtenerEdificiosDTO();
        
        frame = new JFrame("Alta de edificio");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);
        
        String[] columnNames = {"Nombre", "Dirección"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        
        JLabel nombreEdificio = new JLabel("Nombre del edificio");
        nombreEdificio.setBounds(50, 30, 130, 20);
        contentPane.add(nombreEdificio);
        
        textFieldNombreEdificio = new JTextField();
        textFieldNombreEdificio.setBounds(200, 31, 180, 20);
        contentPane.add(textFieldNombreEdificio);
        
        JLabel direccionEdificioLabel = new JLabel("Dirección del edificio");
        direccionEdificioLabel.setBounds(50, 70, 130, 20);
        contentPane.add(direccionEdificioLabel);

        textFieldDireccionEdificio = new JTextField();
        textFieldDireccionEdificio.setBounds(200, 70, 180, 20);
        contentPane.add(textFieldDireccionEdificio);
        
        btnAceptar = new JButton("Aceptar");
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
                    actualizarTabla ();

                    frame.dispose(); // Cierra la ventana
                } catch (DataEmptyException ex) {
                    JOptionPane.showMessageDialog(null, "Error: No se encontraron datos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                   
                }
            }
        });
        btnAceptar.setBounds(111, 160, 85, 30);
        contentPane.add(btnAceptar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra la ventana
            }
        });
        btnCancelar.setBounds(290, 160, 100, 30);
        contentPane.add(btnCancelar);
        
        frame.setVisible(true);
    }
        // Crear y agregar el JComboBox
       // edificioComboBox = new JComboBox<>();
       // edificioComboBox.setBounds(200, 110, 180, 20);
      //  contentPane.add(edificioComboBox);
        
        //actualizarComboBox(); 
 
       //frame.setVisible(true); 
   // }

    //private void actualizarComboBox() {
        //edificioComboBox.removeAllItems(); 
       // for (EdificioDTO edificio : edificios) {
            //edificioComboBox.addItem(edificio.getNombre());
        //}
        
        private void actualizarTabla() {
        	if (tableModel != null) { 
        		tableModel.setRowCount(0); 
        	
            
            // Agregar los edificios al modelo de la tabla
        		for (EdificioDTO edificio : edificios) {
        			tableModel.addRow(new Object[]{edificio.getNombre(), edificio.getDireccion()});
            }
        	} else {
        		System.out.println("Error: tableModel no está inicializado");
        	}
    }
}



