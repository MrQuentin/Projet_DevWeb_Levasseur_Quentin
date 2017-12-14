package manager;

import dao.CategorieDao;
import dao.impl.CategorieDaoImpl;
import entities.Categorie;
import entities.Item;
import entities.Type;

import java.util.List;
import java.util.Map;

public class CategorieLibrary {

    private static class CategorieLibraryHolder{ private final static CategorieLibrary instance = new CategorieLibrary();}
    public static CategorieLibrary getInstance() { return CategorieLibraryHolder.instance;}

    private CategorieDao CategorieDao = new CategorieDaoImpl();

    public Map<Integer, Categorie> listCategories() { return CategorieDao.listCategories(); }

    public Categorie addCategorie(Categorie categorie){ return CategorieDao.addCategorie(categorie);}
    public Type addType(Type type, Integer idCategorie){ return CategorieDao.addType(type, idCategorie);}
    public Item addItem(Item item, Integer idType){return CategorieDao.addItem(item, idType);}

    public void removeItem(Integer idItem){CategorieDao.removeItem(idItem);};
    public void removeType(Integer idType){CategorieDao.removeType(idType);};
    public void removeCategorie(Integer idCategorie){CategorieDao.removeCategorie(idCategorie);};

}
