package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaAula {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaAula window = new AltaAula();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AltaAula() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(206, 63, 69, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Número de aula");
		lblNewLabel.setBounds(84, 66, 112, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEdificio = new JLabel("Edificio");
		lblEdificio.setBounds(84, 33, 112, 13);
		frame.getContentPane().add(lblEdificio);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(84, 99, 112, 13);
		frame.getContentPane().add(lblCapacidad);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(206, 96, 69, 19);
		frame.getContentPane().add(textField_2);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Principal", "Kinesiología"}));
		comboBox.setBounds(204, 32, 134, 21);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(111, 232, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(253, 232, 85, 21);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblRecurso = new JLabel("Recurso");
		lblRecurso.setBounds(84, 133, 112, 13);
		frame.getContentPane().add(lblRecurso);
		
		JButton btnAadirRecursos = new JButton("Añadir Recursos");
		btnAadirRecursos.setBounds(205, 129, 133, 21);
		frame.getContentPane().add(btnAadirRecursos);
	}
}
