package com.example.app.attender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        Intent g = getIntent();
        final String Roll = g.getStringExtra("Roll");
        final String Branch = g.getStringExtra("Branch");
        final String Year = g.getStringExtra("Year");
        final String Division = g.getStringExtra("Division");

        final String[] First_name = new String[1];
        final String[] Last_name = new String[1];
        final String[] Email_Id = new String[1];

        Button Register = findViewById(R.id.Registration_Button);

        final EditText F_name = findViewById(R.id.FirstName);
        final EditText L_name = findViewById(R.id.LastName);
        final EditText E_id = findViewById(R.id.EmailText);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                First_name[0] = F_name.getText().toString();
                Last_name[0] = L_name.getText().toString();
                Email_Id[0] = E_id.getText().toString();

                if(First_name[0].equals("") || Last_name[0].equals("") || Email_Id[0].equals("")) {
                    Toast.makeText(getApplicationContext(),"Fill All Fields First", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(PersonalInfo.this,RegisterInfo.class);
                    i.putExtra("Roll",Roll);
                    i.putExtra("Branch",Branch);
                    i.putExtra("Year", Year);
                    i.putExtra("Division", Division);
                    i.putExtra("First_Name", First_name[0]);
                    i.putExtra("Last_Name", Last_name[0]);
                    i.putExtra("Email_ID", Email_Id[0]);
                    startActivity(i);
                }

            }
        });

    }
}
