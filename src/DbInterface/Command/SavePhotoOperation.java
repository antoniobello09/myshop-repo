package DbInterface.Command;

import DbInterface.DbConnection;

import java.sql.Blob;

public class SavePhotoOperation implements IDbOperation {

    private DbConnection conn = DbConnection.getInstance();
    private String sql;
    private Blob immagine;

    public SavePhotoOperation(Blob immagine, String sql) {
        this.immagine = immagine;
        this.sql = sql;
    }

    @Override
    public DbOperationResult execute() {

        DbOperationResult result = new DbOperationResult();
        result.setRowsAffected(conn.savePhoto(immagine, sql));

        return result;
    }
}
