package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Biblioteca;
import logico.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;

	/**
	 * Create the dialog.
	 */
	public RegCliente() {
		setResizable(false);
		setTitle("Registro de Cliente");
		setBounds(100, 100, 450, 240);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel.setBounds(10, 11, 424, 156);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblCdula = new JLabel("C\u00E9dula:");
				lblCdula.setBounds(10, 35, 60, 14);
				panel.add(lblCdula);
			}

			txtCedula = new JTextField();
			txtCedula.setBounds(80, 32, 334, 23);
			panel.add(txtCedula);
			txtCedula.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 68, 60, 14);
			panel.add(lblNombre);

			JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setBounds(10, 101, 69, 14);
			panel.add(lblDireccin);
			{
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(80, 64, 334, 23);
				panel.add(txtNombre);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(80, 97, 334, 23);
				panel.add(txtDireccion);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String cedula = txtCedula.getText();
						String nombre = txtNombre.getText();
						String direccion = txtDireccion.getText();

						Cliente client = new Cliente(cedula, nombre, direccion);

						Biblioteca.getInstance().insertarCliente(client);

						JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información",
								JOptionPane.INFORMATION_MESSAGE);

						clear();
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

	private void clear() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
	}
}
