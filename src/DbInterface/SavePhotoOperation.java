package DbInterface;

import java.io.File;
import java.sql.ResultSet;

public class SavePhotoOperation implements IDbOperation {

    private static DbConnection conn = DbConnection.getInstance();

    String sql;
    File photo;

    public SavePhotoOperation(String sql, File photo) {
        this.sql = sql;
        this.photo = photo;
    }

    @Override
    public ResultSet execute() {
        int i = conn.addFoto(photo, sql);
        return null;
    }
}
