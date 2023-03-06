import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;

public class TicTacToeFrame extends JFrame {

    JPanel mainPnl;

    JPanel boardPnl;
    JPanel optionPnl;


    // Buttons:

    // Top Row

    TicTacToeButton nwBtn;
    TicTacToeButton nBtn;
    TicTacToeButton neBtn;

    // Middle Row

    TicTacToeButton wBtn;
    TicTacToeButton cBtn;
    TicTacToeButton eBtn;

    // Bottom Row

    TicTacToeButton swBtn;
    TicTacToeButton sBtn;
    TicTacToeButton seBtn;

    JButton quitBtn;

    public TicTacToeFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createBoardPanel();
        mainPnl.add(boardPnl, BorderLayout.CENTER);

        createOptionPanel();
        mainPnl.add(optionPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setTitle("Tic Tac Toe GUI");
        setLocation(screenWidth / 8, screenHeight / 8);
    }

    private void createBoardPanel() {

        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(3, 3));

        nwBtn = new TicTacToeButton(0,0);
        nwBtn.addActionListener((ActionEvent ae) -> gameLogic(nwBtn.getRow(), nwBtn.getCol()));

        nBtn = new TicTacToeButton(0,1);
        nBtn.addActionListener((ActionEvent ae) -> gameLogic(nBtn.getRow(), nBtn.getCol()));

        neBtn = new TicTacToeButton(0,2);
        neBtn.addActionListener((ActionEvent ae) -> gameLogic(neBtn.getRow(), neBtn.getCol()));

        wBtn = new TicTacToeButton(1,0);
        wBtn.addActionListener((ActionEvent ae) -> gameLogic(wBtn.getRow(), wBtn.getCol()));

        cBtn = new TicTacToeButton(1,1);
        cBtn.addActionListener((ActionEvent ae) -> gameLogic(cBtn.getRow(), cBtn.getCol()));

        eBtn = new TicTacToeButton(1, 2);
        eBtn.addActionListener((ActionEvent ae) -> gameLogic(eBtn.getRow(), eBtn.getCol()));

        swBtn = new TicTacToeButton(2,0);
        swBtn.addActionListener((ActionEvent ae) -> gameLogic(swBtn.getRow(), swBtn.getCol()));

        sBtn = new TicTacToeButton(2,1);
        sBtn.addActionListener((ActionEvent ae) -> gameLogic(sBtn.getRow(), sBtn.getCol()));

        seBtn = new TicTacToeButton(2,2);
        seBtn.addActionListener((ActionEvent ae) -> gameLogic(seBtn.getRow(), seBtn.getCol()));

        boardPnl.add(nwBtn);
        boardPnl.add(nBtn);
        boardPnl.add(neBtn);
        boardPnl.add(wBtn);
        boardPnl.add(cBtn);
        boardPnl.add(eBtn);
        boardPnl.add(swBtn);
        boardPnl.add(sBtn);
        boardPnl.add(seBtn);


    }

    private void createOptionPanel() {
        optionPnl = new JPanel();
        optionPnl.setLayout(new GridLayout(1,1));

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        optionPnl.add(quitBtn);
    }

    // Game Logic
    private static final int ROW = 3;
    private static final int COL = 3;
    private static TicTacToeButton[][] board = new TicTacToeButton[ROW][COL];

    public static void gameLogic(int row, int col)
    {
        boolean finished = false;
        boolean playing = true;
        Scanner in = new Scanner(System.in);
        String player = "X";
        int moveCnt = 0;
        //int row = -1;
        //int col = -1;
        final int MOVES_FOR_WIN = 5;
        final int MOVES_FOR_TIE = 7;
        do // program loop
        {
            //begin a game
            player = "X";
            playing = true;
            moveCnt = 0;
            clearBoard();
            do  // game loop
            {
                // get the move
                do
                {
                    JOptionPane.showMessageDialog(null, "Invalid Move: Please choose a different option.");
                    // Further Elaboration ^^
                    //display();
                    //row--; col--;
                }while(!isValidMove(row, col));
                board[row][col].setText(player); // Might have to modify this part. Should change text in selected JButton
                moveCnt++;

                if(moveCnt >= MOVES_FOR_WIN)
                {
                    if(isWin(player))
                    {
                        //display();
                        //System.out.println("Player " + player + " wins!");
                        JOptionPane.showConfirmDialog(null, "You Win! Would you like to play again?");
                        // Further Elaboration required on this to play again ^^
                        playing = false;
                    }
                }
                if(moveCnt >= MOVES_FOR_TIE)
                {
                    if(isTie())
                    {
                        //display();
                        System.out.println("It's a Tie!");
                        JOptionPane.showConfirmDialog(null, "Board is full, Tie game! Play again?");
                        // further elaboration ^^
                        playing = false;
                    }
                }
                if(player.equals("X"))
                {
                    player = "O";
                }
                else
                {
                    player = "X";
                }

            }while(playing);

            //finished = SafeInput.getYNConfirm(in, "Done Playing? ");
            JOptionPane.showConfirmDialog(null, "Would you like to play again?");
            // more further elaboration^^
        }while(!finished);


    }

    private static void clearBoard()
    {
        // sets all the board elements to a space
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col].setText(" ");
            }
        }
    }
//    private static void display()
//    {
//        // shows the Tic Tac Toe game
//        for(int row=0; row < ROW; row++)
//        {
//            System.out.print("| ");
//            for(int col=0; col < COL; col++)
//            {
//                System.out.print(board[row][col] + " | ");
//            }
//            System.out.println();
//        }
//
//    }
    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].getText().equals(" "))
            retVal = true;

        return retVal;

    }
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
    private static boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private static boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private static boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if(board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player) )
        {
            return true;
        }
        if(board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player) )
        {
            return true;
        }
        return false;
    }

    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
}
