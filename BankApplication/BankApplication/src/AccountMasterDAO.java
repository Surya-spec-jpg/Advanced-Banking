import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMasterDAO {
    public accountMaster retrieve(int accNo, Connection connection){
        double balance=0;
        final String getBalanceQuery = "select * from Bank.accountmaster where accno=?";
        //Getting connection
        try {
            accountMaster accountMaster = new accountMaster();
            //Getting balance
            PreparedStatement statement = connection.prepareStatement(getBalanceQuery);
            statement.setInt(1,accNo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                accountMaster.setAccno(resultSet.getInt(1));
                accountMaster.setName(resultSet.getString(2));
                accountMaster.setAddress(resultSet.getString(3));
                accountMaster.setDate(resultSet.getDate(4));
                accountMaster.setPan(resultSet.getString(5));
                accountMaster.setPhone(resultSet.getString(6));
                accountMaster.setEmail(resultSet.getString(7));
                accountMaster.setBalance(resultSet.getBigDecimal(8));
            }

            return accountMaster;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(accountMaster accountMaster, Connection connection){
        final String updateBalanceQuery = "update Bank.accountmaster set name=?,address=?,date=?,pan=?,phone=?,email=?,balance=? where accno=?";

        try {
            //Updating balance
            PreparedStatement statement = connection.prepareStatement(updateBalanceQuery);
            statement.setString(1,accountMaster.getName());
            statement.setString(2,accountMaster.getAddress());
            statement.setDate(3,accountMaster.getDate());
            statement.setString(4,accountMaster.getPan());
            statement.setString(5,accountMaster.getPhone());
            statement.setString(6,accountMaster.getEmail());
            statement.setBigDecimal(7,accountMaster.getBalance());
            statement.setInt(8,accountMaster.getAccno());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

