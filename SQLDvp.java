package sqldeveloper;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SQLDvp extends JFrame {
	
	JTable t;
	Vector<String> columnNames = new Vector<>();
	Vector<Vector> rowData = new Vector<Vector>();
	Connection con;
	Statement stmt;
	ResultSetMetaData rsmd;
	String url="jdbc:oracle:thin:@localhost:1521:JAVA";
	String sql = "select * from DEPT order by DEPTNO";
	String sql2;
	ResultSet rs;
	int cc;
	SQLDvp myFrame;
	DefaultTableModel dfModel;
	public SQLDvp(){
		myFrame = this;
		try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, "scott", "tiger");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            cc  = rsmd.getColumnCount();
         }catch(ClassNotFoundException cnfe){
            System.out.println("드라이버로딩 실패(클래스를 못 찾음)");
         }catch(SQLException se){
               System.out.println("Oracle과 연결 실패 or stmt 생성 실패");
         }
		getColumnName();
		getRowData();
		init();	
	}
	public void getRowData() {
		try {	
			while(rs.next()) {
				Vector<String> row = new Vector<>();
				for(int i=1; i<=cc; i++) {			
						row.add(rs.getString(i));	
				}
				rowData.add(row);		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getColumnName() {
		try{
			for(int i=1; i<=cc;i++) {
				 String cn= rsmd.getColumnName(i);
				 columnNames.add(cn);
			}
			
         }catch(SQLException se){
        	 System.out.println(se);
         }
		
	}
	public void init() {
		Container cp = getContentPane();
		cp.setLayout(null);
		DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
		t = new JTable(model);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(t.getModel());
		
		JComboBox columnName = new JComboBox(columnNames);		
		columnName.setBounds(0, 0, 200, 42);
		cp.add(columnName);
		JTextField data = new JTextField();
		data.setBounds(200, 0, 200, 42);
		cp.add(data);
		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(0, 42, 375, 440);
		
		cp.add(sp);
		JButton insert = new JButton("추가");
		insert.setBounds(0, 522, 125, 40);
		cp.add(insert);

		JButton update = new JButton("수정");
		update.setBounds(125, 522, 125, 40);
		cp.add(update);
		JButton delete = new JButton("삭제");
		delete.setBounds(250, 522, 125, 40);
		cp.add(delete);
		
		JTextField row1 = new JTextField();
		row1.setBounds(0, 482, 125, 40);
		cp.add(row1);
		JTextField row2 = new JTextField();
		row2.setBounds(125, 482, 125, 40);
		cp.add(row2);
		JTextField row3 = new JTextField();
		row3.setBounds(250, 482, 125, 40);
		cp.add(row3);
		
		data.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() != KeyEvent.VK_SPACE) {
					String cn = columnName.getSelectedItem().toString();
					String option =data.getText();
					System.out.println("keylistener");
					try {
						rs = stmt.executeQuery(sql);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						dfModel = (DefaultTableModel)t.getModel();
						dfModel.setDataVector(rowData,columnNames);
						resetUI(cn, option);
					}
				}
			}
			
		});
		
		t.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = t.getSelectedRow();
				Object a =t.getValueAt(selectedRow, 0);
				Object b =t.getValueAt(selectedRow, 1);
				Object c =t.getValueAt(selectedRow, 2);
				String a1 =(String)a;
				String b1 =(String)b;
				String c1 =(String)c;
				row1.setText(a1);
				row2.setText(b1);
				row3.setText(c1);
			}
		});
		insert.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//rowData.clear();
				String deptno = row1.getText();
				int dn = Integer.parseInt(deptno);
				String dname = row2.getText();
				String loc = row3.getText();
				
				Vector<String> v1 = new Vector<>();
				v1.add(deptno); v1.add(dname); v1.add(loc);
				rowData.add(v1);
				String sql3 = "insert into dept values("+dn+",'"+dname+"','"+loc+"')";
				try {
					stmt.executeUpdate(sql3);
					model.fireTableDataChanged();	
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(myFrame, "무결성위반", "바보", JOptionPane.OK_OPTION  );
				}catch(Exception ee){
					JOptionPane.showMessageDialog(myFrame, "타입을 맞춰주세요", "바보", JOptionPane.OK_OPTION  );
				}
				row1.setText("");
				row2.setText("");
				row3.setText("");
				
				
				t.revalidate();
				t.repaint();
				resetUiInsert();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				dfModel = (DefaultTableModel)t.getModel();
				dfModel.setDataVector(rowData,columnNames);
				revalidate();
				repaint();
				System.out.println("released됨");
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = myFrame.t.getSelectedRow();
				if(selectedRow==-1){
					JOptionPane.showMessageDialog(myFrame, "선택된 행이 없어요", "바보", JOptionPane.OK_OPTION  );
					return;
				}
				DefaultTableModel dfModel = (DefaultTableModel)myFrame.t.getModel();
				Object a = myFrame.t.getValueAt(selectedRow, 0);
				dfModel.removeRow(selectedRow);
				String a1 = (String)a;
				System.out.println(a1);
				String sql3 = "delete from dept where deptno="+a+" ";
				try {
					stmt.executeUpdate(sql3);
					model.fireTableDataChanged();	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				resetUiInsert();
			}
			
		});
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = t.getSelectedRow();	
				String deptno = row1.getText();
				int dn = Integer.parseInt(deptno);
				String dname = row2.getText();
				String loc = row3.getText();
				String sql3 = "update dept set DNAME='"+dname+"', LOC='"+loc+"' where DEPTNO="+dn+"";
				try {
					stmt.executeUpdate(sql3);
					DefaultTableModel dfModel = (DefaultTableModel)t.getModel();
					t.setModel(dfModel);
					dfModel.fireTableDataChanged();	
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(myFrame, "무결성위반", "바보", JOptionPane.OK_OPTION  );
					e1.printStackTrace();
				}
				t.setValueAt(row1.getText(), selectedRow, 0);
				t.setValueAt(row2.getText(), selectedRow, 1);
				t.setValueAt(row3.getText(), selectedRow, 2);
				row1.setText("");
				row2.setText("");
				row3.setText("");
				resetUiInsert();
			}
		});
		setUI();
	}
	public void resetUiInsert() {
		try {
			dfModel = (DefaultTableModel)t.getModel();
			dfModel.setDataVector(rowData,columnNames);
			stmt = con.createStatement();
			rs= stmt.executeQuery("Select * from DEPT order by DEPTNO");
			rsmd = rs.getMetaData();
			rowData.clear();
			columnNames.clear();
			getColumnName();
			getRowData();
		}catch(SQLException se) {
			
		}
	}
	
	public void resetUI(String cn, String option) {
		try {
			dfModel = (DefaultTableModel)t.getModel();
			dfModel.setDataVector(rowData,columnNames);
			stmt = con.createStatement();
			//System.out.println("cn: "+cn +" option: "+option);
			rs= stmt.executeQuery("select * from DEPT where "+cn+" like'%"+option+"%' order by "+cn+"");
			rsmd = rs.getMetaData();
			rowData.clear();
			columnNames.clear();
			getColumnName();
			getRowData();
			//System.out.println("resetUI(cn,option)");
		}catch(SQLException se) {
			
		}
	}
	public void setUI() {
		setTitle("JINUN ORACLE DEVELOPER");
		setSize(390,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		SQLDvp sd = new SQLDvp();
	}

}
