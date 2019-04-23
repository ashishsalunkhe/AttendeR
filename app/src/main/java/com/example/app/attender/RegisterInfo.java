package com.example.app.attender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterInfo extends AppCompatActivity {
    //private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);
        Intent g = getIntent();
        final String Roll = g.getStringExtra("Roll");
        final String Year = g.getStringExtra("Year");
        final String Branch = g.getStringExtra("Branch");
        final String Division = g.getStringExtra("Division");
        final String First_Name = g.getStringExtra("First_Name");
        final String Last_Name = g.getStringExtra("Last_Name");
        final String Email_ID = g.getStringExtra("Email_ID");


        final String[] Username = new String[1];
        final String[] Password = new String[1];

        final EditText username = findViewById(R.id.UsernameBox);
        final EditText password = findViewById(R.id.PasswordBox);

        Button Final_Register = findViewById(R.id.Final_Button);
        Final_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username[0] = username.getText().toString();
                Password[0] = password.getText().toString();

                if (Username[0].equals("") || Password[0].equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill All Fields First", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(Roll,First_Name,Last_Name,Year,Branch,Division,Username[0],Email_ID,Password[0]);
                    Intent i = new Intent(RegisterInfo.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });
    }
    private void registerUser(final String rollno, final String fname, final String lname, final String year, final String branch, final String division, final String username, final String email_ID, final String password){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("rollno", rollno);
                params.put("fname", fname);
                params.put("lname", lname);
                params.put("year", year);
                params.put("branch", branch);
                params.put("division", division);
                params.put("username", username);
                params.put("email", email_ID);
                params.put("password", password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}