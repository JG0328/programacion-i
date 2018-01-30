package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Barco;
import logico.Cliente;
import logico.Puerto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class RegistroCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;

	private Puerto puerto;
	private Cliente cliente;

	private JComboBox cbxBarco;
	private JButton btnRegistrar;

	/**
	 * Create the dialog.
	 */
	public RegistroCliente(Puerto puerto, Cliente cliente) {
		setResizable(false);
		this.puerto = puerto;
		this.cliente = cliente;

		setTitle("Registro de Cliente");
		setBounds(100, 100, 450, 420);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel.setBounds(10, 22, 414, 311);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblCdula = new JLabel("* C\u00E9dula:");
				lblCdula.setBounds(10, 22, 66, 14);
				panel.add(lblCdula);
			}
			{
				txtCedula = new JTextField();
				txtCedula.setBounds(10, 38, 394, 23);
				panel.add(txtCedula);
				txtCedula.setColumns(10);
			}
			{
				JLabel lblNombre = new JLabel("* Nombre:");
				lblNombre.setBounds(10, 65, 66, 14);
				panel.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(10, 81, 394, 23);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblapellidos = new JLabel("* Apellidos:");
				lblapellidos.setBounds(10, 108, 66, 14);
				panel.add(lblapellidos);
			}
			{
				txtApellidos = new JTextField();
				txtApellidos.setBounds(10, 124, 394, 23);
				panel.add(txtApellidos);
				txtApellidos.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("* Tel\u00E9fono:");
				lblNewLabel.setBounds(10, 152, 66, 14);
				panel.add(lblNewLabel);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setBounds(10, 169, 394, 23);
				panel.add(txtTelefono);
				txtTelefono.setColumns(10);
			}
			{
				JLabel lblDireccin = new JLabel("* Direcci\u00F3n:");
				lblDireccin.setBounds(10, 197, 66, 14);
				panel.add(lblDireccin);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(10, 214, 394, 23);
				panel.add(txtDireccion);
			}
			{
				JLabel lblBarco = new JLabel("* Barco:");
				lblBarco.setBounds(10, 242, 66, 14);
				panel.add(lblBarco);
			}
			{
				cbxBarco = new JComboBox();
				cbxBarco.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>" }));
				cbxBarco.setBounds(10, 258, 394, 23);
				panel.add(cbxBarco);
			}
		}
		{
			JLabel lblLosIndican = new JLabel("Los * indican campos obligatorios");
			lblLosIndican.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblLosIndican.setBounds(266, 9, 158, 14);
			contentPanel.add(lblLosIndican);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						// Validaciones
						if (txtCedula.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar una cédula", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if (txtNombre.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar un nombre", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if (txtApellidos.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar los apellidos", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if (txtTelefono.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar un teléfono", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if (txtDireccion.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar una dirección", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if (cbxBarco.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un barco", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						} else {

							String cedula = txtCedula.getText();
							String nombre = txtNombre.getText();
							String apellidos = txtApellidos.getText();
							String telefono = txtTelefono.getText();
							String direccion = txtDireccion.getText();

							String temp = cbxBarco.getSelectedItem().toString();
							String[] partes = temp.split(" ");
							Barco barco = puerto.buscarBarco(partes[0]);

							if (cliente == null) {
								Cliente cliente = new Cliente(cedula, nombre, apellidos, telefono, direccion, barco);

								puerto.insertarCliente(cliente);

								JOptionPane.showMessageDialog(null, "Registro de cliente exitoso", "Información",
										JOptionPane.INFORMATION_MESSAGE);
								limpiar();
							} else {
								cliente.setCedula(cedula);
								cliente.setNombre(nombre);
								cliente.setApellidos(apellidos);
								cliente.setTelefono(telefono);
								cliente.setDireccion(direccion);
								cliente.setBarco(barco);

								JOptionPane.showMessageDialog(null, "Modificación de cliente exitosa", "Información",
										JOptionPane.INFORMATION_MESSAGE);

								ListarClientes.cargarTabla();

								dispose();

							}
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		cargarBarcos();

		cargarInfo();
	}

	private void cargarInfo() {
		// TODO Auto-generated method stub
		if (cliente != null) {
			setTitle("Modificación de Cliente");
			btnRegistrar.setText("Modificar");
			txtCedula.setText(cliente.getCedula());
			txtNombre.setText(cliente.getNombre());
			txtApellidos.setText(cliente.getApellidos());
			txtTelefono.setText(cliente.getTelefono());
			txtDireccion.setText(cliente.getDireccion());

			if (cliente.getBarco() == null) {
				cbxBarco.setSelectedIndex(0);
			} else {
				cbxBarco.setSelectedIndex(puerto.buscarBarcoID(cliente.getBarco().getMatricula()) + 1);
			}
		}
	}

	private void cargarBarcos() {
		// TODO Auto-generated method stub
		Barco[] barcos = puerto.getBarcos();

		for (int i = 0; i < puerto.getCantBarcos(); i++) {
			cbxBarco.addItem(new String(barcos[i].getMatricula() + " - " + barcos[i].getNombre()));
		}
	}

	private void limpiar() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		cbxBarco.setSelectedIndex(0);
	}

}
