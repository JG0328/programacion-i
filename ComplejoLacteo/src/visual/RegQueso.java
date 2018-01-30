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

import logico.Cilindrico;
import logico.Complejo;
import logico.Esferico;
import logico.Hueco;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class RegQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JSpinner spnPrecioBase;
	private JSpinner spnPrecioUnitario;
	private JSpinner spnRadio;

	private JRadioButton rdbtnEsferico;
	private JRadioButton rdbtnCilindrico;
	private JRadioButton rdbtnCilindricoHueco;

	private JSpinner spnLongitud;
	private JSpinner spnRadioInterior;
	private JSpinner spnLongitudHueco;

	private JPanel pnlCilindrico;
	private JPanel pnlHueco;
	private JTextField txtId;
	private JTextField txtNombre;

	/**
	 * Create the dialog.
	 */
	public RegQueso() {
		setResizable(false);
		setTitle("Registro de Queso");
		setBounds(100, 100, 450, 470);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel.setBounds(10, 11, 424, 209);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblPrecioBase = new JLabel("Precio base:");
				lblPrecioBase.setBounds(10, 90, 92, 14);
				panel.add(lblPrecioBase);
			}
			{
				JLabel lblPrecioUnitario = new JLabel("Precio unitario:");
				lblPrecioUnitario.setBounds(10, 124, 92, 14);
				panel.add(lblPrecioUnitario);
			}
			{
				JLabel lblRadio = new JLabel("Radio:");
				lblRadio.setBounds(10, 158, 92, 14);
				panel.add(lblRadio);
			}

			spnPrecioBase = new JSpinner();
			spnPrecioBase.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
			spnPrecioBase.setBounds(168, 86, 190, 23);
			panel.add(spnPrecioBase);

			spnPrecioUnitario = new JSpinner();
			spnPrecioUnitario.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
			spnPrecioUnitario.setBounds(168, 120, 190, 23);
			panel.add(spnPrecioUnitario);

			spnRadio = new JSpinner();
			spnRadio.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
			spnRadio.setBounds(130, 154, 228, 23);
			panel.add(spnRadio);

			JLabel lblCm = new JLabel("cm");
			lblCm.setBounds(368, 158, 46, 14);
			panel.add(lblCm);

			JLabel lblRd = new JLabel("RD$");
			lblRd.setBounds(130, 124, 46, 14);
			panel.add(lblRd);

			JLabel label = new JLabel("RD$");
			label.setBounds(130, 90, 46, 14);
			panel.add(label);

			JLabel lblId = new JLabel("ID:");
			lblId.setBounds(10, 23, 46, 14);
			panel.add(lblId);

			txtId = new JTextField();
			txtId.setBounds(130, 19, 228, 23);
			panel.add(txtId);
			txtId.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 57, 92, 14);
			panel.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setBounds(130, 53, 228, 23);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Tipos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 231, 424, 69);
			contentPanel.add(panel);
			panel.setLayout(null);

			rdbtnEsferico = new JRadioButton("Esf\u00E9rico");
			rdbtnEsferico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pnlCilindrico.setVisible(false);
					pnlHueco.setVisible(false);

					rdbtnEsferico.setSelected(true);
					rdbtnCilindrico.setSelected(false);
					rdbtnCilindricoHueco.setSelected(false);
				}
			});
			rdbtnEsferico.setSelected(true);
			rdbtnEsferico.setBounds(18, 23, 110, 23);
			panel.add(rdbtnEsferico);

			rdbtnCilindrico = new JRadioButton("Cil\u00EDndrico");
			rdbtnCilindrico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pnlCilindrico.setVisible(true);
					pnlHueco.setVisible(false);

					rdbtnEsferico.setSelected(false);
					rdbtnCilindrico.setSelected(true);
					rdbtnCilindricoHueco.setSelected(false);
				}
			});
			rdbtnCilindrico.setBounds(146, 23, 110, 23);
			panel.add(rdbtnCilindrico);

			rdbtnCilindricoHueco = new JRadioButton("Cil\u00EDndrico Hueco");
			rdbtnCilindricoHueco.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pnlCilindrico.setVisible(false);
					pnlHueco.setVisible(true);

					rdbtnEsferico.setSelected(false);
					rdbtnCilindrico.setSelected(false);
					rdbtnCilindricoHueco.setSelected(true);
				}
			});
			rdbtnCilindricoHueco.setBounds(274, 23, 129, 23);
			panel.add(rdbtnCilindricoHueco);
		}

		pnlHueco = new JPanel();
		pnlHueco.setBounds(10, 311, 424, 82);
		contentPanel.add(pnlHueco);
		pnlHueco.setLayout(null);
		pnlHueco.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JLabel lblRadioInterior = new JLabel("Radio interior:");
		lblRadioInterior.setBounds(10, 46, 83, 14);
		pnlHueco.add(lblRadioInterior);

		spnRadioInterior = new JSpinner();
		spnRadioInterior.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spnRadioInterior.setBounds(97, 42, 284, 23);
		pnlHueco.add(spnRadioInterior);

		JLabel label_1 = new JLabel("cm");
		label_1.setBounds(387, 46, 29, 14);
		pnlHueco.add(label_1);

		JLabel lblLongitud_1 = new JLabel("Longitud:");
		lblLongitud_1.setBounds(10, 17, 61, 14);
		pnlHueco.add(lblLongitud_1);

		spnLongitudHueco = new JSpinner();
		spnLongitudHueco.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spnLongitudHueco.setBounds(97, 13, 284, 23);
		pnlHueco.add(spnLongitudHueco);

		JLabel label_2 = new JLabel("cm");
		label_2.setBounds(387, 17, 29, 14);
		pnlHueco.add(label_2);

		pnlCilindrico = new JPanel();
		pnlCilindrico.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlCilindrico.setBounds(10, 311, 424, 82);
		contentPanel.add(pnlCilindrico);
		pnlCilindrico.setLayout(null);

		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(10, 34, 100, 14);
		pnlCilindrico.add(lblLongitud);

		spnLongitud = new JSpinner();
		spnLongitud.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spnLongitud.setBounds(120, 30, 255, 23);
		pnlCilindrico.add(spnLongitud);

		JLabel label = new JLabel("cm");
		label.setBounds(385, 34, 29, 14);
		pnlCilindrico.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String id = txtId.getText();
						String nombre = txtNombre.getText();
						float precioBase = (float) (Integer.valueOf(spnPrecioBase.getValue().toString()));
						float precioUnitario = (float) (Integer.valueOf(spnPrecioUnitario.getValue().toString()));
						float radio = (float) (Integer.valueOf(spnRadio.getValue().toString()));
						float longitud = 0.0f;
						if (rdbtnCilindrico.isSelected()) {
							longitud = (float) (Integer.valueOf(spnLongitud.getValue().toString()));
						} else {
							longitud = (float) (Integer.valueOf(spnLongitudHueco.getValue().toString()));
						}
						float radioInterior = (float) (Integer.valueOf(spnRadioInterior.getValue().toString()));
						String tipo = "";

						if (rdbtnEsferico.isSelected()) {
							tipo = "Esférico";
							Esferico esf = new Esferico(id, nombre, precioBase, precioUnitario, radio, tipo);
							Complejo.getInstance().insertQueso(esf);

							JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							loadDefData();
						}

						if (rdbtnCilindrico.isSelected()) {
							tipo = "Cilíndrico";
							Cilindrico cil = new Cilindrico(id, nombre, precioBase, precioUnitario, radio, tipo,
									longitud);
							Complejo.getInstance().insertQueso(cil);

							JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							loadDefData();
						}

						if (rdbtnCilindricoHueco.isSelected()) {
							if (radioInterior >= radio) {
								JOptionPane.showMessageDialog(null, "El radio interior debe ser menor que el radio",
										"Aviso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								tipo = "Cilíndrico Hueco";
								Hueco hue = new Hueco(id, nombre, precioBase, precioUnitario, radio, tipo, longitud,
										radioInterior);
								Complejo.getInstance().insertQueso(hue);

								JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información",
										JOptionPane.INFORMATION_MESSAGE);
								loadDefData();
							}
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
		}

		loadDefData();
	}

	private void loadDefData() {
		txtId.setText("");
		txtNombre.setText("");

		rdbtnEsferico.setSelected(true);
		rdbtnCilindrico.setSelected(false);
		rdbtnCilindricoHueco.setSelected(false);

		pnlCilindrico.setVisible(false);
		pnlHueco.setVisible(false);

		spnRadio.setValue(1);
		spnPrecioBase.setValue(1);
		spnPrecioUnitario.setValue(1);
		spnLongitud.setValue(1);
		spnRadioInterior.setValue(1);
		spnLongitudHueco.setValue(1);
	}
}
