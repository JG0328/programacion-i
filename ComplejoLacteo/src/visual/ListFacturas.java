package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Complejo;
import logico.Factura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ListFacturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;
	private JTextField txtId;

	/**
	 * Create the dialog.
	 */
	public ListFacturas() {
		setTitle("Listado de Facturas");
		setResizable(false);
		setBounds(100, 100, 800, 500);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 41, 774, 386);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.setDefaultEditor(Object.class, null);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					String[] columns = { "Fecha", "Cliente", "Quesos", "Total" };
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columns);

					loadTable("");
					table.setModel(model);

					scrollPane.setViewportView(table);
				}
			}
		}

		JLabel lblIdDelCliente = new JLabel("ID del cliente:");
		lblIdDelCliente.setBounds(10, 16, 100, 14);
		contentPanel.add(lblIdDelCliente);

		txtId = new JTextField();
		txtId.setBounds(120, 12, 526, 23);
		contentPanel.add(txtId);
		txtId.setColumns(10);

		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(txtId.getText());
			}
		});
		btnNewButton.setBounds(656, 12, 128, 23);
		contentPanel.add(btnNewButton);
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

	private void loadTable(String id) {
		// TODO Auto-generated method stub
		Complejo comp = Complejo.getInstance();
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		int i = 0;

		for (Factura factura : comp.getMisFacturas()) {
			if (id.equalsIgnoreCase("") || id.equalsIgnoreCase(factura.getCliente().getId())) {
				fila[0] = factura.getFecha();
				fila[1] = factura.getCliente().getId() + " - " + factura.getCliente().getNombre();
				fila[2] = new Integer(factura.getQuesos().size());
				fila[3] = String.format("RD$ %.2f", comp.precioFactura(factura.getQuesos()));

				model.addRow(fila);
			}
		}
	}
}
