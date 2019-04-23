package com.example.app.attender;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Scan_Start extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    String subject,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan__start);

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                Scan_Start.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       //Toast.makeText(Scan_Start.this, result.getText(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject reader = new JSONObject(result.getText());
                            subject = reader.getString("subject");
                            time = reader.getString("time");
                            //Toast.makeText(Scan_Start.this, time, Toast.LENGTH_SHORT).show();
                           // Toast.makeText(Scan_Start.this, subject, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_AT,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("username", SharedPrefManager.getInstance(Scan_Start.this).getName());
                                params.put("subject", subject);
                                params.put("time", time);
                                return params;
                            }
                        };

                        RequestHandler.getInstance(Scan_Start.this).addToRequestQueue(stringRequest);

                        Intent i = new Intent(Scan_Start.this,QRScanner.class);
                        startActivity(i);
                        finish();

                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}