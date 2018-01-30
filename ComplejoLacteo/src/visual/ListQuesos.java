package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cilindrico;
import logico.Complejo;
import logico.Esferico;
import logico.Hueco;
import logico.Queso;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListQuesos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;
	
	private JComboBox cbxTipo;

	/**
	 * Create the dialog.
	 */
	public ListQuesos() {
		setTitle("Listado de Quesos");
		setResizable(false);
		setBounds(100, 100, 800, 500);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 35, 774, 392);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.setDefaultEditor(Object.class, null);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					loadTable(0);

					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(10, 11, 70, 14);
			contentPanel.add(lblTipo);
		}

		cbxTipo = new JComboBox();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(cbxTipo.getSelectedIndex());
				loadTable(cbxTipo.getSelectedIndex());
			}
		});
		cbxTipo.setModel(new DefaultComboBoxModel(
				new String[] { "<Todos>", "Esf\u00E9rico", "Cil\u00EDndrico", "Cil\u00EDndrico Hueco" }));
		cbxTipo.setBounds(71, 7, 713, 23);
		contentPanel.add(cbxTipo);
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

	private void loadTable(int cond) {
		Complejo comp = Complejo.getInstance();

		if (cond == 0) {
			String[] columns = { "ID", "Nombre", "Precio base", "Precio unitario", "Radio", "Volumen", "Precio total",
					"Tipo" };
			model = new DefaultTableModel();
			model.setRowCount(0);
			model.setColumnIdentifiers(columns);

			fila = new Object[model.getColumnCount()];

			for (Queso queso : comp.getMisQuesos()) {
				fila[0] = queso.getId();
				fila[1] = queso.getNombre();
				fila[2] = String.format("RD$ %.2f", queso.getPrecioBase());
				fila[3] = String.format("RD$ %.2f", queso.getPrecioUnitario());
				fila[4] = String.format("%.2f cm", queso.getRadio());
				fila[5] = String.format("%.2f cm^3", queso.volumen());
				fila[6] = String.format("RD$ %.2f", comp.precioQueso(queso));
				fila[7] = queso.getTipo();
				model.addRow(fila);
			}

			table.setModel(model);
		}
		if (cond == 1) {
			String[] columns = { "ID", "Nombre", "Precio base", "Precio unitario", "Radio", "Volumen", "Precio total" };
			model = new DefaultTableModel();
			model.setRowCount(0);
			model.setColumnIdentifiers(columns);

			fila = new Object[model.getColumnCount()];

			for (Queso queso : comp.getMisQuesos()) {
				if (queso instanceof Esferico) {
					fila[0] = queso.getId();
					fila[1] = queso.getNombre();
					fila[2] = String.format("RD$ %.2f", queso.getPrecioBase());
					fila[3] = String.format("RD$ %.2f", queso.getPrecioUnitario());
					fila[4] = String.format("%.2f cm", queso.getRadio());
					fila[5] = String.format("%.2f cm^3", queso.volumen());
					fila[6] = String.format("RD$ %.2f", comp.precioQueso(queso));
					model.addRow(fila);
				}
			}
			table.setModel(model);
		}
		if (cond == 2) {
			String[] columns = { "ID", "Nombre", "Precio base", "Precio unitario", "Radio", "Longitud", "Volumen",
					"Precio total" };
			model = new DefaultTableModel();
			model.setRowCount(0);
			model.setColumnIdentifiers(columns);

			fila = new Object[model.getColumnCount()];

			for (Queso queso : comp.getMisQuesos()) {
				if (queso instanceof Cilindrico) {
					fila[0] = queso.getId();
					fila[1] = queso.getNombre();
					fila[2] = String.format("RD$ %.2f", queso.getPrecioBase());
					fila[3] = String.format("RD$ %.2f", queso.getPrecioUnitario());
					fila[4] = String.format("%.2f cm", queso.getRadio());
					fila[5] = String.format("%.2f cm", ((Cilindrico) queso).getLongitud());
					fila[6] = String.format("%.2f cm^3", queso.volumen());
					fila[7] = String.format("RD$ %.2f", comp.precioQueso(queso));
					model.addRow(fila);
				}
			}
			table.setModel(model);
		}
		if (cond == 3) {
			String[] columns = { "ID", "Nombre", "Precio base", "Precio unitario", "Radio", "Radio interior",
					"Longitud", "Volumen", "Precio total" };
			model = new DefaultTableModel();
			model.setRowCount(0);
			model.setColumnIdentifiers(columns);

			fila = new Object[model.getColumnCount()];

			for (Queso queso : comp.getMisQuesos()) {
				if (queso instanceof Hueco) {
					fila[0] = queso.getId();
					fila[1] = queso.getNombre();
					fila[2] = String.format("RD$ %.2f", queso.getPrecioBase());
					fila[3] = String.format("RD$ %.2f", queso.getPrecioUnitario());
					fila[4] = String.format("%.2f cm", queso.getRadio());
					fila[5] = String.format("%.2f cm", ((Hueco) queso).getRadioInterior());
					fila[6] = String.format("%.2f cm", ((Hueco) queso).getLongitud());
					fila[7] = String.format("%.2f cm^3", queso.volumen());
					fila[8] = String.format("RD$ %.2f", comp.precioQueso(queso));
					model.addRow(fila);
				}
			}
			table.setModel(model);
		}
	}
}