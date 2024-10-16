package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.AulaDTO;
import ar.edu.unrn.seminario.modelo.Recurso;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class ListadoAula {

	private JFrame frame;
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnSalir; 
	private JTable table;
	private JPanel contentPane;
	DefaultTableModel modelo;
	IApi api;
	/**
	 * Create the application.
	 */
	public ListadoAula(IApi api) {
		frame = new JFrame("Listado de aula");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible (true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane (contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		String[] titulos = { "NUMERO", "EDIFICIO","CAPACIDAD","RECURSOS"};

		table.addMouseListener(new MouseAdapter() { // esto seria que cuando hago click en un campo activo los botones
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		// Obtiene la lista de usuarios a mostrar
		List<AulaDTO> aulas = api.obtenerTodasLasAulasDTO();//esta en la api??
		// Agrega los edificios en el model
		for (AulaDTO a :aulas) {
				modelo.addRow(new Object[] { a.getNumeroAula(), a.getCapacidad(), a.getEdificio(),a.getListaRecurso() });
			}

		table.setModel(modelo);

		scrollPane.setViewportView(table);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
					int opcionSeleccionada = JOptionPane.showConfirmDialog (null,
							"Estas seguro que desea modificar el edificio?","Confirmación", JOptionPane.YES_NO_OPTION);
					if (opcionSeleccionada == JOptionPane.YES_OPTION) {
						
						int numeroAula = (int) table.getModel().getValueAt (table.getSelectedRow(), 0);
						int numeroNuevo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo número de aula:")); // Solicitar nuevo número
						String nombreEdificio =(String)table.getModel().getValueAt(table.getSelectedRow(), 0);
						ArrayList <Recurso> listaRecursos =(ArrayList <Recurso>) table.getModel().getValueAt(table.getSelectedRow(), 0);//evaluar esto porque esta como una lista enrealidad

						api.modificarAula(nombreEdificio,numeroAula, numeroNuevo,listaRecursos );//esto tiene que estar en la API
						actualizarTabla();

						}

					}

				});	
		
		
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int filaSeleccionada = table.getSelectedRow();
			        if (filaSeleccionada == -1) {
			            JOptionPane.showMessageDialog(null, "Seleccione un edificio para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        // Pide confirmación para eliminar el edificio
			        int opcionSeleccionada = JOptionPane.showConfirmDialog(null, 
			            "¿Está seguro que desea eliminar el edificio?", 
			            "Confirmación", JOptionPane.YES_NO_OPTION);

			        if (opcionSeleccionada == JOptionPane.YES_OPTION) {
			            // Obtiene el nombre del edificio desde la tabla
			            int numeroAula = (int) table.getModel().getValueAt(filaSeleccionada, 0);
			            String nombreEdificio =(String)table.getModel().getValueAt(filaSeleccionada, 0);

			            // Llama al método de la API para eliminar el edificio
			            api.bajaDeAula(nombreEdificio, numeroAula); 

			            // Actualiza la tabla para reflejar los cambios
			            actualizarTabla();

			            // Muestra un mensaje confirmando la eliminación
			            JOptionPane.showMessageDialog(null, "El edificio ha sido eliminado correctamente.");
			        }
			    }
			});
					
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.dispose(); // Cierra la ventana
			}	

		});	
		
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		pnlBotonesOperaciones.add(btnModificar);
		pnlBotonesOperaciones.add(btnEliminar);
		pnlBotonesOperaciones.add(btnSalir);

		// Deshabilitar botones que requieren tener una fila seleccionada
		habilitarBotones(false) ;}
	

	private void habilitarBotones(boolean b) {
		btnModificar.setEnabled(b);
		btnEliminar.setEnabled(b);

	}

	private void actualizarTabla() {
		// Obtiene el model del table
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		// Obtiene la lista de usuarios a mostrar
		List<AulaDTO> aulas = api.obtenerTodasLasAulasDTO();
		// Resetea el model
		modelo.setRowCount(0);

		// Agrega los usuarios en el model
		for (AulaDTO a : aulas) {
			modelo.addRow(new Object[] { a.getNumeroAula(), a.getCapacidad(), a.getEdificio(),a.getListaRecurso() });
		}
		}




	}

