package ar.edu.unrn.seminario.gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;

public class VentanaPrincipal extends JFrame {

	//Todas las clases reciben la api. 
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IApi api = new MemoryApi();
					VentanaPrincipal frame = new VentanaPrincipal(api);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(IApi api) {
		
		setTitle("Reserva de aulas UNRN");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);		
		JMenu usuarioMenu = new JMenu("Usuarios");
		menuBar.add(usuarioMenu);

		JMenuItem altaUsuarioMenuItem = new JMenuItem("Alta");
		altaUsuarioMenuItem.addActionListener(new ActionListener () {
			
			public void actionPerformed(ActionEvent arg0) {
				AltaUsuario alta = new AltaUsuario(api);
				alta.setLocationRelativeTo(null);
				alta.setVisible(true);
			}
			
		});
		usuarioMenu.add(altaUsuarioMenuItem);

		JMenuItem listadoUsuarioMenuItem = new JMenuItem("Modificación / Listado");
		listadoUsuarioMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ListadoUsuario listadoDeUsuario= new ListadoUsuario(api);
				listadoDeUsuario.setLocationRelativeTo(null);
				listadoDeUsuario.setVisible(true);
			}			
		});
		usuarioMenu.add(listadoUsuarioMenuItem);
		
		// Agrego aula y sus opciones
		JMenu aulaMenu = new JMenu ("Aula");
		menuBar.add(aulaMenu);
		
		JMenuItem altaAulaMenuItem = new JMenuItem ("Alta");
		
		altaAulaMenuItem.addActionListener(new ActionListener () {
			
			public void actionPerformed (ActionEvent e) {
				AltaAula altaDeAula = new AltaAula (api);				
				
				
			}
		});	

		aulaMenu.add(altaAulaMenuItem);
		
		JMenuItem listadoAulaMenuItem = new JMenuItem ("Modificación / Listado");
		
		listadoAulaMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ListadoAula aulaListado= new ListadoAula(api);
							}
			
		});
		
		aulaMenu.add(listadoAulaMenuItem);
		
		// Agrego edificio y sus opciones
		
		
		JMenu edificioMenu = new JMenu ("Edificio");
		menuBar.add(edificioMenu);
				
		JMenuItem altaEdificioMenuItem = new JMenuItem ("Alta");
		altaEdificioMenuItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		AltaEdificio edificioAlta= new AltaEdificio(api);
		}
			
		});
			
		
		edificioMenu.add(altaEdificioMenuItem);
		
		JMenuItem listadoEdificioMenuItem = new JMenuItem ("Modificación / Listado");
		listadoEdificioMenuItem.addActionListener(new ActionListener () {
		public void actionPerformed(ActionEvent arg0) {
		ListadoEdificio edificioListado= new ListadoEdificio(api);
		}					
		});
		
		edificioMenu.add(listadoEdificioMenuItem);
		
		JMenu configuracionMenu = new JMenu("Configuración");
		menuBar.add(configuracionMenu);

		JMenuItem salirMenuItem = new JMenuItem("Salir");
		configuracionMenu.add(salirMenuItem);
		salirMenuItem.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e) {
				System.exit(0); // Cerrar la aplicación
	


	}
		});
	
		}
	
};
		

	


