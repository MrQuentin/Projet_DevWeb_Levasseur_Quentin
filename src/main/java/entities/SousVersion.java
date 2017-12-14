package entities;

public class SousVersion {
    private Integer id;
    private String title;
    private String description;
    private String picture;

    public SousVersion(Integer id, String title, String description){
        super();
        this.id=id;
        this.title=title;
        this.description=description;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id=id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }
}
