import dao.impl.CategorieDaoImpl;
import dao.impl.DataSourceProvider;
import entities.Categorie;
import entities.Item;
import entities.Type;
import entities.Version;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class CategorieDaoTestCase extends CategorieAbstractDaoTestCase {

    CategorieDaoImpl categorieDao = new CategorieDaoImpl();

    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO categorie (name) VALUES ('Categorie 1');");
        statement.executeUpdate("INSERT INTO categorie (name) VALUES ('Categorie 2');");
        statement.executeUpdate("INSERT INTO type (categorie_id, name) VALUES (2, 'Type 1');");
        statement.executeUpdate("INSERT INTO item (type_id, name, description) VALUES (1, 'Item 1', 'Description');");
        statement.executeUpdate("INSERT INTO item (type_id, name, description) VALUES (1, 'Item 2', 'Description');");
        statement.executeUpdate("INSERT INTO categorie (name) VALUES ('Categorie 3');");
        statement.executeUpdate("INSERT INTO type (categorie_id, name) VALUES (3, 'Type 2');");
        statement.executeUpdate("INSERT INTO item (type_id, name, description) VALUES (2, 'Item 3', 'Description');");
    }

    @Test
    public void shouldListCategories() throws Exception {
        // WHEN
        Map<Integer, Categorie> categories = categorieDao.listCategories();
        // THEN
        Assertions.assertThat(categories).isNotEmpty().hasSize(3);
        Assertions.assertThat(categories.get(1).getName()).isEqualTo("Categorie 1");

        Assertions.assertThat(categories.get(2).getName()).isEqualTo("Categorie 2");
        Assertions.assertThat(categories.get(2).getTypeMap().size()).isEqualTo(1);
        Assertions.assertThat(categories.get(2).getTypeMap().get(1).getName()).isEqualTo("Type 1");

        Assertions.assertThat(categories.get(3).getName()).isEqualTo("Categorie 3");
        Assertions.assertThat(categories.get(3).getTypeMap().size()).isEqualTo(1);
        Assertions.assertThat(categories.get(3).getTypeMap().get(2).getName()).isEqualTo("Type 2");
        Assertions.assertThat(categories.get(3).getTypeMap().get(2).getItemsList().size()).isEqualTo(1);
        Assertions.assertThat(categories.get(3).getTypeMap().get(2).getItemsList().get(0).getName()).isEqualTo("Item 3");
        Assertions.assertThat(categories.get(3).getTypeMap().get(2).getItemsList().get(0).getDescription()).isEqualTo("Description");
    }

    @Test
    public void shouldAddCategorie() throws Exception {
        //WHEN
        Categorie categorie = new Categorie(null, "My New Categorie");
        categorie.setIllustration_name("My Categorie illustration");
        categorieDao.addCategorie(categorie);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categorie WHERE name='My New Categorie'")){
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("categorie_id")).isNotNull();
            Assertions.assertThat(resultSet.getString("name")).isEqualTo("My New Categorie");
            Assertions.assertThat(resultSet.getString("illustration_name")).isEqualTo("My Categorie illustration");
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldAddType() throws Exception {
        //WHEN
        Type type = new Type(null, "My New Type");
        type.setIllustration("My Type illustration");
        categorieDao.addType(type, 1);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM type WHERE name='My New Type'")){
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("type_id")).isNotNull();
            Assertions.assertThat(resultSet.getInt("categorie_id")).isEqualTo(1);
            Assertions.assertThat(resultSet.getString("name")).isEqualTo("My New Type");
            Assertions.assertThat(resultSet.getString("illustration_name")).isEqualTo("My Type illustration");
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldAddItem() throws Exception {
        //WHEN
        Item item = new Item(null, "My new Item", "My Item Description");
        item.setIllutration("My Item Illustration");
        categorieDao.addItem(item, 1);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item WHERE name='My new Item'")){
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("item_id")).isNotNull();
            Assertions.assertThat(resultSet.getInt("type_id")).isEqualTo(1);
            Assertions.assertThat(resultSet.getString("name")).isEqualTo("My new Item");
            Assertions.assertThat(resultSet.getString("description")).isEqualTo("My Item Description");
            Assertions.assertThat(resultSet.getString("illustration_name")).isEqualTo("My Item Illustration");
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldRemoveItem() throws Exception {
        //WHEN
        categorieDao.removeItem(2);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item WHERE item_id=2")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldRemoveType() throws Exception {
        //WHEN
        categorieDao.removeType(1);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item WHERE type_id=1")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM type WHERE type_id=1")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldRemoveCategorie() throws Exception{
        //WHEN
        categorieDao.removeCategorie(3);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM item WHERE type_id=2")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM type WHERE categorie_id=3")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categorie WHERE categorie_id=3")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }

    }

}
