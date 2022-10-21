package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JTable;

import dto.XUserDto;
import utils.AccountTableModel;
import utils.Encription;



public class XUserServices {
	private JTable table;
	private AccountTableModel accountTableModel;
	
	private String username;
	
	XRoleServices xRoleServices = ServicesLocator.getxRoleServices();

	// Encapsulation
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	// Fills the xUser table
	public void fillComponents() throws SQLException{
		accountTableModel = new AccountTableModel();
		table.setModel(accountTableModel);
		
		accountTableModel.InsertXUsers(getXUsers());
	}
	
	// Get the user role having its user name and password (obtained from login window)
	public String getLoginUser(String user, char [] password) {
		String role = "";
		
		try {
			String s = "SELECT xrol.description " +
					   "FROM xuser " +
					   "INNER JOIN xrol ON xuser.rol = xrol.rol " +
					   "WHERE xuser.username = ? " +
					   "AND xuser.xpassword = ? ";
			
			PreparedStatement prepararCons = ServicesLocator.getConnection().prepareStatement(s);
			prepararCons.setString(1, user); 
			String passs = new String(password);
			
			// Encrypting password to save in database
			String pass = Encription.getMd5(passs);
			prepararCons.setString(2, pass);
			prepararCons.execute();
			
			ResultSet result = prepararCons.getResultSet();
			
			while (result.next()) {
				role = result.getString(1);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return role;
	}

	// Get all xusers
	public LinkedList<XUserDto> getXUsers () throws SQLException {
		LinkedList<XUserDto> list= new LinkedList<XUserDto>();
		
		Statement consult = ServicesLocator.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String sqlSentenc = "SELECT xuser.username, xrol.description " +
				            "FROM xuser " +
				            "INNER JOIN xrol ON xuser.rol = xrol.rol";
		
		ResultSet resultado = consult.executeQuery(sqlSentenc);

		while (resultado.next()) {
			XUserDto user = new XUserDto();
			
			user.setUsername(resultado.getString(1));
			user.setRol(resultado.getString(2));
			list.add(user);
		}
		return list;
	}

	// Inserts a xuser
	public void insertXUser(String username, char[] password, String role)  throws SQLException, ClassNotFoundException{
		String pass = new String(password);
		
		String sqlSentenc = "INSERT INTO xuser (username, xpassword, rol) VALUES  (?, ?, ?)"; 
		
		PreparedStatement preparedStatement = ServicesLocator.getConnection().prepareStatement(sqlSentenc);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, Encription.getMd5(pass));
		preparedStatement.setInt(3, xRoleServices.getXRole(role));
		preparedStatement.execute();
	}

	// Updates a xuser
	public void updateXUser(String newUsername, char[] password, int role ) throws SQLException, ClassNotFoundException {
		String pass = new String(password);
		
		String sqlSentenc = "UPDATE xuser SET username = ?, xpassword = ?, rol = ?  " +
				            "WHERE username = ?; ";
		
		PreparedStatement prepararCons = ServicesLocator.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1, newUsername);
		prepararCons.setString(2, Encription.getMd5(pass));
		prepararCons.setInt(3, role);
		prepararCons.setString(4, username);
		prepararCons.execute();
	}

	// Deletes a xuser
	public void  deleteXUser(String username) throws SQLException, ClassNotFoundException { 
		String sqlSentenc = "DELETE FROM xuser WHERE  username = ?; ";
		
		PreparedStatement prepararCons = ServicesLocator.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1, username); 
		prepararCons.execute();
	}
}
