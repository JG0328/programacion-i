package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Banco;
import logico.Corriente;
import logico.Cuenta;
import logico.Inversion;
import logico.Vivienda;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public class ListCuentas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Object[] fila;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public ListCuentas() {
		setTitle("Listado de Cuentas");
		setResizable(false);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 38, 774, 389);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.setDefaultEditor(Object.class, null);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);
					loadTable(0);
				}
			}
		}

		JLabel lblTipoDeCuenta = new JLabel("Tipo de Cuenta:");
		lblTipoDeCuenta.setBounds(10, 11, 105, 14);
		contentPanel.add(lblTipoDeCuenta);

		JComboBox cbxTipo = new JComboBox();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(cbxTipo.getSelectedIndex());
			}
		});
		cbxTipo.setModel(
				new DefaultComboBoxModel(new String[] { "<Todos>", "Corriente", "Vivienda", "Fondo Inversi\u00F3n" }));
		cbxTipo.setBounds(125, 7, 659, 23);
		contentPanel.add(cbxTipo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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

	private void loadTable(int index) {
		model = new DefaultTableModel();

		if (index == 0) {
			String[] columns = { "Código", "Saldo", "Meses", "Corte", "Estado", "Puntos", "Tipo" };
			model.setColumnIdentifiers(columns);
			model.setRowCount(0);
			fila = new Object[model.getColumnCount()];

			for (Cuenta cuenta : Banco.getInstance().getMisCuentas()) {
				fila[0] = cuenta.getCodigo();
				fila[1] = String.format("RD$ %.2f", cuenta.getSaldo());
				fila[2] = cuenta.getMeses();
				fila[3] = cuenta.getCorte();
				fila[4] = cuenta.getEstado();
				fila[5] = cuenta.getPuntos();
				fila[6] = cuenta.getTipo();

				model.addRow(fila);
			}

			table.setModel(model);
		}
		if (index == 1) {
			String[] columns = { "Código", "Saldo", "Retiro Máximo", "Meses", "Corte", "Estado", "Puntos", "Tipo" };
			model.setColumnIdentifiers(columns);
			model.setRowCount(0);
			fila = new Object[model.getColumnCount()];

			for (Cuenta cuenta : Banco.getInstance().getMisCuentas()) {
				if (cuenta instanceof Corriente) {
					fila[0] = cuenta.getCodigo();
					fila[1] = String.format("RD$ %.2f", cuenta.getSaldo());
					fila[2] = String.format("RD$ %.2f", ((Corriente) cuenta).getRetiroMax());
					fila[3] = cuenta.getMeses();
					fila[4] = cuenta.getCorte();
					fila[5] = cuenta.getEstado();
					fila[6] = cuenta.getPuntos();
					fila[7] = cuenta.getTipo();

					model.addRow(fila);
				}
			}

			table.setModel(model);
		}
		if (index == 2) {
			String[] columns = { "Código", "Saldo", "Tiempo de ahorro", "Monto mensual", "Meses", "Corte", "Estado",
					"Puntos", "Tipo" };
			model.setColumnIdentifiers(columns);
			model.setRowCount(0);
			fila = new Object[model.getColumnCount()];

			for (Cuenta cuenta : Banco.getInstance().getMisCuentas()) {
				if (cuenta instanceof Vivienda) {
					fila[0] = cuenta.getCodigo();
					fila[1] = String.format("RD$ %.2f", cuenta.getSaldo());
					fila[2] = ((Vivienda) cuenta).getTiempoAhorro();
					fila[3] = String.format("RD$ %.2f", ((Vivienda) cuenta).getMontoMensual());
					fila[4] = cuenta.getMeses();
					fila[5] = cuenta.getCorte();
					fila[6] = cuenta.getEstado();
					fila[7] = cuenta.getPuntos();
					fila[8] = cuenta.getTipo();

					model.addRow(fila);
				}
			}

			table.setModel(model);
		}
		if (index == 3) {
			String[] columns = { "Código", "Saldo", "Interés fijo", "Meses", "Corte", "Estado", "Puntos", "Tipo" };
			model.setColumnIdentifiers(columns);
			model.setRowCount(0);
			fila = new Object[model.getColumnCount()];

			for (Cuenta cuenta : Banco.getInstance().getMisCuentas()) {
				if (cuenta instanceof Inversion) {
					fila[0] = cuenta.getCodigo();
					fila[1] = String.format("RD$ %.2f", cuenta.getSaldo());
					fila[2] = String.format("%.2f", ((Inversion) cuenta).getInteresFijo());
					fila[3] = cuenta.getMeses();
					fila[4] = cuenta.getCorte();
					fila[5] = cuenta.getEstado();
					fila[6] = cuenta.getPuntos();
					fila[7] = cuenta.getTipo();

					model.addRow(fila);
				}
			}

			table.setModel(model);
		}
	}
}
