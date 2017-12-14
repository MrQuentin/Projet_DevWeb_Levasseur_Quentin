package dao;

import entities.Categorie;
import entities.Item;
import entities.Type;

import java.util.Map;

public interface CategorieDao {

    public Map<Integer, Categorie> listCategories();
    public Categorie addCategorie(Categorie categorie);
    public Type addType(Type type, Integer idCategorie);
    public Item addItem(Item item, Integer idType);

    public void removeItem(Integer idItem);
    public void removeType(Integer idType);
    public void removeCategorie(Integer idCategorie);

}
