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
import logico.Corriente;
import logico.Inversion;
import logico.Vivienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegCuenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JSpinner spnSaldo;
	private JSpinner spnCorte;
	private JRadioButton rdbtnCorriente;
	private JRadioButton rdbtnVivienda;
	private JRadioButton rdbtnFondoInversin;
	private JPanel pnlCorriente;
	private JSpinner spnRetiroMax;
	private JPanel pnlVivienda;
	private JSpinner spnMeses;
	private JPanel pnlFondoInv;
	private JSpinner spnMontoMensual;
	private JSpinner spnInteresFijo;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JSpinner spnApertura;
	private JComboBox cbxEstado;
	private JButton btnBuscar;

	private Cliente cliente;

	/**
	 * Create the dialog.
	 */
	public RegCuenta(Cliente c) {
		setTitle("Registro de Cuenta");
		setResizable(false);
		setBounds(100, 100, 450, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel.setBounds(10, 11, 424, 200);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblCdigo = new JLabel("C\u00F3digo:");
				lblCdigo.setBounds(10, 21, 85, 14);
				panel.add(lblCdigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setBounds(135, 17, 279, 23);
				panel.add(txtCodigo);
				txtCodigo.setColumns(10);
			}
			{
				JLabel lblSaldo = new JLabel("Saldo:");
				lblSaldo.setBounds(10, 56, 85, 14);
				panel.add(lblSaldo);
			}

			spnSaldo = new JSpinner();
			spnSaldo.setModel(new SpinnerNumberModel(new Integer(500), new Integer(500), null, new Integer(1)));
			spnSaldo.setBounds(135, 52, 279, 23);
			panel.add(spnSaldo);

			JLabel lblDaDeCorte = new JLabel("D\u00EDa de corte:");
			lblDaDeCorte.setBounds(10, 161, 75, 14);
			panel.add(lblDaDeCorte);

			spnCorte = new JSpinner();
			spnCorte.setModel(new SpinnerNumberModel(1, 1, 31, 1));
			spnCorte.setBounds(135, 157, 279, 23);
			panel.add(spnCorte);

			JLabel lblRd = new JLabel("RD$");
			lblRd.setBounds(105, 56, 46, 14);
			panel.add(lblRd);
			{
				JLabel lblMeses_1 = new JLabel("Meses:");
				lblMeses_1.setBounds(10, 91, 85, 14);
				panel.add(lblMeses_1);
			}

			spnApertura = new JSpinner();
			spnApertura.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnApertura.setBounds(135, 87, 279, 23);
			panel.add(spnApertura);

			JLabel lblEstado = new JLabel("Estado:");
			lblEstado.setBounds(10, 126, 85, 14);
			panel.add(lblEstado);

			cbxEstado = new JComboBox();
			cbxEstado.setModel(
					new DefaultComboBoxModel(new String[] { "<Seleccione>", "Habilitada", "Bloqueada", "Cancelada" }));
			cbxEstado.setBounds(135, 122, 279, 23);
			panel.add(cbxEstado);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(
					new TitledBorder(null, "Tipo de Cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 222, 424, 71);
			contentPanel.add(panel);
			panel.setLayout(null);

			rdbtnCorriente = new JRadioButton("Corriente");
			rdbtnCorriente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnVivienda.setSelected(false);
					rdbtnFondoInversin.setSelected(false);

					pnlCorriente.setVisible(true);
					pnlVivienda.setVisible(false);
					pnlFondoInv.setVisible(false);
				}
			});
			rdbtnCorriente.setSelected(true);
			rdbtnCorriente.setBounds(19, 24, 109, 23);
			panel.add(rdbtnCorriente);
			{
				rdbtnVivienda = new JRadioButton("Vivienda");
				rdbtnVivienda.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnCorriente.setSelected(false);
						rdbtnFondoInversin.setSelected(false);

						pnlCorriente.setVisible(false);
						pnlVivienda.setVisible(true);
						pnlFondoInv.setVisible(false);
					}
				});
				rdbtnVivienda.setBounds(147, 24, 109, 23);
				panel.add(rdbtnVivienda);
			}
			{
				rdbtnFondoInversin = new JRadioButton("Fondo Inversi\u00F3n");
				rdbtnFondoInversin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnCorriente.setSelected(false);
						rdbtnVivienda.setSelected(false);

						pnlCorriente.setVisible(false);
						pnlVivienda.setVisible(false);
						pnlFondoInv.setVisible(true);
					}
				});
				rdbtnFondoInversin.setBounds(275, 24, 128, 23);
				panel.add(rdbtnFondoInversin);
			}
		}
		{
			pnlFondoInv = new JPanel();
			pnlFondoInv.setBounds(10, 304, 424, 109);
			contentPanel.add(pnlFondoInv);
			pnlFondoInv.setLayout(null);
			pnlFondoInv.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fondo Inversi\u00F3n",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			{
				JLabel lblNewLabel = new JLabel("Inter\u00E9s fijo:");
				lblNewLabel.setBounds(10, 47, 83, 14);
				pnlFondoInv.add(lblNewLabel);
			}
			{
				spnInteresFijo = new JSpinner();
				spnInteresFijo.setModel(new SpinnerNumberModel(1, 1, 100, 1));
				spnInteresFijo.setBounds(103, 43, 83, 23);
				pnlFondoInv.add(spnInteresFijo);
			}
			{
				JLabel label = new JLabel("%");
				label.setBounds(196, 47, 25, 14);
				pnlFondoInv.add(label);
			}
		}
		{
			pnlVivienda = new JPanel();
			pnlVivienda.setBounds(10, 304, 424, 109);
			contentPanel.add(pnlVivienda);
			pnlVivienda.setLayout(null);
			pnlVivienda.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cuenta Vivienda",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			{
				JLabel lblTiempoDeAhorro = new JLabel("Tiempo de Ahorro:");
				lblTiempoDeAhorro.setBounds(10, 29, 109, 14);
				pnlVivienda.add(lblTiempoDeAhorro);
			}
			{
				spnMeses = new JSpinner();
				spnMeses.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spnMeses.setBounds(180, 25, 120, 23);
				pnlVivienda.add(spnMeses);
			}
			{
				JLabel lblMontoMensual = new JLabel("Monto mensual:");
				lblMontoMensual.setBounds(10, 60, 109, 14);
				pnlVivienda.add(lblMontoMensual);
			}
			{
				JLabel lblRd_2 = new JLabel("RD$");
				lblRd_2.setBounds(124, 60, 46, 14);
				pnlVivienda.add(lblRd_2);
			}
			{
				spnMontoMensual = new JSpinner();
				spnMontoMensual.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spnMontoMensual.setBounds(180, 59, 176, 23);
				pnlVivienda.add(spnMontoMensual);
			}

			JLabel lblMeses = new JLabel("meses");
			lblMeses.setBounds(310, 29, 46, 14);
			pnlVivienda.add(lblMeses);
		}
		{
			pnlCorriente = new JPanel();
			pnlCorriente.setBorder(
					new TitledBorder(null, "Cuenta Corriente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlCorriente.setBounds(10, 304, 424, 109);
			contentPanel.add(pnlCorriente);
			pnlCorriente.setLayout(null);
			{
				JLabel lblRetiroMximo = new JLabel("Retiro m\u00E1ximo:");
				lblRetiroMximo.setBounds(10, 47, 95, 14);
				pnlCorriente.add(lblRetiroMximo);
			}
			{
				spnRetiroMax = new JSpinner();
				spnRetiroMax.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spnRetiroMax.setBounds(155, 43, 170, 23);
				pnlCorriente.add(spnRetiroMax);
			}
			{
				JLabel lblRd_1 = new JLabel("RD$");
				lblRd_1.setBounds(115, 47, 46, 14);
				pnlCorriente.add(lblRd_1);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cliente",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 424, 424, 199);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblCdula = new JLabel("C\u00E9dula:");
				lblCdula.setBounds(10, 21, 69, 14);
				panel.add(lblCdula);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setBounds(89, 17, 226, 23);
				panel.add(txtCedula);
				txtCedula.setColumns(10);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(10, 56, 69, 14);
				panel.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setColumns(10);
				txtNombre.setBounds(89, 52, 325, 23);
				panel.add(txtNombre);
			}
			{
				JLabel lblApellidos = new JLabel("Apellidos:");
				lblApellidos.setBounds(10, 91, 69, 14);
				panel.add(lblApellidos);
			}
			{
				txtApellidos = new JTextField();
				txtApellidos.setEditable(false);
				txtApellidos.setColumns(10);
				txtApellidos.setBounds(89, 87, 325, 23);
				panel.add(txtApellidos);
			}
			{
				JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
				lblDireccin.setBounds(10, 126, 69, 14);
				panel.add(lblDireccin);
			}
			{
				JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
				lblTelfono.setBounds(10, 161, 69, 14);
				panel.add(lblTelfono);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setEditable(false);
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(89, 122, 325, 23);
				panel.add(txtDireccion);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setEditable(false);
				txtTelefono.setColumns(10);
				txtTelefono.setBounds(89, 157, 325, 23);
				panel.add(txtTelefono);
			}
			{
				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cliente = Banco.getInstance().buscarCliente(txtCedula.getText());

						if (cliente != null) {
							txtNombre.setText(cliente.getNombre());
							txtApellidos.setText(cliente.getApellidos());
							txtDireccion.setText(cliente.getDireccion());
							txtTelefono.setText(cliente.getTelefono());
						} else {
							JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				btnBuscar.setBounds(325, 17, 89, 23);
				panel.add(btnBuscar);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String codigo = txtCodigo.getText();
						float saldo = (float) Integer.valueOf(spnSaldo.getValue().toString());
						int meses = Integer.valueOf(spnApertura.getValue().toString());
						int corte = Integer.valueOf(spnCorte.getValue().toString());
						String estado = cbxEstado.getSelectedItem().toString();
						int puntos = 0;
						float retiroMax = (float) Integer.valueOf(spnRetiroMax.getValue().toString());
						int tiempoAhorro = Integer.valueOf(spnMeses.getValue().toString());
						float montoMensual = (float) Integer.valueOf(spnMontoMensual.getValue().toString());
						float interesFijo = (float) Integer.valueOf(spnInteresFijo.getValue().toString());

						if (cbxEstado.getSelectedIndex() < 1) {
							JOptionPane.showMessageDialog(null, "Debe ingresar un estado", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						if (cliente == null) {
							JOptionPane.showMessageDialog(null, "Debe ingresar un cliente", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						if (c != null) {
							Banco.getInstance().insertarCliente(c);
						}

						if (rdbtnCorriente.isSelected()) {
							String tipo = "Corriente";
							Corriente corriente = new Corriente(codigo, 0, meses, corte, estado, puntos, tipo,
									retiroMax);
							Banco.getInstance().insertarCuenta(corriente);
							cliente.insertarCuenta(corriente);
						} else if (rdbtnVivienda.isSelected()) {
							String tipo = "Vivienda";
							Vivienda vivienda = new Vivienda(codigo, 0, meses, corte, estado, puntos, tipo,
									tiempoAhorro, montoMensual);
							Banco.getInstance().insertarCuenta(vivienda);
							cliente.insertarCuenta(vivienda);
						} else {
							String tipo = "Fondo Inversión";
							Inversion fondo = new Inversion(codigo, 0, meses, corte, estado, puntos, tipo, interesFijo);
							Banco.getInstance().insertarCuenta(fondo);
							cliente.insertarCuenta(fondo);
						}

						if (cliente.buscarCuenta(codigo).getEstado().equalsIgnoreCase("habilitada")) {
							Banco.getInstance().ingresarDinero(txtCedula.getText(), codigo, saldo);
						} else {
							cliente.buscarCuenta(codigo).setSaldo(saldo);
						}

						JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);

						if (c == null) {
							clean();
						} else {
							dispose();
							RegCliente rgCliente = new RegCliente();
							rgCliente.setModal(true);
							rgCliente.setVisible(true);
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

			loadDefault();
			// Si se crea la cuenta desde el registro del cliente...
			if (c != null) {
				loadCliente(c);
			}
		}
	}

	private void clean() {
		txtCodigo.setText("");
		spnSaldo.setValue(500);
		spnCorte.setValue(1);
		rdbtnCorriente.setSelected(true);
		rdbtnVivienda.setSelected(false);
		rdbtnFondoInversin.setSelected(false);
		pnlCorriente.setVisible(true);
		pnlVivienda.setVisible(false);
		pnlFondoInv.setVisible(false);
		spnRetiroMax.setValue(1);
		spnMeses.setValue(1);
		spnMontoMensual.setValue(1);
		spnInteresFijo.setValue(1);
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		cbxEstado.setSelectedIndex(0);
		spnApertura.setValue(0);
	}

	private void loadDefault() {
		// TODO Auto-generated method stub
		pnlCorriente.setVisible(true);
		pnlVivienda.setVisible(false);
		pnlFondoInv.setVisible(false);
	}

	private void loadCliente(Cliente c) {
		cliente = c;
		btnBuscar.setVisible(false);
		txtCedula.setEditable(false);
		txtCedula.setText(c.getCedula());
		txtNombre.setText(c.getNombre());
		txtApellidos.setText(c.getApellidos());
		txtDireccion.setText(c.getDireccion());
		txtTelefono.setText(c.getTelefono());
	}
}
