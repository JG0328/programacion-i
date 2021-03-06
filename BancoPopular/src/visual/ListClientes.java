package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Banco;
import logico.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public class ListClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Object[] fila;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public ListClientes() {
		setTitle("Listado de Clientes");
		setResizable(false);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 774, 416);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] columns = { "C�dula", "Nombre", "Apellidos", "Direcci�n", "Tel�fono" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		loadTable();

		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];

		for (Cliente cliente : Banco.getInstance().getMisClientes()) {
			fila[0] = cliente.getCedula();
			fila[1] = cliente.getNombre();
			fila[2] = cliente.getApellidos();
			fila[3] = cliente.getDireccion();
			fila[4] = cliente.getTelefono();
			
			model.addRow(fila);
		}

		table.setModel(model);
	}
}
