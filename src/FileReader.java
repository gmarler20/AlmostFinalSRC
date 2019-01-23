import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
    private static String regex = "(<td>)(\\d+[\\.]+[0-9])|(<td>)([\\.]\\d+)|(<td>)(\\d\\d)";        // (<td>)(\d+[\.]+[0-9])|(<td>)([\.]\d+)|(<td>)(\d\d)
    private static String teamregex = "(Stats'>)(L.A.[ ]+\\w+)|(Stats'>)(\\w+ \\w+)|(Stats'>)(\\w+)";          //  /Stats'>\w+           Stats'>\w+ \w+|Stats'>\w+
    public ArrayList <String> StatArray = new ArrayList();
    public ArrayList <String> TeamArray = new ArrayList();

    public FileReader() {
        try {

            String line;
            URL url = new URL("https://basketball.realgm.com/nba/team-stats");
            BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
            line = rdr.readLine();
            while ((line = rdr.readLine()) != null) {
                Pattern stat = Pattern.compile(regex);
                Matcher match = stat.matcher(line);
                while (match.find()) {
                    if(match.group(2) != null) {
                        System.out.println(match.group(2));
                        StatArray.add(match.group(2));
                    }
                    if(match.group(4) != null) {
                        System.out.println(match.group(4));
                        StatArray.add(match.group(4));
                    }
                    if(match.group(6) != null) {
                        System.out.println(match.group(6));
                        StatArray.add(match.group(6));
                    }



                }

                Pattern Teamname = Pattern.compile(teamregex);
                Matcher TeamMatch = Teamname.matcher(line);
                while (TeamMatch.find()) {
                    if(TeamMatch.group(2) != null) {
                        System.out.println(TeamMatch.group(2));
                        TeamArray.add((TeamMatch.group(2)));
                    }
                    if(TeamMatch.group(4) != null) {
                        System.out.println(TeamMatch.group(4));
                        TeamArray.add((TeamMatch.group(4)));
                    }
                    if(TeamMatch.group(6) != null) {
                        System.out.println(TeamMatch.group(6));
                        TeamArray.add((TeamMatch.group(6)));
                    }


                }
            }
        }catch(Exception ex) {
            ;
        }
        System.out.println("Done");

    }

}
