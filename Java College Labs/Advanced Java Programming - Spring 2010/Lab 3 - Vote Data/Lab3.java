
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Lab3 extends JFrame {

    Scanner infile1 = new Scanner(new FileReader("N:\\candidateData.txt"));
    Scanner infile2 = new Scanner(new FileReader("N:\\voteData.txt"));
    String[][] candidate = new String[6][2];
    int[][] voting = new int[6][5];
    JTextArea[][] votes = new JTextArea[6][5];
    JTextField[] name = new JTextField[6];
    ButtonHndlr bhandler = new ButtonHndlr();
    JLabel winner = new JLabel("               Winner:");
    JLabel winnerout = new JLabel(" ");
    JLabel totvotes = new JLabel("       Total Votes:");
    JLabel totvotesout = new JLabel(" ");

    String strFname, strLname, strvote, strtemp, strelection, strcollege;
    int i, j, k, pass, Total, college, vote, mindex;

          //Constructor Module
    public Lab3() throws FileNotFoundException {
        Container pane = getContentPane();
        setTitle("Election Results");
        setSize(600, 400);
        pane.setLayout(new GridLayout(9, 6, 2, 2));
        JLabel nameLabel = new JLabel(" Name");
        pane.add(nameLabel);
        JLabel caLabel = new JLabel("College A");
        pane.add(caLabel);
        JLabel cbLabel = new JLabel("College B");
        pane.add(cbLabel);
        JLabel ccLabel = new JLabel("College C");
        pane.add(ccLabel);
        JLabel cdLabel = new JLabel("College D");
        pane.add(cdLabel);
        JLabel tLabel = new JLabel("Total");
        pane.add(tLabel);
//Module
        for (i = 0; i < 6; i++) {
            strFname = infile1.next();
            strLname = infile1.next();
            candidate[i][0] = strFname;
            candidate[i][1] = strLname;
        }

		// bubble sort
        for (pass = 1; pass < 6; pass++) {
            for (i = 0; i < 6 - pass; i++) {
                int x = i + 1;

        //compare lname
                if (candidate[i][1].compareTo(candidate[x][1]) > 0) {
                    strtemp = candidate[i][1];
                    candidate[i][1] = candidate[x][1];
                    candidate[x][1] = strtemp;
                    strtemp = candidate[i][0];
                    candidate[i][0] = candidate[x][0];
                    candidate[x][0] = strtemp;

                }

            }

        }

              //add name and vote
        for (i = 0; i < 6; i++) {
            name[i] = new JTextField(15);
            name[i].setText(candidate[i][0] + " " + candidate[i][1]);
            name[i].setBackground(Color.cyan);
            pane.add(name[i]);
            for (k = 0; k < 5; k++) {
                votes[i][k] = new JTextArea(2, 2);
                pane.add(votes[i][k]);

            }
        }

		//add a blank row to the gridlayout
        for (i = 0; i < 6; i++) {
            JLabel spholder1 = new JLabel("");
            pane.add(spholder1);

        }

        //create a button and add
        JButton click = new JButton("Results");
        pane.add(click);
        click.addActionListener(bhandler);

        pane.add(winner);
        pane.add(winnerout);
        pane.add(totvotes);
        pane.add(totvotesout);

		//make the window visible
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        infile1.close();

        //end of constructor module
    }

    public int bsearch(String[][] carray, String target) {
        int top, bottom, mid;
        boolean found;

        top = 0;
        bottom = carray.length - 1;
        found = false;
        mid = 0;

        while (top <= bottom & found == false) {
            mid = (top + bottom) / 2;

            if (target.compareTo(carray[mid][1]) == 0) {
                found = true;
            } else if (target.compareTo(carray[mid][1]) > 0) {
                top = mid + 1;
            } else {
                bottom = mid - 1;
            }
        }

        if (found == false) {
            return -1;
        } else {
            return mid;
        }

    }

            //Action performed start
    public class ButtonHndlr implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            while (infile2.hasNext()) {
                strFname = infile2.next();
                strLname = infile2.next();  //read the Fname, Lname

                strcollege = infile2.next();
                college = Integer.parseInt(strcollege); //read the college, and vote

                strvote = infile2.next();
                vote = Integer.parseInt(strvote);

                mindex = bsearch(candidate, strLname);

                if (mindex == -1) {
                    JOptionPane.showMessageDialog(null, "Mindex Should be Non Negetive", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    voting[mindex][college - 1] += vote; //voting[][] = voting[][] + int vote
                }
            }

		     //Calculate Totals
            for (i = 0; i < 6; i++) {
                voting[i][4] = 0;

                for (j = 0; j < 4; j++) {
                    voting[i][4] = voting[i][4] + voting[i][j];

                }

            }

			//Determine the winner
            int Max, Windex;

            Max = 0;
            Windex = 0;

            for (i = 0; i < 6; i++) {
                if (voting[i][4] > Max) {
                    Max = voting[i][4];

                    Windex = i;

                }
            }

		     //Display Vote
            for (i = 0; i < 6; i++) {
                for (j = 0; j < 5; j++) {
                    votes[i][j].setText(String.valueOf(voting[i][j]));

                }

            }

         // Display the winner name, & Total highest Votes
            winnerout.setText(candidate[Windex][0] + " " + candidate[Windex][1]);
            totvotesout.setText(String.valueOf(Max));

            infile2.close();

        }

    }

          //Main Method
    public static void main(String[] args) throws FileNotFoundException {
        Lab3 election = new Lab3();

    }
}
