package entities;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Categorie {

    private Integer id;
    private String name;
    private String illustration_name;
    private Map<Integer, Type> typeMap = new HashMap<>();

    public Categorie(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Categorie() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIllustration_name() { return illustration_name; }
    public void setIllustration_name(String illustration_name) { this.illustration_name = illustration_name; }

    public Map<Integer, Type> getTypeMap() { return typeMap; }
    public void setTypeMap(Map<Integer, Type> typeMap) { this.typeMap = typeMap; }

    public boolean containsKey(Object o) { return typeMap.containsKey(o);}
    public void addType(Integer id, Type type){ typeMap.put(id, type); }
    public Type getType(Object id){return typeMap.get(id);}
}
