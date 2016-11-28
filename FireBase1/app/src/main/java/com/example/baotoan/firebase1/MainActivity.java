package com.example.baotoan.firebase1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference myRef;

    private TextView txtView;
    private EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txt_View);
        edtText = (EditText) findViewById(R.id.edt_Text);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(); // Root node

        // Add new child node
        myRef.child("greeting").setValue("Hello, World!");

        // Add new child object node
        myRef.child("student").setValue(new Student(1, "Ngo Bao Toan", 21));

        // Add new child map node
        Map<String, Student> topStudent = new HashMap<>();
        topStudent.put("Top 1", new Student(1, "Tran Van Tinh", 23));
        topStudent.put("Top 2", new Student(2, "Hoang Thi Thuyet", 42));
        topStudent.put("Top 3", new Student(3, "Le Cong Ly", 32));
        myRef.child("Top").setValue(topStudent);

        // Add new child by push (auto generate id)
        myRef.child("SinhVien").push().setValue(new Student(1, "Nguyen Cong The B", 53));

        // Add event when create complete
        myRef.child("Vehical").push().setValue("Car", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(null == databaseError) {
                    Toast.makeText(MainActivity.this, "Completely", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Event change
        myRef.child("DataChangeEvent").setValue("Nothing");
        myRef.child("DataChangeEvent").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtView.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        edtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myRef.child("DataChangeEvent").setValue(edtText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        myRef.child("Courses").push().setValue("Android");

        myRef.child("Courses").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "Child added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "Child changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(MainActivity.this, "Child removed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "Child moved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Child canceled", Toast.LENGTH_SHORT).show();
            }
        });


        // Push object
        myRef.child("Class").push().setValue(new Student(323, "Nguyen Van C", 42));
        myRef.child("Class").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Student student = dataSnapshot.getValue(Student.class);
                Toast.makeText(MainActivity.this, student.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
