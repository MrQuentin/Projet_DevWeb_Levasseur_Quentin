package entities;

public class Item {

    private Integer id;
    private String name;
    private String description;
    private String illutration;

    public Item(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Item() { }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIllutration() { return illutration; }
    public void setIllutration(String illutration) { this.illutration = illutration; }

}
