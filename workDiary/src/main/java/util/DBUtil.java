package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {

	private Connection connection;
	
	private static final DBUtil INSTANCE = new DBUtil();
	
	private DBUtil() {
		
		try {
			
			Context context = new InitialContext();
			DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			connection = source.getConnection();
		}catch(NamingException | SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
	}
	
	public static DBUtil getInstance() {
		
		return INSTANCE;
	}
	
	public Connection getConnection() {
		
		return connection;
	}
}
