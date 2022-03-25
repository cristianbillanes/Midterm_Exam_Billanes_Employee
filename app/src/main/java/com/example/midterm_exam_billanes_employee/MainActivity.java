package com.example.midterm_exam_billanes_employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mReferenceEmployee;
    private EditText Name,Position, Department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReferenceEmployee = FirebaseDatabase.getInstance().getReference("Employee");
        Name = (EditText) findViewById(R.id.edt_Name);
        Position= (EditText) findViewById(R.id.edt_Position);
        Department = (EditText) findViewById(R.id.edt_Department);
    }
    public void Add(View v){
        Employee employee = new Employee();
        employee.setName(Name.getText().toString());
        employee.setPosition(Position.getText().toString());
        employee.setDepartment(Department.getText().toString());
        final String key = mReferenceEmployee.push().getKey();
        mReferenceEmployee.child(key).setValue(employee)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this,"Successfully Added",Toast.LENGTH_LONG).show();
                        Name.setText("");
                        Position.setText("");
                        Department.setText("");
                    }
                });
    }
    public void View(View v){
        Intent intent = new Intent(this,Employee_View.class);
        startActivity(intent);
    }
}