package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Articulo;
import logico.Biblioteca;
import logico.Libro;
import logico.Publicacion;
import logico.Revista;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.awt.event.ActionEvent;

public class RegPublicacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtTitulo;
	private JTextField txtNumero;
	private JTextField txtAutorLibro;
	private JTextField txtArbitro;
	private JTextField txtAutorArticulo;

	private JPanel pnlLibro;
	private JPanel pnlArticulo;
	private JPanel pnlRevista;

	private JRadioButton rdbtnLibro;
	private JRadioButton rdbtnArticulo;
	private JRadioButton rdbtnRevista;

	private JSpinner spnAgno;
	private JSpinner spnEjemplares;

	private JComboBox cbxMateria;
	private JComboBox cbxEditorial;

	/**
	 * Create the dialog.
	 */
	public RegPublicacion() {
		setResizable(false);
		setTitle("Registro de Publicaci\u00F3n");
		setBounds(100, 100, 480, 390);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 454, 130);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 29, 46, 14);
		panel.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(61, 25, 170, 23);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setBounds(10, 60, 46, 14);
		panel.add(lblTtulo);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(61, 56, 383, 23);
		panel.add(txtTitulo);
		txtTitulo.setColumns(10);

		JLabel lblMateria = new JLabel("Materia:");
		lblMateria.setBounds(10, 93, 46, 14);
		panel.add(lblMateria);

		cbxMateria = new JComboBox();
		cbxMateria.setModel(new DefaultComboBoxModel(
				new String[] { "<Seleccione>", "Espa\u00F1ol", "Historia", "Matem\u00E1ticas", "Biolog\u00EDa" }));
		cbxMateria.setBounds(61, 89, 148, 23);
		panel.add(cbxMateria);

		JLabel lblCantidadDeEjemplares = new JLabel("Cantidad de Ejemplares:");
		lblCantidadDeEjemplares.setBounds(219, 93, 139, 14);
		panel.add(lblCantidadDeEjemplares);

		spnEjemplares = new JSpinner();
		spnEjemplares.setBounds(362, 89, 82, 23);
		panel.add(spnEjemplares);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Tipo de publicaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 152, 454, 60);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		rdbtnLibro = new JRadioButton("Libro");
		rdbtnLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnArticulo.setSelected(false);
				rdbtnRevista.setSelected(false);
				pnlLibro.setVisible(true);
				pnlArticulo.setVisible(false);
				pnlRevista.setVisible(false);
			}
		});
		rdbtnLibro.setSelected(true);
		rdbtnLibro.setBounds(59, 21, 55, 23);
		panel_1.add(rdbtnLibro);

		rdbtnArticulo = new JRadioButton("Art\u00EDculo");
		rdbtnArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnLibro.setSelected(false);
				rdbtnRevista.setSelected(false);
				pnlLibro.setVisible(false);
				pnlArticulo.setVisible(true);
				pnlRevista.setVisible(false);
			}
		});
		rdbtnArticulo.setBounds(173, 21, 80, 23);
		panel_1.add(rdbtnArticulo);

		rdbtnRevista = new JRadioButton("Revista");
		rdbtnRevista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnArticulo.setSelected(false);
				rdbtnLibro.setSelected(false);
				pnlLibro.setVisible(false);
				pnlArticulo.setVisible(false);
				pnlRevista.setVisible(true);
			}
		});
		rdbtnRevista.setBounds(312, 21, 80, 23);
		panel_1.add(rdbtnRevista);

		pnlLibro = new JPanel();
		pnlLibro.setBounds(10, 223, 454, 84);
		contentPanel.add(pnlLibro);
		pnlLibro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlLibro.setLayout(null);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(10, 15, 65, 14);
		pnlLibro.add(lblEditorial);

		cbxEditorial = new JComboBox();
		cbxEditorial.setModel(new DefaultComboBoxModel(
				new String[] { "<Seleccione>", "Santillana", "Susaeta", "Coral", "Del Bolsillo" }));
		cbxEditorial.setBounds(75, 11, 369, 23);
		pnlLibro.add(cbxEditorial);

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 50, 65, 14);
		pnlLibro.add(lblAutor);

		txtAutorLibro = new JTextField();
		txtAutorLibro.setBounds(75, 46, 369, 23);
		pnlLibro.add(txtAutorLibro);
		txtAutorLibro.setColumns(10);

		pnlArticulo = new JPanel();
		pnlArticulo.setBounds(10, 223, 454, 84);
		contentPanel.add(pnlArticulo);
		pnlArticulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlArticulo.setLayout(null);

		JLabel lblArbitro = new JLabel("\u00C1rbitro:");
		lblArbitro.setBounds(10, 15, 55, 14);
		pnlArticulo.add(lblArbitro);

		txtArbitro = new JTextField();
		txtArbitro.setBounds(75, 11, 369, 23);
		pnlArticulo.add(txtArbitro);
		txtArbitro.setColumns(10);

		JLabel lblAutor_1 = new JLabel("Autor:");
		lblAutor_1.setBounds(10, 50, 55, 14);
		pnlArticulo.add(lblAutor_1);

		txtAutorArticulo = new JTextField();
		txtAutorArticulo.setBounds(75, 46, 369, 23);
		pnlArticulo.add(txtAutorArticulo);
		txtAutorArticulo.setColumns(10);

		pnlRevista = new JPanel();
		pnlRevista.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlRevista.setBounds(10, 223, 454, 84);
		contentPanel.add(pnlRevista);
		pnlRevista.setLayout(null);

		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(10, 35, 32, 14);
		pnlRevista.add(lblAo);

		spnAgno = new JSpinner();
		spnAgno.setModel(new SpinnerDateModel(new Date(1507521600000L), null, null, Calendar.YEAR));
		JSpinner.DateEditor de = new JSpinner.DateEditor(spnAgno, "yyyy");
		spnAgno.setEditor(de);
		spnAgno.setBounds(52, 31, 146, 23);
		pnlRevista.add(spnAgno);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(214, 35, 59, 14);
		pnlRevista.add(lblNmero);

		txtNumero = new JTextField();
		txtNumero.setBounds(280, 31, 164, 23);
		pnlRevista.add(txtNumero);
		txtNumero.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Publicacion aux = null;

						String id = txtId.getText();
						String titulo = txtTitulo.getText();
						String materia = cbxMateria.getSelectedItem().toString();
						int cantidad = Integer.valueOf(spnEjemplares.getValue().toString());
						boolean estado = (cantidad > 0) ? true : false;

						if (rdbtnLibro.isSelected()) {
							String autor = txtAutorLibro.getText();
							String editorial = cbxEditorial.getSelectedItem().toString();

							aux = new Libro(id, titulo, materia, cantidad, estado, editorial, autor);
						}
						if (rdbtnRevista.isSelected()) {
							String[] split = spnAgno.getValue().toString().split("BOT ");
							int agno = Integer.valueOf(split[1]);
							String numero = txtNumero.getText();
							aux = new Revista(id, titulo, materia, cantidad, estado, agno, numero);
						}
						if (rdbtnArticulo.isSelected()) {
							String autor = txtAutorArticulo.getText();
							String arbitro = txtArbitro.getText();
							aux = new Articulo(id, titulo, materia, cantidad, estado, arbitro, autor);
						}

						Biblioteca.getInstance().insertarPublicacion(aux);

						JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información",
								JOptionPane.INFORMATION_MESSAGE);

						clear();
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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

		setDefaultValues();
	}

	private void setDefaultValues() {
		rdbtnLibro.setSelected(true);
		rdbtnArticulo.setSelected(false);
		rdbtnRevista.setSelected(false);

		pnlLibro.setVisible(true);
		pnlArticulo.setVisible(false);
		pnlRevista.setVisible(false);
	}

	private void clear() {
		txtId.setText("");
		txtTitulo.setText("");
		cbxMateria.setSelectedIndex(0);
		spnEjemplares.setValue(new Integer(0));

		cbxEditorial.setSelectedIndex(0);
		txtAutorLibro.setText("");

		txtArbitro.setText("");
		txtAutorArticulo.setText("");

		spnAgno.setModel(new SpinnerDateModel(new Date(1507521600000L), null, null, Calendar.YEAR));
		JSpinner.DateEditor de = new JSpinner.DateEditor(spnAgno, "yyyy");
		spnAgno.setEditor(de);
		txtNumero.setText("");

		setDefaultValues();
	}
}
