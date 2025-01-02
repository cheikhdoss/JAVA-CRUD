package crud;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(System.in);
		String get_driver="com.mysql.cj.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/gestion";
		String user="root";
		String pass ="";
    try {
		Class.forName(get_driver);
		Connection conn = DriverManager.getConnection(url,user,pass);
		Statement stmt= conn.createStatement();
		
		
		System.out.println("saisir le nom");
		String nom= scanner.nextLine();
		
		System.out.println("saisir le prenom");
		String prenom= scanner.nextLine();
		
		
		System.out.println("saisir le filiere");
		String filiere= scanner.nextLine();
		
		String query ="INSERT INTO gestioniage (nom,prenom,filiere)VALUES('"+nom+"','"+prenom+"','"+filiere+"') ";
		stmt.executeUpdate(query);
		
		String Query ="SELECT * FROM gestioniage ";
		ResultSet result =stmt.executeQuery(Query);
		while(result.next()) {
			System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4));
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.print("error :"+e.getMessage());
	}

	}

}
