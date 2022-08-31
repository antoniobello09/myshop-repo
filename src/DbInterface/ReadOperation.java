package DbInterface;

import java.sql.Connection;
import java.sql.ResultSet;

public class ReadOperation implements IDbOperation {

    private IDbConnection conn = DbConnection.getInstance();
    private String sql;

    public ReadOperation(String sql) {
        this.sql = sql;
    }

    @Override
    public ResultSet execute() {
        //esegui query
        return conn.executeQuery(sql);
    }
}
