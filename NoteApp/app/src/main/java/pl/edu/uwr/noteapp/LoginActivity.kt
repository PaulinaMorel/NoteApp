package pl.edu.uwr.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val registerText: TextView = findViewById(R.id.textViw_register)

        registerText.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        val loginButton: Button = findViewById(R.id.button2)

        loginButton.setOnClickListener {
            performLogin()
        }
    }
    private fun performLogin(){
        val emaillog: EditText = findViewById(R.id.login)
        val passwordlog: EditText = findViewById(R.id.pass)

        if(emaillog.text.isEmpty() || passwordlog.text.isEmpty()){
            Toast.makeText(baseContext, "Fill all fields.",
                Toast.LENGTH_SHORT).show()
            return
        }
        val inputEmaillog = emaillog.text.toString()
        val inputPasslog = passwordlog.text.toString()

        auth.signInWithEmailAndPassword(inputEmaillog,inputPasslog)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Error",
                    Toast.LENGTH_SHORT).show()
            }
    }
}