package services;

import java.sql.SQLException;

import utils.Connection;

public class ServicesLocator {
	private static BookServices bookServices = null;
	private static AuthorServices authorServices = null;
	private static BookAuthorServices bookAuthorServices = null;
	private static CopyServices copyServices = null;
	private static MatterServices matterServices = null;
	private static UserServices userServices = null;
	private static DefaulterUserServices defaulterUserServices = null;
	private static SanctionedUserServices sanctionedUserServices = null;
	private static XRoleServices xRoleServices = null;
	private static XUserServices xUserServices = null;
	private static ControlServices controlServices = null;
	private static LoanServices loanServices = null;
	
	public static BookServices getBookServices() {
		if (bookServices == null) {
			bookServices = new BookServices();
		}
		return bookServices;
	}
	
	// Get an instance of a service
	public static AuthorServices getAuthorServices() {
		if (authorServices == null) {
			authorServices = new AuthorServices();
		}
		return authorServices;
	}
	
	public static BookAuthorServices getBookAuthorServices() {
		if (bookAuthorServices == null) {
			bookAuthorServices = new BookAuthorServices();
		}
		return bookAuthorServices;
	}
	
	public static CopyServices getCopyServices() {
		if (copyServices == null) {
			copyServices = new CopyServices();
		}
		return copyServices;
	}
	
	public static MatterServices getMatterServices() {
		if (matterServices == null) {
			matterServices = new MatterServices();
		}
		return matterServices;
	}
	
	public static UserServices getUserServices() {
		if (userServices == null) {
			userServices = new UserServices();
		}
		return userServices;
	}
	
	public static DefaulterUserServices getDefaulterUserServices() {
		if (defaulterUserServices == null) {
			defaulterUserServices = new DefaulterUserServices();
		}
		return defaulterUserServices;
	}
	
	public static SanctionedUserServices getSanctionedUserServices() {
		if (sanctionedUserServices == null) {
			sanctionedUserServices = new SanctionedUserServices();
		}
		return sanctionedUserServices;
	}
	
	public static XRoleServices getxRoleServices() {
		if (xRoleServices == null) {
			xRoleServices = new XRoleServices();
		}
		return xRoleServices;
	}
	
	public static XUserServices getxUserServices() {
		if (xUserServices == null) {
			xUserServices = new XUserServices();
		}
		return xUserServices;
	}
	
	public static ControlServices getControlServices() {
		if(controlServices == null)
			controlServices = new ControlServices();
		return controlServices;
	}
	
	public static LoanServices getLoanServices() {
		if (loanServices == null) {
			loanServices = new LoanServices();
		}
		return loanServices;
	}
	
	// Establishes connection with the database
	public static java.sql.Connection getConnection(){
		Connection connection = null;
		
		try {
			connection = new Connection("localhost", "DB_Library", "postgres", "toor");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection.getConnection();
	}
}
