package com.example.helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.sql.SQLOutput;
import java.util.HashMap;

public class Register extends AppCompatActivity {
    Button btnRegisterSignUp;
    Button btnSignInRegister;
    MaterialEditText editName, editSurname, editEmail, editPassword, editConfirmPassword, editSsn, editPhone;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);



        /*Toolbar toolbar= findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("Register");
        getActionBar().setDisplayShowHomeEnabled(true);*/

        editName = findViewById(R.id.txtRegisterName);
        editSurname = findViewById(R.id.txtRegisterSurname);
        editEmail = findViewById(R.id.txtRegisterEmail);
        editPassword =findViewById(R.id.txtRegisterPassword);
        editConfirmPassword = findViewById(R.id.txtRegisterConfirmPassword);
        editSsn = findViewById(R.id.txtRegisterSsn);
        editPhone =findViewById(R.id.txtRegisterPhone);

        mAuth = FirebaseAuth.getInstance();
        btnRegisterSignUp = (Button) findViewById(R.id.btnRegisterSignUp);
        btnRegisterSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtName = editName.getText().toString();
                String txtSurname = editSurname.getText().toString();
                String txtEmail = editEmail.getText().toString();
                String txtPassword = editPassword.getText().toString();
                String txtSsn = editSsn.getText().toString();
                String txtPhone = editPhone.getText().toString();
                String txtConfirmPassword = editConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(txtName) || TextUtils.isEmpty(txtSurname) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtConfirmPassword)) {
                    Toast.makeText(Register.this, "Please fill in the required fields!", Toast.LENGTH_SHORT);

                }else if (!TextUtils.equals(txtPassword, txtConfirmPassword) && txtPassword.length()<8) {
                    Toast.makeText(Register.this, "Passwords doesn't match and minimum password length is 8 character!!!", Toast.LENGTH_SHORT);
                }else{
                    register(txtName,txtSurname,txtEmail,txtPassword,txtPhone,txtSsn);


                }
            }
        });


    }
    public void register(String name, String surname, String email, String password, String phone, String ssn ) {
        ssn.toString();
        phone.toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userid= firebaseUser.getUid();
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    HashMap<String, String> hashMap= new HashMap<>();
                    hashMap.put("id", userid);
                    hashMap.put("Name", name);
                    hashMap.put("Surname", surname);
                    hashMap.put("Mail", email);
                    hashMap.put("Password", password);
                    hashMap.put("Ssn", ssn);
                    hashMap.put("Phone", phone);
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Register.this,Login.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();;
                            }
                        }
                    });
                    Toast.makeText(Register.this, "The registration process is successful :)", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT);
                }
            }
        });
    }
}