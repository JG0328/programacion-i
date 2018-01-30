package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.Complejo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;

	/**
	 * Create the dialog.
	 */
	public RegCliente() {
		setResizable(false);
		setTitle("Registro de Cliente");
		setBounds(100, 100, 450, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 424, 166);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel label = new JLabel("C\u00E9dula:");
			label.setBounds(16, 28, 64, 14);
			panel.add(label);
		}
		{
			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(90, 24, 322, 23);
			panel.add(txtCedula);
		}
		{
			JLabel label = new JLabel("Nombre:");
			label.setBounds(16, 59, 64, 14);
			panel.add(label);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(90, 55, 322, 23);
			panel.add(txtNombre);
		}
		{
			JLabel label = new JLabel("Direcci\u00F3n:");
			label.setBounds(16, 90, 64, 14);
			panel.add(label);
		}
		{
			txtDireccion = new JTextField();
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(90, 86, 322, 23);
			panel.add(txtDireccion);
		}
		{
			JLabel label = new JLabel("Tel\u00E9fono:");
			label.setBounds(16, 121, 64, 14);
			panel.add(label);
		}
		{
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(90, 117, 322, 23);
			panel.add(txtTelefono);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = txtCedula.getText();
						String nombre = txtNombre.getText();
						String direccion = txtDireccion.getText();
						String telefono = txtTelefono.getText();
						Cliente client = new Cliente(id, nombre, direccion, telefono);
						Complejo.getInstance().insertCliente(client);
						
						JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información", JOptionPane.INFORMATION_MESSAGE);
						
						clean();
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
		// TODO Auto-generated method stub
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
	}
}
