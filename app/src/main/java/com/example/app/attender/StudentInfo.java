package com.example.app.attender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StudentInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        final String[] Roll = new String[1];
        final String[] year = new String[1];
        final String[] branch = new String[1];
        final String[] division = new String[1];
        final int[] entry = {0,0,0};

        final EditText roll_no = findViewById(R.id.RollNoEdit);

        final Spinner year_Spinner = findViewById(R.id.YearSpinner);
        ArrayAdapter<CharSequence> year_adapter = ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item);

        final Spinner branch_Spinner = findViewById(R.id.BranchSpinner);
        final ArrayAdapter<CharSequence> branch_adapter = ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item);

        final Spinner division_Spinner = findViewById(R.id.DivisionSpinner);
        ArrayAdapter<CharSequence> division_adapter = ArrayAdapter.createFromResource(this,R.array.division,android.R.layout.simple_spinner_item);

        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_Spinner.setAdapter(year_adapter);
        branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch_Spinner.setAdapter(branch_adapter);
        division_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        division_Spinner.setAdapter(division_adapter);

        year_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year[0] = year_Spinner.getSelectedItem().toString();
                entry[0]=1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                entry[0]=0;
            }
        });

        branch_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch[0] = branch_Spinner.getSelectedItem().toString();
                entry[1]=1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                entry[1]=0;
            }
        });

        division_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                division[0] = division_Spinner.getSelectedItem().toString();
                entry[2]=1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                entry[2]=0;
            }


        });

        Button next = findViewById(R.id.NextButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Roll[0] = roll_no.getText().toString();

                    if(Roll[0].equals("") ){
                        Toast.makeText(getApplicationContext(),"Fill All Fields First", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent i = new Intent(StudentInfo.this, PersonalInfo.class);
                        i.putExtra("Roll", Roll[0]);
                        i.putExtra("Branch",branch[0]);
                        i.putExtra("Year",year[0]);
                        i.putExtra("Division",division[0]);
                        startActivity(i);
                    }

            }
        });
    }
}
