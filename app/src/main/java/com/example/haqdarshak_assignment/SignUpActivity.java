package com.example.haqdarshak_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextName,editTextAge,editTextEmail,editTextPassword,editTextPhone;
    Button buttonSignUp;
    RadioButton radioButtonFemale,radioButtonMale;

    DatabaseReference reference;
    Member member;
    long maxId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEmail= findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonMale = findViewById(R.id.radioButtonMale);

        member = new Member();

        reference = FirebaseDatabase.getInstance().getReference().child("Database");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxId = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Age = Integer.parseInt(editTextAge.getText().toString().trim());
                long phone = Long.parseLong(editTextPhone.getText().toString());
                String female = radioButtonFemale.getText().toString();
                String male = radioButtonMale.getText().toString();

                member.setName(editTextName.getText().toString().trim());

                if (radioButtonFemale.isChecked()){
                    member.setSex(female);
                }else {
                    member.setSex(male);
                }

                member.setAge(Age);
                member.setEmail(editTextEmail.getText().toString().trim());
                member.setPhone((int) phone);
                member.setPassword(editTextPassword.getText().toString().trim());

                reference.child(String.valueOf(maxId+1)).setValue(member);
                Toast.makeText(SignUpActivity.this, "Thank You for Creating an account:)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });
    }

    public void Login(View view) {
        Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}