import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbCreator {

    private String urlPostgres;
    private String urlDb;
    private String user;
    private String psw;
    private String nameSQLFile;

    public DbCreator(String urlPostgres, String urlDb, String user, String psw, String nameSQLFile) {
        this.urlPostgres = urlPostgres;
        this.urlDb = urlDb;
        this.user = user;
        this.psw = psw;
        this.nameSQLFile = nameSQLFile;
    }

    public void dropCreateDbAndTables() {
        dropDB();
        createDB();
        createTables();
    }

    public void createDB(){

        try (Connection c = DriverManager.getConnection(urlPostgres, user, psw);
             Statement statement = c.createStatement()) {
            statement.executeUpdate("CREATE DATABASE banktestdb");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createTables(){

        String query = "";

        try(InputStream inputStream =
                    getClass().getClassLoader().getResourceAsStream(nameSQLFile);
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream)))
        {
            String readline;
            while((readline = bufferedReader.readLine()) !=null){
                query  = query + readline;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(urlDb, user, psw);
             Statement statement = c.createStatement()){

            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropDB() {

        try (Connection c = DriverManager.getConnection(urlPostgres, user, psw);
             Statement statement = c.createStatement()) {

            statement.executeUpdate("DROP DATABASE testdb");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}