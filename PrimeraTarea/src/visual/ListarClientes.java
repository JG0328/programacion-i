package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Puerto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;
	private static Puerto puerto;
	private JButton btnModificar;
	private JButton btnEliminar;
	private String cedula;

	/**
	 * Create the dialog.
	 */
	public ListarClientes(Puerto puerto) {
		setResizable(false);
		ListarClientes.puerto = puerto;
		setTitle("Listado de Clientes");
		setBounds(100, 100, 1000, 700);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {

							if (table.getSelectedRow() >= 0) {
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);

								int index = table.getSelectedRow();
								cedula = table.getModel().getValueAt(index, 0).toString();
							}
						}
					});
					table.setDefaultEditor(Object.class, null);

					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					String[] columnas = { "Cédula", "Nombre", "Apellidos", "Teléfono", "Dirección", "Barco" };
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnas);
					table.setModel(model);

					cargarTabla();

					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Cliente cliente = puerto.buscarCliente(cedula);

						RegistroCliente regCliente = new RegistroCliente(puerto, cliente);
						regCliente.setModal(true);
						regCliente.setVisible(true);

						btnEliminar.setEnabled(false);
						btnModificar.setEnabled(false);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Cliente cliente = puerto.buscarCliente(cedula);

							int option = JOptionPane.showConfirmDialog(null,
									"¿Estás seguro de que desea eliminar este cliente?\n" + cliente.getCedula() + " - "
											+ cliente.getNombre() + " " + cliente.getApellidos(),
									"Aviso", JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.OK_OPTION) {
								puerto.eliminarCliente(cliente);
								puerto.eliminarClienteEnAlquiler(cliente);
								cargarTabla();
								btnEliminar.setEnabled(false);
								btnModificar.setEnabled(false);
							}
						}
					});
					btnEliminar.setEnabled(false);
					buttonPane.add(btnEliminar);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void cargarTabla() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (int i = 0; i < puerto.getCantClientes(); i++) {
			fila[0] = puerto.getClientes()[i].getCedula();
			fila[1] = puerto.getClientes()[i].getNombre();
			fila[2] = puerto.getClientes()[i].getApellidos();
			fila[3] = puerto.getClientes()[i].getTelefono();
			fila[4] = puerto.getClientes()[i].getDireccion();
			fila[5] = (puerto.getClientes()[i].getBarco() == null) ? (new String("Barco eliminado"))
					: (new String(puerto.getClientes()[i].getBarco().getMatricula() + " - "
							+ puerto.getClientes()[i].getBarco().getNombre()));
			model.addRow(fila);
		}
	}

}
