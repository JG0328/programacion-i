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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.TrayIcon.MessageType;

public class RegSuplidor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdentificador;
	private JComboBox cbxPais;
	private JSpinner spnEntrega;
	private JTextField txtNombre;
	private Almacen alma;
	private Suministrador miSumi;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 * @param alma 
	 */
	public RegSuplidor(Almacen alma, Suministrador sumi) {
		this.alma = alma;
		this.miSumi = sumi;
		setResizable(false);
		if(miSumi == null){
		setTitle("Registro de Suplidores");
		}else{
		  setTitle("Modificar Suplidor");	
		}
		setBounds(100, 100, 450, 283);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 23, 424, 184);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setBounds(10, 27, 86, 14);
		panel.add(lblIdentificador);
		
		txtIdentificador = new JTextField();
		txtIdentificador.setEditable(false);
		txtIdentificador.setBounds(10, 48, 86, 23);
		if(sumi == null){
		txtIdentificador.setText("Supli-"+(Suministrador.getCantSuplidores()+1));
		}else{
			txtIdentificador.setText(sumi.getIdentificador());
		}
		panel.add(txtIdentificador);
		txtIdentificador.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 76, 99, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 101, 404, 23);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblPas = new JLabel("Pa\u00EDs:");
		lblPas.setBounds(20, 129, 58, 14);
		panel.add(lblPas);
		
		cbxPais = new JComboBox();
		cbxPais.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Chile", "Espa\u00F1a", "Francia", "Italia", "Usa", "Portugal"}));
		cbxPais.setBounds(10, 154, 198, 23);
		panel.add(cbxPais);
		
		JLabel lblEntrega = new JLabel("Entrega:");
		lblEntrega.setBounds(231, 129, 71, 14);
		panel.add(lblEntrega);
		
		spnEntrega = new JSpinner();
		spnEntrega.setModel(new SpinnerNumberModel(1, 1, 180, 1));
		spnEntrega.setBounds(231, 154, 183, 23);
		panel.add(spnEntrega);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(10, 76, 11, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(10, 129, 11, 14);
		panel.add(label_2);
		
		JLabel lblLos = new JLabel("Los");
		lblLos.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblLos.setBounds(266, 11, 22, 14);
		contentPanel.add(lblLos);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(287, 11, 11, 14);
		contentPanel.add(label);
		
		JLabel lblIndicanCamposObligatorios = new JLabel("indican campos obligatorios");
		lblIndicanCamposObligatorios.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblIndicanCamposObligatorios.setBounds(298, 11, 146, 14);
		contentPanel.add(lblIndicanCamposObligatorios);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				if(sumi == null){
				 okButton.setText("Registrar");
				}else{
				 okButton.setText("Modificar");	
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(sumi == null){
						System.out.println(alma.getCantSumi());
						String identificador = txtIdentificador.getText();
						String nombre = txtNombre.getText();
						String pais = cbxPais.getSelectedItem().toString();
						int entrega = Integer.valueOf(spnEntrega.getValue().toString());
						Suministrador sumi = new Suministrador(identificador, nombre, pais, entrega);
						alma.insertSuministrador(sumi);
						JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
						clean();
						System.out.println(alma.getCantSumi());
						}else{
							sumi.setNombre(txtNombre.getText());
							sumi.setPais(cbxPais.getSelectedItem().toString());
							sumi.setEntrega(Integer.valueOf(spnEntrega.getValue().toString()));
							alma.modificarSuministrador(sumi);
							JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
							ListarSuminitradores.loadTable();
							dispose();
						}
						//System.out.println(alma.getCantSumi());
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
		loadSuministrador(sumi);
	}
	
	private void loadSuministrador(Suministrador sumi) {
		if(sumi!=null){
			txtNombre.setText(sumi.getNombre());
			int anIndex = -1;
			for (int i = 0; i < cbxPais.getItemCount(); i++) {
			  if(cbxPais.getItemAt(i).toString().contentEquals(sumi.getPais())){
				  anIndex = i;
			  }	
			}
		    cbxPais.setSelectedIndex(anIndex);
		    spnEntrega.setValue(new Integer(sumi.getEntrega()));
		}
		
	}

	private void clean() {
		txtNombre.setText("");
		txtIdentificador.setText("Supli-"+(Suministrador.getCantSuplidores()+1));
		cbxPais.setSelectedIndex(0);
		spnEntrega.setValue(new Integer(1));

		
	}
}
