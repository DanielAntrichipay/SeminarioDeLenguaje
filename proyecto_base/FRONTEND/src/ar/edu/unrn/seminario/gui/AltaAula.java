package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ar.edu.unrn.seminario.api.IApi;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class AltaAula {

	private JFrame frame;
	
	private JTextField textField;
	private JTextField textField_2;
	private JComboBox comboBox;
	private JTextField textField_1;
	

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
		// Creo una instancia de la clase frame
		frame = new JFrame("Alta de aula");
		
		
		// Configuro las caracteristicas del frame
		frame.setBounds(100, 100, 450, 300);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	
		
		
		textField = new JTextField();
		textField.setBounds(206, 41, 69, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Número de aula");
		lblNewLabel.setBounds(65, 44, 112, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEdificio = new JLabel("Edificio");
		lblEdificio.setBounds(65, 14, 112, 13);
		frame.getContentPane().add(lblEdificio);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(65, 77, 112, 13);
		frame.getContentPane().add(lblCapacidad);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(206, 74, 69, 19);
		frame.getContentPane().add(textField_2);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Principal", "Kinesiología"}));
		comboBox.setBounds(204, 10, 134, 21);
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
		
		JLabel lblRecurso = new JLabel("Nombre del recurso");
		lblRecurso.setBounds(65, 111, 112, 13);
		frame.getContentPane().add(lblRecurso);
		
		JButton btnAadirRecursos = new JButton("Añadir más recursos");
		btnAadirRecursos.setBounds(253, 201, 133, 21);
		frame.getContentPane().add(btnAadirRecursos);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(206, 108, 132, 19);
		frame.getContentPane().add(textField_1);
		
		JLabel lblDescripcinDelRecurso = new JLabel("Descripción del recurso");
		lblDescripcinDelRecurso.setBounds(65, 155, 112, 13);
		frame.getContentPane().add(lblDescripcinDelRecurso);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(206, 137, 132, 43);
		frame.getContentPane().add(textArea);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(340, 137, 17, 43);
		frame.getContentPane().add(scrollBar);
	}
}
