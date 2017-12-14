import java.sql.Connection;
import java.sql.Statement;

import dao.impl.DataSourceProvider;
import org.junit.Before;


public abstract class CategorieAbstractDaoTestCase {

	@Before
	public void initDatabase() throws Exception {
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
			Statement statement = connection.createStatement()){

			statement.executeUpdate("DROP TABLE item");
			statement.executeUpdate("DROP TABLE type");
			statement.executeUpdate("DROP TABLE categorie");

			statement.executeUpdate("CREATE TABLE `categorie` (\n" +
					"  `categorie_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
					"  `name` varchar(30) NOT NULL,\n" +
					"  `illustration_name` varchar(30),\n" +
					"  PRIMARY KEY (`categorie_id`)\n" +
					");");
			statement.executeUpdate("CREATE TABLE `type` (\n" +
					"  `type_id` INT NOT NULL AUTO_INCREMENT,\n" +
					"  `categorie_id` INT NOT NULL,\n" +
					"  `name` VARCHAR(45) NOT NULL,\n" +
					"  `illustration_name` VARCHAR(45),\n" +
					"  PRIMARY KEY (`type_id`),\n" +
					"  INDEX `categorie_id_idx` (`categorie_id` ASC),\n" +
					"  CONSTRAINT `categorie_id`\n" +
					"    FOREIGN KEY (`categorie_id`)\n" +
					"    REFERENCES `categorie` (`categorie_id`)\n" +
					");");
			statement.executeUpdate("CREATE TABLE `item` (\n" +
					"  `item_id` INT NOT NULL AUTO_INCREMENT,\n" +
					"  `type_id` INT NOT NULL,\n" +
					"  `name` VARCHAR(45) NOT NULL,\n" +
					"  `description` VARCHAR(300) NOT NULL,\n" +
					"  `illustration_name` VARCHAR(45),\n" +
					"  PRIMARY KEY (`item_id`),\n" +
					"  INDEX `type_id_idx` (`type_id` ASC),\n" +
					"  CONSTRAINT `type_id`\n" +
					"    FOREIGN KEY (`type_id`)\n" +
					"    REFERENCES `type` (`type_id`)\n" +
					");");

			this.insertDataSet(statement);
		}
	}
	
	public abstract void insertDataSet(Statement statement) throws Exception;
}
