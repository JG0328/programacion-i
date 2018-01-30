package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Banco;
import logico.Cliente;
import logico.Cuenta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RlzRevision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	private JTable table;
	private Object[] fila;
	private DefaultTableModel model;
	private JTextField txtTelefono;

	private ArrayList<Cuenta> cuentas;
	private JTextField txtTotal;
	private JTextField txtPuntosTotales;
	private JButton btnGenerarReporte;

	Cliente cliente;

	/**
	 * Create the dialog.
	 */
	public RlzRevision() {
		setTitle("Revisi\u00F3n de Cuentas");
		setResizable(false);
		setBounds(100, 100, 550, 590);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del Cliente", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(10, 11, 524, 182);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setBounds(10, 22, 65, 14);
		panel.add(lblCdula);

		txtCedula = new JTextField();
		txtCedula.setBounds(85, 19, 330, 23);
		panel.add(txtCedula);
		txtCedula.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = Banco.getInstance().buscarCliente(txtCedula.getText());

				if (cliente != null) {

					btnGenerarReporte.setEnabled(true);

					txtNombre.setText(cliente.getNombre());
					txtApellidos.setText(cliente.getApellidos());
					txtDireccion.setText(cliente.getDireccion());
					txtTelefono.setText(cliente.getTelefono());

					cuentas = cliente.getMisCuentas();

					loadTable();
				} else {
					JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnBuscar.setBounds(425, 18, 89, 23);
		panel.add(btnBuscar);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 53, 65, 14);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(85, 50, 429, 23);
		panel.add(txtNombre);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(10, 84, 65, 14);
		panel.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(85, 81, 429, 23);
		panel.add(txtApellidos);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 115, 65, 14);
		panel.add(lblDireccin);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(85, 112, 429, 23);
		panel.add(txtDireccion);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(10, 146, 65, 14);
		panel.add(lblTelfono);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(85, 143, 429, 23);
		panel.add(txtTelefono);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cuentas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 204, 524, 219);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] columns = { "Código", "Saldo", "Meses", "Corte", "Estado", "Puntos", "Tipo" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		loadTable();

		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 434, 524, 79);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel label = new JLabel("Total:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 15, 59, 14);
		panel_2.add(label);

		JLabel label_1 = new JLabel("RD$");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(79, 15, 46, 14);
		panel_2.add(label_1);

		txtTotal = new JTextField();
		txtTotal.setText("0.00");
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setForeground(Color.BLUE);
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(115, 11, 399, 23);
		panel_2.add(txtTotal);

		JLabel label_2 = new JLabel("Puntos:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(10, 46, 59, 14);
		panel_2.add(label_2);

		txtPuntosTotales = new JTextField();
		txtPuntosTotales.setText("0");
		txtPuntosTotales.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuntosTotales.setForeground(Color.MAGENTA);
		txtPuntosTotales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPuntosTotales.setEditable(false);
		txtPuntosTotales.setColumns(10);
		txtPuntosTotales.setBounds(115, 42, 399, 23);
		panel_2.add(txtPuntosTotales);
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

				btnGenerarReporte = new JButton("Generar reporte");
				btnGenerarReporte.setEnabled(false);
				btnGenerarReporte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						File arch = new File("Revision.txt");
						BufferedWriter writer;

						try {
							writer = new BufferedWriter(new FileWriter(arch));

							writer.write("Fecha: " + (new Date()).toString());
							writer.newLine();
							writer.newLine();
							writer.write("Cédula: " + cliente.getCedula());
							writer.newLine();
							writer.write("Cliente: " + cliente.getNombre() + " " + cliente.getApellidos());
							writer.newLine();
							writer.write("Teléfono: " + cliente.getTelefono());
							writer.newLine();
							writer.write("Dirección: " + cliente.getDireccion());
							writer.newLine();
							writer.write("------------------------------");
							writer.newLine();

							for (Cuenta cuenta : cliente.getMisCuentas()) {
								writer.write("No. cuenta: " + cuenta.getCodigo());
								writer.newLine();
								writer.write("Tipo: " + cuenta.getTipo());
								writer.newLine();
								writer.write("Saldo real: " + "RD$ " + cuenta.revision());
								writer.newLine();
								writer.write("Interés acumulado: " + "RD$ " + cuenta.obtenerInteres());
								writer.newLine();
								writer.write("Comisiones: " + "RD$ " + (cuenta.getMeses() * 3.0f));
								writer.newLine();
								writer.newLine();
							}
							writer.close();
							
							JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				buttonPane.add(btnGenerarReporte);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void loadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		if (cuentas != null) {
			for (Cuenta cuenta : cuentas) {
				fila[0] = cuenta.getCodigo();
				fila[1] = String.format("RD$ %.2f", cuenta.revision());
				fila[2] = cuenta.getMeses();
				fila[3] = cuenta.getCorte();
				fila[4] = cuenta.getEstado();
				fila[5] = cuenta.getPuntos();
				fila[6] = cuenta.getTipo();

				model.addRow(fila);
			}

			txtTotal.setText(String.format("%.2f", Banco.getInstance().revisionTotal(txtCedula.getText())));
			txtPuntosTotales.setText(String.format("%d", Banco.getInstance().revisionTotalPuntos(txtCedula.getText())));
		}

		table.setModel(model);
	}
}
