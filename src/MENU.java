import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MENU extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MENU frame = new MENU();
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
	public MENU() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MAIN MENU");
		lblNewLabel.setBounds(170, 11, 87, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Manage Products");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PRODUCT productFrame = new PRODUCT(); // Create instance of PRODUCT frame
				productFrame.setVisible(true);        // Open PRODUCT window
				dispose();
				
			}
		});
		btnNewButton.setBounds(10, 55, 133, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Manage Suppliers");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SUPPLIER supplierFrame = new SUPPLIER();
				supplierFrame.setVisible(true);
				dispose(); // Closes the current MENU frame
			}
		});
		btnNewButton_1.setBounds(10, 101, 133, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Manage Purchases");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PURHCASE purchaseFrame = new PURHCASE();
				purchaseFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(10, 147, 133, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("LOGOUT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LOGON_AUTH loginWin = new LOGON_AUTH();
				loginWin.setVisible(true);
				dispose(); //Close MENU 
			}
			
		});
		btnNewButton_3.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton_3);

	}

}
