package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import dto.UserDto;
import utils.SanctionedUserTableModel;

public class SanctionedUserServices {
	private JTable table;
	private SanctionedUserTableModel sanctionedUserTableModel;

	private List<Integer> sanctionedUsersId = new ArrayList<Integer>();
	
	private UserServices userServices = ServicesLocator.getUserServices();

	// Encapsulation
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public SanctionedUserTableModel getSanctionedUserTableModel() {
		return sanctionedUserTableModel;
	}

	public void setSanctionedUserTableModel(SanctionedUserTableModel sanctionedUserTableModel) {
		this.sanctionedUserTableModel = sanctionedUserTableModel;
	}

	// Initialize the sanctioned user table
	public void fillComponents() throws SQLException, ClassNotFoundException{
		sanctionedUserTableModel = new SanctionedUserTableModel();
		table.setModel(sanctionedUserTableModel);

		clearSanctionedUsersId();
		GetAllSanctionedUsers();
	}

	// Clears the list of sanctioned user ids
	private void clearSanctionedUsersId(){
		sanctionedUsersId.clear();
	}

	// Load sanctioned user table
	private void GetAllSanctionedUsers() throws SQLException, ClassNotFoundException{
		String function = "{?= call all_sanctioned_users()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			int sanctionedUserId = resultSet.getInt(1);
			sanctionedUsersId.add(sanctionedUserId);

			int userId = resultSet.getInt(2);
			String sanction = resultSet.getString(3);
			String startSanction = resultSet.getDate(4).toString();
			String endSanction = resultSet.getDate(5).toString();

			UserDto user = userServices.findUserById(userId);

			String area = user.getArea();
			String name = user.getName();
			String firstSurname = user.getFirstSurname();
			String lastSurname = user.getLastSurname();
			String DNI = user.getDNI();

			((SanctionedUserTableModel)table.getModel()).InsertSanctionedUser(DNI, area, name, firstSurname,
					                                      lastSurname, sanction, startSanction, endSanction);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}
	// Load sanctioned user table having a date range
		public void getAllSanctionedUsersRangeDate(Date initialDate, Date endDate) throws SQLException, ClassNotFoundException{
			String function = "{?= call all_sanctioned_users()}";
			java.sql.Connection connection = ServicesLocator.getConnection();
			connection.setAutoCommit(false);

			CallableStatement preparedFunction = connection.prepareCall(function);
			preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
			preparedFunction.execute();
			ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

			while (resultSet.next()){
				int sanctionedUserId = resultSet.getInt(1);
				sanctionedUsersId.add(sanctionedUserId);

				int userId = resultSet.getInt(2);
				String sanction = resultSet.getString(3);
				
				java.sql.Date ss = resultSet.getDate(4);
				java.sql.Date es = resultSet.getDate(5);
				
				Date startSanction = new Date(ss.getTime());
				
				if (startSanction.after(initialDate) && startSanction.before(endDate)) {
					String startSanctionS = ss.toString();
					String endSanctionS = es.toString();
					
					UserDto user = userServices.findUserById(userId);

					String area = user.getArea();
					String name = user.getName();
					String firstSurname = user.getFirstSurname();
					String lastSurname = user.getLastSurname();
					String DNI = user.getDNI();

					((SanctionedUserTableModel)table.getModel()).InsertSanctionedUser(DNI, area, name, firstSurname,
							lastSurname, sanction, startSanctionS, endSanctionS);
				}
			}

			resultSet.close();
			preparedFunction.close();
			connection.close();
		}
}
