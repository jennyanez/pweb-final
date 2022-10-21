package services;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import dto.XRoleDto;


public class XRoleServices {

	// Get all roles
		public LinkedList<XRoleDto> getXRoles()throws SQLException,ClassNotFoundException{
			LinkedList<XRoleDto> list = new LinkedList<XRoleDto>();

			Statement consult =  ServicesLocator.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sqlSentenc = "SELECT rol, description FROM  xrol";
			ResultSet result = consult.executeQuery(sqlSentenc);

			while (result.next()) {
				XRoleDto role = new XRoleDto();
				
				role.setRol(result.getString(1));
				role.setDescription(result.getString(2));
				list.add(role);
			}

			return list;
		}

		// Get a role having its description
		public int getXRole(String roleDesc)throws SQLException,ClassNotFoundException{
			int role = -1;

			String sqlSentenc = "SELECT * " + 
								"FROM xrol " + 
								"WHERE xrol.description = ? ";

			PreparedStatement preparedStatement = ServicesLocator.getConnection().prepareStatement(sqlSentenc);
			preparedStatement.setString(1, roleDesc);
			preparedStatement.execute();
			
			ResultSet result = preparedStatement.getResultSet();

			result.next();
			role = result.getInt(1);

			return role;
		}
}