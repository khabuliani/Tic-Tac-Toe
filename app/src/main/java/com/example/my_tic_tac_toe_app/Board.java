package com.example.my_tic_tac_toe_app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Board extends View {
    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winLineColor;
    private int cellSize;
    private boolean winner = false;
    private final Paint paint = new Paint();
    private final Logic game;
    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new Logic();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Board, 0,0);

        try{
            boardColor = a.getInteger(R.styleable.Board_boardColor,0);
            XColor = a.getInteger(R.styleable.Board_XColor,0);
            OColor = a.getInteger(R.styleable.Board_OColor,0);
            winLineColor = a.getInteger(R.styleable.Board_winLineColor,0);
        }finally{
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width,height);
        int demention = Math.min(getMeasuredHeight(),getMeasuredWidth());
        cellSize = demention/3;
        setMeasuredDimension(demention,demention);
    }

    @Override
    protected  void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawBoard(canvas);
        drawMarkers(canvas);
        if(winner){
            paint.setColor(winLineColor);
            drawWinigLine(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            int row = (int)Math.ceil(y/cellSize);
            int col = (int)Math.ceil(x/cellSize);
            if(!winner) {
                if (game.updateBoard(row, col)) {
                    invalidate();
                    if(game.winnerOrNot()){
                        winner = true;
                        invalidate();
                    }
                    if (game.getPlayer() % 2 == 0) {
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }
            }
            invalidate();

            return  true;
        }
        return  false;
    }

    private void drawBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(15);
        for(int i =0; i<=3; i++){
            canvas.drawLine(cellSize*i, 0, cellSize*i, canvas.getWidth(), paint);
        }
        for(int i =0; i<=3; i++){
            canvas.drawLine(0, cellSize*i, canvas.getWidth(), cellSize*i, paint);
        }
    }
    private void drawMarkers(Canvas canvas){
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(game.getBoard()[i][j] != 0){
                    if(game.getBoard()[i][j] == 1){
                        drawX( canvas, i, j);
                    }else {
                        drawO(canvas, i, j);
                    }
                }
            }
        }
    }

    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(XColor);
        canvas.drawLine((float)((col+1)*cellSize - cellSize*0.1),
                (float)(row*cellSize + cellSize*0.1),
                (float)(col*cellSize + cellSize*0.1),
                (float)((row+1)*cellSize - cellSize*0.1),
                paint);
        canvas.drawLine((float)(col*cellSize + cellSize*0.1),
                (float)(row*cellSize + cellSize*0.1),
                (float)((col+1)*cellSize - cellSize*0.1),
                (float)((row+1)*cellSize - cellSize*0.1),
                paint);
    }

    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(OColor);
        canvas.drawOval( (float) (col*cellSize + cellSize*0.1),
                (float) (row*cellSize + cellSize*0.1),
                (float) ((col*cellSize + cellSize) - cellSize*0.1),
                (float) ((row*cellSize + cellSize) - cellSize*0.1),
                paint);
    }

    private  void drawHorizontalWin(Canvas canvas, int row, int col){
        canvas.drawLine( col*1, row*cellSize + (float) cellSize/2,
                cellSize*3, row*cellSize + (float) cellSize/2, paint);
    }

    private  void drawVerticalWin(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellSize + (float) cellSize/2, row*1,
                col*cellSize + (float) cellSize/2, cellSize*3, paint);
    }

    private  void drawUpGoingWin(Canvas canvas){
        canvas.drawLine( 0, cellSize*3,
                cellSize*3, 0, paint);
    }

    private  void drawLoweringWin(Canvas canvas){
        canvas.drawLine(0, 0,
                cellSize*3, cellSize*3, paint);
    }

    private void drawWinigLine(Canvas canvas){
        int row = game.getWiningType()[0];
        int col = game.getWiningType()[1];
        int type = game.getWiningType()[2];
        switch (type){
            case 1:drawHorizontalWin(canvas,row,col);break;
            case 2:drawVerticalWin(canvas,row,col);break;
            case 3:drawLoweringWin(canvas);break;
            case 4:drawUpGoingWin(canvas);break;
        }
    }

    public void setUpGamePlay(Button homeButton, Button rePlayButton, TextView displayName, String[] names) {
        game.setRePlayButton(rePlayButton);
        game.setHomeButton(homeButton);
        game.setPlayerTurn(displayName);
        game.setPlayerNames(names);
    }

    public void resetGame(){
        game.resetBoard();
        winner = false ;
    }
}