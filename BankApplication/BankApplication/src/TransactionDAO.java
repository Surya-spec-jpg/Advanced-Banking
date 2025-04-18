import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {
    public void insertTransaction(transaction transaction, Connection connection){

        try {

            //Inserting Transaction
            final String insertTransactionQuery = "insert into Bank.transaction (accno,date,type,amount,balance) values (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertTransactionQuery);
            statement.setInt(1,transaction.getAccno());
            statement.setDate(2, transaction.getDate());
            statement.setString(3,transaction.getType());
            statement.setBigDecimal(4,transaction.getAmount());
            statement.setBigDecimal(5,transaction.getBalance());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
