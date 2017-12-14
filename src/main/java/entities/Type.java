package entities;

import java.util.ArrayList;
import java.util.List;

public class Type {

    private Integer id;
    private String name;
    private String illustration;
    private List<Item> itemsList = new ArrayList<>();

    public Type(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIllustration() { return illustration; }
    public void setIllustration(String illustration) { this.illustration = illustration; }

    public List<Item> getItemsList() { return itemsList; }
    public void setItemsList(List<Item> itemsList) { this.itemsList = itemsList; }

    public void addItem(Item item) { itemsList.add(item);}
}
