import dao.impl.DataSourceProvider;
import dao.impl.VersionDaoImpl;
import entities.SousVersion;
import entities.Version;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class VersionDaoTestCase extends VersionAbstractDaoTestCase{

    private VersionDaoImpl versionDao = new VersionDaoImpl();

    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO version (name) VALUES ('Version 1');");
        statement.executeUpdate("INSERT INTO version (name) VALUES ('Version 2');");
        statement.executeUpdate("INSERT INTO sousversion (name, version_id, description) VALUES ('SV1', 2, 'SV1');");
    }

    @Test
    public void shouldListCities() throws Exception {
        // WHEN
        Map<Integer, Version> versions = versionDao.listVersions();
        // THEN
        Assertions.assertThat(versions).isNotEmpty().hasSize(2);
        Assertions.assertThat(versions.get(1).getName()).isEqualTo("Version 1");
        Assertions.assertThat(versions.get(2).getName()).isEqualTo("Version 2");
        Assertions.assertThat(versions.get(2).getSousVersions().get(0).getTitle()).isEqualTo("SV1");
        Assertions.assertThat(versions.get(2).getSousVersions().get(0).getDescription()).isEqualTo("SV1");
    }

    @Test
    public void shouldAddVersion() throws Exception {
        //WHEN
        Version newVersion = (new Version(null, "New Version" ));
        newVersion.setIllustration_name("illustration");
        versionDao.addVersion(newVersion);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM version WHERE name='New Version'")){
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("version_id")).isNotNull();
            Assertions.assertThat(resultSet.getString("name")).isEqualTo("New Version");
            Assertions.assertThat(resultSet.getString("illustration_name")).isEqualTo("illustration");
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldAddSousVersion() throws Exception {
        //WHEN
        SousVersion sousVersion = new SousVersion(null, "My New SousVersion", "My Description");
        versionDao.addSousVersion(sousVersion, 1);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sousversion WHERE name='My New SousVersion'")) {
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("sousversion_id")).isNotNull();
            Assertions.assertThat(resultSet.getString("name")).isEqualTo("My New SousVersion");
            Assertions.assertThat(resultSet.getInt("version_id")).isEqualTo(1);
            Assertions.assertThat(resultSet.getString("description")).isEqualTo("My Description");
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldRemoveSousVersion() throws Exception {
        //WHEN
        versionDao.removeSousVersion(1);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sousversion WHERE sousversion_id=1")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldRemoveVersion() throws Exception {
        //WHEN
        versionDao.removeVersion(2);
        //THEN
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sousversion WHERE version_id=2")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }
        try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM version WHERE version_id=2")) {
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }
}
