package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import logico.Almacen;
import logico.Suministrador;
import logico.Vino;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarVino extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdent;
	private JTextField txtNombre;
	private JSpinner spnCosecha;
	private JSpinner spnMini;
	private JSpinner spnMax;
	private JSpinner spinner;
	private JComboBox cbxTipo;
	private Almacen alma;
	private JComboBox cbxSupli;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public RegistrarVino(Almacen alma) {
		this.alma = alma;
		setTitle("Registro de Vinos");
		setBounds(100, 100, 445, 335);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 413, 246);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 74, 85, 14);
			panel.add(lblNombre);
		}
		{
			JLabel lblIdentificador = new JLabel("Identificador:");
			lblIdentificador.setBounds(10, 20, 100, 14);
			panel.add(lblIdentificador);
		}
		{
			JLabel lblCosecha = new JLabel("Cosecha:");
			lblCosecha.setBounds(10, 131, 85, 14);
			panel.add(lblCosecha);
		}
		{
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(227, 131, 46, 14);
			panel.add(lblTipo);
		}
		{
			JLabel lblSuplidor = new JLabel("Suplidor:");
			lblSuplidor.setBounds(227, 20, 79, 14);
			panel.add(lblSuplidor);
		}
		{
			JLabel lblDispMnima = new JLabel("Disp. M\u00EDnima:");
			lblDispMnima.setBounds(10, 186, 100, 14);
			panel.add(lblDispMnima);
		}
		{
			JLabel lblDispMxima = new JLabel("Disp. M\u00E1xima:");
			lblDispMxima.setBounds(150, 186, 100, 14);
			panel.add(lblDispMxima);
		}
		{
			JLabel lblDispReal = new JLabel("Disp. Real:");
			lblDispReal.setBounds(293, 186, 79, 14);
			panel.add(lblDispReal);
		}
		{
			txtIdent = new JTextField();
			txtIdent.setEditable(false);
			txtIdent.setBounds(10, 43, 174, 23);
			txtIdent.setText("V-"+(Vino.getCodigo()+1));
			panel.add(txtIdent);
			txtIdent.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setBounds(10, 97, 394, 23);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			spnCosecha = new JSpinner();
			spnCosecha.setModel(new SpinnerNumberModel(1600, 1600, 3000, 1));
			spnCosecha.setBounds(10, 149, 174, 23);
			panel.add(spnCosecha);
		}
		{
			cbxTipo = new JComboBox();
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Tinto", "Blanco", "Rosado"}));
			cbxTipo.setBounds(227, 149, 174, 23);
			panel.add(cbxTipo);
		}
		{
			spnMini = new JSpinner();
			spnMini.setModel(new SpinnerNumberModel(1, 1, 100, 1));
			spnMini.setBounds(10, 208, 100, 23);
			panel.add(spnMini);
		}
		{
			spnMax = new JSpinner();
			spnMax.setModel(new SpinnerNumberModel(1, 1, 120, 1));
			spnMax.setBounds(150, 208, 100, 23);
			panel.add(spnMax);
		}
		{
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(1, 1, 120, 1));
			spinner.setBounds(301, 208, 100, 23);
			panel.add(spinner);
		}
		{
			cbxSupli = new JComboBox();
			cbxSupli.setBounds(227, 43, 174, 23);
			panel.add(cbxSupli);
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
						String identificador = txtIdent.getText();
						String nombre = txtNombre.getText();
						int cocecha = Integer.valueOf(spnCosecha.getValue().toString());
						int dispMin = Integer.valueOf(spnMini.getValue().toString());
						int dispMax = Integer.valueOf(spnMax.getValue().toString());
						int dispReal = Integer.valueOf(spinner.getValue().toString());
						String tipo = cbxTipo.getSelectedItem().toString();
						Vino vino = new Vino(identificador, nombre, cocecha, tipo, dispMin, dispMax, dispReal);
						alma.insertVino(vino);
						System.out.println(alma.getCantVino());
						String identSumi = cbxSupli.getSelectedItem().toString();
						
						Suministrador aux = alma.buscarSuminitrador(identSumi.substring(0, identSumi.lastIndexOf("-")));
						aux.insertarVino(vino);
						System.out.println(aux.getCantVino());
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		loadSuplidores();
	}

	private void loadSuplidores() {
		for (int i = 0; i < alma.getCantSumi(); i++) {
			cbxSupli.addItem(new String(alma.getMisSuminis()[i].getIdentificador()+"-"+alma.getMisSuminis()[i].getNombre()));	
		}
		cbxSupli.insertItemAt("<Seleccione>", 0);
		cbxSupli.setSelectedIndex(0);
		
	}
}
