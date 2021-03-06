package game.sieteYMedia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfiguracion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JSpinner spinner;
	private boolean jugar = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConfiguracion frame = new VentanaConfiguracion();
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
	public VentanaConfiguracion() {
		setTitle("Siete y Media");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 164);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(
					    null, // Componente padre
					    "�Est�s seguro que desea salir?", //Mensaje
					    "Seleccione una opci�n", // T�tulo
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,    // null para icono por defecto.
					    new Object[] { "Si", "No"},    // null para YES, NO y CANCEL
					    "Si");
				if (seleccion != -1)
				{
				   if((seleccion + 1)==1)
				   {
				      // PRESIONO SI
					   System.exit(DISPOSE_ON_CLOSE); // Cerrar
				   }
				   else
				   {
				      // PRESIONO NO
				   }
				}
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//About popup
				JOptionPane.showMessageDialog(null, "Siete y Media V2.0\nAuthor: Rafael Garc�a Maliga");
			}
		});
		
		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String helpString = "<html>" +
						"1.- Selecciona el numero de jugadores (de 1-8) y pulsa sobre jugar.<br>" +
						"2.- Introduce el nombre de cada usuario o deja el nombre generado por defecto.<br>" +
						"3.- Se preguntar� a cada usuario de forma c�clica si desea recibir una carta o plantarse.<br>" +
						"	 Si desea plantarse no prodr� jugar hasta la pr�xima ronda.<br>" +
						"4.- Cuando finalize una partida (si un jugador ha ganado, todos han perdido o bien no queden cartas)<br>" +
						"	 Se le pregunta al usuario si desea continuar una nueva ronda con los mismo jugadores o si desea reiniciar, " +
						"lo cual nos devuelve al punto 1.<br><br>" +
						"Para salir del sistema pulsar el Aspa de la ventana o pulsar sobre File/Exit en la ventana de configuraci�n.<br>" +
						"Si desea salir durante la partida tiene que esperarse a que todos los jugadores acaben su turno, por ejemplo plantandose." +
						"</html>";
				// Instrucciones / ayuda
				JOptionPane.showMessageDialog(null, helpString);
			}
		});
		mnHelp.add(mntmInstructions);
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.BOLD, 20));
		spinner.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		contentPane.add(spinner, BorderLayout.CENTER);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugar = true;
				setVisible(false);
			}
		});
		contentPane.add(btnJugar, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("Seleccione el n�mero de jugadores:");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

	public boolean isPlaying(){
		return jugar;
	}
}
