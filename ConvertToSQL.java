import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertToSQL {

    //Initializes the SQL schema and inserts tuples
    public static void InitializeDatabase() {
        String url;
        Connection conn;
        PreparedStatement pStatement;
        ResultSet rs;
        String queryString;

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Failed to find the JDBC driver");
        }

        try {
            url = "jdbc:postgresql://localhost:5432/postgres"; // DB = postgres
            conn = DriverManager.getConnection(url, "postgres", "postgres");


            //Initialize the database
            queryString = "CREATE TABLE IF NOT EXISTS Videos(publishedAt date not null, " +
                          "title varchar(200) not null, " +
                          "videoId varchar(30) not null, " +
                          "characterOne varchar(30), " +
                          "characterTwo varchar(30), " +
                          "primary key (videoId));";

            pStatement = conn.prepareStatement(queryString);
            int result = pStatement.executeUpdate();

            System.out.println("Executed Update: " + queryString);
            System.out.println("Result: " + result); // Expected: 0


            //Insert tuples from JSON files
            JSONToData(System.getProperty("user.dir") + "\\src\\VODSInfo\\page.json", pStatement, conn);
        }

        catch (SQLException se) {
            System.err.println("SQL Exception." +
                    "<Message>: " + se.getMessage());
        }
    }


    //Helper method for parsing SQL schema information from JSON files.
    public static String JSONToData(String filename, PreparedStatement pStatement, Connection conn) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            Pattern p;
            Matcher m;
            String line;
            String publishedAt = "";
            String title = "";
            String characterOne = "";
            String characterTwo = "";
            String url = "";
            String queryString = "";
            while((line = br.readLine()) != null) {
                if(line.contains("videoPublishedAt")) {
                    p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                    m = p.matcher(line);
                    if(m.find()) {
                        publishedAt = m.group(0);
                    }
                } else if(line.contains("title")) {
                    p = Pattern.compile("(?<=\\()\\w+(?=\\)\\s[Vv][Ss])");
                    m = p.matcher(line);
                    if(m.find()) {
                        characterOne = m.group(0);
                    }

                    p = Pattern.compile("(?<=\\()\\w+(?=\\)\\s[^Vv])");
                    m = p.matcher(line);
                    if(m.find()) {
                        characterTwo = m.group(0);
                    }

                    p = Pattern.compile("(?<=:)\\s\".+");
                    m = p.matcher(line);
                    if(m.find()) {
                        title = m.group(0);
                    }

                } else if(line.contains("videoId")) {
                    p = Pattern.compile("(?<=:\\s\").+(?=\")");
                    m = p.matcher(line);
                    if(m.find()) {
                        url = "https://www.youtube.com/watch?v=" + m.group(0);
                    }
                    if(publishedAt.length() > 0 && title.length() > 0 && url.length() > 0 && characterOne.length() > 0 && characterTwo.length() > 0) {
                        queryString = "INSERT INTO Videos VALUES(?, ?, ?, ?, ?);";
                        pStatement = conn.prepareStatement(queryString);
                        pStatement.setString(1, publishedAt);
                        pStatement.setString(2, title);
                        pStatement.setString(3, url);
                        pStatement.setString(4, characterOne);
                        pStatement.setString(5, characterTwo);


                        pStatement.executeUpdate();
                    }
                }

            }



        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



    public static void main(String[] args) {
        InitializeDatabase();
    }
}