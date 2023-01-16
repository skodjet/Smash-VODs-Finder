// Java program to read and download
// webpage in html file
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class download {

    public static String DownloadWebPage(String webpage, String nextPageToken)
    {

        try {
            String newNextPageToken = "";
            // Create URL object
            URL url = new URL(webpage);
            BufferedReader readr =
                    new BufferedReader(new InputStreamReader(url.openStream()));

            // Enter filename in which you want to download
            String fileName = "page" + nextPageToken + ".json";
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(fileName));

            // read each line from stream till end
            String line;
            while ((line = readr.readLine()) != null) {
                if(line.contains("nextPageToken")) { //Gets the next page token and trims it
                    newNextPageToken = line.substring(19).replaceAll("[\",]", "");
                }
                writer.write(line);
                writer.write('\n');


            }

            readr.close();
            writer.close();
            System.out.println("Successfully Downloaded.");
            return newNextPageToken;
        }

        // Exceptions
        catch (MalformedURLException mue) {
            System.out.println("Malformed URL Exception raised");
        }
        catch (IOException ie) {
            System.out.println("IOException raised");
        }
        return null;
    }
    public static void main(String args[])
            throws IOException
    {
        //TODO: Add you own Youtube API token here for the program to execute correctly.
        String APIToken = "";
        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet%2CcontentDetails&maxResults=50&playlistId=UUj1J3QuIftjOq9iv_rr7Egw&key="
                     + APIToken;
        String nextPage = DownloadWebPage(url, "");

        //Get the results for all 400 pages of video results (19988 videos / 50 results per page = 399.76).
        for(int i = 0; i < 400; i++) {
            url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet%2CcontentDetails&maxResults=50&playlistId=UUj1J3QuIftjOq9iv_rr7Egw&key="
                    + APIToken + "&pageToken=" + nextPage;
            nextPage = DownloadWebPage(url, nextPage);
        }
    }
}
