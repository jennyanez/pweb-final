package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

import dto.AuthorDto;
import dto.BookDto;
import dto.MatterDto;
import utils.BookReportTableModel;
import utils.BookTableModel;

public class BookServices {
	private JTable table;
	private BookTableModel bookTableModel;
	private BookReportTableModel bookReportTableModel;

	private JComboBox<Object> matterComboBox;
	private DefaultComboBoxModel<Object> defaultComboBoxModel;

	private int bookId;
	private BookDto book;
	private List<Integer> booksId = new ArrayList<Integer>();

	private AuthorServices authorServices = ServicesLocator.getAuthorServices();
	private MatterServices matterServices = ServicesLocator.getMatterServices();

	// Encapsulation
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public BookTableModel getBookTableModel() {
		return bookTableModel;
	}

	public void setBookTableModel(BookTableModel bookTableModel) {
		this.bookTableModel = bookTableModel;
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	public JComboBox<Object> getMatterComboBox() {
		return matterComboBox;
	}

	public void setMatterComboBox(JComboBox<Object> matterComboBox) {
		this.matterComboBox = matterComboBox;
	}

	// Initialize the book table
	public void fillComponents() throws SQLException, ClassNotFoundException{
		bookTableModel = new BookTableModel();
		table.setModel(bookTableModel);

		clearBooksId();
		getAllBooks();
	}

	// Clears the list of book ids
	private void clearBooksId(){
		booksId.clear();
	}

	// Load book table
	private void getAllBooks() throws SQLException, ClassNotFoundException{
		String function = "{?= call all_books()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			booksId.add(resultSet.getInt(1));
			String title = resultSet.getString(2);
			int yearEdition = resultSet.getInt(3);
			String editorial = resultSet.getString(4);
			String countryOrigin = resultSet.getString(5);
			String summary = resultSet.getString(6);
			int amountPages = resultSet.getInt(7);
			String matter = matterServices.findMatterById(resultSet.getInt(8));
			String code = resultSet.getString(9);

			List<Integer> authorsIds = authorServices.getAuthorsIdByBookId(resultSet.getInt(1));
			String bookAuthorsNames = "";

			for (Integer i : authorsIds) {
				AuthorDto author = authorServices.findAuthorById(i);
				bookAuthorsNames += author.getFullName() + "  ";
			}

			((BookTableModel)table.getModel()).InsertBook(code, title, yearEdition, editorial, countryOrigin,
					summary, amountPages, matter, bookAuthorsNames);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}

	// Get the id of a book based on the selected row
	public int getBookIdByRow(int row){
		return booksId.get(row);
	}

	// Delete selected book in table
	public void deleteSelectedBook() throws SQLException, ClassNotFoundException{
		String function = "{call delete_book(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, bookId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Delete the authors of the selected book
	public void deleteAuthorsOfSelectedBook() throws SQLException, ClassNotFoundException{
		String function = "{call delete_book_author(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, bookId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Update a book
	public void updateBook() throws SQLException, ClassNotFoundException{
		String function = "{call update_book(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, bookId);
		preparedFunction.setString(2, book.getCode());
		preparedFunction.setString(3, book.getTitle());
		preparedFunction.setInt(4, book.getYearEdition());
		preparedFunction.setString(5, book.getEditorial());
		preparedFunction.setString(6, book.getCountryOrigin());
		preparedFunction.setString(7, book.getSummary());
		preparedFunction.setInt(8, book.getAmountPages());
		preparedFunction.setInt(9, matterServices.findMatterByName(book.getMatter().getName()));
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Inserts the book
	public void insertBook() throws SQLException, ClassNotFoundException{
		String function = "{call insert_book(?, ?, ?, ?, ?, ?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setString(1, book.getCode());
		preparedFunction.setString(2, book.getTitle());
		preparedFunction.setInt(3, book.getYearEdition());
		preparedFunction.setString(4, book.getEditorial());
		preparedFunction.setString(5, book.getCountryOrigin());
		preparedFunction.setString(6, book.getSummary());
		preparedFunction.setInt(7, book.getAmountPages());
		preparedFunction.setInt(8, matterServices.findMatterByName(book.getMatter().getName()));
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}

	// Get a book by its code
	public int getBookIdByCode() throws SQLException, ClassNotFoundException{
		int bookId = -1;
		
		String function = "{?= call get_book_id_by_code(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.INTEGER);
		preparedFunction.setString(2, book.getCode());
		preparedFunction.execute();
		
		bookId = preparedFunction.getInt(1);

		preparedFunction.close();
		connection.close();

		return bookId;
	}

	// Get a book by its id
	public BookDto findBookById(int bookId) throws SQLException, ClassNotFoundException{
		String function = "{?= call find_book(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, bookId);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		resultSet.next();

		String title = resultSet.getString(2);
		int yearEdition = resultSet.getInt(3);
		String editorial = resultSet.getString(4);
		String countryOrigin = resultSet.getString(5);
		String summary = resultSet.getString(6);
		int amountPages = resultSet.getInt(7);
		String matterName = matterServices.findMatterById(resultSet.getInt(8));
		String code = resultSet.getString(9);

		MatterDto matter = new MatterDto(matterName);
		BookDto book = new BookDto(code, title, yearEdition, editorial, countryOrigin, summary, amountPages, matter);

		List<AuthorDto> authors = authorServices.getBookAuthors(bookId);
		book.setAuthors(authors);

		resultSet.close();
		preparedFunction.close();
		connection.close();

		return book;
	}

	// Fill the matter combo box
	public void fillMatterComboBox() throws ClassNotFoundException, SQLException{
		matterServices.setFillingComboBox(true);
		Object[] matters = matterServices.getAllMatters().toArray();
		matterServices.setFillingComboBox(false);

		defaultComboBoxModel = new DefaultComboBoxModel<Object>(matters);
		matterComboBox.setModel(defaultComboBoxModel);
	}

	// Load book data table by author *report 1*
	private void getAllBooksByAuthor() throws SQLException, ClassNotFoundException{
		String function = "{?= call show_books_by_author_id(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, authorServices.getAuthorId());
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			String title = resultSet.getString(1);
			int yearEdition = resultSet.getInt(2);
			String code = resultSet.getString(3);

			((BookReportTableModel)table.getModel()).InsertBook(code, title, yearEdition);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}

	// Load book data table by matter*report 2*
	private void getAllBooksByMatter() throws SQLException, ClassNotFoundException{
		String function = "{?= call show_books_by_matter_id(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, matterServices.getMatterId());
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			String title = resultSet.getString(1);
			int yearEdition = resultSet.getInt(2);
			String code = resultSet.getString(3);

			((BookReportTableModel)table.getModel()).InsertBook(code, title, yearEdition);
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
	}

	// Load book data table by book title *report 3*
	public BookDto getBookData() throws SQLException, ClassNotFoundException{
		String function = "{?= call show_book_data_by_id(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.setInt(2, bookId);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);

		while (resultSet.next()){
			String title = resultSet.getString(2);
			int yearEdition = resultSet.getInt(3);
			String editorial = resultSet.getString(4);
			String countryOrigin = resultSet.getString(5);
			String summary = resultSet.getString(6);
			int amountPages = resultSet.getInt(7);
			String code = resultSet.getString(8);

			book = new BookDto(code, title, yearEdition, editorial, countryOrigin, summary, amountPages, null);

			List<Integer> authorsIds = authorServices.getAuthorsIdByBookId(resultSet.getInt(1));

			for (Integer i : authorsIds) {
				AuthorDto author = authorServices.findAuthorById(i);
				book.getAuthors().add(author);
			}
		}

		resultSet.close();
		preparedFunction.close();
		connection.close();
		
		return book;
	}

	// Initialize the book data table by author *report 1*
	public void fillComponentsBookReport1() throws SQLException, ClassNotFoundException{
		bookReportTableModel = new BookReportTableModel();
		table.setModel(bookReportTableModel);

		clearBooksId();
		getAllBooksByAuthor();
	}
	// Initialize the book data table by matter *report 2*
	public void fillComponentsBookReport2() throws SQLException, ClassNotFoundException{
		bookReportTableModel = new BookReportTableModel();
		table.setModel(bookReportTableModel);

		clearBooksId();
		getAllBooksByMatter();
	}

	// Initialize the book data table by book title *report 3*
	public void fillComponentsBookReport3() throws SQLException, ClassNotFoundException{
		getBookData();
	}
}