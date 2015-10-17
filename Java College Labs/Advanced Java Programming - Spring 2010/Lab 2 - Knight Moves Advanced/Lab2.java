
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Lab2 extends JFrame {
//Declarations

    JTextArea[][] square = new JTextArea[9][9];
    int[][] bucket = new int[65][2];
    JButton sort = new JButton("Sort Move");
    JPanel NorthPanel = new JPanel();
    JPanel SouthPanel = new JPanel();
    Scanner inFile = new Scanner(new FileReader("K:\\Java Files\\Spring 2010\\knightmoves.dat"));
    PrintWriter outFile = new PrintWriter("K:\\Java Files\\Spring 2010\\SortedMoves.dat");
    String strmove;
    int i, j, move;
    buttonHndlr bhandler = new buttonHndlr();
    Color lightRed = new Color(255, 90, 90);

    public Lab2() throws FileNotFoundException {
        Container pane = getContentPane();
        setTitle("Knight's Tour");
        setSize(455, 460);
        SouthPanel.setLayout(new BorderLayout());
        SouthPanel.add(sort, BorderLayout.SOUTH);
        sort.addActionListener(bhandler);
        NorthPanel.setLayout(new GridLayout(8, 8, 2, 2));
        for (i = 1; i < 9; i++) {
            for (j = 1; j < 9; j++) {
                strmove = inFile.nextLine();
                move = Integer.parseInt(strmove);
                square[i][j] = new JTextArea(3, 3);
                if (move % 2 == 0) {
                    square[i][j].setBackground(lightRed);
                }
                square[i][j].setText(strmove);
                NorthPanel.add(square[i][j]);
            }
        }
        pane.setLayout(new BorderLayout());
        pane.add(NorthPanel, BorderLayout.NORTH);
        pane.add(SouthPanel, BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
//ActionListner

    public class buttonHndlr implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (i = 1; i < 9; i++) {
                for (j = 1; j < 9; j++) {
                    strmove = square[i][j].getText();
                    move = Integer.parseInt(strmove);
                    bucket[move][0] = i;
                    bucket[move][1] = j;
                }
            }
            outFile.println("\t\t\t" + "Knight's Tour");
            outFile.println();
            outFile.println("Move" + "\t\t\t\t\t\t" + "Sqaure");
            outFile.println("Number" + "\t\t\t\t\t" + "Row" + "\t" + "Column");
            for (i = 1; i < 65; i++) {
                outFile.println("" + i + "\t\t\t\t\t" + bucket[i][0] + "\t" + bucket[i][1]);
            }
            inFile.close();
            outFile.close();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Lab2 classmoves = new Lab2();
    }
}
