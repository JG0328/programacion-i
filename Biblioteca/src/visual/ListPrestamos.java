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
import logico.Cliente;
import logico.Libro;
import logico.Prestamo;
import logico.Revista;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class ListPrestamos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public ListPrestamos() {
		setTitle("Listado de Pr\u00E9stamos");
		setResizable(false);
		setBounds(100, 100, 1000, 500);

		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setDefaultEditor(Object.class, null);

					String[] columns = { "Fecha de Solicitud", "Fecha de Devolución", "Estado", "Cliente",
							"Publicación" };
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columns);

					loadTable();

					table.setModel(model);

					scrollPane.setViewportView(table);
				}
			}
		}
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

	private void loadTable() {
		Biblioteca biblio = Biblioteca.getInstance();

		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (Prestamo prest : biblio.getMisPrestamos()) {
			fila[0] = prest.getFechaSolicitud();
			fila[1] = prest.getFechaDevolucion();
			fila[2] = (prest.isStatus()) ? "Activo" : "Inactivo";

			if (prest.isStatus()) {
				Cliente client = biblio.findClienteByPrestamo(prest);
				fila[3] = client.getCedula() + " - " + client.getNombre();
				fila[4] = prest.getPublicacion().getId() + " - "
						+ prest.getPublicacion()
								.getTitulo()
						+ " ("
						+ (prest.getPublicacion() instanceof Libro ? "Libro"
								: ((prest.getPublicacion() instanceof Articulo) ? "Artículo"
										: (prest.getPublicacion() instanceof Revista ? "Revista" : null)))
						+ ")";
			}
			model.addRow(fila);
		}

	}

}
