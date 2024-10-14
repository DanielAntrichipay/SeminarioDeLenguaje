package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;

import ar.edu.unrn.seminario.api.IApi;
import javax.swing.JFrame;

public class ListadoEdifico {

	private JFrame frame;

// constructor
	
	public ListadoEdifico(IApi api) {
		frame = new JFrame("Listado de edificios");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible (true);
	}

}