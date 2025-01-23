package in.ineuron.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class JDBCUtil {
	
	
	private JDBCUtil() {}
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getJDBCConnection() throws SQLException, IOException {
		
		HikariConfig config = new HikariConfig("/Users/abhinavraj/eclipse-workspace/JDBCCRUDAPP/application.properties");
		
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
		
		
	}

	private static Connection physicalConnection() throws FileNotFoundException, IOException, SQLException {
		FileInputStream fis =new FileInputStream("/Users/abhinavraj/eclipse-workspace/JDBCCRUDAPP/application.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		String url = prop.getProperty("jdbcUrl");
		String username = prop.getProperty("dataSource.user");
		String password = prop.getProperty("dataSource.password");
		
		return DriverManager.getConnection(url, username, password);
	}

}
