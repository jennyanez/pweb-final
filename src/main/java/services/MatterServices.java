package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import dto.MatterDto;
import utils.MatterTableModel;

public class MatterServices {
	private JTable table;
	private MatterTableModel matterTableModel;

	private int matterId;
	private MatterDto matter;
	private List<Integer> mattersId = new ArrayList<Integer>();
	
	private boolean fillingComboBox;

	// Encapsulation
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public int getMatterId() {
		return matterId;
	}

	public void setMatterId(int matterId) {
		this.matterId = matterId;
	}
	
	public MatterDto getMatter() {
		return matter;
	}

	public void setMatter(MatterDto matter) {
		this.matter = matter;
	}
	
	public boolean isFillingComboBox() {
		return fillingComboBox;
	}

	public void setFillingComboBox(boolean fillingComboBox) {
		this.fillingComboBox = fillingComboBox;
	}

	// Initialize the matter table
	public void fillComponents() throws SQLException, ClassNotFoundException{
		matterTableModel = new MatterTableModel();
		table.setModel(matterTableModel);

		clearMattersId();
		getAllMatters();
	}

	// Clears the list of matter ids
	private void clearMattersId(){
		mattersId.clear();
	}

	// Load matter table
	public List<String> getAllMatters() throws SQLException, ClassNotFoundException{
		List<String> mattersName = new ArrayList<String>();
		
		String function = "{?= call all_matters()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			String matterName = resultSet.getString(2);
			mattersName.add(matterName);

			if(!fillingComboBox){
				mattersId.add(resultSet.getInt(1));
				((MatterTableModel)table.getModel()).InsertMatter(matterName);
			}
				
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return mattersName;
	}

	// Get the id of a matter based on the selected row
	public int getMatterIdByRow(int row){
		return mattersId.get(row);
	}

	// Delete selected matter in table
	public void deleteSelectedMatter() throws SQLException, ClassNotFoundException{
		String function = "{call delete_matter(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, matterId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Inserts a matter
	public void insertMatter() throws SQLException, ClassNotFoundException{
		String function = "{call insert_matter(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1, matter.getName());
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Updates a matter
	public void updateMatter() throws SQLException, ClassNotFoundException{
		String function = "{call update_matter(?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, matterId);
		preparedFunction.setString(2, matter.getName());
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Find matter having its id
	public String findMatterById(int matterId) throws SQLException, ClassNotFoundException{
		String matter = "";

		String function = "{?= call find_matter_by_id(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, matterId);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		resultSet.next();
		matter = resultSet.getString(2);

		resultSet.close();
		preparedFunction.close();
		connection.close();

		return matter;
	}

	// Find matter having its name
	public int findMatterByName(String matterName) throws SQLException, ClassNotFoundException{
		int matterId = -1;
		String function = "{?= call find_matter_by_name(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setString(2, matterName);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		resultSet.next();
		matterId = resultSet.getInt(1);

		resultSet.close();
		preparedFunction.close();
		connection.close();

		return matterId;
	}
}
