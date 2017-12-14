package dao.impl;

import dao.VersionDao;
import entities.SousVersion;
import entities.Version;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class VersionDaoImpl implements VersionDao {
    @Override
    public Map<Integer, Version> listVersions() {

        Map<Integer, Version> versionsMap = new HashMap<>();

        String querry = "SELECT * from version left JOIN sousversion ON  version.version_id = sousversion.version_id ;";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()){
                try (ResultSet result = statement.executeQuery(querry)) {
                    while (result.next()) {
                        if(!versionsMap.containsKey(result.getInt("version.version_id"))){
                            versionsMap.put(
                                    result.getInt("version.version_id"),
                                    new Version(
                                        result.getInt("version.version_id"),
                                        result.getString("version.name")
                                    )
                            );
                            if(!(result.getString("sousversion_id") == null)){
                                versionsMap.get(result.getInt("version.version_id"))
                                        .addSousVersion(new SousVersion(
                                                result.getInt("sousversion_id"),
                                                result.getString("sousversion.name"),
                                                result.getString("description")
                                        ));
                            }
                        } else {
                            if(!(result.getString("sousversion_id") == null)){
                                versionsMap.get(result.getInt("version.version_id"))
                                        .addSousVersion(new SousVersion(
                                                result.getInt("sousversion_id"),
                                                result.getString("sousversion.name"),
                                                result.getString("description")
                                        ));
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return versionsMap;
    }

    @Override
    public Version addVersion(Version version){
        String querry = "INSERT INTO version (name, illustration_name) VALUES (?,?);";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(querry)){
                statement.setString(1, version.getName());
                statement.setString(2, version.getIllustration_name());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (version);
    }

    @Override
    public SousVersion addSousVersion(SousVersion sousVersion, Integer versionID) {
        String querry = "INSERT INTO sousversion (name, version_id, description) VALUES (?,?,?);";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(querry)){
                statement.setString(1, sousVersion.getTitle());
                statement.setInt(2, versionID);
                statement.setString(3, sousVersion.getDescription());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sousVersion;
    }

    @Override
    public void removeSousVersion(Integer idSousVersion){
        String querry = "delete from sousversion WHERE  sousversion_id = ?";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(querry)){
                statement.setInt(1, idSousVersion);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeVersion (Integer idVersion) {
        String querry1 = "delete from sousversion where version_id=?";
        String querry2 = "delete from version where version_id=?";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(querry1)){
                statement.setInt(1, idVersion);
                statement.executeUpdate();
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(querry2)){
                preparedStatement.setInt(1,idVersion);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
