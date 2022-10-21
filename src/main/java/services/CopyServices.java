package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import dto.AuthorDto;
import dto.BookDto;
import utils.CopyTableModel;

public class CopyServices {
	private JTable table;
	private CopyTableModel copyTableModel;

	private int bookId;
	private int copyNumber;
	private int copyId;

	private BookServices bookServices = ServicesLocator.getBookServices();
	private List<Integer> copiesId = new ArrayList<Integer>();

	// Encapsulation
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public CopyTableModel getCopyTableModel() {
		return copyTableModel;
	}

	public void setCopyTableModel(CopyTableModel copyTableModel) {
		this.copyTableModel = copyTableModel;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}

	public int getCopyId() {
		return copyId;
	}

	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}

	// Initialize the copy table
	public void fillComponents() throws SQLException, ClassNotFoundException{
		copyTableModel = new CopyTableModel();
		table.setModel(copyTableModel);

		clearCopiesId();
		getAllCopies();
	}

	// Clears the list of copy ids
	private void clearCopiesId(){
		copiesId.clear();
	}

	// Get the id of a copy based on the selected row
	public int getCopyIdByRow(int row){
		return copiesId.get(row);
	}

	// Load copy table
	private void getAllCopies() throws SQLException, ClassNotFoundException{
		String function = "{?= call all_copies()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			copiesId.add(resultSet.getInt(1));
			int bookId = resultSet.getInt(2);
			int copyNumber = resultSet.getInt(3);
			BookDto book = bookServices.findBookById(bookId);

			String title = book.getTitle();
			int yearEdition = book.getYearEdition();
			String editorial = book.getEditorial();
			String countryOrigin = book.getCountryOrigin();
			String summary = book.getSummary();
			int amountPages = book.getAmountPages();
			String matterName = book.getMatter().getName();

			List<AuthorDto> authors = book.getAuthors();
			String authorsName = "";

			for (AuthorDto authorDto : authors) {
				authorsName += authorDto.getFullName() + " ";
			}

			((CopyTableModel)table.getModel()).InsertCopy(copyNumber, title, yearEdition, editorial, countryOrigin,
					summary, amountPages, matterName, authorsName);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}

	// Inserts a copy
	public void insertCopy() throws SQLException, ClassNotFoundException{
		String function = "{call insert_copy(?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, copyNumber);
		preparedFunction.setInt(2, bookId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Delete selected copy in table
	public void deleteSelectedCopy() throws SQLException, ClassNotFoundException{
		String function = "{call delete_copy(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, copyId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Updates a copy
	public void updateCopy() throws SQLException, ClassNotFoundException{
		int bookId = findBookIdOfCopy();

		String function = "{call update_copy(?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, copyId);
		preparedFunction.setInt(2, copyNumber);
		preparedFunction.setInt(3, bookId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Finds book id of a copy having the copy id
	public int findBookIdOfCopy() throws SQLException, ClassNotFoundException{
		int bookId = -1;

		String function = "{?= call find_copy(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, copyId);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		resultSet.next();
		bookId = resultSet.getInt(2);

		resultSet.close();
		preparedFunction.close();
		connection.close();

		return bookId;
	}

	// Gets the copy number having its id
	public int findCopyNumberById(int copyId) throws SQLException {
		int copyNumber = -1;

		String function = "{?= call find_copy(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, copyId);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		resultSet.next();
		copyNumber = resultSet.getInt(3);

		resultSet.close();
		preparedFunction.close();
		connection.close();		

		return copyNumber;
	}

	// Load copy table for selecting a book for a loan
	private void getAllAvailablesCopies() throws SQLException, ClassNotFoundException{
		String function = "{?= call copies_availables()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			copiesId.add(resultSet.getInt(1));
			int bookId = resultSet.getInt(2);
			int copyNumber = resultSet.getInt(3);
			BookDto book = bookServices.findBookById(bookId);

			String title = book.getTitle();
			int yearEdition = book.getYearEdition();
			String editorial = book.getEditorial();
			String countryOrigin = book.getCountryOrigin();
			String summary = book.getSummary();
			int amountPages = book.getAmountPages();
			String matterName = book.getMatter().getName();

			List<AuthorDto> authors = book.getAuthors();
			String authorsName = "";

			for (AuthorDto authorDto : authors) {
				authorsName += authorDto.getFullName() + " ";
			}

			((CopyTableModel)table.getModel()).InsertCopy(copyNumber, title, yearEdition, editorial, countryOrigin,
					summary, amountPages, matterName, authorsName);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}

	// Initialize the copies available table
	public void fillComponentsAvailableCopies() throws ClassNotFoundException, SQLException {
		copyTableModel = new CopyTableModel();
		table.setModel(copyTableModel);

		clearCopiesId();
		getAllAvailablesCopies();		
	}
}
