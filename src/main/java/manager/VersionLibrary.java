package manager;

import dao.VersionDao;
import dao.impl.VersionDaoImpl;
import entities.SousVersion;
import entities.Version;

import java.util.Map;

public class VersionLibrary {

    private static class VersionLibraryHolder{ private final static VersionLibrary instance = new VersionLibrary();}
    public static VersionLibrary getInstance() { return VersionLibraryHolder.instance;}

    private VersionDao versionDao = new VersionDaoImpl();

    public Map<Integer, Version> listVersions() { return versionDao.listVersions(); }

    public Version addVersion(Version version) {return versionDao.addVersion(version);}
    public SousVersion addSousVersion(SousVersion sousversion, Integer idVersion) {return versionDao.addSousVersion(sousversion, idVersion);}
    public SousVersion getSousVersion(Integer idSousVersion) {return versionDao.getSousVersion(idSousVersion);}
    public void updateSousVersion (Integer sousVersionId, String newName, String newDescription) {versionDao.updateSousVersion(sousVersionId, newName, newDescription);}

    public void removeSousVersion (Integer idSousVersion){versionDao.removeSousVersion(idSousVersion);}
    public void removeVersion (Integer idVersion){versionDao.removeVersion(idVersion);}


}
