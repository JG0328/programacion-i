package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Alquiler;
import logico.Barco;
import logico.Cliente;
import logico.Puerto;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

public class RegistroAlquiler extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Puerto puerto;
	private Alquiler alquiler;
	private JTextField txtFecha;
	private JTextField txtComentario;
	private JComboBox cbxCliente;
	private JSpinner spnDias;
	private JTextField txtMonto;
	private JSpinner spnAmarre;
	private JButton btnRegistrar;
	private JLabel lblComentario;
	private JComboBox cbxMatricula;

	/**
	 * Create the dialog.
	 */
	public RegistroAlquiler(Puerto puerto, Alquiler alquiler) {
		setResizable(false);
		this.puerto = puerto;
		this.alquiler = alquiler;

		setTitle("Registro de Alquiler");

		setBounds(100, 100, 450, 452);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel.setBounds(10, 25, 414, 340);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblFechaDeInicio = new JLabel("Fecha de inicio:");
				lblFechaDeInicio.setBounds(10, 25, 87, 14);
				panel.add(lblFechaDeInicio);
			}
			{
				txtFecha = new JTextField();
				txtFecha.setEditable(false);
				txtFecha.setBounds(11, 42, 122, 23);

				// Fecha actual
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date fecha = new Date();
				txtFecha.setText(formato.format(fecha));

				panel.add(txtFecha);
				txtFecha.setColumns(10);
			}
			{
				JLabel lblCantidadDe = new JLabel("* Cantidad de d\u00EDas:");
				lblCantidadDe.setBounds(10, 73, 123, 14);
				panel.add(lblCantidadDe);
			}
			{
				spnDias = new JSpinner();
				spnDias.setModel(new SpinnerNumberModel(1, 1, 120, 1));
				spnDias.setBounds(10, 89, 123, 23);
				panel.add(spnDias);
			}
			{
				JLabel lblMatrculaDe = new JLabel("* Embarcaci\u00F3n:");
				lblMatrculaDe.setBounds(10, 122, 87, 14);
				panel.add(lblMatrculaDe);
			}
			{
				JLabel lblPosicinDel = new JLabel("* Posici\u00F3n del amarre:");
				lblPosicinDel.setBounds(185, 73, 163, 14);
				panel.add(lblPosicinDel);
			}
			{
				spnAmarre = new JSpinner();
				spnAmarre.setModel(new SpinnerNumberModel(1, 1, 20, 1));
				spnAmarre.setBounds(185, 89, 123, 23);
				panel.add(spnAmarre);
			}
			{
				JLabel lblCliente = new JLabel("* Cliente:");
				lblCliente.setBounds(10, 171, 87, 14);
				panel.add(lblCliente);
			}
			{
				cbxCliente = new JComboBox();
				cbxCliente.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>" }));
				cbxCliente.setBounds(10, 190, 394, 23);
				panel.add(cbxCliente);
			}
			{
				lblComentario = new JLabel("* Comentario:");
				lblComentario.setBounds(9, 274, 87, 14);
				panel.add(lblComentario);
			}
			{
				txtComentario = new JTextField();
				txtComentario.setBounds(9, 294, 394, 23);
				panel.add(txtComentario);
				txtComentario.setColumns(10);
			}
			{
				JLabel lblMontoAPagar = new JLabel("Monto a cobrar:");
				lblMontoAPagar.setBounds(10, 223, 123, 14);
				panel.add(lblMontoAPagar);
			}
			{
				txtMonto = new JTextField();
				txtMonto.setText("RD$ 0.00");
				txtMonto.setForeground(Color.BLUE);
				txtMonto.setHorizontalAlignment(SwingConstants.CENTER);
				txtMonto.setEditable(false);
				txtMonto.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtMonto.setBounds(11, 243, 220, 23);
				panel.add(txtMonto);
				txtMonto.setColumns(10);
			}

			JButton btnCalcular = new JButton("Calcular");
			btnCalcular.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String[] partes = cbxMatricula.getSelectedItem().toString().split(" ");
					float cobro = puerto.calcularAlquiler(partes[0], Integer.valueOf(spnDias.getValue().toString()));
					txtMonto.setText("RD$ " + String.format("%.2f", cobro));
				}
			});
			btnCalcular.setBounds(241, 243, 163, 23);
			panel.add(btnCalcular);

			cbxMatricula = new JComboBox();
			cbxMatricula.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>" }));
			cbxMatricula.setBounds(10, 141, 394, 23);
			panel.add(cbxMatricula);
		}
		{
			JLabel lblLosIndican = new JLabel("Los * indican campos obligatorios");
			lblLosIndican.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblLosIndican.setBounds(266, 11, 158, 14);
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
						String[] partes = cbxMatricula.getSelectedItem().toString().split(" ");
						String matricula = partes[0];

						Barco barco = puerto.buscarBarco(matricula);

						if (barco == null) {
							JOptionPane.showMessageDialog(null, "Debe seleccionar una embarcación", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						if (cbxCliente.getSelectedIndex() == 0) {

							JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;

						}

						if (alquiler != null && txtComentario.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Debe insertar un comentario", "Aviso",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						String fechaInicio = txtFecha.getText();
						int cantDias = Integer.valueOf(spnDias.getValue().toString());
						int posicion = Integer.valueOf(spnAmarre.getValue().toString());

						String[] parts = cbxCliente.getSelectedItem().toString().split(" ");
						Cliente cliente = puerto.buscarCliente(parts[0]);

						String[] partesMonto = (txtMonto.getText()).split(" ");
						float monto = Float.parseFloat(partesMonto[1]);

						String comentario = txtComentario.getText();

						if (alquiler == null) {

							Alquiler alquiler = new Alquiler(barco, fechaInicio, cantDias, posicion, cliente, monto,
									comentario);
							puerto.insertarAlquiler(alquiler);
							JOptionPane.showMessageDialog(null, "Registro de alquiler exitoso", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							limpiar();

						} else {
							alquiler.setFechaInicio(fechaInicio);
							alquiler.setCantDias(cantDias);
							// alquiler.setMatricula(matricula);
							alquiler.setBarco(barco);
							alquiler.setPosicion(posicion);
							alquiler.setCliente(cliente);
							alquiler.setMonto(monto);
							alquiler.setComentario(comentario);

							ListarAlquileres.cargarTabla();

							JOptionPane.showMessageDialog(null, "Modificación de alquiler exitosa", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
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
		cargarClientes();

		cargarInfo();
	}

	private void cargarBarcos() {
		for (int i = 0; i < puerto.getCantBarcos(); i++) {
			cbxMatricula.addItem(
					new String(puerto.getBarcos()[i].getMatricula() + " - " + puerto.getBarcos()[i].getNombre()));
		}
	}

	private void cargarClientes() {
		// TODO Auto-generated method stub
		Cliente[] clientes = puerto.getClientes();

		for (int i = 0; i < puerto.getCantClientes(); i++) {
			cbxCliente.addItem(new String(
					clientes[i].getCedula() + " - " + clientes[i].getNombre() + " " + clientes[i].getApellidos()));
		}
	}

	private void cargarInfo() {
		// TODO Auto-generated method stub
		if (alquiler != null) {
			setTitle("Modificación de Alquiler");
			btnRegistrar.setText("Modificar");
			txtFecha.setText(alquiler.getFechaInicio());
			spnDias.setValue(alquiler.getCantDias());
			if (alquiler.getBarco() == null) {
				cbxMatricula.setSelectedIndex(0);
			} else {
				cbxMatricula.setSelectedIndex(puerto.buscarBarcoID(alquiler.getBarco().getMatricula()) + 1);
			}
			spnAmarre.setValue(alquiler.getPosicion());
			if (alquiler.getCliente() == null) {
				cbxCliente.setSelectedIndex(0);
			} else {
				cbxCliente.setSelectedIndex(puerto.buscarClienteID(alquiler.getCliente().getCedula()) + 1);
			}
			txtMonto.setText(String.format("RD$ %.2f", alquiler.getMonto()));
			txtComentario.setVisible(true);
			lblComentario.setVisible(true);
			txtComentario.setText(alquiler.getComentario());
		} else {
			txtComentario.setVisible(false);
			lblComentario.setVisible(false);
		}

	}

	private void limpiar() {
		// Fecha actual
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		txtFecha.setText(formato.format(fecha));
		spnDias.setValue(1);
		cbxMatricula.setSelectedIndex(0);
		spnAmarre.setValue(1);
		cbxCliente.setSelectedIndex(0);
		txtMonto.setText(String.format("RD$ %.2f", 0f));
		txtComentario.setText("");
	}
}
