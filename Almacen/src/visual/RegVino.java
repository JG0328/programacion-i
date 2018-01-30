package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RegVino extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVino dialog = new RegVino();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVino() {
		setResizable(false);
		setTitle("Registro de vinos");
		setBounds(100, 100, 390, 294);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblIdentificador = new JLabel("Identificador:");
			lblIdentificador.setBounds(10, 23, 89, 14);
			panel.add(lblIdentificador);
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(10, 41, 165, 21);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(23, 71, 70, 14);
			panel.add(lblNombre);
			
			textField_1 = new JTextField();
			textField_1.setBounds(10, 95, 350, 21);
			panel.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel lblPas = new JLabel("Pa\u00EDs:");
			lblPas.setBounds(23, 127, 46, 14);
			panel.add(lblPas);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Chile", "Espa\u00F1a", "Italia", "Francia", "Portugal", "Argentina", "Usa"}));
			comboBox.setBounds(10, 150, 165, 23);
			panel.add(comboBox);
			
			JLabel lblEntrega = new JLabel("Entrega:");
			lblEntrega.setBounds(199, 129, 52, 14);
			panel.add(lblEntrega);
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(1, 1, 180, 1));
			spinner.setBounds(195, 150, 165, 23);
			panel.add(spinner);
			
			JLabel label = new JLabel("*");
			label.setForeground(Color.RED);
			label.setBounds(10, 73, 13, 14);
			panel.add(label);
			
			JLabel label_1 = new JLabel("*");
			label_1.setForeground(Color.RED);
			label_1.setBounds(10, 129, 13, 14);
			panel.add(label_1);
		}
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblLos = new JLabel("Los");
		lblLos.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		panel.add(lblLos);
		
		JLabel label = new JLabel("*");
		label.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		label.setForeground(Color.RED);
		panel.add(label);
		
		JLabel lblIndicanCamposObligatorios = new JLabel("indican campos obligatorios");
		lblIndicanCamposObligatorios.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		panel.add(lblIndicanCamposObligatorios);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
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
}
