package com.example.taxhealassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String URL="https://api.github.com/users/akashsarkar188";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView UserList=(RecyclerView)findViewById(R.id.UserlList);
        UserList.setLayoutManager(new LinearLayoutManager(this));

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new Fragment1());
        fragmentTransaction.commit();

        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("code", response);
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                User users=gson.fromJson(response,User.class);
                UserList.setAdapter(new GithubAdapter(MainActivity.this,users));
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
        }
    });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

    }
}
