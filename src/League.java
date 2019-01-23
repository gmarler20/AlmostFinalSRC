import java.util.ArrayList;

public class League {
    private FileReader NBAReader = new FileReader();
    public ArrayList<NBATeam> NBATeams = new ArrayList<>();


    public ArrayList getNBATeams() {
        return NBATeams;
    }

    public League() {
        genTeams(NBAReader);
    }

    public void genTeams(FileReader rdr) {
        int teamcount = 0;
        int i = 2;

        try {

            while (teamcount < 30) {
                NBATeam t = new NBATeam();
                t.setTeamName(rdr.TeamArray.get(teamcount));
                t.setFGp(Double.parseDouble(rdr.StatArray.get(i)));
                i++;
                t.setFGa(Double.parseDouble(rdr.StatArray.get(i)));
                i = i + 2;
                t.setThreePTp(Double.parseDouble(rdr.StatArray.get(i)));
                i++;
                t.setThreePTa(Double.parseDouble(rdr.StatArray.get(i)));
                i = i + 2;
                t.setFTp(Double.parseDouble(rdr.StatArray.get(i)));
                i++;
                t.setFTa(Double.parseDouble(rdr.StatArray.get(i)));
                i = i + 2;
                t.setTOV(Double.parseDouble(rdr.StatArray.get(i)));
                i = i + 4;
                t.setREB(Double.parseDouble(rdr.StatArray.get(i)));
                NBATeams.add(t);
                teamcount++;
                if (teamcount == 30) {
                    break;
                } else
                    i = i + 7;
            }

            NBATeam East = new NBATeam();
            East.setTeamName("East All Stars");
            East.setFGp(60.1);
            East.setFGa(90.4);
            East.setThreePTp(18.4);
            East.setThreePTa(45.3);
            East.setFTp(15);
            East.setFTa(20);
            East.setTOV(5.6);
            East.setREB(49.1);

            NBATeams.add(East);

            NBATeam West = new NBATeam();
            West.setTeamName("West All Stars");
            West.setFGp(65.1);
            West.setFGa(99.1);
            West.setThreePTp(19.3);
            West.setThreePTa(43.1);
            West.setFTp(11);
            West.setFTa(18);
            West.setTOV(9.4);
            West.setREB(55.3);

            NBATeams.add(West);


        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}
