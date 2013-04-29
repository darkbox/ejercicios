package primerEntornoGrafico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class NumberAddition extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7770430535965316121L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static NumberAddition frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new NumberAddition();
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
	public NumberAddition() {
		setAlwaysOnTop(true);
		setTitle("Number Addition RGM v0.1");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 214);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Number Addition", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(10, 11, 307, 133);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("First number:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 21, 94, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Second Number:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 46, 94, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Result:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 71, 94, 14);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(114, 18, 177, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 43, 177, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(114, 68, 177, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Boton Add
			    float num1, num2, result;
			 
			    num1 = Float.parseFloat(textField.getText());
			    num2 = Float.parseFloat(textField_1.getText());
			  
			    result = num1+num2;
			    
			    textField_2.setText(String.valueOf(result));
			}
		});
		btnNewButton.setBounds(114, 99, 78, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Botón Clear
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				JOptionPane.showMessageDialog(frame, "Campos vaciados!");
			}
		});
		btnNewButton_1.setBounds(213, 99, 78, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Boton salir
				JOptionPane.showMessageDialog(frame, "Pero que haces.");
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(228, 155, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
