package testingwithhsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import testingwithhsqldb.entities.ProductEntity;

public class DAO {
	private final DataSource myDataSource;
	
	public DAO(DataSource dataSource) {
		myDataSource = dataSource;
	}

	/**
	 * Renvoie le nom d'un client à partir de son ID
	 * @param id la clé du client à chercher
	 * @return le nom du client (LastName) ou null si pas trouvé
	 * @throws SQLException 
	 */
	public String nameOfCustomer(int id) throws SQLException {
		String result = null;
		
		String sql = "SELECT LastName FROM Customer WHERE ID = ?";
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql)) {
			statement.setInt(1, id); // On fixe le 1° paramètre de la requête
			try ( ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// est-ce qu'il y a un résultat ? (pas besoin de "while", 
                                        // il y a au plus un enregistrement)
					// On récupère les champs de l'enregistrement courant
					result = resultSet.getString("LastName");
				}
			}
		}
		// dernière ligne : on renvoie le résultat
		return result;
	}
        
        /**
	 * Insert un nouveau produit dans la table Product
	 * @param id la clé du produit
         * @param name le nom du produit
         * @param price le prix du produit
	 * @throws SQLException 
	 */
	public void addProduct(int id, String name, double price) throws SQLException {
            String sql = "INSERT INTO Product (ID, Name, Price) VALUES (?,?,?)";
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql)) {
			statement.setInt(1, id); // On fixe le 1° paramètre de la requête
                        statement.setString(2, name); // On fixe le 2° paramètre de la requête
                        statement.setDouble(3, price); // On fixe le 3° paramètre de la requête
			statement.executeUpdate();
		}
        }
        
        /**
	 * Insert un nouveau produit dans la table Product
	 * @param id la clé du produit
	 * @throws SQLException 
	 */
	public ProductEntity getProduct(int id) throws SQLException {
            ProductEntity produit;
            String sql = "SELECT ID, Name, Price FROM Product WHERE ID = ?";
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql)) {
			statement.setInt(1, id); // On fixe le 1° paramètre de la requête
			statement.executeQuery();
                        try ( ResultSet resultSet = statement.executeQuery()) {
                            resultSet.next();
                            produit = new ProductEntity(resultSet.getInt("ID"), resultSet.getString("Name"), resultSet.getDouble("Price"));
                        }
		}
                return produit;
        }
	
}
