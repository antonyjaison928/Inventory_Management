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

public class PURHCASE extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField PURCH_ID;
	private JTextField PRD_ID;
	private JTextField SUPP_ID;
	private JTextField QTY;
	private JTextField PURCH_DTE;
	private JTextField TTL_AMT;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PURHCASE frame = new PURHCASE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void clearFields() {
		PURCH_ID.setText("");
		PRD_ID.setText("");
		SUPP_ID.setText("");
		QTY.setText("");
		PURCH_DTE.setText("");
		TTL_AMT.setText("");
	}

	/**
	 * Create the frame.
	 */
	public PURHCASE() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PURCHASES");
		lblNewLabel.setBounds(201, 0, 79, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PURCHASE_ID:");
		lblNewLabel_1.setBounds(10, 31, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PRODUCT_ID:");
		lblNewLabel_2.setBounds(10, 62, 100, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("SUPPLIER_ID :");
		lblNewLabel_3.setBounds(10, 90, 100, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("QUANTITY:");
		lblNewLabel_4.setBounds(10, 121, 100, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("PURCHASE_DATE :");
		lblNewLabel_5.setBounds(10, 152, 119, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("TOTAL_AMOUNT:");
		lblNewLabel_6.setBounds(10, 183, 100, 14);
		contentPane.add(lblNewLabel_6);
		
		PURCH_ID = new JTextField();
		PURCH_ID.setBounds(194, 25, 86, 20);
		contentPane.add(PURCH_ID);
		PURCH_ID.setColumns(10);
		
		PRD_ID = new JTextField();
		PRD_ID.setBounds(194, 56, 86, 20);
		contentPane.add(PRD_ID);
		PRD_ID.setColumns(10);
		
		SUPP_ID = new JTextField();
		SUPP_ID.setBounds(194, 84, 86, 20);
		contentPane.add(SUPP_ID);
		SUPP_ID.setColumns(10);
		
		QTY = new JTextField();
		QTY.setBounds(194, 115, 86, 20);
		contentPane.add(QTY);
		QTY.setColumns(10);
		
		PURCH_DTE = new JTextField();
		PURCH_DTE.setBounds(194, 146, 86, 20);
		contentPane.add(PURCH_DTE);
		PURCH_DTE.setColumns(10);
		
		TTL_AMT = new JTextField();
		TTL_AMT.setBounds(194, 177, 86, 20);
		contentPane.add(TTL_AMT);
		TTL_AMT.setColumns(10);
		
		JButton INSERT = new JButton("INSERT");
		INSERT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "bca", "root");
					Statement stmt = con.createStatement();

					int purchId = Integer.parseInt(PURCH_ID.getText());
					int prdId = Integer.parseInt(PRD_ID.getText());
					int suppId = Integer.parseInt(SUPP_ID.getText());
					int qty = Integer.parseInt(QTY.getText());
					String dte = PURCH_DTE.getText();
					double ttl = Double.parseDouble(TTL_AMT.getText());

					String qry = "insert into purchase values (" + purchId + "," + prdId + "," + suppId + "," + qty + ",'" + dte + "'," + ttl + ")";
					stmt.executeUpdate(qry);

					JOptionPane.showMessageDialog(null, "Purchase added successfully!");
					con.close();
					clearFields();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		INSERT.setBounds(10, 236, 89, 23);
		contentPane.add(INSERT);
		
		JButton VIEW = new JButton("VIEW");
		VIEW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "bca", "root");
					Statement stmt = con.createStatement();

					StringBuilder qry = new StringBuilder("select * from purchase where 1=1");

					if (!PURCH_ID.getText().trim().isEmpty()) {
						qry.append(" and purchase_id=").append(PURCH_ID.getText().trim());
					}
					if (!PRD_ID.getText().trim().isEmpty()) {
						qry.append(" and product_id=").append(PRD_ID.getText().trim());
					}
					if (!SUPP_ID.getText().trim().isEmpty()) {
						qry.append(" and supplier_id=").append(SUPP_ID.getText().trim());
					}
					if (!QTY.getText().trim().isEmpty()) {
						qry.append(" and quantity=").append(QTY.getText().trim());
					}
					if (!PURCH_DTE.getText().trim().isEmpty()) {
						qry.append(" and purchase_date like '%").append(PURCH_DTE.getText().trim()).append("%'");
					}
					if (!TTL_AMT.getText().trim().isEmpty()) {
						qry.append(" and total_amount=").append(TTL_AMT.getText().trim());
					}

					ResultSet rs = stmt.executeQuery(qry.toString());

					Vector<String> columnNames = new Vector<>();
					columnNames.add("PURCHASE_ID");
					columnNames.add("PRODUCT_ID");
					columnNames.add("SUPPLIER_ID");
					columnNames.add("QUANTITY");
					columnNames.add("PURCHASE_DATE");
					columnNames.add("TOTAL_AMOUNT");

					Vector<Vector<Object>> data = new Vector<>();
					while (rs.next()) {
						Vector<Object> row = new Vector<>();
						row.add(rs.getInt(1));
						row.add(rs.getInt(2));
						row.add(rs.getInt(3));
						row.add(rs.getInt(4));
						row.add(rs.getString(5));
						row.add(rs.getDouble(6));
						data.add(row);
					}

					table.setModel(new DefaultTableModel(data, columnNames));
					con.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		VIEW.setBounds(396, 236, 89, 23);
		contentPane.add(VIEW);
		
		JButton UPD = new JButton("UPDATE");
		UPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "bca", "root");
					Statement stmt = con.createStatement();

					int purchId = Integer.parseInt(PURCH_ID.getText());
					int prdId = Integer.parseInt(PRD_ID.getText());
					int suppId = Integer.parseInt(SUPP_ID.getText());
					int qty = Integer.parseInt(QTY.getText());
					String dte = PURCH_DTE.getText();
					double ttl = Double.parseDouble(TTL_AMT.getText());

					String qry = "update purchase set product_id=" + prdId + ",supplier_id=" + suppId + ",quantity=" + qty + ",purchase_date='" + dte + "',total_amount=" + ttl + " where purchase_id=" + purchId;
					int rows = stmt.executeUpdate(qry);

					if (rows > 0) {
						JOptionPane.showMessageDialog(null, "Purchase updated successfully!");
						clearFields();
					} else {
						JOptionPane.showMessageDialog(null, "Purchase ID not found!");
					}
					con.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		UPD.setBounds(142, 236, 89, 23);
		contentPane.add(UPD);
		
		JButton DEL = new JButton("DELETE");
		DEL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/FREEPDB1", "bca", "root");
					Statement stmt = con.createStatement();

					int purchId = Integer.parseInt(PURCH_ID.getText());
					String qry = "delete from purchase where purchase_id=" + purchId;
					int rows = stmt.executeUpdate(qry);

					if (rows > 0) {
						JOptionPane.showMessageDialog(null, "Purchase deleted successfully!");
						clearFields();
					} else {
						JOptionPane.showMessageDialog(null, "Purchase ID not found!");
					}
					con.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		DEL.setBounds(275, 236, 89, 23);
		contentPane.add(DEL);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 300, 475, 253);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"PURCHASE_ID", "PRODUCT_ID", "SUPPLIER_ID", "QUANTITY", "PURCHASE_DATE", "TOTAL_AMOUNT"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(105);
		table.getColumnModel().getColumn(1).setPreferredWidth(104);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(83);
		table.getColumnModel().getColumn(4).setPreferredWidth(116);
		table.getColumnModel().getColumn(5).setPreferredWidth(117);
		scrollPane.setViewportView(table);
		
		JButton BACK = new JButton("BACK");
		BACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MENU menu = new MENU();
				menu.setVisible(true);
				dispose();
			}
		});
		BACK.setBounds(396, 11, 89, 23);
		contentPane.add(BACK);

	}

}