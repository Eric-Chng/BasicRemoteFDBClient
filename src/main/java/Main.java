import com.apple.foundationdb.Database;
import com.apple.foundationdb.FDB;
import com.apple.foundationdb.Transaction;
public class Main {
    public static final String FDBCLUSTER_FILEPATH = "conf/fdb.cluster";
    public static void main(String[] args) {
        FDB fdb = FDB.selectAPIVersion(620);

        try (Database db = fdb.open(FDBCLUSTER_FILEPATH)) {
            //Set hello world
            db.run(tr -> {
                tr.set("hello".getBytes(), "world".getBytes());
                return null; //return required
            });

            //get hello world
            String value = db.run(tr -> {
                byte[] result = tr.get("hello".getBytes()).join();
                return result != null ? new String(result) : null;
            });

            System.out.println("Value for 'hello': " + value);
        }
    }
}