package entities;

import java.util.ArrayList;
import java.util.List;

public class Version {

    private Integer id;
    private String name;
    private List<SousVersion> sousVersions = new ArrayList<>();
    private String illustration_name;

    public Version(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getIllustration_name() {
        return illustration_name;
    }

    public void setIllustration_name(String illustration_name) {
        this.illustration_name = illustration_name;
    }

    public Version() { }

    public void addSousVersion(SousVersion sv) { sousVersions.add(sv); }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<SousVersion> getSousVersions() { return sousVersions; }
    public void setSousVersions(List<SousVersion> sousVersions) { this.sousVersions = sousVersions; }

    public void resetSousVersion() {
        sousVersions = new ArrayList<>();
    }
}
