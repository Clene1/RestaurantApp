package com.example.grannyapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var btnSave: Button = findViewById(R.id.btnReg)
        btnSave.setOnClickListener()
        {
            val Name : EditText = findViewById(R.id.user)
            val Pass1 : EditText = findViewById(R.id.pass)
            val Pass2 : EditText = findViewById(R.id.confirmPass)

            if (Pass1.text.toString().equals(Pass2.text.toString()) and (Name.text.toString().length >= 1))
            {
                var temp=ArrayList<User>()
                temp.add(User(Name.text.toString(), Pass1.text.toString(), "https://picsum.photos/200/300"))
                UserSignedIn.getInstance().arrayUsers.addAll(temp)
                Toast.makeText(this, "Successfully registered", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Name.error = "This needs to be filled in"
                Pass1.error = "This needs to be filled in"
                Pass2.error = "This needs to be filled in"
            }
        }

        val buttonBack = findViewById<Button>(R.id.btnMain)
        buttonBack.setOnClickListener ()
        {
            finish()
        }
    }
}