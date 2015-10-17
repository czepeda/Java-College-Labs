
import java.util.*;
import java.io.*;

public class Lab1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inFile = new Scanner(new FileReader("K:\\Java Files\\Spring 2010\\knightmoves.dat"));
        PrintWriter outFile = new PrintWriter("K:\\Java Files\\Spring 2010\\SortedMoves.dat");
        String StrMove;
        int i, j, move;
        j = 1;
        i = 1;
        int[][] Chessboard = new int[9][9];
        int[][] bucket = new int[65][2];
        outFile.println("\t\t\t" + "Knight's Tour");
        outFile.println();
        outFile.println("Move" + "\t\t\t\t\t" + "Square");
        outFile.println("Number" + "\t\t\t\t" + "Row" + "\t" + "Column");
        for (i = 1; i < 9; i++) {
            for (j = 1; j < 9; j++) {
                StrMove = inFile.nextLine();
                move = Integer.parseInt(StrMove);
                Chessboard[i][j] = move;
            }
        }
        for (i = 1; i < 9; i++) {
            for (j = 1; j < 9; j++) {
                move = Chessboard[i][j];
                bucket[move][0] = i;
                bucket[move][1] = j;
            }
        }
        for (i = 1; i < 65; i++) {
            outFile.println("" + i + "\t\t\t\t" + bucket[i][0] + "\t" + bucket[i][1]);
        }
        inFile.close();
        outFile.close();
    }
}
