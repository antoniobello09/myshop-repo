package DbInterface;

import java.io.File;
import java.sql.Blob;
import java.sql.ResultSet;

public interface IDbConnection {
    ResultSet executeQuery(String sqlStatement);
    int executeUpdate(String sqlStatement);
    void close();

    int savePhoto(Blob immagine, String sql);
}
