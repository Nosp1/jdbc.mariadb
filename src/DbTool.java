import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DbTool {
    private static final DbTool INSTANCE = new DbTool();
    final static String DB_URL = "jdbc:mariadb://localhost:3306";
    final static String root = "root";
    final static String pw = readFileAsString("C://Users/tryme/Go/src/github.com/Nosp1/TA/JDBC/src/passord.txt");
    static Connection connection;

    /**
     * initiates the class as a singleton.
     * @return DbTool
     */
    public static DbTool getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Establishes a connection with a mariaDB or returns an existing one.
     * @param out for html printing in front-end e.g. (for errors or content)
     * @return connection to db
     * @throws SQLException if the connection fails
     */
    public Connection dbLoggIn(PrintWriter out) throws SQLException {
        Connection toReturn = null;
        try {
               toReturn =  (connection != null) ?  connection  : DriverManager.getConnection(DB_URL, root, pw);



        } catch (SQLException e) {
            e.printStackTrace();
            out.println("SQL Exception " + e);
         }
      return toReturn;
    }
    /**
     * Reads locally stored password file as a string to parse the password for better security
     * @return data with stored password
     * @param fileName the name of the locally stored file
     */
    private static String readFileAsString(String fileName)
    {
        //initializes data string to store the read characters
        String data = "";
        //reads the characters in the file and replaces escaped quotes with an empty character
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName))).replaceAll("\"", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //prints for debugging
        System.out.println(data);
        //returns the stored value of data
        return data;

    }
}

/*static {
        try {
            connection = DriverManager.getConnection(DB_URL,root,pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/