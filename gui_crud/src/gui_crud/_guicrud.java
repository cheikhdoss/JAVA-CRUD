package gui_crud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class _guicrud {

	private JFrame frame;
	private JTable table;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textFiliere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					_guicrud window = new _guicrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public _guicrud() {
		initialize();
		connect();
		table();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection conn;
	PreparedStatement rs;
	ResultSet pst;
	Statement stmt;
	protected boolean isTableLoaded;
	protected boolean isLoadedTable;
	private JTextField textID;
	
	public void connect() {
		String get_url="jdbc:mysql://localhost:3306/gestion";
		String user="root";
		String pass="";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(get_url,user,pass);
			
		}
		catch(Exception E) {
			System.out.print(""+E.getMessage());
		}
		
	}
	public void table()
	{
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		try {
			//Jtable table =new Jtable;
			
			rs=conn.prepareStatement("SELECT * FROM gestioniage");
			pst=rs.executeQuery();
			
		while (pst.next())
		{
			model.addRow(new String[] {pst.getString(1),pst.getString(2),pst.getString(3),pst.getString(4)});
		}
		}
		catch(Exception E) {
			System.out.print(""+E.getMessage());
		}
	}
	
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 984, 713);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 110, 444, 354);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INFORMATIONS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(150, 11, 151, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(24, 55, 70, 36);
		panel.add(lblNewLabel_1);
		
		textNom = new JTextField();
		textNom.setBounds(97, 58, 303, 33);
		panel.add(textNom);
		textNom.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(24, 138, 104, 36);
		panel.add(lblNewLabel_1_1);
		
		textPrenom = new JTextField();
		textPrenom.setColumns(10);
		textPrenom.setBounds(133, 141, 267, 33);
		panel.add(textPrenom);
		
		JLabel lblNewLabel_1_2 = new JLabel("Filiere :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1_2.setBounds(24, 235, 104, 36);
		panel.add(lblNewLabel_1_2);
		
		textFiliere = new JTextField();
		textFiliere.setColumns(10);
		textFiliere.setBounds(133, 238, 267, 33);
		panel.add(textFiliere);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(495, 73, 465, 466);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(-6, -16, 477, 488);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new CompoundBorder());
		scrollPane_1.setBounds(6, 16, 465, 466);
		panel_3.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nom", "Prenom", "filiere"
			}
		));
		
		
		
		
		JButton btnNewButton = new JButton("save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom,prenom,filiere;
				nom=textNom.getText();
				prenom=textPrenom.getText();
				filiere=textFiliere.getText();
				try {
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					model.setRowCount(0);
					rs=conn.prepareStatement("INSERT INTO gestioniage (nom,prenom,filiere) VALUES (?,?,?)");
					rs.setString(1,nom);
					rs.setString(2,prenom);
					rs.setString(3,filiere);
					rs.executeUpdate();
					table();
					textNom.setText("");
					textPrenom.setText("");
					textFiliere.setText("");
					JOptionPane.showMessageDialog(frame, "les donnees on bien ete enregistrer");
				}
				
				catch(Exception E) {
					System.out.print("erreur rencontrer"+E.getMessage());
				}
			}
		});
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(10, 487, 108, 63);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNom.setText(null);
				textPrenom.setText(null);
				textFiliere.setText(null);
				textID.setText(null);
				JOptionPane.showMessageDialog(null, "ecriture effacer");
			}
		});
		btnEffacer.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEffacer.setBounds(329, 487, 108, 63);
		frame.getContentPane().add(btnEffacer);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom,prenom,filiere,id;
				id = textID.getText();
				nom=textNom.getText();
				prenom=textPrenom.getText();
				filiere=textFiliere.getText();
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					stmt=conn.createStatement();
					String query=("UPDATE gestioniage SET nom='"+nom+"', prenom='"+prenom+"' ,filiere='"+filiere+"' WHERE id ='"+id+"'");
					stmt.executeUpdate(query);
					table();
					JOptionPane.showMessageDialog(null, "les donnees on ete mise a jour ");
					
				}
				catch(Exception E) {
					System.out.print("erreur rencontrer"+E.getMessage());
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(549, 569, 108, 63);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id;
				id=textID.getText();
				try {
					DefaultTableModel model= (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					rs=conn.prepareStatement("DELETE  FROM gestioniage WHERE id =?");
					rs.setString(1,id);
					rs.executeUpdate();
				table();
				JOptionPane.showMessageDialog(null, "donnee effacer sur la table");
				}
				catch(Exception ex) {
					System.out.print(ex.getMessage());
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(791, 569, 108, 63);
		frame.getContentPane().add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 561, 398, 104);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("recherche");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(150, 0, 86, 22);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ID :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(26, 39, 49, 34);
		panel_1.add(lblNewLabel_3);
		
		textID = new JTextField();
		textID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				String id = textID.getText();
				try {
					
					String query=("SELECT * FROM gestioniage WHERE id='"+id+"'");
					 stmt = conn.createStatement();
					
					pst=stmt.executeQuery(query);
					while (pst.next()) {
					textNom.setText(pst.getString(2));
					textPrenom.setText(pst.getString(3));
					textFiliere.setText(pst.getString(4));
					}
				}
				catch(Exception E) {
					System.out.print("erreur rencontrer"+E.getMessage());
				}
			}
		});
		textID.setBounds(69, 38, 290, 35);
		panel_1.add(textID);
		textID.setColumns(10);
		
		JLabel lblNewLabel_4 =  new JLabel("CRUD");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblNewLabel_4.setBounds(377, 0, 171, 62);
		frame.getContentPane().add(lblNewLabel_4);
	}
}
