package com.example.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.function.ToDoubleBiFunction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Password Manager"
        val context = this
        val db = DataBaseHelper(context)

        btnInsert.setOnClickListener {
            if(editText.text.toString().isNotEmpty() &&
                editUserName.text.toString().isNotEmpty() &&
                    editPassword.text.toString().isNotEmpty()) {
                val datainsert = Datainsert(editText.text.toString(), editUserName.text.toString(), editPassword.text.toString())
                db.insertData(datainsert)
                clearField()
            }
            else {
                Toast.makeText(context, "Bitte alle Felder ausfüllen!", Toast.LENGTH_SHORT).show()
            }
        }

        btnRead.setOnClickListener {
            val data = db.readData()
            tvResult.text = ""
            for(i in 0 until data.size){
                tvResult.append(
                    data[i].text + ": " + data[i].username + " " + data[i].password + "\n"
                )
            }
            TODO("Implementieren des Fingerabdruckscanner")
            //https://github.com/android/security-samples/tree/main/BiometricAuthentication
        }

    }

    fun clearField(){
        editText.text.clear()
        editUserName.text.clear()
        editPassword.text.clear()
    }
}