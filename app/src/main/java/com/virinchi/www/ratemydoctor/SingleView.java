package com.virinchi.www.ratemydoctor;

/**
 * Created by rohan on 8/9/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.Spinner;
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

/**
 * Created by rohan on 6/14/2016.
 */
public class SingleView extends AppCompatActivity implements View.OnClickListener{
    EditText doc_name, doc_number, doc_hospital;
    EditText doc_speciality;
    Button submitButton;
    private RequestQueue requestQueue1;

    private static final String ADD_DOCTOR_URL = "http://192.168.0.115/doctor_control.php";
    private StringRequest request1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_single_view);

        doc_name = (EditText)findViewById(R.id.doctor_name);
       doc_speciality = (EditText) findViewById(R.id.doctor_speciality);
        doc_hospital = (EditText)findViewById(R.id.doctor_hospital);
        doc_number = (EditText) findViewById(R.id.doctor_number);

        requestQueue1 = Volley.newRequestQueue(this);

        submitButton = (Button)findViewById(R.id.submitbutton2);
        submitButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitbutton2:
                if (doc_name.getText().toString().equals("")) {
                    Toast.makeText(SingleView.this, "Enter A Doctors Name", Toast.LENGTH_SHORT).show();
                } else if (doc_speciality.getText().toString().equals("")) {
                    Toast.makeText(SingleView.this, "Select A Speciality", Toast.LENGTH_SHORT).show();
                } else if (doc_hospital.getText().toString().equals("")) {
                    Toast.makeText(SingleView.this, "Enter A Hospital", Toast.LENGTH_SHORT).show();
                } else if (doc_number.getText().toString().length()!=10) {
                    Toast.makeText(SingleView.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    request1 = new StringRequest(Request.Method.POST, ADD_DOCTOR_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("success")) {
                                    Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), SingleView.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("doctor_name", doc_name.getText().toString());
                            hashMap.put("speciality", doc_speciality.getText().toString());
                            hashMap.put("address", doc_hospital.getText().toString());
                            hashMap.put("doctor_number", doc_number.getText().toString());
                            return hashMap;
                        }
                    };

                    requestQueue1.add(request1);
                }


                break;
        }

    }
}

