package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import logico.Banco;
import logico.Cliente;
import logico.Cuenta;
import logico.Transaccion;

public class ListTransacciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Object[] fila;
	private DefaultTableModel model;
	private JTextField txtCodigo;
	private JButton btnGenerarReporte;

	private Cliente cliente;
	private Cuenta cuenta;

	/**
	 * Create the dialog.
	 */
	public ListTransacciones() {
		setTitle("Listado de Transacciones");
		setResizable(false);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 41, 774, 382);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] columns = { "Fecha", "Transacciones", "Comisión", "Saldo real" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		loadTable(null);
		scrollPane.setViewportView(table);

		JLabel lblNoDeCuenta = new JLabel("No. de cuenta:");
		lblNoDeCuenta.setBounds(10, 16, 103, 14);
		contentPanel.add(lblNoDeCuenta);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(98, 12, 587, 23);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cliente = Banco.getInstance().buscarClienteByCuenta(txtCodigo.getText());

				if (cliente != null) {
					cuenta = cliente.buscarCuenta(txtCodigo.getText());
					loadTable(cuenta.getMisTransacciones());
					btnGenerarReporte.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Cuenta no encontrada", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(695, 12, 89, 23);
		contentPanel.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});

				btnGenerarReporte = new JButton("Generar reporte");
				btnGenerarReporte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						File arch = new File("Transacciones.txt");
						BufferedWriter writer;

						// System.out.println(cliente.buscarCuenta(cuenta.getCodigo()).getMisTransacciones().size());

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
							writer.write("No. de Cuenta: " + cuenta.getCodigo());
							writer.newLine();
							writer.write("Tipo de Cuenta: " + cuenta.getTipo());
							writer.newLine();
							writer.newLine();
							writer.write(String.format("%s %40s %15s %17s", "Fecha", "Transacción", "Comisión",
									"Saldo Real"));
							writer.newLine();
							for (Transaccion trans : cuenta.getMisTransacciones()) {
								writer.write(String.format("%s %17.2f %15.2f %17.2f", trans.getFecha(),
										trans.getMonto(), trans.getComision(), trans.getSaldoActual()));
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
				btnGenerarReporte.setEnabled(false);
				buttonPane.add(btnGenerarReporte);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void loadTable(ArrayList<Transaccion> transacciones) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		if (transacciones != null) {

			// System.out.println(transacciones.size());

			for (Transaccion transaccion : transacciones) {
				fila[0] = transaccion.getFecha();
				fila[1] = transaccion.getMonto();
				fila[2] = transaccion.getComision();
				fila[3] = transaccion.getSaldoActual();

				model.addRow(fila);
			}
		}

		table.setModel(model);
	}
}
