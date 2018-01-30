package visual;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import logico.Puerto;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.Color;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private Puerto puerto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Puerto puerto = new Puerto();
					
					puerto.loadData();
					
					Principal frame = new Principal(puerto);
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent we) {
							try {
								puerto.saveData();
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
	public Principal(Puerto puerto) {
		this.puerto = puerto;

		setTitle("PUERTO SAN FELIPE");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		// Dimensiones
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height - 50);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnRegistrar = new JMenu("Registrar");
		menuBar.add(mnRegistrar);

		JMenuItem mntmBarco = new JMenuItem("Barco");
		mntmBarco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroBarco regBarco = new RegistroBarco(puerto, null);
				regBarco.setModal(true);
				regBarco.setVisible(true);
			}
		});
		mnRegistrar.add(mntmBarco);

		JMenuItem mntmNewMenuItem = new JMenuItem("Cliente");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroCliente regCliente = new RegistroCliente(puerto, null);
				regCliente.setModal(true);
				regCliente.setVisible(true);
			}
		});
		mnRegistrar.add(mntmNewMenuItem);

		JMenuItem mntmAlquiler = new JMenuItem("Alquiler");
		mntmAlquiler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistroAlquiler regAlquiler = new RegistroAlquiler(puerto, null);
				regAlquiler.setModal(true);
				regAlquiler.setVisible(true);
			}
		});
		mnRegistrar.add(mntmAlquiler);

		JMenu mnListar = new JMenu("Listar");
		menuBar.add(mnListar);

		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListarClientes listarClientes = new ListarClientes(puerto);
				listarClientes.setModal(true);
				listarClientes.setVisible(true);
			}
		});

		JMenuItem mntmBarcos = new JMenuItem("Barcos");
		mntmBarcos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListarBarcos listarBarcos = new ListarBarcos(puerto);
				listarBarcos.setModal(true);
				listarBarcos.setVisible(true);
			}
		});
		mnListar.add(mntmBarcos);
		mnListar.add(mntmClientes);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Alquileres");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListarAlquileres listarAlquileres = new ListarAlquileres(puerto);
				listarAlquileres.setModal(true);
				listarAlquileres.setVisible(true);
			}
		});
		mnListar.add(mntmNewMenuItem_1);

		JMenu mnResumen = new JMenu("Resumen");
		menuBar.add(mnResumen);

		JMenuItem mntmEstadsticas = new JMenuItem("Ganancias");
		mntmEstadsticas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ganancias ganancias = new Ganancias(puerto);
				ganancias.setModal(true);
				ganancias.setVisible(true);
			}
		});
		mnResumen.add(mntmEstadsticas);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0, 637, 1360, 30);
		contentPane.add(panel);

		JLabel lblHola = new JLabel("\u00A92017 Jos\u00E9 Gonz\u00E1lez en asociaci\u00F3n con Puerto San Felipe");
		panel.add(lblHola);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n General",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 1340, 619);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblBienvenidoaAlPrograma = new JLabel("Bienvenido/a al programa administrativo del Puerto San Felipe");
		lblBienvenidoaAlPrograma.setBounds(10, 26, 365, 14);
		panel_1.add(lblBienvenidoaAlPrograma);

		JLabel lblAquSePueden = new JLabel("Aqu\u00ED se pueden realizar las siguientes acciones:");
		lblAquSePueden.setBounds(10, 51, 297, 14);
		panel_1.add(lblAquSePueden);

		JLabel lblRegistrarBarcos = new JLabel("- Registrar barcos, clientes y alquileres.");
		lblRegistrarBarcos.setBounds(10, 76, 297, 14);
		panel_1.add(lblRegistrarBarcos);

		JLabel lblModificarBarcos = new JLabel("- Modificar barcos, clientes y alquileres.");
		lblModificarBarcos.setBounds(10, 101, 297, 14);
		panel_1.add(lblModificarBarcos);

		JLabel lblEliminarBarcos = new JLabel("- Eliminar barcos y clientes.");
		lblEliminarBarcos.setBounds(10, 126, 297, 14);
		panel_1.add(lblEliminarBarcos);

		JLabel lblNotas = new JLabel("Notas:");
		lblNotas.setBounds(10, 201, 297, 14);
		panel_1.add(lblNotas);

		JLabel lblParaRegistrar = new JLabel("* Para registrar un cliente se deben tener embarcaciones registradas.");
		lblParaRegistrar.setBounds(10, 226, 513, 14);
		panel_1.add(lblParaRegistrar);

		JLabel lblParaRegistrar_1 = new JLabel(
				"* Para registrar un alquiler se deben tener embarcaciones y clientes registrados.");
		lblParaRegistrar_1.setBounds(10, 251, 513, 14);
		panel_1.add(lblParaRegistrar_1);

		JLabel lblNoSe = new JLabel("* No se puede eliminar un alquiler.");
		lblNoSe.setBounds(10, 276, 221, 14);
		panel_1.add(lblNoSe);

		JLabel lblAlModificar = new JLabel(
				"* Al modificar un alquiler, se debe dejar un comentario notificando los cambios o la raz\u00F3n de la modificaci\u00F3n.");
		lblAlModificar.setBounds(10, 301, 636, 14);
		panel_1.add(lblAlModificar);

		JLabel lblVerUn = new JLabel("- Ver un resumen de las entidades y las ganancias");
		lblVerUn.setBounds(10, 151, 297, 14);
		panel_1.add(lblVerUn);
	}
}
