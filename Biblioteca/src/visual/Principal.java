package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setTitle("Biblioteca Nacional");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		// Dimensiones
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height - 50);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Registro");
		menuBar.add(mnNewMenu);

		JMenuItem mntmPublicacin = new JMenuItem("Publicaci\u00F3n");
		mntmPublicacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPublicacion regPubli = new RegPublicacion();
				regPubli.setModal(true);
				regPubli.setVisible(true);
			}
		});
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente regClient = new RegCliente();
				regClient.setModal(true);
				regClient.setVisible(true);
			}
		});
		mnNewMenu.add(mntmCliente);
		mnNewMenu.add(mntmPublicacin);
		
		JMenuItem mntmPrstamo = new JMenuItem("Pr\u00E9stamo");
		mntmPrstamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPrestamo regPrest = new RegPrestamo();
				regPrest.setModal(true);
				regPrest.setVisible(true);
			}
		});
		mnNewMenu.add(mntmPrstamo);

		JMenu mnListar = new JMenu("Listado");
		menuBar.add(mnListar);

		JMenuItem mntmPublicaciones = new JMenuItem("Publicaciones");
		mntmPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListPublicaciones listPublis = new ListPublicaciones();
				listPublis.setModal(true);
				listPublis.setVisible(true);
			}
		});
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListClientes listClients = new ListClientes();
				listClients.setModal(true);
				listClients.setVisible(true);
			}
		});
		mnListar.add(mntmClientes);
		mnListar.add(mntmPublicaciones);
		
		JMenuItem mntmPrstamos = new JMenuItem("Pr\u00E9stamos");
		mntmPrstamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListPrestamos listPrests = new ListPrestamos();
				listPrests.setModal(true);
				listPrests.setVisible(true);
			}
		});
		mnListar.add(mntmPrstamos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
