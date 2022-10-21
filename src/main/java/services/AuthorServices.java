package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import dto.AuthorDto;
import utils.AuthorTableModel;


public class AuthorServices {
	private JTable table;
	private AuthorTableModel authorTableModel;

	private List<Integer> selectedAuthorsId = new ArrayList<Integer>();
	private List<Integer> authorsId = new ArrayList<Integer>();
	private List<AuthorDto> authors = new ArrayList<>();
	
	private int authorId;
	

	// Encapsulation
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public AuthorTableModel getAuthorTableModel() {
		return authorTableModel;
	}

	public void setAuthorTableModel(AuthorTableModel authorTableModel) {
		this.authorTableModel = authorTableModel;
	}

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public List<Integer> getSelectedAuthorsId() {
		return selectedAuthorsId;
	}

	public void setSelectedAuthorsId(List<Integer> selectedAuthorsId) {
		this.selectedAuthorsId = selectedAuthorsId;
	}

	// Initialize the author table
	public void fillComponents() throws SQLException, ClassNotFoundException{
		authorTableModel = new AuthorTableModel();
		table.setModel(authorTableModel);

		clearAuthors();
		clearAuthorsId();
		clearSelectedAuthors();
		getAllAuthors();
	}

	// Clears the list of authors ids
	private void clearAuthorsId(){
		authorsId.clear();
	}

	// Clears the list of authors
	private void clearAuthors(){
		authors.clear();
	}

	// Clears the list of authors selected on the table
	private void clearSelectedAuthors(){
		selectedAuthorsId.clear();
	}

	// Load author table
	private void getAllAuthors() throws SQLException, ClassNotFoundException{
		String function = "{?= call all_authors()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			authorsId.add(resultSet.getInt(1));
			String firstName = resultSet.getString(2);
			String firstSurname = resultSet.getString(3);
			String secondSurname = resultSet.getString(4);


			((AuthorTableModel)table.getModel()).InsertAuthor(firstName, firstSurname, secondSurname);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}

	// Get the id of an author based on the selected row
	public int getAuthorIdByRow(int row){
		return authorsId.get(row);
	}

	// Load the selected authors
	public void loadSelectedAuthors(int[] rows) throws ClassNotFoundException, SQLException{
		for (int row : rows) {
			int authorId = getAuthorIdByRow(row);

			selectedAuthorsId.add(authorId);
			authors.add(findAuthorById(authorId));
		}
	}

	// Inserts an author
	public void insertAuthor(AuthorDto author) throws SQLException, ClassNotFoundException{
		String function = "{call insert_author(?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1, author.getName());
		preparedFunction.setString(2, author.getFirstSurname());
		preparedFunction.setString(3, author.getSecondSurname());
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}
	
	// Update author
	public void updateAuthor(AuthorDto author) throws SQLException {
		String function = "{call update_author(?, ?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, authorId);
		preparedFunction.setString(2, author.getName());
		preparedFunction.setString(3, author.getFirstSurname());
		preparedFunction.setString(4, author.getSecondSurname());
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Get a list of authors ids by having their full names
	public List<Integer> getAuthorsIdsByFullName(List<AuthorDto> authors) throws SQLException, ClassNotFoundException{
		List<Integer> authorsIds = new ArrayList<Integer>();

		String function = "{?= call get_author_id_by_full_name(?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.INTEGER);

		for (AuthorDto a : authors) {
			preparedFunction.setString(2, a.getName());
			preparedFunction.setString(3, a.getFirstSurname());
			preparedFunction.setString(4, a.getSecondSurname());
			preparedFunction.execute();
			authorsIds.add(preparedFunction.getInt(1));
		}

		preparedFunction.close();
		connection.close();

		return authorsIds;
	}

	// Get an author by having their id
	public AuthorDto findAuthorById(int authorId) throws SQLException, ClassNotFoundException{
		AuthorDto author = null;

		String function = "{?= call find_author(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, authorId);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		resultSet.next();
		String name = resultSet.getString(2);
		String firstSurname = resultSet.getString(3);
		String secondSurname = resultSet.getString(4);

		resultSet.close();
		preparedFunction.close();
		connection.close();

		author = new AuthorDto(name, firstSurname, secondSurname);

		return author;
	}

	// Get the id of the authors of a book having the id of the book
	public List<Integer> getAuthorsIdByBookId(int bookId) throws SQLException, ClassNotFoundException{
		List<Integer> authorsIds = new ArrayList<Integer>();

		String function = "{?= call get_authors_id_by_book_id(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, bookId);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			authorsIds.add(resultSet.getInt(2));
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();

		return authorsIds;
	}

	// Get book authors by having book id
	public List<AuthorDto> getBookAuthors(int bookId) throws ClassNotFoundException, SQLException{
		List<AuthorDto> authors = new ArrayList<AuthorDto>();
		List<Integer> authorsIds = getAuthorsIdByBookId(bookId);

		for (Integer i : authorsIds) {
			AuthorDto a = findAuthorById(i);
			authors.add(a);
		}

		return authors;
	}
	
	// Delete selected author in the table
	public void deleteSelectedAuthor() throws SQLException {
		String function = "{call delete_author(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, authorId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}
}
