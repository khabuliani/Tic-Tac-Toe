package com.example.my_tic_tac_toe_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton

class MainActivity2 : AppCompatActivity() {
    private lateinit var imageButton: ImageButton
    private lateinit var editText: EditText
    private lateinit var editText2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()

        imageButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun init(){
        editText = findViewById<EditText>(R.id.editTextTextPersonName)
        editText2 = findViewById<EditText>(R.id.editTextTextPersonName2)
        imageButton = findViewById<ImageButton>(R.id.imageButton)
    }
    public fun onButtonClick(view: View){
        var player1 = editText.text.toString()
        if(player1.isEmpty()){
            player1 = "Player 1"
        }
        var player2 = editText2.text.toString()
        if(player2.isEmpty()){
            player2 = "Player 2"
        }
        var abc = arrayOf(player1,player2)
        val intent = Intent(this, MainActivity3::class.java)
        intent.putExtra("Names",abc );
        startActivity(intent)
    }
}