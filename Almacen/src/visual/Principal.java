package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Almacen;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private Almacen alma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Almacen alma = new Almacen();
					Principal frame = new Principal(alma);
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
	public Principal(Almacen alma) {
		this.alma = alma;
		setResizable(false);
		
		setTitle("Almacen de Vino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 448);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, (dim.height-50));
		setLocationRelativeTo(null);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVinos = new JMenu("Vinos");
		menuBar.add(mnVinos);
		
		JMenuItem mntmRegistrarVino = new JMenuItem("Registrar Vino");
		mntmRegistrarVino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   RegistrarVino rgVino = new RegistrarVino(alma);
			   rgVino.setVisible(true);
			}
		});
		mnVinos.add(mntmRegistrarVino);
		
		JMenu mnSuministradores = new JMenu("Suministradores");
		menuBar.add(mnSuministradores);
		
		JMenuItem mntmRegistrarSuministrador = new JMenuItem("Registrar Suministrador");
		mntmRegistrarSuministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegSuplidor supli = new RegSuplidor(alma,null);
				supli.setModal(true);
				supli.setVisible(true);
			}
		});
		mnSuministradores.add(mntmRegistrarSuministrador);
		
		JMenuItem mntmListado = new JMenuItem("Listado");
		mntmListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarSuminitradores lista = new ListarSuminitradores(alma);
				lista.setModal(true);
				lista.setVisible(true);
				
			}
		});
		mnSuministradores.add(mntmListado);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 648, 1018, 21);
		contentPane.add(panel);
	}
}
