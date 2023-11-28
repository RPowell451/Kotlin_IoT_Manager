package com.cc.iot_manager_kot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.cc.iot_manager_kot.databinding.ActivitySignUpPageBinding

class SignUpPage : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpPageBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.submit.setOnClickListener {
            registerNewUser()
        }
    }

    private fun registerNewUser() {

        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val mailAddr = binding.email.text.toString()
        val dob = binding.DOB.text.toString()
        val pwd = binding.password.text.toString()
        val confirmPWD = binding.confirmPassword.text.toString()

        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "Please enter First Name", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "Please enter Last Name", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(mailAddr)) {
            Toast.makeText(this, "Please enter E-mail", Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Please enter Date of Birth", Toast.LENGTH_LONG).show()
            return
        }

        if (pwd != confirmPWD) {
            Toast.makeText(this, "Password did not match", Toast.LENGTH_LONG).show()
            return
        }

        val mAuth = FirebaseAuth.getInstance()

        val database = FirebaseDatabase.getInstance()
        val ref = database.reference.child("Users")

        mAuth.createUserWithEmailAndPassword(mailAddr, pwd).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                val userID = mAuth.currentUser!!.uid
                val currentUser = ref.child(userID)

                currentUser.child("UserData").child("firstName").setValue(firstName)
                currentUser.child("UserData").child("lastName").setValue(lastName)
                currentUser.child("UserData").child("dob").setValue(dob)
                currentUser.child("UserData").child("e-mail").setValue(mailAddr)
                Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()

                val loginIntent = Intent(this, LoginPage::class.java)
                startActivity(loginIntent)

            } else {
                Toast.makeText(this, "SignUp Failed!", Toast.LENGTH_LONG).show()
            }
        }

    }
}


/*
package com.cc.iot_manager_kot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)
    }
}
 */