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
		setTitle("Complejo L\u00E1cteo de Ciudad de La Habana");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		// Dimensiones
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height - 50);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);

		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente rgClient = new RegCliente();
				rgClient.setModal(true);
				rgClient.setVisible(true);
			}
		});

		JMenuItem mntmQueso = new JMenuItem("Queso");
		mntmQueso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegQueso rgQueso = new RegQueso();
				rgQueso.setModal(true);
				rgQueso.setVisible(true);
			}
		});
		mnRegistro.add(mntmQueso);
		mnRegistro.add(mntmCliente);

		JMenuItem mntmFactura = new JMenuItem("Factura");
		mntmFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegFactura rgFactura = new RegFactura();
				rgFactura.setModal(true);
				rgFactura.setVisible(true);
			}
		});
		mnRegistro.add(mntmFactura);

		JMenu mnListado = new JMenu("Listado");
		menuBar.add(mnListado);

		JMenuItem mntmQuesos = new JMenuItem("Quesos");
		mntmQuesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListQuesos lQuesos = new ListQuesos();
				lQuesos.setModal(true);
				lQuesos.setVisible(true);
			}
		});
		mnListado.add(mntmQuesos);

		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListClientes lClients = new ListClientes();
				lClients.setModal(true);
				lClients.setVisible(true);
			}
		});
		mnListado.add(mntmClientes);

		JMenuItem mntmFacturas = new JMenuItem("Facturas");
		mntmFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListFacturas lFacturas = new ListFacturas();
				lFacturas.setModal(true);
				lFacturas.setVisible(true);
			}
		});
		mnListado.add(mntmFacturas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
