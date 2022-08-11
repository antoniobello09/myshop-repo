package DbInterface;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbOperationExecutor {

    private final List<IDbOperation> dbOperations = new ArrayList<>();

    public ResultSet executeOperation(IDbOperation dbOperation) {
        dbOperations.add(dbOperation);
        return dbOperation.execute();
    }

}
