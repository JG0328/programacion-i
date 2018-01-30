package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Articulo;
import logico.Biblioteca;
import logico.Cliente;
import logico.Libro;
import logico.Publicacion;
import logico.Revista;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

public class RegPrestamo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;

	private int mode;
	private String materia;

	private JList listBiblio;
	private JList listCliente;

	private JButton btnPrestar;
	private JButton btnDevolver;

	private Cliente client;

	private int biblioIndex;
	private int clientIndex;

	/**
	 * Create the dialog.
	 */
	public RegPrestamo() {
		setResizable(false);
		setTitle("Registro de Pr\u00E9stamo");
		setBounds(100, 100, 500, 500);

		materia = "<Todas>";
		mode = 0;

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del Cliente", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(10, 11, 474, 128);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setBounds(10, 23, 54, 14);
		panel.add(lblCdula);

		txtCedula = new JTextField();
		txtCedula.setBounds(74, 19, 291, 23);
		panel.add(txtCedula);
		txtCedula.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client = Biblioteca.getInstance().findClientById(txtCedula.getText());
				if (client != null) {
					txtNombre.setText(client.getNombre());
					txtDireccion.setText(client.getDireccion());

					loadClientList();
				}
			}
		});
		btnBuscar.setBounds(375, 19, 89, 23);
		panel.add(btnBuscar);

		JLabel lblNomre = new JLabel("Nombre:");
		lblNomre.setBounds(10, 56, 65, 14);
		panel.add(lblNomre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(73, 52, 391, 23);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(73, 85, 391, 23);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 89, 65, 14);
		panel.add(lblDireccin);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Realizaci\u00F3n de Pr\u00E9stamo", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 150, 474, 277);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 25, 46, 14);
		panel_1.add(lblTipo);

		JComboBox cbxTipo = new JComboBox();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = cbxTipo.getSelectedIndex();
				loadPubliList();
			}
		});
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] { "<Todos>", "Libro", "Art\u00EDculo", "Revista" }));
		cbxTipo.setBounds(43, 21, 117, 23);
		panel_1.add(cbxTipo);

		JLabel lblMateria = new JLabel("Materia:");
		lblMateria.setBounds(184, 25, 56, 14);
		panel_1.add(lblMateria);

		JComboBox cbxMateria = new JComboBox();
		cbxMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				materia = cbxMateria.getSelectedItem().toString();
				loadPubliList();
			}
		});
		cbxMateria.setModel(new DefaultComboBoxModel(
				new String[] { "<Todas>", "Espa\u00F1ol", "Historia", "Matem\u00E1ticas", "Biolog\u00EDa" }));
		cbxMateria.setBounds(238, 21, 226, 23);
		panel_1.add(cbxMateria);

		JLabel lblBiblioteca = new JLabel("Biblioteca:");
		lblBiblioteca.setBounds(10, 60, 76, 14);
		panel_1.add(lblBiblioteca);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(278, 60, 66, 14);
		panel_1.add(lblCliente);

		listBiblio = new JList();
		listBiblio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBiblio.setBounds(10, 80, 186, 186);
		panel_1.add(listBiblio);

		listCliente = new JList();
		listCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCliente.setBounds(278, 80, 186, 186);
		panel_1.add(listCliente);

		btnPrestar = new JButton(">");
		btnPrestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (client != null && !listBiblio.isSelectionEmpty()) {
					if (client.getMisPrestamos().size() < 5) {
						String[] split = listBiblio.getSelectedValue().toString().split(" ");
						String fechaDev = "";

						SpinnerDateModel sModel = new SpinnerDateModel(new Date(), new Date(1507521600000L), null,
								Calendar.DAY_OF_YEAR);
						JSpinner spinner = new JSpinner(sModel);

						int option = JOptionPane.showOptionDialog(null, spinner, "Fecha de devolución",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (option == JOptionPane.OK_OPTION) {
							fechaDev = spinner.getValue().toString();
							Biblioteca.getInstance().setPrestamo(client, split[0], fechaDev);

						}
					} else {
						JOptionPane.showMessageDialog(null, "El cliente no puede tener más de 5 préstamos activos",
								"Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
					loadPubliList();
					loadClientList();
				}
			}
		});
		btnPrestar.setBounds(216, 133, 41, 23);
		panel_1.add(btnPrestar);

		btnDevolver = new JButton("<");
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (client != null && !listCliente.isSelectionEmpty()) {
					String[] split = listCliente.getSelectedValue().toString().split(" ");
					Publicacion publi = Biblioteca.getInstance().findPublicacionById(split[0]);
					Biblioteca.getInstance().returnPrestamo(client, publi);
				}

				loadPubliList();
				loadClientList();
			}
		});
		btnDevolver.setBounds(216, 167, 41, 23);
		panel_1.add(btnDevolver);
		{

			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

			loadPubliList();
			loadClientList();
		}

	}

	private void loadPubliList() {
		Biblioteca biblio = Biblioteca.getInstance();
		DefaultListModel<String> list = new DefaultListModel<>();

		list.clear();

		switch (mode) {
		case 0:
			for (int i = 0; i < biblio.getMisPublicaciones().size(); i++) {
				if (biblio.getMisPublicaciones().get(i).getCantidad() > 0
						&& (materia.equalsIgnoreCase(biblio.getMisPublicaciones().get(i).getMateria())
								|| materia.equalsIgnoreCase("<Todas>"))) {
					list.addElement(new String(biblio.getMisPublicaciones().get(i).getId() + " - "
							+ biblio.getMisPublicaciones().get(i).getTitulo() + " ("
							+ biblio.getMisPublicaciones().get(i).getCantidad() + ")"));
				}
			}
			break;
		case 1:
			for (int i = 0; i < biblio.getMisPublicaciones().size(); i++) {
				if (biblio.getMisPublicaciones().get(i) instanceof Libro) {
					if (biblio.getMisPublicaciones().get(i).getCantidad() > 0
							&& (materia.equalsIgnoreCase(biblio.getMisPublicaciones().get(i).getMateria())
									|| materia.equalsIgnoreCase("<Todas>"))) {
						list.addElement(new String(biblio.getMisPublicaciones().get(i).getId() + " - "
								+ biblio.getMisPublicaciones().get(i).getTitulo() + " ("
								+ biblio.getMisPublicaciones().get(i).getCantidad() + ")"));
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < biblio.getMisPublicaciones().size(); i++) {
				if (biblio.getMisPublicaciones().get(i) instanceof Articulo) {
					if (biblio.getMisPublicaciones().get(i).getCantidad() > 0
							&& (materia.equalsIgnoreCase(biblio.getMisPublicaciones().get(i).getMateria())
									|| materia.equalsIgnoreCase("<Todas>"))) {
						list.addElement(new String(biblio.getMisPublicaciones().get(i).getId() + " - "
								+ biblio.getMisPublicaciones().get(i).getTitulo() + " ("
								+ biblio.getMisPublicaciones().get(i).getCantidad() + ")"));
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < biblio.getMisPublicaciones().size(); i++) {
				if (biblio.getMisPublicaciones().get(i) instanceof Revista) {
					if (biblio.getMisPublicaciones().get(i).getCantidad() > 0
							&& (materia.equalsIgnoreCase(biblio.getMisPublicaciones().get(i).getMateria())
									|| materia.equalsIgnoreCase("<Todas>"))) {
						list.addElement(new String(biblio.getMisPublicaciones().get(i).getId() + " - "
								+ biblio.getMisPublicaciones().get(i).getTitulo() + " ("
								+ biblio.getMisPublicaciones().get(i).getCantidad() + ")"));
					}
				}
			}
			break;
		}

		listBiblio.setModel(list);
	}

	private void loadClientList() {
		if (client != null) {
			DefaultListModel<String> list = new DefaultListModel<>();

			list.clear();

			for (int i = 0; i < client.getMisPrestamos().size(); i++) {
				list.addElement(new String(client.getMisPrestamos().get(i).getPublicacion().getId() + " - "
						+ client.getMisPrestamos().get(i).getPublicacion().getTitulo()));
			}

			listCliente.setModel(list);
		}
	}
}
