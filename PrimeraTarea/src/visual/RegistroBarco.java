package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import logico.Barco;
import logico.Puerto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class RegistroBarco extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMatricula;
	private JTextField txtNombre;
	private Puerto puerto;
	private Barco barco;

	private JSpinner spnAgno;
	private JComboBox cbxTipo;

	private JButton btnRegistrar;
	private JSpinner spnEslora;

	/**
	 * Create the dialog.
	 */
	public RegistroBarco(Puerto puerto, Barco barco) {
		setResizable(false);

		this.puerto = puerto;
		this.barco = barco;

		setTitle("Registro de Barco");
		setBounds(100, 100, 450, 310);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel.setBounds(10, 25, 414, 198);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("* Matr\u00EDcula:");
				lblNewLabel.setBounds(10, 21, 65, 14);
				panel.add(lblNewLabel);
			}

			txtMatricula = new JTextField();
			txtMatricula.setBounds(10, 37, 125, 23);
			panel.add(txtMatricula);
			txtMatricula.setColumns(10);

			JLabel lblNombre = new JLabel("* Nombre:");
			lblNombre.setBounds(10, 67, 65, 14);
			panel.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(10, 84, 394, 23);
			panel.add(txtNombre);

			JLabel lblNewLabel_1 = new JLabel("* A\u00F1o:");
			lblNewLabel_1.setBounds(10, 114, 65, 14);
			panel.add(lblNewLabel_1);

			spnAgno = new JSpinner();
			spnAgno.setModel(new SpinnerNumberModel(1976, 1976, 2017, 1));
			spnAgno.setBounds(10, 132, 80, 23);
			panel.add(spnAgno);

			JLabel lblNewLabel_2 = new JLabel("* Tipo:");
			lblNewLabel_2.setBounds(117, 114, 65, 14);
			panel.add(lblNewLabel_2);

			cbxTipo = new JComboBox();
			cbxTipo.setModel(
					new DefaultComboBoxModel(new String[] { "<Seleccione>", "Velero", "Bote", "Lancha a motor" }));
			cbxTipo.setBounds(117, 132, 160, 23);
			panel.add(cbxTipo);

			JLabel lblEslora = new JLabel("* Eslora:");
			lblEslora.setBounds(288, 114, 80, 14);
			panel.add(lblEslora);

			JLabel lblM = new JLabel("m");
			lblM.setBounds(391, 136, 15, 14);
			panel.add(lblM);

			spnEslora = new JSpinner();
			spnEslora.setModel(new SpinnerNumberModel(2, 2, 50, 1));
			spnEslora.setBounds(287, 132, 100, 23);
			panel.add(spnEslora);
		}

		JLabel lblNewLabel_3 = new JLabel("Los * indican campos obligatorios");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(266, 11, 158, 14);
		contentPanel.add(lblNewLabel_3);
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
						if(txtMatricula.getText().equalsIgnoreCase(""))
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar una matrícula", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(txtNombre.getText().equalsIgnoreCase(""))
						{
							JOptionPane.showMessageDialog(null, "Debe ingresar un nombre", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(cbxTipo.getSelectedIndex() == 0)
						{
							JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						String matricula = txtMatricula.getText();
						String nombre = txtNombre.getText();
						int fabricacion = new Integer(spnAgno.getValue().toString());
						String tipo = cbxTipo.getSelectedItem().toString();
						int eslora = new Integer(spnEslora.getValue().toString());

						if (barco == null) {
							Barco barco = new Barco(matricula, nombre, fabricacion, tipo, eslora);
							puerto.insertarBarco(barco);

							// System.out.println(barco.getMatricula());

							JOptionPane.showMessageDialog(null, "Registro de barco exitoso", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							limpiar();
						} else {
							barco.setMatricula(matricula);
							barco.setNombre(nombre);
							barco.setFabricacion(fabricacion);
							barco.setTipo(tipo);
							barco.setEslora(eslora);
							
							//System.out.println(barco.getMatricula());

							JOptionPane.showMessageDialog(null, "Modificación de barco exitosa", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							ListarBarcos.cargarTabla();

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

		cargarInfo();
	}

	private void cargarInfo() {
		// TODO Auto-generated method stub
		if (barco != null) {
			setTitle("Modificación de Barco");
			btnRegistrar.setText("Modificar");
			txtMatricula.setText(barco.getMatricula());
			txtNombre.setText(barco.getNombre());
			spnAgno.setValue(barco.getFabricacion());
			// cbxTipo.setSelectedIndex(puerto.buscarBarcoID(barco.getMatricula())
			// + 1);

			for (int i = 0; i < cbxTipo.getItemCount(); i++) {
				if (cbxTipo.getItemAt(i).toString().equalsIgnoreCase(barco.getTipo())) {
					cbxTipo.setSelectedIndex(i);
					i = cbxTipo.getItemCount();
				}
			}

			spnEslora.setValue(barco.getEslora());
		}
	}

	private void limpiar() {
		txtMatricula.setText("");
		txtNombre.setText("");
		spnAgno.setValue(1976);
		cbxTipo.setSelectedIndex(0);
		spnEslora.setValue(2);
	}
}
