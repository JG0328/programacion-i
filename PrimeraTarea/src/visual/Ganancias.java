package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Puerto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Ganancias extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTotalBarcos;
	private JTextField txtTotalClientes;
	private JTextField txtTotalAlquileres;
	private JTextField txtGanancias;

	private Puerto puerto;

	/**
	 * Create the dialog.
	 */
	public Ganancias(Puerto puerto) {
		this.puerto = puerto;
		setTitle("Resumen - Ganancias");
		setBounds(100, 100, 450, 300);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel.setBounds(10, 11, 414, 206);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblTotalDeBarcos = new JLabel("Total de barcos:");
				lblTotalDeBarcos.setBounds(10, 51, 113, 14);
				panel.add(lblTotalDeBarcos);
			}

			txtTotalBarcos = new JTextField();
			txtTotalBarcos.setHorizontalAlignment(SwingConstants.RIGHT);
			txtTotalBarcos.setText("0");
			txtTotalBarcos.setEditable(false);
			txtTotalBarcos.setBounds(139, 48, 86, 20);
			panel.add(txtTotalBarcos);
			txtTotalBarcos.setColumns(10);

			JLabel lblTotalDeClientes = new JLabel("Total de clientes:");
			lblTotalDeClientes.setBounds(10, 76, 113, 14);
			panel.add(lblTotalDeClientes);

			txtTotalClientes = new JTextField();
			txtTotalClientes.setHorizontalAlignment(SwingConstants.RIGHT);
			txtTotalClientes.setText("0");
			txtTotalClientes.setEditable(false);
			txtTotalClientes.setBounds(139, 73, 86, 20);
			panel.add(txtTotalClientes);
			txtTotalClientes.setColumns(10);

			JLabel lblTotalDeAlquileres = new JLabel("Total de alquileres:");
			lblTotalDeAlquileres.setBounds(10, 101, 113, 14);
			panel.add(lblTotalDeAlquileres);

			txtTotalAlquileres = new JTextField();
			txtTotalAlquileres.setHorizontalAlignment(SwingConstants.RIGHT);
			txtTotalAlquileres.setText("0");
			txtTotalAlquileres.setEditable(false);
			txtTotalAlquileres.setBounds(139, 98, 86, 20);
			panel.add(txtTotalAlquileres);
			txtTotalAlquileres.setColumns(10);

			JLabel lblGanaciasalquileres = new JLabel("Ganacias (alquileres):");
			lblGanaciasalquileres.setBounds(10, 178, 136, 14);
			panel.add(lblGanaciasalquileres);

			txtGanancias = new JTextField();
			txtGanancias.setHorizontalAlignment(SwingConstants.CENTER);
			txtGanancias.setText("RD$ 0.00");
			txtGanancias.setForeground(Color.BLUE);
			txtGanancias.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtGanancias.setEditable(false);
			txtGanancias.setBounds(156, 175, 248, 20);
			panel.add(txtGanancias);
			txtGanancias.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
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

		cargarDatos();
	}

	private void cargarDatos() {
		// TODO Auto-generated method stub
		txtTotalBarcos.setText("" + puerto.getCantBarcos());
		txtTotalClientes.setText("" + puerto.getCantClientes());
		txtTotalAlquileres.setText("" + puerto.getCantAlquileres());

		txtGanancias.setText(String.format("RD$ %.2f", puerto.obtenerGanancias()));
	}
}
