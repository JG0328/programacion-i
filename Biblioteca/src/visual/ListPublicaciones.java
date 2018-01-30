package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Articulo;
import logico.Biblioteca;
import logico.Libro;
import logico.Revista;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ListPublicaciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public ListPublicaciones() {
		setResizable(false);
		setBounds(100, 100, 800, 500);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 36, 764, 381);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setDefaultEditor(Object.class, null);

				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				loadTable(0);

				scrollPane.setViewportView(table);
			}
		}

		JLabel lblListarElementos = new JLabel("Listar elementos:");
		lblListarElementos.setBounds(10, 13, 105, 14);
		contentPanel.add(lblListarElementos);

		JComboBox cbxListar = new JComboBox();
		cbxListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable(cbxListar.getSelectedIndex());
			}
		});
		cbxListar
				.setModel(new DefaultComboBoxModel(new String[] { "<Todos>", "Libros", "Art\u00EDculos", "Revistas" }));
		cbxListar.setBounds(125, 9, 649, 23);
		contentPanel.add(cbxListar);
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
		}
	}

	private void loadTable(int mode) {
		Biblioteca biblio = Biblioteca.getInstance();

		switch (mode) {
		case 0:
			String[] columns = { "ID", "Título", "Materia", "Tipo", "Ejemplares" };
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columns);

			model.setRowCount(0);

			fila = new Object[model.getColumnCount()];
			for (int i = 0; i < biblio.getMisPublicaciones().size(); i++) {
				fila[0] = biblio.getMisPublicaciones().get(i).getId();
				fila[1] = biblio.getMisPublicaciones().get(i).getTitulo();
				fila[2] = biblio.getMisPublicaciones().get(i).getMateria();

				if (biblio.getMisPublicaciones().get(i) instanceof Libro) {
					fila[3] = "Libro";
				}
				if (biblio.getMisPublicaciones().get(i) instanceof Articulo) {
					fila[3] = "Artículo";
				}
				if (biblio.getMisPublicaciones().get(i) instanceof Revista) {
					fila[3] = "Revista";
				}

				fila[4] = biblio.getMisPublicaciones().get(i).getCantidad();

				model.addRow(fila);
			}

			table.setModel(model);
			break;
		case 1:
			String[] columnsLibro = { "ID", "Título", "Autor", "Editorial", "Materia", "Ejemplares" };
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columnsLibro);

			fila = new Object[model.getColumnCount()];
			for (int i = 0; i < biblio.getMisPublicaciones().size(); i++) {
				if (biblio.getMisPublicaciones().get(i) instanceof Libro) {
					Libro lib = (Libro) biblio.getMisPublicaciones().get(i);
					fila[0] = lib.getId();
					fila[1] = lib.getTitulo();
					fila[2] = lib.getAutor();
					fila[3] = lib.getEditorial();
					fila[4] = lib.getMateria();
					fila[5] = lib.getCantidad();

					model.addRow(fila);
				}
			}

			table.setModel(model);
			break;
		case 2:
			String[] columnsArticulo = { "ID", "Título", "Autor", "Árbitro", "Materia", "Ejemplares" };
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columnsArticulo);

			fila = new Object[model.getColumnCount()];
			for (int i = 0; i < biblio.getMisPublicaciones().size(); i++) {
				if (biblio.getMisPublicaciones().get(i) instanceof Articulo) {
					Articulo art = (Articulo) biblio.getMisPublicaciones().get(i);
					fila[0] = art.getId();
					fila[1] = art.getTitulo();
					fila[2] = art.getAutor();
					fila[3] = art.getArbitro();
					fila[4] = art.getMateria();
					fila[5] = art.getCantidad();

					model.addRow(fila);
				}
			}

			table.setModel(model);
			break;
		case 3:
			String[] columnsRevista = { "ID", "Título", "Año", "Número", "Materia", "Ejemplares" };
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columnsRevista);

			fila = new Object[model.getColumnCount()];
			for (int i = 0; i < biblio.getMisPublicaciones().size(); i++) {
				if (biblio.getMisPublicaciones().get(i) instanceof Revista) {
					Revista rev = (Revista) biblio.getMisPublicaciones().get(i);
					fila[0] = rev.getId();
					fila[1] = rev.getTitulo();
					fila[2] = rev.getAgno();
					fila[3] = rev.getNumero();
					fila[4] = rev.getMateria();
					fila[5] = rev.getCantidad();

					model.addRow(fila);
				}
			}

			table.setModel(model);
			break;
		}
	}
}
