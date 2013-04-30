package entornoGrafico1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import entornoGrafico1.sistemaAscensor.Ascensor;
import entornoGrafico1.sistemaAscensor.Puerta;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.UIManager;
/**
 * 
 * Clase EntornoGrafico1.java
 *
 * @author Rafael García Maliga
 * @since 30/04/2013
 * @version 1.0
 *
 */
public class EntornoGrafico1 extends JFrame {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1064214493974150905L;
	// Componentes
	private JPanel contentPane;
	private JProgressBar progressBar;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	// Otros
	private static int floors = 20;
	private Ascensor ascensor = new Ascensor(floors, new Puerta());
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntornoGrafico1 frame = new EntornoGrafico1();
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
	public EntornoGrafico1() {
		redirectSystemStreams(); // redirecciona las salidas del sistema
		setResizable(false);
		setTitle("Ascensor v2.0");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 371);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int seleccion = JOptionPane.showOptionDialog(
					    null, // Componente padre
					    "¿Estás seguro que desea salir?", //Mensaje
					    "Seleccione una opción", // Título
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
		
		JLabel lblNumeroDePlantas = new JLabel("Numero de plantas");
		menuBar.add(lblNumeroDePlantas);
		
		spinner_1 = new JSpinner();
		spinner_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//Configura el nuevo numero de plantas del edificio
				floors = (int) ((Integer) spinner_1.getValue());
				ascensor.setFloors(floors);
				ascensor.setCurrentFloor(0);
				spinner.setModel(new SpinnerNumberModel(0, 0, floors, 1));
				lblNewLabel.setText("Planta actual => 0");
			}
		});
		spinner_1.setModel(new SpinnerNumberModel(20, 1, 99, 1));
		menuBar.add(spinner_1);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//About popup
				JOptionPane.showMessageDialog(null, "Ascensor V2.0\nAuthor: Rafael García Maliga");
			}
		});
		menuBar.add(mntmAbout);
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(5, 11, 42, 245);
		progressBar.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(progressBar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(5, 267, 429, 43);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPlanta = new JLabel("Elegir planta: ");
		lblPlanta.setBounds(138, 15, 81, 14);
		panel.add(lblPlanta);
		lblPlanta.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, floors, 1));
		spinner.setBounds(229, 12, 49, 20);
		panel.add(spinner);
		
		JButton btnNewButton = new JButton("Utilizar ascensor");
		btnNewButton.addActionListener(new ActionListener() {
			int targetFloor = 0;
			public void actionPerformed(ActionEvent arg0) {
				// Utilizar ascensor
				targetFloor = (int)((Integer)spinner.getValue());
				if(targetFloor >= 0 && targetFloor <= floors){
					ascensor.setTargetFloor(targetFloor);
				}
				ascensor.move();
				lblNewLabel.setText("Planta actual => " + ascensor.getCurrentFloor());
				progressBar.setValue(calcularPorcentaje(ascensor.getCurrentFloor(), floors));
				
			}
		});
		btnNewButton.setBounds(288, 11, 131, 23);
		panel.add(btnNewButton);
		
		lblNewLabel = new JLabel("Planta actual => 0");
		lblNewLabel.setBounds(10, 15, 133, 14);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(59, 11, 375, 245);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(UIManager.getFont("TextArea.font"));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
	}
	
	/**
	 * calcularPorcentaje
	 * @param n
	 * @return
	 */
	public int calcularPorcentaje(int n, int max){
		return (n*100)/max;
	}
	
	/**
	 * 
	 * updateTextArea
	 * @param text
	 */
	private void updateTextArea(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textArea.append(text);
			}
		});
	}
	/**
	 * 
	 * redirectSystemStreams
	 */
	private void redirectSystemStreams() {
		  OutputStream out = new OutputStream() {
		    @Override
		    public void write(int b) throws IOException {
		      updateTextArea(String.valueOf((char) b));
		    }
		 
		    @Override
		    public void write(byte[] b, int off, int len) throws IOException {
		      updateTextArea(new String(b, off, len));
		    }
		 
		    @Override
		    public void write(byte[] b) throws IOException {
		      write(b, 0, b.length);
		    }
		  };
		 
		  System.setOut(new PrintStream(out, true));
		  System.setErr(new PrintStream(out, true));
		}
}
