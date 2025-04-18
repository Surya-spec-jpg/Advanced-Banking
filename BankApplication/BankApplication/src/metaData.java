import java.sql.*;

public class metaData {
    public static void main(String[] args){
        try {
            Connection connection=getConnection.getconnection();
            DatabaseMetaData databaseMetaData=connection.getMetaData();
            System.out.println("Database product name : "+databaseMetaData.getDatabaseProductName());
            System.out.println("Database product version : "+databaseMetaData.getDatabaseProductVersion());
            System.out.println("Database driver name : "+databaseMetaData.getDriverName());
            System.out.println("Database driver version : "+databaseMetaData.getDriverVersion());
            System.out.println("Database URL : "+databaseMetaData.getURL());
            System.out.println("Database current name : "+databaseMetaData.getUserName());
            System.out.println("============================================================== ");

            //Getting List of Tables
            ResultSet result = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
            while (result.next()) {
                String tableName=result.getString("TABLE_NAME");
                System.out.println(tableName);

                //getting List of Columns resultset
                ResultSet resultSet = databaseMetaData.getColumns(null, null, tableName, null);

                //getting primary key resltset
                ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, tableName);

                //getting List of column
                while (resultSet.next()) {
                    String col_Name = resultSet.getString("COLUMN_NAME");
                    String col_Type = resultSet.getString("TYPE_NAME");
                    int col_Size = resultSet.getInt("COLUMN_SIZE");
                    System.out.println("\t" + col_Name + " - " + col_Type + "(" + col_Size + ")");
                }

                //Primary key of the table
                while (primaryKeys.next()){
                    String primaryKeyColumn = primaryKeys.getString("COLUMN_NAME");
                    System.out.println("\tPrimary Key Column: " + primaryKeyColumn);
                }

            }

        }catch (Exception e){
            System.out.println("not connected");
        }
    }
}
