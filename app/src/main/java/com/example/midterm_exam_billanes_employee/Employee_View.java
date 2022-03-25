package com.example.midterm_exam_billanes_employee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Employee_View extends AppCompatActivity {
    private DatabaseReference mReferenceEmployee;
    private ListView listView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> keys;
    private ArrayList<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view);
        mReferenceEmployee = FirebaseDatabase.getInstance().getReference("Employee");
        listView = (ListView) findViewById(R.id.list_all);
        keys = new ArrayList<>();
        employees = new ArrayList<Employee>();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        mReferenceEmployee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                keys.clear();
                employees.clear();
                for (DataSnapshot keyNods : snapshot.getChildren())
                {
                    keys.add(keyNods.getKey());
                    Employee employee = keyNods.getValue(Employee.class);
                    employees.add(employee);
                    arrayList.add(employee.getName()+
                            " "+employee.getDepartment()+
                            " "+employee.getPosition());
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Employee_View.this,employees.get(i).getName(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Employee_View.this, Update_Delete.class);
                intent.putExtra("key",keys.get(i));
                intent.putExtra("name",employees.get(i).getName());
                intent.putExtra("position",employees.get(i).getPosition());
                intent.putExtra("department",employees.get(i).getDepartment());
                startActivity(intent);
            }
        });
    }
    public void Back(View v){
        finish();return;
    }
}