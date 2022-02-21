package solution.minibankexercise.dataaccess;

import java.sql.ResultSet;
import java.util.*;

import solution.minibankexercise.model.Account;

// ACCOUNT TABLE FORMAT
// accNo		int			NOT NULL,      
// balance		float		NOT NULL,  
// inRate		float		NOT NULL,   public class AccountConverter {
// clientNo	char(10)		NOT NULL,	private AccountConverter() {}
public class AccountConverter {

	private AccountConverter() {}

	public static Account SingleAccountFromResultSet(ResultSet resultSet) throws Exception {
		return new Account(resultSet.getInt("accNo"), resultSet.getFloat("balance"), resultSet.getFloat("inRate"),
				resultSet.getString("clientNo"));
	}

	public static List<Account> MultipleAccountsFromResultSet(ResultSet resultSet) throws Exception {

		List<Account> accounts = new ArrayList<Account>();
		while (resultSet.next()) {
			accounts.add(SingleAccountFromResultSet(resultSet));
		}
		return accounts;
	}
}