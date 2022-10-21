package services;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import dto.BookDto;
import dto.UserDto;
import utils.LoanTableModel;

public class LoanServices {
	private JTable table;
	private LoanTableModel loanTableModel;
	
	private int loanId;	
	private int copyId;
	private int bookId;
	private int userId;
	private Date date;
	
	
	private List<Integer> loansId = new ArrayList<Integer>();
	
	private CopyServices copyServices = ServicesLocator.getCopyServices();
	private BookServices bookServices = ServicesLocator.getBookServices();
	private UserServices userServices = ServicesLocator.getUserServices();
	
	//Encapsulation
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public LoanTableModel getLoanTableModel() {
		return loanTableModel;
	}
	public void setLoanTableModel(LoanTableModel loanTableModel) {
		this.loanTableModel = loanTableModel;
	}
	
	public void setLoansId(List<Integer> loansId) {
		this.loansId = loansId;
	}
	
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public int getCopyId() {
		return copyId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	//Initialize the loan table
	public void fillComponents() throws SQLException, ClassNotFoundException {
		loanTableModel = new LoanTableModel();
		table.setModel(loanTableModel);
		
		clearLoansId();
		getAllLoans();
	}
	
	//Clears the list of loans ids
	private void clearLoansId() {
		loansId.clear();
	}
	
	//Load loan table
	private void getAllLoans() throws SQLException, ClassNotFoundException {
		String function = "{?= call all_loans()}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
		
		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
		preparedFunction.execute();
		ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
		
		while(resultSet.next()) {
			int copyId = resultSet.getInt(1);
			int bookId = resultSet.getInt(2);
			int userId = resultSet.getInt(3);
			String returnDate = resultSet.getDate(4).toString();
			loansId.add(resultSet.getInt(5));
			String loanDate = resultSet.getDate(6).toString();
			
			int copyNumber = copyServices.findCopyNumberById(copyId);
			BookDto book = bookServices.findBookById(bookId);
			UserDto user = userServices.findUserById(userId);
			
			String bookTitle = book.getTitle();
			String userDNI = user.getDNI();
			String userArea = user.getArea();
			
			((LoanTableModel)table.getModel()).InsertLoan(bookTitle, copyNumber, userDNI, userArea, loanDate, returnDate);
		}
		resultSet.close();
		preparedFunction.close();
		connection.close();
	}
	
	// Gets the id of the loan based on the selected row
	public int getLoanIdByRow(int row) {
		return loansId.get(row);
	}
	
	// Delete selected loan in the table
	public void deleteSelectedLoan() throws SQLException {
		String function = "{call delete_loan(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, loanId);
		preparedFunction.execute();
		
		preparedFunction.close();
		connection.close();
	}
	
	// Inserts a loan
	public void insertLoan() throws SQLException {
		String function = "{call insert_loan(?, ?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, copyId);
		preparedFunction.setInt(2, bookId);
		preparedFunction.setInt(3, userId);
		preparedFunction.setDate(4, date);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}
	
	// Updates a loan
	public void updateLoan() throws SQLException {
		String function = "{call update_loan(?, ?, ?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.setInt(1, userId);
		preparedFunction.setDate(2, date);
		preparedFunction.setInt(3, loanId);
		preparedFunction.execute();

		preparedFunction.close();
		connection.close();
	}
	
	// Gets the copy id by the loan id
	public int getCopyIdByLoanId(int loanId) throws SQLException {
		int copyId = -1;
		
		String function = "{?= call get_copy_id_by_loan_id(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);

		CallableStatement preparedFunction = connection.prepareCall(function);
		preparedFunction.registerOutParameter(1, java.sql.Types.INTEGER);
		preparedFunction.setInt(2, loanId);
		preparedFunction.execute();	
		
		copyId = preparedFunction.getInt(1);
		
		preparedFunction.close();
		connection.close();
		
		return copyId;
	}

}
