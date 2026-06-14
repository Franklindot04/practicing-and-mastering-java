import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementShapeDemo {
    public static void main(String[] args) {
        System.out.println(sqlForFindCustomerByEmail());
    }

    static String sqlForFindCustomerByEmail() {
        return "select id, name, email from customers where email = ?";
    }

    static PreparedStatement prepareFindByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sqlForFindCustomerByEmail());
        statement.setString(1, email);
        return statement;
    }
}
