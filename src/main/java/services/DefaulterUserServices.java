package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import dto.UserDto;
import utils.DefaulterUserTableModel;

public class DefaulterUserServices {
	private JTable table;
	private DefaulterUserTableModel defaulterUserTableModel;

	private List<Integer> defaulterUsersId = new ArrayList<Integer>();
	
	private UserServices userServices = ServicesLocator.getUserServices();

	// Encapsulation
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaulterUserTableModel getDefaulterUserTableModel() {
		return defaulterUserTableModel;

	}

	public void setDefaulterUserTableModel(DefaulterUserTableModel defaulterUserTableModel) {
		this.defaulterUserTableModel = defaulterUserTableModel;
	}

	// Initialize the defaulter user table
	public void fillComponents() throws SQLException, ClassNotFoundException{
		defaulterUserTableModel = new DefaulterUserTableModel();
		table.setModel(defaulterUserTableModel);

		clearDefaulterUsersId();
		getAllDefaulterUsers();
	}

	// Clears the list of defaulter user ids
	private void clearDefaulterUsersId(){
		defaulterUsersId.clear();
	}

	// Load defaulter user table
	private void getAllDefaulterUsers() throws SQLException, ClassNotFoundException{
		String function = "{?= call all_defaulter_users()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			int defaulterUserId = resultSet.getInt(2);
			defaulterUsersId.add(defaulterUserId);

			int userId = resultSet.getInt(1);
			String book = resultSet.getString(3);
			int days = resultSet.getInt(4);

			UserDto user = userServices.findUserById(userId);

			String area = user.getArea();
			String name = user.getName();
			String firstSurname = user.getFirstSurname();
			String lastSurname = user.getLastSurname();
			String DNI = user.getDNI();

			((DefaulterUserTableModel)table.getModel()).InsertDefaulterUser(DNI, area, name, firstSurname, lastSurname, book, days);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}

	// Get the id of a user based on the selected row
	public int getDefaulterUserIdByRow(int row){
		return defaulterUsersId.get(row);
	}

	// Delete selected user in table
	public void deleteSelectedDefaulterUser(int defaulterUserId) throws SQLException, ClassNotFoundException{
		String function = "{call delete_defaulter_user(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, defaulterUserId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}
}
