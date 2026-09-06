import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LOGON_AUTH extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField uname;
	private JTextField pname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGON_AUTH frame = new LOGON_AUTH();
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
	public LOGON_AUTH() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(46, 58, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(46, 116, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		uname = new JTextField();
		uname.setBounds(138, 55, 86, 20);
		contentPane.add(uname);
		uname.setColumns(10);
		
		pname = new JTextField();
		pname.setBounds(138, 113, 86, 20);
		contentPane.add(pname);
		pname.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = uname.getText();
				String p = pname.getText();
				try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");

                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","bca","root");

                    PreparedStatement pstmt = con.prepareStatement("select * from login where username=? and password=?");

                    pstmt.setString(1, u);
                    pstmt.setString(2, p);

                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        System.out.println("Successful");
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        MENU menuFrame = new MENU(); //open menu frame
                        menuFrame.setVisible(true);
                        dispose();  // close the auth frame
                    }
                    else {
                        System.out.println("Unsuccessful");
                        JOptionPane.showMessageDialog(null,"Login Unsuccesful");
                        
                    }

                    con.close();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
			}
		});
		btnNewButton.setBounds(135, 197, 89, 23);
		contentPane.add(btnNewButton);

	}
}
