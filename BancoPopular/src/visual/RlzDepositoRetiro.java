package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.Banco;
import logico.Cliente;
import logico.Cuenta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.border.EtchedBorder;
import javax.swing.SpinnerNumberModel;

public class RlzDepositoRetiro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtSaldo;
	private JComboBox cbxCodigo;

	private Cliente cliente;
	private Cuenta cuenta;

	private JLabel lblMonto;
	private JSpinner spnMonto;

	/**
	 * Create the dialog.
	 */
	public RlzDepositoRetiro(boolean mode) {
		if (mode) {
			setTitle("Retiro");
		} else {
			setTitle("Depósito");
		}
		setResizable(false);
		setBounds(100, 100, 515, 425);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n de Cliente", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(10, 11, 489, 197);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setBounds(10, 26, 70, 14);
		panel.add(lblCdula);

		txtCedula = new JTextField();
		txtCedula.setBounds(90, 22, 290, 23);
		panel.add(txtCedula);
		txtCedula.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = Banco.getInstance().buscarCliente(txtCedula.getText());

				if (cliente != null) {
					txtNombre.setText(cliente.getNombre());
					txtApellidos.setText(cliente.getApellidos());
					txtDireccion.setText(cliente.getDireccion());
					txtTelefono.setText(cliente.getTelefono());

					loadCuentas();
				} else {
					JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(390, 22, 89, 23);
		panel.add(btnBuscar);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 59, 70, 14);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(90, 55, 290, 23);
		panel.add(txtNombre);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(10, 90, 70, 14);
		panel.add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(90, 87, 290, 23);
		panel.add(txtApellidos);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 123, 70, 14);
		panel.add(lblDireccin);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(10, 156, 70, 14);
		panel.add(lblTelfono);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(90, 120, 290, 23);
		panel.add(txtDireccion);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(90, 153, 290, 23);
		panel.add(txtTelefono);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Informaci\u00F3n de Cuenta", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.setBounds(10, 219, 489, 66);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(10, 26, 46, 14);
		panel_1.add(lblCdigo);

		cbxCodigo = new JComboBox();
		cbxCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxCodigo.getSelectedIndex() >= 1) {
					cuenta = cliente.getMisCuentas().get(cbxCodigo.getSelectedIndex() - 1);
					txtSaldo.setText(String.format("RD$ %.2f", cuenta.getSaldo()));
				} else {
					cuenta = null;
					txtSaldo.setText("RD$ 0.00");
				}
			}
		});
		cbxCodigo.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>" }));
		cbxCodigo.setBounds(66, 22, 169, 23);
		panel_1.add(cbxCodigo);

		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(245, 26, 46, 14);
		panel_1.add(lblSaldo);

		txtSaldo = new JTextField();
		txtSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaldo.setText("RD$ 0.00");
		txtSaldo.setForeground(Color.BLUE);
		txtSaldo.setEditable(false);
		txtSaldo.setBounds(288, 22, 191, 23);
		panel_1.add(txtSaldo);
		txtSaldo.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 296, 489, 52);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);

		lblMonto = new JLabel("Texto");

		if (mode) {
			lblMonto.setText("Monto a retirar:");
		} else {
			lblMonto.setText("Monto a depositar:");
		}
		lblMonto.setBounds(32, 19, 121, 14);
		panel_2.add(lblMonto);

		JLabel lblRd = new JLabel("RD$");
		lblRd.setBounds(185, 19, 46, 14);
		panel_2.add(lblRd);

		spnMonto = new JSpinner();
		spnMonto.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnMonto.setBounds(241, 14, 216, 23);
		panel_2.add(spnMonto);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Terminar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (cliente == null) {
							JOptionPane.showMessageDialog(null, "Debe ingresar un cliente", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if (cuenta == null) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar una cuenta", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						String cedula = cliente.getCedula();
						String codigo = cuenta.getCodigo();
						float monto = (float) Integer.valueOf(spnMonto.getValue().toString());
						boolean resultado = true;

						if (mode) {
							resultado = Banco.getInstance().retirarDinero(cedula, codigo, monto);
						} else {
							if (cuenta.getEstado().equalsIgnoreCase("habilitada")) {
								Banco.getInstance().ingresarDinero(cedula, codigo, monto);
							} else {
								JOptionPane.showMessageDialog(null, "Operación fallida, la cuenta no está habilitada",
										"Aviso", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}

						if (resultado) {
							JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							clean();
						} else {
							JOptionPane.showMessageDialog(null, "Operación fallida, revise las restricciones del Banco",
									"Aviso", JOptionPane.INFORMATION_MESSAGE);
						}
						
						Banco.getInstance().actualizarCuentas();
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
	}

	private void clean() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		spnMonto.setValue(0);
		cliente = null;
		cuenta = null;
		loadCuentas();
		txtSaldo.setText("RD$ 0.00");
	}

	private void loadCuentas() {
		cbxCodigo.removeAllItems();

		cbxCodigo.addItem("<Seleccione>");

		if (cliente != null) {
			for (Cuenta cuenta : cliente.getMisCuentas()) {
				cbxCodigo.addItem(new String(cuenta.getCodigo()));
			}
		}
	}
}
