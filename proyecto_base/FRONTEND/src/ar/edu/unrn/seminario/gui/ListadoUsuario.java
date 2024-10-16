package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;


import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.modelo.Rol;

public class ListadoUsuario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo;
	IApi api;
	JButton activarButton;
	JButton desactivarButton;
	JButton modificarButton;
	JButton darDeBajaButton;
	/**
	 * Create the frame.
	 */
	public ListadoUsuario(IApi api) {
		this.api = api;
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		String[] titulos = { "USUARIO", "NOMBRE", "EMAIL", "ESTADO", "ROL" };

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		// Obtiene la lista de usuarios a mostrar
		List<UsuarioDTO> usuarios = api.obtenerUsuarios();
		// Agrega los usuarios en el model
		for (UsuarioDTO u : usuarios) {
			modelo.addRow(new Object[] { u.getUsername(), u.getNombre(), u.getEmail(), u.getEstado(), u.getRol() });
		}

		table.setModel(modelo);

		scrollPane.setViewportView(table);

		activarButton = new JButton("Activar");
		activarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcionSeleccionada = JOptionPane.showConfirmDialog(null,
						"Estas seguro que queres cambiar el estado del Usuario?", "Confirmar cambio de estado.",
						JOptionPane.YES_NO_OPTION);
				if (opcionSeleccionada == JOptionPane.YES_OPTION) {
					String username = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);

					api.activarUsuario(username);
					actualizarTabla();
				}
			}

		});

		desactivarButton = new JButton("Desactivar");
		desactivarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null,
						"¿Estas seguro que queres cambiar el estado del Usuario?", "Confirmar cambio de estado.",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					String username = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
				
					api.desactivarUsuario(username);
					actualizarTabla();

				}
			}
		});

		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		//-------- ultimas modificaciones --------------
		modificarButton = new JButton("Modificar");
		
		modificarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea modificar este usuario?", "Confirmar modificacion.",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
		            int selectedRow = table.getSelectedRow(); 

		            if (selectedRow == -1) { //todavía no se selecciono usuario
		                JOptionPane.showMessageDialog(null, "Seleccione un usuario a modificar.");
		                return;
		            }
		            
		            // Obtener los valores de la fila seleccionada
		            String username = (String) table.getModel().getValueAt(selectedRow, 0); // Columna 0 : nombre usuario  
		            String nombre = (String) table.getModel().getValueAt(selectedRow, 1);   // Columna 1: nombre
		            String email = (String) table.getModel().getValueAt(selectedRow, 2);    // Columna 2: email
		            //String contrasena = (String) table.getModel().getValueAt(selectedRow, 3); // Columna 3: contraseña
		            
		            //campos de texto 
		            JTextField nombreField = new JTextField(nombre);
		            JTextField emailField = new JTextField(email);
		            String[] rolesDisponibles = {"ADMI", "ESTUDIANTE", "INVITADO"};
		            JComboBox<String> comboBoxRol = new JComboBox<>(rolesDisponibles);
		            
		            
		            //campos de texto
		            JPanel panel = new JPanel(new GridLayout(0, 1));
		            panel.add(new JLabel("Nombre:"));
		            panel.add(nombreField);
		            panel.add(new JLabel("Email:"));
		            panel.add(emailField);
		            panel.add(new JLabel("Rol:"));
		            panel.add(comboBoxRol);

		            int reply1 = JOptionPane.showConfirmDialog(null, panel, "Modificar Usuario",
		                    JOptionPane.OK_CANCEL_OPTION); //elige si aceptar la opcion o no
		            
		            if (reply1 == JOptionPane.OK_OPTION) { //si se acepta 
		                String nombreActual = nombreField.getText().trim();
		                String emailActual = emailField.getText().trim();
		                Rol rol = null;
		                String rolSeleccionadoStr = (String) comboBoxRol.getSelectedItem();
		                
		            // Dialogo para que el usuario seleccione el rol
		                if ("ADMIN".equals(rolSeleccionadoStr)) {
		                    rol = new Rol(1, "ADMIN"); 
		                } else if ("ESTUDIANTE".equals(rolSeleccionadoStr)) {
		                    rol = new Rol(2, "ESTUDIANTE"); 
		                } else if ("INVITADO".equals(rolSeleccionadoStr)) {
		                    rol = new Rol(3, "INVITADO"); 
		                }
		                
		            // USO DE EXCEPTION (en caso de que esten vacios)
		            if (username.isEmpty() || nombre.isEmpty() || email.isEmpty() ||  rol == null ) { //  contrasena.isEmpty())
		                JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
		                return;
		            }

		            try {
		                // Llamada a la API
		                api.modificarUsuario(username, nombre, email, rol.getCodigo()); //me falta el metodo modificarUsuario en api
		                actualizarTabla();  
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "Error al querer modificar el usuario: " + ex.getMessage());
	                }
		            }
		        }
		    } 
		});
			
		
		darDeBajaButton = new JButton("Dar de baja");
		darDeBajaButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		      // Verificar que hay una fila seleccionada
		      int filaSeleccionada = table.getSelectedRow(); 
		      if (filaSeleccionada == -1) {
		         JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario primero.");
		         return;
		      }

		      // En caso de confirmar la accion..
		      int reply = JOptionPane.showConfirmDialog(null,
		            "¿Está seguro que desea dar de baja este usuario?", "Confirmar baja.",
		            JOptionPane.YES_NO_OPTION);
		      //Llamada a la Api
		      if (reply == JOptionPane.YES_OPTION) { 
		         String username = (String) table.getModel().getValueAt(filaSeleccionada, 0);
		         api.darDeBajaUsuario(username); 
		         actualizarTabla(); 
		      }
		   }
		});

		
		contentPane.add(cerrarButton, BorderLayout.SOUTH);

		JPanel pnlBotonesOperaciones = new JPanel(new FlowLayout());
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);

		pnlBotonesOperaciones.add(desactivarButton);
		pnlBotonesOperaciones.add(activarButton);
		pnlBotonesOperaciones.add(modificarButton); 
		pnlBotonesOperaciones.add(darDeBajaButton); 
		pnlBotonesOperaciones.add(cerrarButton); 

		
		// Deshabilitar botones que requieren tener una fila seleccionada
		habilitarBotones(false);
	}

	private void habilitarBotones(boolean b) {
		activarButton.setEnabled(b);
		desactivarButton.setEnabled(b);
		modificarButton.setEnabled(b); 
		darDeBajaButton.setEnabled(b); 

	}

	private void actualizarTabla() {
		// Obtiene el model del table
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		// Obtiene la lista de usuarios a mostrar
		List<UsuarioDTO> usuarios = api.obtenerUsuarios();
		// Resetea el model
		modelo.setRowCount(0);

		// Agrega los usuarios en el model
		for (UsuarioDTO u : usuarios) {
			modelo.addRow(new Object[] { u.getUsername(), u.getNombre(), u.getEmail(), u.getEstado(), u.getRol() });
		}

	}

}
