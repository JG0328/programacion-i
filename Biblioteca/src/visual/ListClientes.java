package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Biblioteca;
import logico.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ListClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public ListClientes() {
		setResizable(false);
		setTitle("Listado de Clientes");
		setBounds(100, 100, 800, 500);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 764, 406);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.setDefaultEditor(Object.class, null);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					String[] columns = { "Cédula", "Nombre", "Dirección" };
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columns);

					loadTable();
					
					table.setModel(model);

					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void loadTable() {
		Biblioteca biblio = Biblioteca.getInstance();

		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (Cliente client : biblio.getMisClientes()) {
			fila[0] = client.getCedula();
			fila[1] = client.getNombre();
			fila[2] = client.getDireccion();
			
			model.addRow(fila);
		}

	}

}
