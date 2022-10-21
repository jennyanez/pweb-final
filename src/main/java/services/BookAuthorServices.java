package services;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import dto.BookDto;

public class BookAuthorServices {
	private int bookId;
	private List<Integer> authorsIds = new ArrayList<Integer>();
	private List<Integer> booksId = new ArrayList<Integer>();
	private BookDto book;
	private JTable table;
	
	public List<Integer> getBooksId() {
		return booksId;
	}

	public void setBooksId(List<Integer> booksId) {
		this.booksId = booksId;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public List<Integer> getAuthorsIds() {
		return authorsIds;
	}

	// Get the id of a book based on the selected row
		public int getBookIdByRow(int row){
			return booksId.get(row);
		}

	// Encapsulation
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	public void setAuthorsIds(List<Integer> authorsIds) {
		this.authorsIds = authorsIds;
	}

	// Inserts the id of the corresponding books and authors
	public void insertBookIdAuthorId() throws SQLException, ClassNotFoundException{
		String function = "{call insert_book_author(?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		CallableStatement preparedFunction = connection.prepareCall(function);


		for (Integer i : authorsIds) {
			preparedFunction.setInt(1, bookId);
			preparedFunction.setInt(2, i);
			preparedFunction.execute();
		}

		preparedFunction.close();
		connection.close();
	}
	
}
