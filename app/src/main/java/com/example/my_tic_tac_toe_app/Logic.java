package com.example.my_tic_tac_toe_app;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Logic {
    private int [] [] board;
    private int player = 1;
    private String[] playerNames = {"Player 1", "Player 2"};
    private TextView playerTurn;
    private Button rePlayButton;
    private Button homeButton;
    private  int[] winingType = {-1 , -1, -1};
    public Logic(){
        board = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int[][] getBoard(){
        return board;
    }

    public boolean updateBoard(int row, int col) {
        if (board[row - 1][col - 1] == 0) {
            board[row - 1][col - 1] = player;
            if(player == 1 ){
                playerTurn.setText((playerNames[1]+"'s turn"));
            }else {
                playerTurn.setText((playerNames[0]+"'s turn"));
            }
            return  true;
        } else {
            return false;
        }
    }

    public void resetBoard(){
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
        rePlayButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);
        if(player == 1 ){
            playerTurn.setText((playerNames[0]+"'s turn"));
        }else {
            playerTurn.setText((playerNames[1]+"'s turn"));
        };
    }

    public int getPlayer(){
        return player;
    }

    public int[] getWiningType() {
        return winingType;
    }

    public void setPlayer(int player){
        this.player = player;
    }

    public void setPlayerTurn(TextView playerTurn){
        this.playerTurn = playerTurn;
    }

    public void  setPlayerNames(String[] playerNames){
        this.playerNames = playerNames;
    }

    public  void setRePlayButton(Button rePlayButton){
        this.rePlayButton = rePlayButton;
    }

    public void  setHomeButton(Button homeButton){
        this.homeButton = homeButton;
    }

    public boolean winnerOrNot(){
        boolean isWinner = false;
        int totalCount = 0;
        for(int i = 0; i<3; i++){
            if(board[i][0] == board[i][1] && board[i][0] == board[i][2] &&
                    board[i][0] != 0){
                isWinner = true;
                winingType = new int[]{i, 0, 1};
            }
        }
        for(int i = 0; i<3; i++){
            if(board[0][i] == board[1][i] && board[0][i] == board[2][i] &&
                    board[0][i] != 0){
                isWinner = true;
                winingType = new int[]{0, i, 2};
            }
        }
        if(board[0][0] == board[1][1] && board[0][0] == board[2][2] &&
                board[0][0] != 0){
            isWinner = true;
            winingType = new int[]{0, 2, 3};
        }
        if(board[2][0] == board[1][1] && board[2][0] == board[0][2] &&
                board[2][0] != 0){
            isWinner = true;
            winingType = new int[]{2, 2, 4};
        }
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(board[i][j] != 0){
                    totalCount++;
                }
            }
        }
        if(isWinner){
            rePlayButton.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText((playerNames[player -1] + " Won!"));
        }else if(totalCount == 9){
            rePlayButton.setVisibility(View.VISIBLE);
            homeButton.setVisibility(View.VISIBLE);
            playerTurn.setText("Tie Game!");
        }
        return isWinner;
    }
}
