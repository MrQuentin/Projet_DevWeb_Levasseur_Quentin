import java.sql.Connection;
import java.sql.Statement;

import dao.impl.DataSourceProvider;
import org.junit.Before;


public abstract class VersionAbstractDaoTestCase {

	@Before
	public void initDatabase() throws Exception {
		try(
				Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement()){
			statement.executeUpdate("DROP TABLE sousversion");
			statement.executeUpdate("DROP TABLE version");
			statement.executeUpdate("CREATE TABLE `version` (\n" +
					"  `version_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
					"  `name` varchar(30) NOT NULL,\n" +
					"  `illustration_name` varchar(30),\n" +
					"  PRIMARY KEY (`version_id`)\n" +
					")");
			statement.executeUpdate("\n" +
					"CREATE TABLE `sousversion` (\n" +
					"  `sousversion_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
					"  `name` varchar(30) NOT NULL,\n" +
					"  `version_id` int(11) NOT NULL,\n" +
					"  `description` varchar(300) NOT NULL,\n" +
					"  `illustration_name` varchar(30),\n" +
					"  PRIMARY KEY (`sousversion_id`),\n" +
					"  INDEX `version_id_idx` (`version_id` ASC ),\n" +
					"  CONSTRAINT `version_id_fk`\n" +
					"    FOREIGN KEY (`version_id`)\n" +
					"    REFERENCES `version` (`version_id`)\n" +
					");");

	        this.insertDataSet(statement);
		}
	}
	
	public abstract void insertDataSet(Statement statement) throws Exception;
}
