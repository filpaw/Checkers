
import java.sql.*;

class DataBase {
	public DataBase() {
	}

	public String readData(String url, String login, String parametr) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(url);
			System.out.println("connected");

			Statement statement = conn.createStatement();
			String queryString = "select " + parametr + " from [Checker].[dbo].[User] where Login=" + login;

			ResultSet rs = statement.executeQuery(queryString);
			String result = null;
			while (rs.next()) {
				result = rs.getString(1);
			}
			conn.close();

			return result;
		} catch (Exception e) {
			return "Error";
		}
	}

	public void writeUser(String url, String login, String password) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(url);
			System.out.println("connected");

			Statement statement = conn.createStatement();
			String queryString = "insert into [Checker].[dbo].[User](Login,Password,Beatens,Losts"
					+ ",Wins,Fails) values('" + login + "','" + password + "',0,0,0,0)";

			ResultSet rs = statement.executeQuery(queryString);

			conn.close();

		} catch (Exception e) {

		}
	}

};