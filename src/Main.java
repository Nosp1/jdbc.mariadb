import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) {

        PrintWriter p = new PrintWriter(System.out);
        try {

           Connection db = null;
           db = DbTool.getINSTANCE().dbLoggIn(p);
            ResultSet rs = null;
            String query = "SELECT * FROM otra.students";
            Statement stmt = (db != null) ? db.prepareStatement(query) : null;
            rs = (stmt != null) ? stmt.executeQuery(query) : null;

            while(rs.next()) {
                String studentName = rs.getString("student_fname");
                System.out.println(studentName);

            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
