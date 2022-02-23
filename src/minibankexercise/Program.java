package minibankexercise;

import java.sql.*;
import java.util.List;

import minibankexercise.dataaccess.*;
import minibankexercise.model.*;

public class Program {

	public static void main(String[] args) {
		try (Connection connection = Database.getConnection()) {

			// 1. Write a method that finds info (cpr, name, status, accNo, balance) for all
			// a given client’s accounts. Client name is to be input.
			String clientName = "Finn";
			String sqlStatement = "SELECT * FROM Account INNER JOIN Client on Account.ClientNo=Client.Cpr WHERE Client.Name=?";
			PreparedStatement statement = connection.prepareStatement(sqlStatement);
			statement.setString(1, clientName);
			ResultSet resultSet = statement.executeQuery();
			List<Account> accounts = AccountConverter.MultipleAccountsFromResultSet(resultSet);
			for (Account account : accounts) {
				System.out.println(account);
			}

			// 2. Write a method that lists all clients (cpr, name, status, and address) of
			// a given status in given city. (status and city are to be input).
			String clientStatus = "A";
			String clientCity = "Aalborg";
			sqlStatement = "SELECT * FROM Client INNER JOIN PostalCity on Client.PostalCode=PostalCity.postalCode WHERE Client.Status=? AND PostalCity.city=?";
			statement = connection.prepareStatement(sqlStatement);
			statement.setString(1, clientStatus);
			statement.setString(2, clientCity);
			resultSet = statement.executeQuery();
			List<Client> clients = ClientConverter.MultipleClientsFromResultSet(resultSet);
			for (Client client : clients) {
				System.out.println(client);
			}

			// 3. Write a method that can create a new city in the database.
			String cityName = "Vejle";
			String cityZipcode = "7500";
			sqlStatement = "INSERT INTO PostalCity (city, postalcode) VALUES (?, ?)";
			statement = connection.prepareStatement(sqlStatement);
			statement.setString(1, cityName);
			statement.setString(2, cityZipcode);
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected < 1) {
				throw new Exception("City " + cityName + " with zipcode " + cityZipcode + " not created!");
			}

			// 4. Write a method that lists all clients (cpr, name, and address) in a given
			// area(startCodeIncl and endCodeExcl code are to be input).

			String postalCodeBegin = "8000";
			String postalCodeEnd = "9900";
			sqlStatement = "SELECT * FROM Client WHERE Client.PostalCode BETWEEN ? AND ? ORDER BY PostalCode";
			statement = connection.prepareStatement(sqlStatement);
			statement.setString(1, postalCodeBegin);
			statement.setString(2, postalCodeEnd);
			resultSet = statement.executeQuery();
			clients = ClientConverter.MultipleClientsFromResultSet(resultSet);
			for (Client client : clients) {
				System.out.println(client);
			}
			
			// 5. Write a method that creates a new account.
			
			Account newAccount = new Account(42, 4200, 2, "1234567890");
			createAccount(newAccount);
						
			// 6. Write a method that checks if a client with a given cpr number is in the
			// database.
			// Cpr should be a parameter to the method and the method should return a
			// boolean.

			try {
				String clientNumber = "1234567890";
				boolean clientExists = clientExists(clientNumber);
				System.out.println("Client " + clientNumber + " exists: " + clientExists );
			} catch (Exception e) {
				System.out.println("Exception finding out if client exists. Error is: " + e.getMessage());
			}
			
			
			// 7. Create a new version of the method that creates an account (exercise 5).
			// This method should check if the client exists (exercise 6).
			// If not, the client should be created before the account is created
			// (avoiding foreign key violation)

			newAccount = new Account(30, 3000, 3, "1111111111");
			createAccountWithPlaceholderClientIfNecessary(newAccount);
			
		} catch (Exception e) {
			System.out.println("Exception occurred. Exception is: '" + e.getMessage() + "'");
		}
	}

	private static void createAccount(Account accountToCreate) throws Exception {
		 Connection connection = Database.getConnection();
		String  sqlStatement = "INSERT INTO Account (accno, balance, inrate, clientno) VALUES (?, ?, ?, ?)";
		 PreparedStatement statement = connection.prepareStatement(sqlStatement);
		statement.setInt(1, accountToCreate.getAccNo());
		statement.setFloat(2, accountToCreate.getBalance());
		statement.setFloat(3, accountToCreate.getInRate());
		statement.setString(4, accountToCreate.getClientNo());
		int rowsAffected = statement.executeUpdate();

		if (rowsAffected < 1) {
			throw new Exception("Account not created: " + accountToCreate);
		}
	}
	
	private static Boolean clientExists(String clientNumber) throws Exception {
			String sqlStatement = "SELECT COUNT(*) clients FROM Client WHERE cpr=?";
			PreparedStatement statement = Database.getConnection().prepareStatement(sqlStatement);
			statement.setString(1, clientNumber);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt("clients") > 0;
			}
				return false;
	}
	
	private static void createAccountWithPlaceholderClientIfNecessary(Account accountToCreate) throws Exception {
		if(!clientExists(accountToCreate.getClientNo())) {
			//public Client(String cpr, String name, String street, String postalCode, String status)
			Client placeholderClient = new Client(accountToCreate.getClientNo(), "dummy name", "dummy stret", "9000", "D"  );
			createClient(placeholderClient);
			
			createAccount(accountToCreate);
		}
		
		Connection connection = Database.getConnection();
		String  sqlStatement = "INSERT INTO Account (accno, balance, inrate, clientno) VALUES (?, ?, ?, ?)";
		 PreparedStatement statement = connection.prepareStatement(sqlStatement);
		statement.setInt(1, accountToCreate.getAccNo());
		statement.setFloat(2, accountToCreate.getBalance());
		statement.setFloat(3, accountToCreate.getInRate());
		statement.setString(4, accountToCreate.getClientNo());
		int rowsAffected = statement.executeUpdate();
		if (rowsAffected < 1) {
			throw new Exception("Account not created: " + accountToCreate);
		}
	}
	
	private static void createClient(Client clientToCreate) throws Exception {
		Connection connection = Database.getConnection();
		String  sqlStatement = "INSERT INTO Client (cpr, name, street, postalCode, status) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sqlStatement);
		statement.setString(1, 	clientToCreate.getCpr());
		statement.setString(2, 	clientToCreate.getName());
		statement.setString(3, 	clientToCreate.getStreet());
		statement.setString(4, 	clientToCreate.getPostalCode());
		statement.setString(5, 	clientToCreate.getStatus());
		int rowsAffected = statement.executeUpdate();

		if (rowsAffected < 1) {
			throw new Exception("Client not created: " + clientToCreate);
		}
	}
	
	
	
}