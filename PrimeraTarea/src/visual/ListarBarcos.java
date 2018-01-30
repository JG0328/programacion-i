package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import logico.Barco;
import logico.Puerto;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarBarcos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;
	private static Puerto puerto;
	private JButton btnEliminar;
	private JButton btnModificar;

	private String matricula;

	/**
	 * Create the dialog.
	 */
	public ListarBarcos(Puerto puerto) {
		setResizable(false);
		ListarBarcos.puerto = puerto;
		setTitle("Listado de Barcos");
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
					table.setDefaultEditor(Object.class, null);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (table.getSelectedRow() >= 0) {
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);

								int index = table.getSelectedRow();
								matricula = (String) table.getModel().getValueAt(index, 0);
							}
						}
					});

					String[] columnas = { "Matrícula", "Nombre", "Año", "Tipo", "Eslora" };
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
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Barco barco = puerto.buscarBarco(matricula);

						RegistroBarco regBarco = new RegistroBarco(puerto, barco);
						regBarco.setModal(true);
						regBarco.setVisible(true);
						
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
							Barco barco = puerto.buscarBarco(matricula);

							int option = JOptionPane
									.showConfirmDialog(null,
											"¿Está seguro de que desea eliminar este barco?\n" + barco.getMatricula()
													+ " - " + barco.getNombre(),
											"Aviso", JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.OK_OPTION) {
								puerto.eliminarBarco(barco);
								puerto.eliminarBarcoEnCliente(barco);
								puerto.eliminarBarcoEnAlquiler(barco);
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
		for (int i = 0; i < puerto.getCantBarcos(); i++) {
			fila[0] = puerto.getBarcos()[i].getMatricula();
			fila[1] = puerto.getBarcos()[i].getNombre();
			fila[2] = puerto.getBarcos()[i].getFabricacion();
			fila[3] = puerto.getBarcos()[i].getTipo();
			fila[4] = puerto.getBarcos()[i].getEslora();
			model.addRow(fila);
		}
	}

}
