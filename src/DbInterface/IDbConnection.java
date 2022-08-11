package DbInterface;

import java.io.File;
import java.sql.ResultSet;

public interface IDbConnection {
    ResultSet executeQuery(String sqlStatement);
    int executeUpdate(String sqlStatement);
    void close();

    int addFoto(File photo, String sql);
}
