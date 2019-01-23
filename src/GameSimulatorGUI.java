import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import sun.audio.*;

import sun.audio.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class GameSimulatorGUI extends JFrame {
    private League NBA = new League();
    private JFrame OpeningFrame;
    private JLayeredPane OpeningPane;
    private JLayeredPane MatchupPane;
    private JFrame SimulationFrame;
    private JLayeredPane SimulationPane;
    private JLabel SimulationPaneBackground;
    private JLabel SimulationTeamLogo1;
    private JLabel SimulationTeamLogo2;
    private JLabel ScoreLabelTeam1;
    private JLabel ScoreLabelTeam2;
    private JLabel MatchupPaneBackground;
    private JLabel OpeningBackground;
    private JLabel OpeningTitle;
    private JLabel LeftLabel;
    private JLabel RightLabel;
    private JButton OpeningStartButton;
    private JButton SimulateButton;
    private JButton ReturnFromSimulation;
    private JList<String> TeamList1;
    private JList<String> TeamList2;
    private JLabel NBALOGO;
    private JLabel TeamLogo1;
    private JLabel TeamLogo2;
    private JTable StatTable1;
    private JTable StatTable2;
    private JTable SimulationTable1;
    private JTable SimulationTable2;
    int framecount = 0;


    private JFrame TeamSelectFrame;
    private JPanel VisualPanel;
    private JPanel LeftListPanel;

    private ArrayList<NBATeamSelect> SelectedTeams = new ArrayList<>();


    GameSimulatorGUI(){
        GenOpeningComponents();
        OpeningFrame.setVisible(true);
    }


    public void GenOpeningComponents(){
        OpeningFrame = new JFrame("NBA Simulator");
        OpeningFrame.setSize(new Dimension(1000,600));
        OpeningFrame.setLayout(new FlowLayout());
        OpeningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        OpeningPane = new JLayeredPane();
        OpeningPane.setPreferredSize(new Dimension(1000,600));
        OpeningFrame.add(OpeningPane);


        OpeningBackground = new JLabel(genImage("openingbackground.png",1000,600));
        OpeningBackground.setBounds(0,0,1000,600);
        OpeningPane.add(OpeningBackground, new Integer(0));

        OpeningTitle = new JLabel("NBA Simulator");
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        OpeningTitle.setFont(new Font("Garamond", Font.BOLD, 68));
        OpeningTitle.setBounds(275,100,500,300);
        OpeningTitle.setForeground(Color.white);
        OpeningTitle.setBackground(Color.black);
        //OpeningTitle.setHorizontalAlignment(SwingConstants.CENTER);
        OpeningPane.add(OpeningTitle,new Integer(1));

        OpeningStartButton = new JButton("Start");
        OpeningStartButton.setBounds(450,350,100,50);
        OpeningPane.add(OpeningStartButton, new Integer(1));
        OpeningStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenTeamSelectComponents();
                OpeningFrame.setVisible(false);
                TeamSelectFrame.setVisible(true);
            }
        });
        OpeningPane.revalidate();
        OpeningPane.repaint();
        music("NBA");
    }

    public void GenTeamSelectComponents(){

        TeamSelectFrame = new JFrame();
       // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       // TeamSelectFrame.setLocation(dim.width/2-TeamSelectFrame.getSize().width/2, dim.height/2,)
        TeamSelectFrame.setSize(new Dimension(1000,620));
        TeamSelectFrame.setLayout(new FlowLayout());
        TeamSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MatchupPane = new JLayeredPane();
        MatchupPane.setPreferredSize(new Dimension(1000, 620));
        MatchupPaneBackground = new JLabel(genImage("MatchupBackground.jpg", 1000, 620));
        MatchupPaneBackground.setBounds(0,0,1000,620);
        MatchupPane.add(MatchupPaneBackground, new Integer(0));



        ListModel LeftList = new DefaultListModel<>();
        for(int i = 0; i < NBA.getNBATeams().size(); i++) {
            ((DefaultListModel) LeftList).addElement(NBA.NBATeams.get(i).getTeamName());

        }

        TeamList1 = new JList(LeftList);
        TeamList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TeamList1.setLayoutOrientation(JList.VERTICAL);
        TeamList1.setBackground(Color.darkGray);
        TeamList1.setForeground(Color.white);
        TeamList1.setOpaque(true);
        TeamList1.setVisibleRowCount(-1);
        TeamList1.setSelectedIndex(31);



        JScrollPane listscroller = new JScrollPane(TeamList1);
        listscroller.setPreferredSize(new Dimension(125, 620));
        listscroller.setBounds(10,0,100,620);
        listscroller.setVisible(true);
     //   listscroller.setBackground(Color.darkGray);
       // listscroller.setOpaque(true);
        MatchupPane.add(listscroller, new Integer(10));


        ListModel RightList = new DefaultListModel<>();
        for(int i = 0; i < NBA.getNBATeams().size(); i++) {
            ((DefaultListModel) RightList).addElement(NBA.NBATeams.get(i).getTeamName());
        }

        TeamList2 = new JList(RightList);
        TeamList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TeamList2.setLayoutOrientation(JList.VERTICAL);
        TeamList2.setBackground(Color.darkGray);
        TeamList2.setForeground(Color.white);
        TeamList2.setOpaque(true);
        TeamList2.setVisibleRowCount(-1);
        TeamList2.setSelectedIndex(30);


        JScrollPane listscroller2 = new JScrollPane(TeamList2);
        listscroller2.setPreferredSize(new Dimension(125, 620));
        listscroller2.setBounds(890, 0, 100, 620);
        listscroller2.setVisible(true);
        MatchupPane.add(listscroller2, new Integer(10));

        SimulateButton = new JButton();
        SimulateButton.setText("Simulate");
        SimulateButton.setPreferredSize(new Dimension(125,50));
        SimulateButton.setBounds(450,400,125,50);
        SimulateButton.setVisible(true);
        MatchupPane.add(SimulateButton, new Integer(9));

        NBALOGO = new JLabel(genImage("nba logo.png", 75, 150));
        NBALOGO.setBounds(473, 0, 75, 150);
        MatchupPane.add(NBALOGO, new Integer(3));

       TeamLogo1 = new JLabel(genImage("West All Stars.png", 200,200));
        TeamLogo1.setBounds(192, 140, 200, 200);
        MatchupPane.add(TeamLogo1, new Integer(4));

        TeamLogo2 = new JLabel(genImage("East All Stars.png", 220, 220));
        TeamLogo2.setBounds(604, 144, 220,220);
        MatchupPane.add(TeamLogo2, new Integer(4));

        StatTable1 = new JTable(NBA.NBATeams.get(30).genStats(),NBA.NBATeams.get(30).genColNames());
        StatTable1.setBounds(180,365,400,250);
        StatTable1.setOpaque(false);
        StatTable1.setForeground(Color.white);
        StatTable1.setRowHeight(25);
        StatTable1.setRowMargin(5);
        ((DefaultTableCellRenderer)StatTable1.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
        StatTable1.setShowGrid(false);
        MatchupPane.add(StatTable1, new Integer(5));


        StatTable2 = new JTable(NBA.NBATeams.get(31).genStats(),NBA.NBATeams.get(31).genColNames());
        StatTable2.setBounds(605,365,400,250);
        StatTable2.setOpaque(false);
        StatTable2.setForeground(Color.white);
        StatTable2.setRowHeight(25);
        StatTable2.setRowMargin(5);
        ((DefaultTableCellRenderer)StatTable2.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
        StatTable2.setShowGrid(false);
        MatchupPane.add(StatTable2, new Integer(5));



        TeamSelectFrame.setVisible(true);
        TeamSelectFrame.add(MatchupPane);
        TeamSelectFrame.revalidate();
        TeamSelectFrame.repaint();
        addMatchupPaneListeners();

    }

    public void GenSimulationComponents(ArrayList<NBATeamSelect> Teams) {
        SimulationFrame = new JFrame("Game Simulation");
        SimulationFrame.setSize(new Dimension(1000,600));
        SimulationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SimulationPane = new JLayeredPane();
        SimulationPane.setPreferredSize(new Dimension(1000, 600));
        SimulationFrame.add(SimulationPane);

        SimulationPaneBackground = new JLabel(genImage("SimulationBackground.png", 1000, 600));
        SimulationPaneBackground.setBounds(0,0,1000,600);
        SimulationPane.add(SimulationPaneBackground, new Integer(0));



        ScoreLabelTeam1 = new JLabel(genImage("ScoreboardBackground.png", 250, 100));
        ScoreLabelTeam1.setText(Integer.toString(Teams.get(0).getTotalPTS()) + "  -  " + Integer.toString(Teams.get(1).getTotalPTS()));
        ScoreLabelTeam1.setBounds(375, 70, 250, 100);
        ScoreLabelTeam1.setHorizontalTextPosition(JLabel.CENTER);
        ScoreLabelTeam1.setForeground(Color.RED);
        ScoreLabelTeam1.setFont(new Font("Garamond", Font.BOLD, 50));
        SimulationPane.add(ScoreLabelTeam1, new Integer(2));

        SimulationTeamLogo1 = new JLabel(genImage(Teams.get(0).getTeamName() +".png", 200,200));
        SimulationTeamLogo1.setBounds(160, 27, 200, 200);
        SimulationPane.add(SimulationTeamLogo1, new Integer(2));

        SimulationTeamLogo2 = new JLabel(genImage(Teams.get(1).getTeamName() +".png", 200, 200));
        SimulationTeamLogo2.setBounds(650, 27, 200, 200);
        SimulationPane.add(SimulationTeamLogo2, new Integer(2));

        //FIX IF THERE IS A TIE

        SimulationTable1 = new JTable(Teams.get(0).genGameStats(), Teams.get(0).genColNames());
        SimulationTable1.setBounds(130, 300, 450, 225);
        SimulationTable1.setOpaque(false);
        SimulationTable1.setForeground(Color.BLACK);
        SimulationTable1.setRowHeight(40);
        SimulationTable1.setRowMargin(0);
        ((DefaultTableCellRenderer)SimulationTable1.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
        SimulationTable1.setShowGrid(false);
        SimulationTable1.setFont(new Font("Garamond", Font.BOLD, 28));
        SimulationPane.add(SimulationTable1, new Integer(5));

        SimulationTable2 = new JTable(Teams.get(1).genGameStats(), Teams.get(1).genColNames());
        SimulationTable2.setBounds(570, 300, 450, 225);
        SimulationTable2.setOpaque(false);
        SimulationTable2.setForeground(Color.BLACK);
        SimulationTable2.setRowHeight(40);
        SimulationTable2.setRowMargin(0);
        ((DefaultTableCellRenderer)SimulationTable2.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
        SimulationTable2.setShowGrid(false);
        SimulationTable2.setFont(new Font("Garamond", Font.BOLD, 28));
        SimulationPane.add(SimulationTable2, new Integer(5));

        ReturnFromSimulation = new JButton();
        ReturnFromSimulation.setText("Done");
        ReturnFromSimulation.setPreferredSize(new Dimension(100, 50));
        ReturnFromSimulation.setHorizontalTextPosition(JLabel.CENTER);
        ReturnFromSimulation.setForeground(Color.RED);
        ReturnFromSimulation.setFont(new Font("Garamond", Font.BOLD, 24));
        ReturnFromSimulation.setBounds(850, 510, 100, 50);
        ReturnFromSimulation.setIcon(genImage("ScoreboardBackground.png", 100, 50));
        SimulationPane.add(ReturnFromSimulation, new Integer(6));

        addSimulationPaneListeners();
        SimulationFrame.revalidate();
        SimulationFrame.repaint();
        SimulationFrame.setVisible(true);


    }

    public ImageIcon genImage(String s, int w, int h){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL imgURL = getClass().getResource("/Resources/"+ s);
        Image image = toolkit.getImage(imgURL);
        image = image.getScaledInstance(w,h,Image.SCALE_SMOOTH);
        ImageIcon imageIcon= new ImageIcon(image);
        return imageIcon;

    }

    public void music(String name){
        try {
            String soundfile = "/cs1/2020/gmarler20/CS-372-1-JavaAppDev/LAtestVersionFinal/src/Resources/" + name +".mp3";
            InputStream in = new FileInputStream(soundfile);

            AudioStream stream = new AudioStream(in);

            AudioPlayer.player.start(stream);
        }catch (Exception ex){System.out.println(ex.getCause());}
    }

    public void addSimulationPaneListeners() {
            ReturnFromSimulation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    framecount++;
                    SelectedTeams.remove(1);
                    SelectedTeams.remove(0);

                    SimulationFrame.setVisible(false);
                    GenTeamSelectComponents();
                    System.out.println("Clicked");

                }
            });
    }

    public void addMatchupPaneListeners() {
        MouseListener mouseListener = new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String selectedItem = TeamList1.getSelectedValue();
                for (int i = 0; i < NBA.NBATeams.size(); i++) {
                    if (selectedItem == NBA.NBATeams.get(i).getTeamName()) {
                        TeamLogo1.setIcon(genImage(NBA.NBATeams.get(i).getTeamName() + ".png", 200, 200));
                        StatTable1.setVisible(false);
                        StatTable1 = new JTable(NBA.NBATeams.get(i).genStats(),NBA.NBATeams.get(i).genColNames());
                        StatTable1.setBounds(180,365,400,250);
                        StatTable1.setOpaque(false);
                        StatTable1.setForeground(Color.white);
                        StatTable1.setRowHeight(25);
                        StatTable1.setRowMargin(5);
                        ((DefaultTableCellRenderer)StatTable1.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
                        StatTable1.setShowGrid(false);
                        MatchupPane.add(StatTable1, new Integer(5));
                       StatTable1.setVisible(true);
                        TeamSelectFrame.revalidate();
                        TeamSelectFrame.repaint();

                    }
                }
            }

        };
        TeamList1.addMouseListener(mouseListener);

        MouseListener List2Listener = new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param a
             */
            @Override
            public void mouseClicked(MouseEvent a) {
                super.mouseClicked(a);

                String selectedItem = TeamList2.getSelectedValue();
                for (int i = 0; i < NBA.NBATeams.size(); i++) {
                    if (selectedItem == NBA.NBATeams.get(i).getTeamName()) {
                        TeamLogo2.setIcon(genImage(NBA.NBATeams.get(i).getTeamName() + ".png", 200, 200));
                        StatTable2.setVisible(false);
                        StatTable2 = new JTable(NBA.NBATeams.get(i).genStats(),NBA.NBATeams.get(i).genColNames());
                        StatTable2.setBounds(605,365,400,250);
                        StatTable2.setOpaque(false);
                        StatTable2.setForeground(Color.white);
                        StatTable2.setRowHeight(25);
                        StatTable2.setRowMargin(5);
                        ((DefaultTableCellRenderer)StatTable2.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
                        StatTable2.setShowGrid(false);
                        MatchupPane.add(StatTable2, new Integer(5));
                        StatTable2.setVisible(true);
                        TeamSelectFrame.revalidate();
                        TeamSelectFrame.repaint();
                    }
                }
            }

        };
        TeamList2.addMouseListener(List2Listener);
        SimulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
                String selectedTeam1 = TeamList1.getSelectedValue();
                String selectedTeam2 = TeamList2.getSelectedValue();
                if(selectedTeam1 == selectedTeam2) {
                    JOptionPane.showMessageDialog(null, "You cannot simulate a game between the same teams!");
                }
                else {
                    for (int i = 0; i < NBA.NBATeams.size(); i++) {
                        if (selectedTeam1 == NBA.NBATeams.get(i).getTeamName()) {
                            SelectedTeams.add(new NBATeamSelect(NBA.NBATeams.get(i)));
                        }
                    }

                    for (int j = 0; j < NBA.NBATeams.size(); j++) {
                        if (selectedTeam2 == NBA.NBATeams.get(j).getTeamName()) {
                            SelectedTeams.add(new NBATeamSelect(NBA.NBATeams.get(j)));
                        }
                    }

                    if(SelectedTeams.get(0).getTotalPTS() == SelectedTeams.get(1).getTotalPTS()) {
                        System.out.println("tie");
                        Random r = new Random();

                        int rando = r.nextInt(2);

                        if(rando == 0) {
                            SelectedTeams.get(0).setTiebreaker(2);
                        }
                        else {
                            SelectedTeams.get(1).setTiebreaker(2);
                        }


                    }
                    GenSimulationComponents(SelectedTeams);

                    TeamSelectFrame.setVisible(false);
                }
            }
        });

    }


}
