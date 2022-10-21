package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import dto.UserDto;
import utils.UserTableModel;

public class UserServices {
	private JTable table;
	private UserTableModel userTableModel;

	private UserDto user;
	private int userId;

	private List<Integer> usersId = new ArrayList<Integer>();

	// Encapsulation
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public UserTableModel getUserTableModel() {
		return userTableModel;
	}

	public void setUserTableModel(UserTableModel userTableModel) {
		this.userTableModel = userTableModel;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	// Initialize the user table
	public void fillComponents() throws SQLException, ClassNotFoundException{
		setUserTableModel(new UserTableModel());
		table.setModel(userTableModel);

		clearUsersId();
		getAllUsers();
	}

	// Clears the list of user ids
	private void clearUsersId(){
		usersId.clear();
	}

	// Load user table
	private void getAllUsers() throws SQLException, ClassNotFoundException{
		String function = "{?= call all_users()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			usersId.add(resultSet.getInt(1));
			String area = resultSet.getString(2);
			String name = resultSet.getString(3);
			String firstSurname = resultSet.getString(4);
			String lastSurname = resultSet.getString(5);
			String DNI = resultSet.getString(6);

			((UserTableModel)table.getModel()).InsertUser(DNI, area, name, firstSurname, lastSurname);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}

	// Get the id of a user based on the selected row
	public int getUserIdByRow(int row){
		return usersId.get(row);
	}

	// Delete selected user in table
	public void deleteSelectedUser() throws SQLException, ClassNotFoundException{
		String function = "{call delete_user(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, userId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Inserts an user
	public void insertUser() throws SQLException, ClassNotFoundException{
		String function = "{call insert_user(?, ?, ?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1, user.getArea());
		preparedFunction.setString(2, user.getName());
		preparedFunction.setString(3, user.getFirstSurname());
		preparedFunction.setString(4, user.getLastSurname());
		preparedFunction.setString(5, user.getDNI());
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Updates an user
	public void updateUser() throws SQLException, ClassNotFoundException{
		String function = "{call update_user(?, ?, ?, ?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, userId);
		preparedFunction.setString(2, user.getArea());
		preparedFunction.setString(3, user.getName());
		preparedFunction.setString(4, user.getFirstSurname());
		preparedFunction.setString(5, user.getLastSurname());
		preparedFunction.setString(6, user.getDNI());
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Get an user by having their id
	public UserDto findUserById(int userId) throws SQLException, ClassNotFoundException{
		UserDto user = null;

		String function = "{?= call find_user(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, userId);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		resultSet.next();
		String area = resultSet.getString(2);
		String name = resultSet.getString(3);
		String firstSurname = resultSet.getString(4);
		String lastSurname = resultSet.getString(5);
		String DNI = resultSet.getString(6);

		resultSet.close();
		preparedFunction.close();
		connection.close();

		user = new UserDto(DNI, area, name, firstSurname, lastSurname);

		return user;
	}
	public int findUserIdByDni(String userDni) throws SQLException {
		
		String function = "{?= call find_user_id_by_dni(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.INTEGER);
		preparedFunction.setString(2, userDni);
		preparedFunction.execute();
		
		userId = preparedFunction.getInt(1);
		
		preparedFunction.close();
		connection.close();
		
		return userId;
	}
}
