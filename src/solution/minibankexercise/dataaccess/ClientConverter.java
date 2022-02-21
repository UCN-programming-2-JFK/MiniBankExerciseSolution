package solution.minibankexercise.dataaccess;

import java.sql.ResultSet;
import java.util.*;

import solution.minibankexercise.model.Client;

// CLIENT TABLE FORMAT
// cpr 		char(10)	NOT NULL,
// name		varchar(20)	NOT NULL,
// street		varchar(20)	NOT NULL,
// postalCode	varchar(4),
// status		varchar(2),

public class ClientConverter {
	private ClientConverter() {}
	
	public static Client SingleClientFromResultSet(ResultSet resultSet) throws Exception {
		return new Client(resultSet.getString("cpr"), resultSet.getString("name"), resultSet.getString("street"), resultSet.getString("postalCode"), resultSet.getString("status"));
	}
	
	public static List<Client> MultipleClientsFromResultSet(ResultSet resultSet) throws Exception {
		
		List<Client> Clients = new ArrayList<Client>();
		while(resultSet.next()) {
			Clients.add(SingleClientFromResultSet(resultSet));
		}
		return Clients;
	}
}
