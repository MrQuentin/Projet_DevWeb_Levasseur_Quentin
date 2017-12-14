package dao.impl;

import dao.CategorieDao;
import entities.Categorie;
import entities.Item;
import entities.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategorieDaoImpl implements CategorieDao {

    @Override
    public Map<Integer, Categorie> listCategories() {

        Map<Integer, Categorie> categorieMap = new HashMap<>();

        String querry = "select * from categorie left JOIN type ON categorie.categorie_id = type.categorie_id left JOIN item ON type.type_id = item.type_id;";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
            try (Statement statement = connection.createStatement()){
                try (ResultSet result = statement.executeQuery(querry)){
                    while(result.next()) {
                        //regarde si la categorie existe déjà
                        if (!categorieMap.containsKey(result.getInt("categorie.categorie_id"))){
                            //ajout d'une nouvelle catégorie
                            if (!(result.getString("categorie.categorie_id") == null)) {
                                categorieMap.put(
                                        result.getInt("categorie.categorie_id"),
                                        new Categorie(
                                                result.getInt("categorie.categorie_id"),
                                                result.getString("categorie.name")
                                        )
                                );
                            }
                            //ajout d'un nouveau type dans la catégorie
                            if (!(result.getString("type.type_id") == null)) {
                                categorieMap.get(result.getInt("categorie.categorie_id"))
                                        .addType(
                                                result.getInt("type.type_id"),
                                                new Type(
                                                        result.getInt("type.type_id"),
                                                        result.getString("type.name")
                                                )
                                        );
                            }
                            //ajout d'un nouvel item dans le type
                            if (!(result.getString("item_id") == null)) {
                                categorieMap.get(result.getInt("categorie.categorie_id"))
                                        .getType(result.getInt("type.type_id"))
                                        .addItem(new Item(
                                                result.getInt("item_id"),
                                                result.getString("item.name"),
                                                result.getString("description")
                                        ));
                            }
                        } else {
                            if (!categorieMap.get(result.getInt("categorie.categorie_id")).containsKey(result.getInt("type.type_id"))){
                                //ajout d'un nouveau type dans la catégorie
                                if (!(result.getString("type.type_id") == null)) {
                                    categorieMap.get(result.getInt("categorie.categorie_id"))
                                            .addType(
                                                    result.getInt("type.type_id"),
                                                    new Type(
                                                            result.getInt("type.type_id"),
                                                            result.getString("type.name")
                                                    )
                                            );
                                }
                                //ajout d'un nouvel item dans le type
                                if (!(result.getString("item_id") == null)) {
                                    categorieMap.get(result.getInt("categorie.categorie_id"))
                                            .getType(result.getInt("type.type_id"))
                                            .addItem(new Item(
                                                    result.getInt("item_id"),
                                                    result.getString("item.name"),
                                                    result.getString("description")
                                            ));
                                }
                            } else {
                                //ajout d'un nouvel item dans le type
                                if (!(result.getString("item_id") == null)){
                                    categorieMap.get(result.getInt("categorie.categorie_id"))
                                            .getType(result.getInt("type.type_id"))
                                            .addItem(new Item(
                                                    result.getInt("item_id"),
                                                    result.getString("item.name"),
                                                    result.getString("description")
                                            ));
                                }
                            }
                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorieMap;
    }

    @Override
    public Categorie addCategorie(Categorie categorie) {
        String querry = "INSERT INTO categorie (name, illustration_name) VALUES (?,?)";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(querry)){
                statement.setString(1, categorie.getName());
                statement.setString(2, categorie.getIllustration_name());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }

    @Override
    public Type addType(Type type, Integer idCategorie) {
        String querry = "INSERT INTO type (categorie_id, name, illustration_name) VALUES (?,?,?);";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(querry)){
                statement.setInt(1, idCategorie);
                statement.setString(2, type.getName());
                statement.setString(3, type.getIllustration());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public Item addItem(Item item, Integer idType) {
        String querry = "INSERT INTO item (type_id, name, description, illustration_name) VALUES (?,?,?,?);";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(querry)){
                statement.setInt(1, idType);
                statement.setString(2, item.getName());
                statement.setString(3, item.getDescription());
                statement.setString(4, item.getIllutration());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void removeItem(Integer idItem) {
        String querry = "DELETE FROM item WHERE item_id=?";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
            try (PreparedStatement statement = connection.prepareStatement(querry)){
                statement.setInt(1, idItem);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeType(Integer idType) {
        String querry1 = "DELETE FROM item WHERE type_id=?";
        String querry2 = "DELETE FROM type WHERE type_id=?";
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
            try (PreparedStatement statement1 = connection.prepareStatement(querry1)){
                statement1.setInt(1, idType);
                statement1.executeUpdate();
            }
            try (PreparedStatement statement2 = connection.prepareStatement(querry2)){
                statement2.setInt(1, idType);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCategorie(Integer idCategorie) {
        String querry1 = "SELECT type_id FROM type WHERE categorie_id=?";//recover all type from a category
        String querry2 = "DELETE FROM categorie WHERE categorie_id=?";
        List<Integer> typesFromCategorie = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
            try (PreparedStatement statement1 = connection.prepareStatement(querry1)){
                statement1.setInt(1, idCategorie);
                try (ResultSet resultSet = statement1.executeQuery()){
                    while (resultSet.next()){
                        typesFromCategorie.add(resultSet.getInt("type_id"));
                    }
                }
            }
            for (Integer typeid : typesFromCategorie){
                this.removeType(typeid);
            }
            try (PreparedStatement statement2 = connection.prepareStatement(querry2)){
                statement2.setInt(1, idCategorie);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
