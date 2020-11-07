package com.unip.tcc.scarblade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unip.tcc.scarblade.factory.HikariCP3ConnectionFactory;

public class UserDAO {
	
public static List<String> selectFaces() {
		
		
		try (Connection connection = HikariCP3ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<String> f = new ArrayList<String>();

			
			final String selectUser = "SELECT NOME FROM usuarios";
			try {
				pstmt = connection.prepareStatement(selectUser);
				rs = pstmt.executeQuery();
				
				f.add("");
				while(rs.next()) {
					f.add(rs.getString("NOME"));
				}
				
				return f;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}finally {
				if (connection != null) {
					connection.close();
					pstmt.close();
					rs.close();
				}
			}
	} catch (SQLException e1) {
		e1.printStackTrace();
		return null;
	}
}
	
}
