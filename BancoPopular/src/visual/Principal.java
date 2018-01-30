package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import logico.Banco;
import sun.applet.Main;

import javax.swing.ImageIcon;

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
					Banco.getInstance().loadData();
					Principal frame = new Principal();
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					frame.addWindowListener(new WindowAdapter() {

						public void windowClosing(WindowEvent we) {
							try {
								Banco.getInstance().saveData();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							System.exit(0);
						}
					});
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
		URL url = Main.class.getResource("/icon.png");
		ImageIcon icon = new ImageIcon(url);
		setIconImage(icon.getImage());
		setTitle("Banco Popular");
		setResizable(false);
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
				RegCliente regC = new RegCliente();
				regC.setModal(true);
				regC.setVisible(true);
			}
		});
		mnRegistro.add(mntmCliente);

		JMenuItem mntmCuenta = new JMenuItem("Cuenta");
		mntmCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCuenta regCuenta = new RegCuenta(null);
				regCuenta.setModal(true);
				regCuenta.setVisible(true);
			}
		});
		mnRegistro.add(mntmCuenta);

		JMenu mnRealizar = new JMenu("Realizar");
		menuBar.add(mnRealizar);

		JMenuItem mntmDepsito = new JMenuItem("Dep\u00F3sito");
		mntmDepsito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RlzDepositoRetiro rlzDep = new RlzDepositoRetiro(false);
				rlzDep.setModal(true);
				rlzDep.setVisible(true);
			}
		});
		mnRealizar.add(mntmDepsito);

		JMenuItem mntmRetiro = new JMenuItem("Retiro");
		mntmRetiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RlzDepositoRetiro rlzDep = new RlzDepositoRetiro(true);
				rlzDep.setModal(true);
				rlzDep.setVisible(true);
			}
		});
		mnRealizar.add(mntmRetiro);

		JMenuItem mntmRevisin = new JMenuItem("Revisi\u00F3n");
		mntmRevisin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RlzRevision rlzRev = new RlzRevision();
				rlzRev.setModal(true);
				rlzRev.setVisible(true);
			}
		});
		mnRealizar.add(mntmRevisin);

		JMenu mnListado = new JMenu("Listado");
		menuBar.add(mnListado);

		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListClientes lclients = new ListClientes();
				lclients.setModal(true);
				lclients.setVisible(true);
			}
		});
		mnListado.add(mntmClientes);

		JMenuItem mntmCuentas = new JMenuItem("Cuentas");
		mntmCuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCuentas lcuentas = new ListCuentas();
				lcuentas.setModal(true);
				lcuentas.setVisible(true);
			}
		});
		mnListado.add(mntmCuentas);

		JMenuItem mntmTransacciones = new JMenuItem("Transacciones");
		mntmTransacciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListTransacciones listTrans = new ListTransacciones();
				listTrans.setModal(true);
				listTrans.setVisible(true);
			}
		});
		mnListado.add(mntmTransacciones);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 636, 1360, 32);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblJosGonzlez = new JLabel("\u00A92017 Jos\u00E9 Gonz\u00E1lez");
		lblJosGonzlez.setHorizontalAlignment(SwingConstants.CENTER);
		lblJosGonzlez.setBounds(592, 8, 156, 14);
		panel.add(lblJosGonzlez);

		JLabel lblLogo = new JLabel("");
		URL ban = Main.class.getResource("/banner.jpg");
		ImageIcon banner = new ImageIcon(ban);
		ImageIcon ic = new ImageIcon(banner.getImage().getScaledInstance(1360, 636, Image.SCALE_DEFAULT));
		lblLogo.setIcon(ic);
		lblLogo.setBounds(0, 0, 1360, 636);
		contentPane.add(lblLogo);
	}
}
