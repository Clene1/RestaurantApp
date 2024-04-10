package com.example.grannyapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executors


var Signedin :Int = -1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Username :EditText =  findViewById(R.id.user)
        var Password :EditText =  findViewById(R.id.password)

        val executor2 = Executors.newSingleThreadExecutor()
        executor2.execute {
            //Fetch data from the URL and parse it into a list of User Objects
            val url = URL("https://opsc.azurewebsites.net/?userdb")
            val json = url.readText()

            val userList= Gson().fromJson(json,Array<User>::class.java).toList()
            Handler(Looper.getMainLooper()).post{
                UserSignedIn.getInstance().arrayUsers.addAll(userList)
            }
        }

        var temp=ArrayList<User>()
        temp.add(User("1", "1", "https://picsum.photos/200/300"))
        temp.add(User("Granny", "Timmy", "https://picsum.photos/200/300"))
        temp.add(User("Granpa", "John", "https://picsum.photos/200/300"))
        temp.add(User("Timmy", "1234", "https://picsum.photos/200/300"))
        temp.add(User("Mom", "4321", "https://picsum.photos/200/300"))
        UserSignedIn.getInstance().arrayUsers.addAll(temp)

        var btnTest: Button = findViewById(R.id.btntest)
        btnTest.setOnClickListener() {
            var i = UserSignedIn.getInstance().count
            UserSignedIn.getInstance().IncreaseCount()
            Toast.makeText(this,i.toString(),Toast.LENGTH_SHORT).show()
        }

        var btnLogin:Button = findViewById(R.id.btnMainMenu)
        btnLogin.setOnClickListener()
        {
            var Found = false
            Signedin =-1
            for (i in 0..UserSignedIn.getInstance().arrayUsers.size-1)
            {
                if(Username.text.toString().equals(UserSignedIn.getInstance().arrayUsers[i].Name)
                    and Password.text.toString().equals(UserSignedIn.getInstance().arrayUsers[i].Password)  )
                {
                    Toast.makeText(this,"Name :"+Username.text+
                            "\nPassword :"+Password.text,Toast.LENGTH_LONG).show()
                    Found=true
                    val intent = Intent(this, Welcome::class.java)
                    startActivity(intent)
                    Signedin=i
                    break
                }
            }
            if(Found==false)
            {
                Toast.makeText(this,"RUNNNNNNN...",Toast.LENGTH_SHORT).show()
            }

        }

        var btnReg:Button = findViewById(R.id.btnReg)
        btnReg.setOnClickListener()
        {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
    }
}