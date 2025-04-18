import java.math.BigDecimal;
import java.sql.*;
class BankBusinessService {
    AccountMasterDAO accountMasterDAO;
    TransactionDAO transactionDAO;
    AccountValidationAndDetails accountValidationAndDetails;

    public BankBusinessService(){ //Helper classes /  constructor
        accountMasterDAO=new AccountMasterDAO();
        transactionDAO=new TransactionDAO();
        accountValidationAndDetails=new AccountValidationAndDetails();
    }
    public double checkBalance(int accNo) {
        Connection connection=null;
        double balance=0;
        try {

            //getting connection
             connection = getConnection.getconnection();

             //getting balance from accountMasterDAO
            accountMaster account = accountMasterDAO.retrieve(accNo, connection);
            balance=account.getBalance().doubleValue();
            //closing the connection
             connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }
    public void deposit(int accNo , double amount){
        Connection connection=null;
        try {
            //getting connection
            connection=getConnection.getconnection();

            //setting autocommit- false
            connection.setAutoCommit(false);


            //get current Balance
            accountMaster a=accountMasterDAO.retrieve(accNo,connection);
            double currentBalance=a.getBalance().doubleValue();
            //calculate new Balance
            double newBalance=currentBalance+amount;

            //inserting data in Transaction table
            Date date= new Date(new java.util.Date().getTime());
            String type="Deposit";
            transaction transaction=new transaction();
            transaction.setAccno(accNo);
            transaction.setDate(date);
            transaction.setType(type);
            transaction.setAmount(BigDecimal.valueOf(amount));
            //transaction.setBalance(BigDecimal.valueOf(currentBalance));

            transaction.setBalance(new BigDecimal(newBalance));
            transactionDAO.insertTransaction(transaction,connection);

            accountMaster accountMaster=new accountMaster();
            accountMaster.setAccno(a.getAccno());
            accountMaster.setName(a.getName());
            accountMaster.setAddress(a.getAddress());
            accountMaster.setDate(a.getDate());
            accountMaster.setPan(a.getPan());
            accountMaster.setPhone(a.getPhone());
            accountMaster.setEmail(a.getEmail());
            //accountMaster.setBalance(a.getBalance());

            //updating accountMaster Table
            accountMaster.setBalance(BigDecimal.valueOf(newBalance));
            accountMasterDAO.update(accountMaster,connection);

            //commit connection
            connection.commit();

        } catch (Exception e) {
            try {

                //connection rollback-if any problem will occurs in transactionDAO and accountMasterDAO
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }finally {
            try {

                //checking connection
                if (connection != null)

                    //close the connection
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


   public void withdraw(int accNo , double amount){
       Connection connection=null;
       try {
           //getting connection
           connection=getConnection.getconnection();

           //setting autocommit- false
           connection.setAutoCommit(false);

           //get current Balance
           accountMaster a=accountMasterDAO.retrieve(accNo,connection);
           double currentBalance=a.getBalance().doubleValue();
           //calculate new Balance
           double newBalance=currentBalance-amount;

           //inserting data in Transaction table
           Date date= new Date(new java.util.Date().getTime());
           String type="Withdraw";
           transaction transaction=new transaction();
           transaction.setAccno(accNo);
           transaction.setDate(date);
           transaction.setType(type);
           transaction.setAmount(BigDecimal.valueOf(amount));
           //transaction.setBalance(BigDecimal.valueOf(currentBalance));

           transaction.setBalance(new BigDecimal(newBalance));
           transactionDAO.insertTransaction(transaction,connection);

           accountMaster accountMaster=new accountMaster();
           accountMaster.setAccno(a.getAccno());
           accountMaster.setName(a.getName());
           accountMaster.setAddress(a.getAddress());
           accountMaster.setDate(a.getDate());
           accountMaster.setPan(a.getPan());
           accountMaster.setPhone(a.getPhone());
           accountMaster.setEmail(a.getEmail());
           //accountMaster.setBalance(a.getBalance());

           //updating accountMaster Table
           accountMaster.setBalance(BigDecimal.valueOf(newBalance));
           accountMasterDAO.update(accountMaster,connection);

           //commit connection
           connection.commit();

       } catch (Exception e) {
           try {

               //connection rollback-if any problem will occurs in transactionDAO and accountMasterDAO
               connection.rollback();
           } catch (SQLException ex) {
               throw new RuntimeException(ex);
           }
           e.printStackTrace();
       }finally {
           try {

               //checking connection
               if (connection != null)

                   //close the connection
                   connection.close();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
    }

    public void generateStatement(int accNo) {
        try {
            Connection connection=getConnection.getconnection();

            //get MetaData amd display table column name
            DatabaseMetaData databaseMetaData=connection.getMetaData();
            ResultSet result = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});

            if(accountNumValidation(accNo)) {
                while (result.next()) {
                    String tableName = result.getString("TABLE_NAME");

                    if (tableName.equals("transaction")) {
                        ResultSet resultSet = databaseMetaData.getColumns(null, null, tableName, null);
                        while (resultSet.next()) {
                            String col_Name = resultSet.getString("COLUMN_NAME");
                            System.out.print(col_Name + "     ");
                        }
                        System.out.println();
                    }
                }

                //getting each records for that particular account Number
                ResultSet rs = accountValidationAndDetails.getAccountDetails(accNo, connection);

                //Displaying records
                while (rs.next()) {
                    System.out.println(" " + rs.getInt(1) + "      " + rs.getInt(2) + "   " + rs.getDate(3) + " " + rs.getString(4) + "   " + rs.getBigDecimal(5) + "   " + rs.getBigDecimal(6));
                }
            }

            //close connection
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean accountNumValidation(int accNo){
        try {
            Connection connection= getConnection.getconnection();

            //getting Account number and store Resultset
            ResultSet rs=accountValidationAndDetails.getAccountDetails(accNo,connection);
            while(rs.next())
            {
                if (rs.getInt("accno") == accNo) return true;
            }
            //close connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
}