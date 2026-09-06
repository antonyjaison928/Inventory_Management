import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SUPPLIER extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField SUP_ID;
	private JTextField SUP_NME;
	private JTextField PH;
	private JTextField EMAIL;
	private JTextField PRD_ID;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SUPPLIER frame = new SUPPLIER();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void clearFields() {
		SUP_ID.setText("");
		SUP_NME.setText("");
		PH.setText("");
		EMAIL.setText("");
		PRD_ID.setText("");
	}

	/**
	 * Create the frame.
	 */
	public SUPPLIER() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SUPPLIER");
		lblNewLabel.setBounds(212, 0, 68, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SUPPLIER_ID:");
		lblNewLabel_1.setBounds(10, 31, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SUPPLIER_NAME:");
		lblNewLabel_2.setBounds(10, 61, 110, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PHONE:");
		lblNewLabel_3.setBounds(10, 92, 60, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("EMAIL:");
		lblNewLabel_4.setBounds(10, 123, 77, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("PRODUCT_ID:");
		lblNewLabel_5.setBounds(10, 151, 100, 14);
		contentPane.add(lblNewLabel_5);
		
		SUP_ID = new JTextField();
		SUP_ID.setBounds(194, 25, 100, 20);
		contentPane.add(SUP_ID);
		SUP_ID.setColumns(10);
		
		SUP_NME = new JTextField();
		SUP_NME.setBounds(194, 55, 100, 20);
		contentPane.add(SUP_NME);
		SUP_NME.setColumns(10);
		
		PH = new JTextField();
		PH.setBounds(194, 86, 100, 20);
		contentPane.add(PH);
		PH.setColumns(10);
		
		EMAIL = new JTextField();
		EMAIL.setBounds(194, 117, 100, 20);
		contentPane.add(EMAIL);
		EMAIL.setColumns(10);
		
		PRD_ID = new JTextField();
		PRD_ID.setBounds(194, 145, 100, 20);
		contentPane.add(PRD_ID);
		PRD_ID.setColumns(10);
		
		// INSERT BUTTON
		JButton INSERT = new JButton("INSERT");
		INSERT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "bca", "root");
					Statement stmt = con.createStatement();

					int id = Integer.parseInt(SUP_ID.getText());
					String nm = SUP_NME.getText();
					String ph = PH.getText();
					String email = EMAIL.getText();
					int prdId = Integer.parseInt(PRD_ID.getText());

					String qry = "insert into supplier values (" + id + ",'" + nm + "','" + ph + "','" + email + "'," + prdId + ")";
					stmt.executeUpdate(qry);

					JOptionPane.showMessageDialog(null, "Supplier added successfully!");
					con.close();
					clearFields();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		INSERT.setBounds(10, 206, 89, 23);
		contentPane.add(INSERT);
		
		// UPDATE BUTTON
		JButton UPDATE = new JButton("UPDATE");
		UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "bca", "root");
					Statement stmt = con.createStatement();

					int id = Integer.parseInt(SUP_ID.getText());
					String nm = SUP_NME.getText();
					String ph = PH.getText();
					String email = EMAIL.getText();
					int prdId = Integer.parseInt(PRD_ID.getText());

					String qry = "update supplier set supplier_name='" + nm + "',phone='" + ph + "',email='" + email + "',product_id=" + prdId + " where supplier_id=" + id;
					int rows = stmt.executeUpdate(qry);

					if (rows > 0) {
						JOptionPane.showMessageDialog(null, "Supplier updated successfully!");
						clearFields();
					} else {
						JOptionPane.showMessageDialog(null, "Supplier ID not found!");
					}
					con.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		UPDATE.setBounds(139, 206, 89, 23);
		contentPane.add(UPDATE);
		
		// DELETE BUTTON
		JButton DEL = new JButton("DELETE");
		DEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "bca", "root");
					Statement stmt = con.createStatement();

					int id = Integer.parseInt(SUP_ID.getText());
					String qry = "delete from supplier where supplier_id=" + id;
					int rows = stmt.executeUpdate(qry);

					if (rows > 0) {
						JOptionPane.showMessageDialog(null, "Supplier deleted successfully!");
						clearFields();
					} else {
						JOptionPane.showMessageDialog(null, "Supplier ID not found!");
					}
					con.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		DEL.setBounds(273, 206, 89, 23);
		contentPane.add(DEL);

		// VIEW BUTTON
		JButton VIEW = new JButton("VIEW");
		VIEW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "bca", "root");
					Statement stmt = con.createStatement();

					StringBuilder qry = new StringBuilder("select * from supplier where 1=1");

					if (!SUP_ID.getText().trim().isEmpty()) {
						qry.append(" and supplier_id=").append(SUP_ID.getText().trim());
					}
					if (!SUP_NME.getText().trim().isEmpty()) {
						qry.append(" and supplier_name like '%").append(SUP_NME.getText().trim()).append("%'");
					}
					if (!PH.getText().trim().isEmpty()) {
						qry.append(" and phone like '%").append(PH.getText().trim()).append("%'");
					}
					if (!EMAIL.getText().trim().isEmpty()) {
						qry.append(" and email like '%").append(EMAIL.getText().trim()).append("%'");
					}
					if (!PRD_ID.getText().trim().isEmpty()) {
						qry.append(" and product_id=").append(PRD_ID.getText().trim());
					}

					ResultSet rs = stmt.executeQuery(qry.toString());

					Vector<String> columnNames = new Vector<>();
					columnNames.add("SUPPLIER_ID");
					columnNames.add("SUPPLIER_NAME");
					columnNames.add("PHONE");
					columnNames.add("EMAIL");
					columnNames.add("PRODUCT_ID");

					Vector<Vector<Object>> data = new Vector<>();
					while (rs.next()) {
						Vector<Object> row = new Vector<>();
						row.add(rs.getInt(1));
						row.add(rs.getString(2));
						row.add(rs.getString(3));
						row.add(rs.getString(4));
						row.add(rs.getInt(5));
						data.add(row);
					}

					table.setModel(new DefaultTableModel(data, columnNames));
					con.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		VIEW.setBounds(404, 206, 89, 23);
		contentPane.add(VIEW);

		// BACK BUTTON
		JButton btnNewButton_4 = new JButton("BACK");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MENU menu = new MENU();
				menu.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(404, 11, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 263, 483, 243);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"SUPPLIER_ID", "SUPPLIER_NAME", "PHONE", "EMAIL", "PRODUCT_ID"
			}
		));
		scrollPane.setViewportView(table);
	}
}