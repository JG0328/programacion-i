package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import javafx.scene.control.TableColumn;
import logico.Cilindrico;
import logico.Cliente;
import logico.Complejo;
import logico.Esferico;
import logico.Factura;
import logico.Hueco;
import logico.Queso;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;

public class RegFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private static Object[] fila;
	private static MyTableModel model;
	private static JTable table;
	private JTextField txtTotal;

	private Factura factura;
	private JList listCliente;
	private JComboBox cbxTipo;

	/**
	 * Create the dialog.
	 */
	public RegFactura() {
		setTitle("Registro de Factura");

		// quesos = new ArrayList<>();
		factura = new Factura(null);

		setResizable(false);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del Cliente", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 774, 154);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblCedula = new JLabel("C\u00E9dula:");
				lblCedula.setBounds(10, 30, 74, 14);
				panel.add(lblCedula);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(10, 61, 74, 14);
				panel.add(lblNombre);
			}
			{
				JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
				lblDireccion.setBounds(10, 92, 74, 14);
				panel.add(lblDireccion);
			}
			{
				JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
				lblTelefono.setBounds(10, 123, 74, 14);
				panel.add(lblTelefono);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setBounds(94, 26, 571, 23);
				panel.add(txtCedula);
				txtCedula.setColumns(10);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setColumns(10);
				txtNombre.setBounds(94, 57, 571, 23);
				panel.add(txtNombre);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setEditable(false);
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(94, 88, 571, 23);
				panel.add(txtDireccion);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setEditable(false);
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(94, 119, 571, 23);
				panel.add(txtTelefono);
			}
			{
				JButton btnNewButton = new JButton("Buscar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cliente client = Complejo.getInstance().buscarCliente(txtCedula.getText());

						if (client != null) {
							factura.setCliente(client);

							txtNombre.setText(client.getNombre());
							txtDireccion.setText(client.getDireccion());
							txtTelefono.setText(client.getTelefono());
						}
					}
				});
				btnNewButton.setBounds(675, 26, 89, 23);
				panel.add(btnNewButton);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(
					new TitledBorder(null, "Venta de Quesos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 176, 774, 351);
			contentPanel.add(panel_1);
			panel_1.setLayout(null);
			{
				JPanel panel = new JPanel();
				panel.setBounds(10, 58, 456, 282);
				panel_1.add(panel);
				panel.setLayout(new BorderLayout(0, 0));

				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);

				table = new JTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setDefaultEditor(Object.class, null);

				String[] columns = { " ", "ID", "Nombre", "Volumen", "Precio", "Tipo" };

				model = new MyTableModel();
				model.setColumnIdentifiers(columns);
				loadTable(0);

				table.setModel(model);

				scrollPane.setViewportView(table);
			}

			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(10, 26, 46, 14);
			panel_1.add(lblTipo);

			cbxTipo = new JComboBox();
			cbxTipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadTable(cbxTipo.getSelectedIndex());
				}
			});
			cbxTipo.setModel(new DefaultComboBoxModel(
					new String[] { "<Todos>", "Esf\u00E9rico", "Cil\u00EDndrico", "Cil\u00EDndrico Hueco" }));
			cbxTipo.setBounds(66, 22, 202, 23);
			panel_1.add(cbxTipo);
			{
				JLabel lblCliente = new JLabel("Cliente:");
				lblCliente.setBounds(535, 26, 72, 14);
				panel_1.add(lblCliente);
			}

			listCliente = new JList();
			listCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCliente.setBounds(535, 58, 229, 248);
			panel_1.add(listCliente);

			txtTotal = new JTextField();
			txtTotal.setForeground(Color.BLUE);
			txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
			txtTotal.setText("RD$ 0.00");
			txtTotal.setEditable(false);
			txtTotal.setBounds(535, 317, 229, 23);
			panel_1.add(txtTotal);
			txtTotal.setColumns(10);

			JButton btnComprar = new JButton(">");
			btnComprar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadClient();
				}
			});
			btnComprar.setBounds(475, 117, 50, 23);
			panel_1.add(btnComprar);

			JButton btnDevolver = new JButton("<");
			btnDevolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listCliente.getSelectedIndex() >= 0) {
						factura.getQuesos().remove(listCliente.getSelectedIndex());
					}
					loadList();
				}
			});
			btnDevolver.setBounds(475, 151, 50, 23);
			panel_1.add(btnDevolver);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (factura.getCliente() != null) {
							Factura oficial = new Factura(factura.getCliente());
							oficial.setQuesos(factura.getQuesos());
							Complejo.getInstance().insertFactura(oficial);

							JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							JOptionPane.showMessageDialog(null, "Debe buscar un cliente", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		loadClient();
	}

	private void loadClient() {
		// TODO Auto-generated method stub
		for (int i = 0; i < table.getRowCount(); i++) {

			if ((boolean) table.getValueAt(i, 0) == true) {
				Queso queso = Complejo.getInstance().buscarQueso(table.getValueAt(i, 1).toString());

				if (queso != null) {
					factura.getQuesos().add(queso);
				}
			}
		}
		
		loadList();

		loadPrecio();
	}

	private void loadList() {
		// TODO Auto-generated method stub
		Complejo comp = Complejo.getInstance();

		DefaultListModel<String> list = new DefaultListModel<>();
		list.clear();

		if (factura.getQuesos() != null) {
			for (Queso queso : factura.getQuesos()) {
				list.addElement(String.format("%s - %s (%s): %.2f RD$", queso.getId(), queso.getNombre(),
						queso.getTipo(), comp.precioQueso(queso)));
				// System.out.println(queso);
			}
		}

		listCliente.setModel(list);
	}

	private void loadPrecio() {
		// TODO Auto-generated method stub
		if (factura.getQuesos().size() > 0) {
			txtTotal.setText(String.format("%.2f RD$", Complejo.getInstance().precioFactura(factura.getQuesos())));
		}
	}

	private void clean() {
		// TODO Auto-generated method stub
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtTotal.setText("RD$ 0.00");

		factura.setCliente(null);
		factura.setQuesos(new ArrayList<>());
		cbxTipo.setSelectedIndex(0);
		loadTable(0);

		DefaultListModel<String> list = new DefaultListModel<>();
		list.clear();
		listCliente.setModel(list);

	}

	private void loadTable(int cond) {
		// TODO Auto-generated method stub
		Complejo comp = Complejo.getInstance();
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (Queso queso : comp.getMisQuesos()) {
			if (cond == 0 || (cond == 1 && queso.getTipo().equalsIgnoreCase("Esférico"))
					|| (cond == 2 && queso.getTipo().equalsIgnoreCase("Cilíndrico"))
					|| (cond == 3 && queso.getTipo().equalsIgnoreCase("Cilíndrico Hueco"))) {
				fila[0] = new Boolean(false);
				fila[1] = queso.getId();
				fila[2] = queso.getNombre();
				fila[3] = String.format("%.2f cm^3", queso.volumen());
				fila[4] = String.format("RD$ %.2f", comp.precioQueso(queso));
				fila[5] = queso.getTipo();
				model.addRow(fila);
			}
		}
	}

	public class MyTableModel extends DefaultTableModel {
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			Class clase = String.class;
			switch (columnIndex) {
			case 0:
				clase = Boolean.class;
				break;
			}
			return clase;
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return column == 0;
		}

		@Override
		public void setValueAt(Object aValue, int row, int column) {
			if (aValue instanceof Boolean && column == 0) {
				Vector rowData = (Vector) getDataVector().get(row);
				rowData.set(0, (boolean) aValue);
				fireTableCellUpdated(row, column);
			}
		}

	}
}
