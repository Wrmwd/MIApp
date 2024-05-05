package net.codejava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL{

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:sqlite:/D:\\PROGS\\SQLite\\sqlite-tools-win-x64-3450300\\supplies.db";

		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "SELECT rowid, * FROM supplies";
			
			Statement statement = connection.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				Integer id = result.getInt("rowid");
				String name = result.getString("name");
				String brand = result.getString("brand");
				String description = result.getString("description");
				String price = result.getString("price");
				String date = result.getString("date");
				String country = result.getString("country");
				String manufacturer = result.getString("manufacturer");
				String seller = result.getString("seller");
				String purpose = result.getString("purpose");
				
				System.out.println(id + "|" + name + "|" + brand + "|" + description + "|" + price + "|" + date + "|" + country + "|" + manufacturer + "|" + seller + "|" + purpose);
			}
			
		} catch (SQLException e) {
			System.out.println("Error connecting to SQLite database");
			e.printStackTrace();
		}
	}

}
