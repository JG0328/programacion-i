package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Almacen;
import logico.Suministrador;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarSuminitradores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel model;
	private JButton btnModificar;
	private JButton btnEliminar;
	private static Almacen alma;
	private String identificador;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public ListarSuminitradores(Almacen alma) {
		this.alma = alma;
		this.identificador = "";
		setBounds(100, 100, 497, 342);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Listado de Suministradores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>=0){
								btnEliminar.setEnabled(true);
								btnModificar.setEnabled(true);
								int index = table.getSelectedRow();
								identificador = (String)table.getModel().getValueAt(index, 0);
								System.out.println(identificador);				
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					String[] columnNames = {"Identificador","Nombre","País","Entrega"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnNames);
					table.setModel(model);
					loadTable();
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
					public void actionPerformed(ActionEvent e) {
						if(!identificador.equalsIgnoreCase("")){
							Suministrador sumi = alma.buscarSuminitrador(identificador);
							RegSuplidor supli = new RegSuplidor(alma, sumi);
							supli.setModal(true);
							supli.setVisible(true);
							
							 //loadTable();
							 btnEliminar.setEnabled(false);
							 btnModificar.setEnabled(false);
						}
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!identificador.equalsIgnoreCase("")){
							Suministrador sumi = alma.buscarSuminitrador(identificador);
							 int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el Suministrador: " + sumi.getNombre(),"Información",JOptionPane.WARNING_MESSAGE);
							  if(option == JOptionPane.OK_OPTION){
							    alma.eliminaSuministrador(sumi);
							    loadTable();
							    btnEliminar.setEnabled(false);
							    btnModificar.setEnabled(false);
							}
						}
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void loadTable() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (int i = 0; i < alma.getCantSumi(); i++) {
			fila[0] = alma.getMisSuminis()[i].getIdentificador();
			fila[1] = alma.getMisSuminis()[i].getNombre();
			fila[2] = alma.getMisSuminis()[i].getPais();
			fila[3] = alma.getMisSuminis()[i].getEntrega();
			model.addRow(fila);
		}
		
	}

}
