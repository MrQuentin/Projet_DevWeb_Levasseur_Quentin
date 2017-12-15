package dao;

import entities.SousVersion;
import entities.Version;

import java.util.List;
import java.util.Map;

public interface VersionDao {

    public Map<Integer, Version> listVersions();
    public Version addVersion(Version version);
    public SousVersion addSousVersion (SousVersion sousVersion, Integer versionID);
    public SousVersion getSousVersion(Integer idSousVersion);
    public void updateSousVersion (Integer sousVersionId, String newName, String newDescription);

    public void removeSousVersion (Integer idSousVersion);
    public void removeVersion (Integer idVersion);

}
