package com.example.qbhcomics.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.qbhcomics.R;
import com.example.qbhcomics.model.Comic;
import com.example.qbhcomics.model.Comic_Adapter;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    List<Comic> ls= new ArrayList<>();
    Comic_Adapter cmAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        toolbar =findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        listView=findViewById(R.id.listView);
        recyclerView=(RecyclerView)findViewById(R.id.rcv_comic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue= Volley.newRequestQueue(this);
        //requestQueue= VolleySingleton.getInstance(this).getRequestQueue();
        loadComic();
        actionBar();
        //hiding the title bar

    }

    private void actionBar() {
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);
        //icon
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.email);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }


    private void loadComic() {
        String url ="https://api.jsonserve.com/nRz5Bp?fbclid=IwAR189_wDhP0tgcDG_kSvv6CsgG9GdzIDrLxeyiQTKaHER1GlQRKpNgLSXyE";
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String author = jsonObject.getString("author");
                        String desc = jsonObject.getString("description");
                        String img = jsonObject.getString("image");
                        String content=jsonObject.getString("contents");

                        Comic comic = new Comic(name, author, img, desc, content);
                        ls.add(comic);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    Comic_Adapter comic_adapter =new Comic_Adapter(Home.this,ls);
                    recyclerView.setAdapter(comic_adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonArrayRequest);


}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to sign out?");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent =new Intent(Home.this, Login.class);
                                startActivity(intent);
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();


                break;
            case R.id.share:
                AlertDialog.Builder alert1 = new AlertDialog.Builder(this);
                alert1.setTitle("Link Github");

                WebView wv = new WebView(this);
                wv.loadUrl("https://github.com/backoi/Android_QBHcomics");
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);

                        return true;
                    }
                });

                alert1.setView(wv);
                alert1.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alert1.show();

                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}