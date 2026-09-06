import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class PRODUCT extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField PRD_ID;
	private JTextField PRD_NME;
	private JTextField CTG;
	private JTextField PRICE;
	private JTextField QTY;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PRODUCT frame = new PRODUCT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void clearFields() {
		PRD_ID.setText("");
		PRD_NME.setText("");
		CTG.setText("");
		PRICE.setText("");
		QTY.setText("");
	}

	/**
	 * Create the frame.
	 */
	public PRODUCT() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRODUCT");
		lblNewLabel.setBounds(224, 6, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PRODUCT_ID:");
		lblNewLabel_1.setBounds(10, 37, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		PRD_ID = new JTextField();
		PRD_ID.setBounds(226, 31, 86, 20);
		contentPane.add(PRD_ID);
		PRD_ID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("PRODUCT_NAME:");
		lblNewLabel_2.setBounds(10, 65, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CATEGORY:");
		lblNewLabel_3.setBounds(10, 97, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PRICE(Numeric):");
		lblNewLabel_4.setBounds(10, 123, 86, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("QUANTITY(Numeric):");
		lblNewLabel_5.setBounds(10, 148, 111, 14);
		contentPane.add(lblNewLabel_5);
		
		PRD_NME = new JTextField();
		PRD_NME.setBounds(226, 62, 86, 20);
		contentPane.add(PRD_NME);
		PRD_NME.setColumns(10);
		
		CTG = new JTextField();
		CTG.setBounds(226, 91, 86, 20);
		contentPane.add(CTG);
		CTG.setColumns(10);
		
		PRICE = new JTextField();
		PRICE.setBounds(226, 117, 86, 20);
		contentPane.add(PRICE);
		PRICE.setColumns(10);
		
		QTY = new JTextField();
		QTY.setBounds(226, 142, 86, 20);
		contentPane.add(QTY);
		QTY.setColumns(10);
		
		JButton INSERT = new JButton("INSERT");
		INSERT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","bca","root");
					Statement stmt = con.createStatement();

					int id = Integer.parseInt(PRD_ID.getText());
					String nm = PRD_NME.getText();
					String ctg = CTG.getText();
					double prc = Double.parseDouble(PRICE.getText());
					int qty = Integer.parseInt(QTY.getText());

					String qry = "insert into product values ("+id+",'"+nm+"','"+ctg+"',"+prc+","+qty+")";
					stmt.executeUpdate(qry);

					JOptionPane.showMessageDialog(null, "Values inserted successfully!");
					con.close();
					clearFields();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
				
			}
		});
		
		INSERT.setBounds(10, 227, 89, 23);
		contentPane.add(INSERT);
		
		JButton UPDATE = new JButton("UPDATE");
		UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","bca","root");
					Statement stmt = con.createStatement();

					int id = Integer.parseInt(PRD_ID.getText());
					String nm = PRD_NME.getText();
					String ctg = CTG.getText();
					double prc = Double.parseDouble(PRICE.getText());
					int qty = Integer.parseInt(QTY.getText());

					String qry = "update product set product_name='" + nm + "',category='" + ctg + "',price=" + prc + ",quantity=" + qty + " where product_id=" + id;
					int rows = stmt.executeUpdate(qry);

					if (rows > 0) {
						JOptionPane.showMessageDialog(null, "Updated");
						clearFields();
					} else {
						JOptionPane.showMessageDialog(null, "ID not found");
					}
					con.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
				
			}
		});
		
		UPDATE.setBounds(140, 227, 89, 23);
		contentPane.add(UPDATE);
		
		JButton DEL = new JButton("DELETE");
		DEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","bca","root");
					Statement stmt = con.createStatement();

					int id = Integer.parseInt(PRD_ID.getText());
					String qry = "delete from product where product_id=" + id;
					int rows = stmt.executeUpdate(qry);

					if (rows > 0) {
						JOptionPane.showMessageDialog(null, "Product deleted successfully!");
						clearFields();
					} else {
						JOptionPane.showMessageDialog(null, "Product ID not found!");
					}
					con.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
	
				
			}
		});
		
		DEL.setBounds(281, 227, 89, 23);
		contentPane.add(DEL);
		
		JButton VIEW = new JButton("VIEW");
		VIEW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1","bca","root");
					Statement stmt = con.createStatement();

					StringBuilder qry = new StringBuilder("select * from product where 1=1");

					if (!PRD_ID.getText().trim().isEmpty()) {
						qry.append(" and product_id=").append(PRD_ID.getText().trim());
					}
					if (!PRD_NME.getText().trim().isEmpty()) {
						qry.append(" and product_name like '%").append(PRD_NME.getText().trim()).append("%'");
					}
					if (!CTG.getText().trim().isEmpty()) {
						qry.append(" and category like '%").append(CTG.getText().trim()).append("%'");
					}
					if (!PRICE.getText().trim().isEmpty()) {
						qry.append(" and price=").append(PRICE.getText().trim());
					}
					if (!QTY.getText().trim().isEmpty()) {
						qry.append(" and quantity=").append(QTY.getText().trim());
					}

					ResultSet rs = stmt.executeQuery(qry.toString());

					Vector<String> columnNames = new Vector<>();
					columnNames.add("PRODUCT_ID");
					columnNames.add("PRODUCT_NAME");
					columnNames.add("CATEGORY");
					columnNames.add("PRICE");
					columnNames.add("QUANTITY");

					Vector<Vector<Object>> data = new Vector<>();
					while (rs.next()) {
						Vector<Object> row = new Vector<>();
						row.add(rs.getInt(1));
						row.add(rs.getString(2));
						row.add(rs.getString(3));
						row.add(rs.getDouble(4));
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
		
		VIEW.setBounds(407, 227, 89, 23);
		contentPane.add(VIEW);
		
		JButton btnNewButton_4 = new JButton("BACK");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MENU menu = new MENU();
				menu.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(407, 11, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 287, 484, 204);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"PRODUCT_ID", "PRODUCT_NAME", "CATEGORY", "PRICE", "QUANTITY"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(82);
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		scrollPane.setViewportView(table);

	}
}