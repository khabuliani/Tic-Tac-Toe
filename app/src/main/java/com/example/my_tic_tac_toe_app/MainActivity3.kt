package com.example.my_tic_tac_toe_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    private lateinit var imageButton: ImageButton
    private lateinit var button: Button
    private lateinit var homeButton: Button
    private lateinit var board:Board;
    private lateinit var textView: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        init()

        homeButton.visibility = View.INVISIBLE
        button.visibility = View.INVISIBLE
        var playerNames = getIntent().getStringArrayExtra("Names");
        if(playerNames != null){
            textView.setText((playerNames[0]+"'s turn"));
        }
        board.setUpGamePlay(homeButton,button,textView,playerNames);
        imageButton.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
    private fun init(){
        board = findViewById<Board>(R.id.board4)
        homeButton = findViewById<Button>(R.id.button4)
        imageButton = findViewById<ImageButton>( R.id.imageButton2)
        button = findViewById<Button>(R.id.button2)
        textView = findViewById<TextView>(R.id.textView4)
    }

    public  fun replayButtonClick( view: View){
        board.resetGame();
        board.invalidate();
    }

    public fun homeButtonClick( view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}