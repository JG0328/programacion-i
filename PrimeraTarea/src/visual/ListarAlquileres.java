package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Alquiler;
import logico.Puerto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarAlquileres extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;
	private static Puerto puerto;
	private JButton btnModificar;
	private String id;

	/**
	 * Create the dialog.
	 */
	public ListarAlquileres(Puerto puerto) {
		setResizable(false);
		ListarAlquileres.puerto = puerto;

		setTitle("Listado de Alquileres");
		setBounds(100, 100, 1000, 700);

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
					table.setDefaultEditor(Object.class, null);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (table.getSelectedRow() >= 0) {
								btnModificar.setEnabled(true);

								int index = table.getSelectedRow();
								id = table.getModel().getValueAt(index, 0).toString();
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

					String[] columnas = { "ID", "Inicio", "Días", "Embarcación", "Amarre", "Cliente", "Monto",
							"Comentario" };
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnas);
					table.setModel(model);

					cargarTabla();

					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Alquiler alquiler = puerto.buscarAlquiler(id);

						RegistroAlquiler regAlquiler = new RegistroAlquiler(puerto, alquiler);
						regAlquiler.setModal(true);
						regAlquiler.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
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
	}

	public static void cargarTabla() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (int i = 0; i < puerto.getCantAlquileres(); i++) {
			fila[0] = puerto.getAlquileres()[i].getId();
			fila[1] = puerto.getAlquileres()[i].getFechaInicio();
			fila[2] = puerto.getAlquileres()[i].getCantDias();
			fila[3] = (puerto.getAlquileres()[i].getBarco() == null) ? (new String("Barco eliminado"))
					: (new String(puerto.getAlquileres()[i].getBarco().getMatricula() + " - "
							+ puerto.getAlquileres()[i].getBarco().getNombre()));
			fila[4] = puerto.getAlquileres()[i].getPosicion();
			fila[5] = (puerto.getAlquileres()[i].getCliente() == null) ? (new String("Cliente eliminado"))
					: (new String(puerto.getAlquileres()[i].getCliente().getCedula() + " - "
							+ puerto.getAlquileres()[i].getCliente().getNombre() + " "
							+ puerto.getAlquileres()[i].getCliente().getApellidos()));
			fila[6] = String.format("RD$ %.2f", puerto.getAlquileres()[i].getMonto());
			fila[7] = puerto.getAlquileres()[i].getComentario();
			model.addRow(fila);
		}
	}

}
