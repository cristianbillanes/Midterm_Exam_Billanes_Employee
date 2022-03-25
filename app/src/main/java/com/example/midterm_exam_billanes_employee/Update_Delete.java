package com.example.midterm_exam_billanes_employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Update_Delete extends AppCompatActivity {
    private DatabaseReference mReferenceEmployee;
    private EditText Name,Position, Department;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        mReferenceEmployee = FirebaseDatabase.getInstance().getReference("Employee");
        Name = (EditText) findViewById(R.id.edt_Name);
        Position= (EditText) findViewById(R.id.edt_Position);
        Department = (EditText) findViewById(R.id.edt_Department);
        key = getIntent().getStringExtra("key");
        Name.setText(getIntent().getStringExtra("name"));
        Position.setText(getIntent().getStringExtra("position"));
        Department.setText(getIntent().getStringExtra("department"));
    }
    public void Delete(View v){
        mReferenceEmployee.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Update_Delete.this,"Successfully Delete",Toast.LENGTH_LONG).show();
                        finish();return;
                    }
                });
    }
    public void Update(View v){
        Employee employee = new Employee();
        employee.setName(Name.getText().toString());
        employee.setPosition(Position.getText().toString());
        employee.setDepartment(Department.getText().toString());
        mReferenceEmployee.child(key).setValue(employee).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Update_Delete.this,"Successfully Update",Toast.LENGTH_LONG).show();
                finish();return;
            }
        });

    }
    public void Back(View v){
        finish();return;
    }

}