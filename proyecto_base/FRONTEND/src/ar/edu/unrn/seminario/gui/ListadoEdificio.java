package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.EdificioDTO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class ListadoEdificio {

    private JFrame frame;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnSalir;
    private JTable table;
    private JPanel contentPane;
    private DefaultTableModel modelo;
    private IApi api;

    public ListadoEdificio(IApi api) {
        this.api = api;

        // Inicialización del frame
        frame = new JFrame("Listado de edificios");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        frame.setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        String[] titulos = { "NOMBRE", "DIRECCION" };
        modelo = new DefaultTableModel(new Object[][] {}, titulos);

        // Llenar tabla con datos
        try {
            List<EdificioDTO> edificios = api.obtenerEdificiosDTO();
            for (EdificioDTO e : edificios) {
                modelo.addRow(new Object[] { e.getNombre(), e.getDireccion() });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los edificios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        table.setModel(modelo);
        scrollPane.setViewportView(table);

        // Listeners para eventos en la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                habilitarBotones(true);
            }
        });

        // Botones
        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar este edificio?", "Confirmar modificación.",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Seleccione un edificio a modificar.");
                        return;
                    }

                    String nombreDelEdificio = (String) table.getModel().getValueAt(selectedRow, 0);
                    String direccion = (String) table.getModel().getValueAt(selectedRow, 1);

                    JTextField nombreDelEdificioField = new JTextField(nombreDelEdificio);
                    JTextField direccionField = new JTextField(direccion);

                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Nombre:"));
                    panel.add(nombreDelEdificioField);
                    panel.add(new JLabel("Dirección:"));
                    panel.add(direccionField);

                    int result = JOptionPane.showConfirmDialog(null, panel, "Modificar edificio", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String nuevoNombre = nombreDelEdificioField.getText().trim();
                        String nuevaDireccion = direccionField.getText().trim();

                        if (nuevoNombre.isEmpty() || nuevaDireccion.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
                            return;
                        }

                        try {
                            api.actualizarEdificio(nombreDelEdificio, nuevoNombre, nuevaDireccion);
                            actualizarTabla();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al modificar el edificio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un edificio para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int option = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el edificio?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    String nombreDelEdificio = (String) table.getModel().getValueAt(selectedRow, 0);

                    try {
                        api.bajaDeEdificio(nombreDelEdificio);
                        JOptionPane.showMessageDialog(null, "El edificio ha sido eliminado correctamente.");
                        actualizarTabla();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el edificio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JPanel pnlBotonesOperaciones = new JPanel();
        pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
        pnlBotonesOperaciones.add(btnModificar);
        pnlBotonesOperaciones.add(btnEliminar);
        pnlBotonesOperaciones.add(btnSalir);

        // Deshabilitar botones inicialmente
        habilitarBotones(false);

        frame.setVisible(true);
    }

    private void habilitarBotones(boolean habilitar) {
        btnModificar.setEnabled(habilitar);
        btnEliminar.setEnabled(habilitar);
    }

    private void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0);

        try {
            List<EdificioDTO> edificios = api.obtenerEdificiosDTO();
            for (EdificioDTO e : edificios) {
                modelo.addRow(new Object[] { e.getNombre(), e.getDireccion() });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la tabla: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
