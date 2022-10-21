package services;


import dto.XUserDto;

public class ControlServices {
	// private static XUserDto sessionXUser;   	                                     
	private static XUserDto sessionXUser = new XUserDto("kid_bourbon", "Admin");    // USUARIO DE PRUEBA //

	public ControlServices() {
		super();
	}

	public XUserDto getSessionUser() {
		return sessionXUser;
	}

	public void setSessionUser(XUserDto sessionUser) {
		sessionXUser = sessionUser;
	}
	
	public boolean xUserIsAdmin(){
		boolean isAdmin = false;
		
		if(sessionXUser.getRol().equals("Admin"))
			isAdmin = true;
		
		return isAdmin;
	}
	
	public boolean xUserIsWorker(){
		boolean isWorker = false;
		
		if(sessionXUser.getRol().equals("Worker"))
			isWorker = true;
		
		return isWorker;
	}
	
	public boolean xUserIsUser(){
		boolean isUser = false;
		
		if(sessionXUser.getRol().equals("User"))
			isUser = true;
		
		return isUser;
	}
}
