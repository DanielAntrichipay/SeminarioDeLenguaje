package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;

import ar.edu.unrn.seminario.api.IApi;
import javax.swing.JFrame;

public class ListadoAula {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ListadoAula(IApi api) {
		frame = new JFrame("Listado de aula");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible (true);
	}

}
