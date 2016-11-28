package com.example.baotoan.firebaseauthentication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUser;
    private EditText edtPass;
    private Button btnReg;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        edtUser = (EditText) findViewById(R.id.edt_user);
        edtPass = (EditText) findViewById(R.id.edt_pass);
        btnReg = (Button) findViewById(R.id.btn_reg);

        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnReg) {
            registry(edtUser.getText().toString(), edtPass.getText().toString());
        }
    }

    private void registry(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registry successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Registry error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
