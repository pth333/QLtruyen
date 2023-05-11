package com.example.qbhcomics.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.example.qbhcomics.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Details extends AppCompatActivity {
    private Button button;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_details);
        CollapsingToolbarLayout collapsingToolbarLayout =findViewById(R.id.collapsingtoolbar);
        ImageView imageView=findViewById(R.id.img_detail);
        TextView name=findViewById(R.id.name_detail);
        TextView author=findViewById(R.id.author_detail);
        TextView content=findViewById(R.id.desc_detail);

        Bundle bundle =getIntent().getExtras();

        String nameDetail =bundle.getString("name");
        String authorDetail =bundle.getString("author");
        String contentDetail =bundle.getString("content");
        String imgDetail =bundle.getString("img");

        Glide.with(this).load(imgDetail).into(imageView);
        name.setText(nameDetail);
        author.setText(authorDetail);
        content.setText(contentDetail);
        collapsingToolbarLayout.setTitle(nameDetail);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Details.this,Message.class);
                startActivity(intent);
            }
        });

        //loadContent();

    }

//    private void loadContent() {
//        String url ="https://api.jsonserve.com/5qmhQN?fbclid=IwAR2X8uMKWu7wDz71SC4vGD9yqd01jRpgVU44hhB7vc0qGeWxyBWccGoIAFs";
//        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                JSONArray jsonArray =response.getJSONArray("chapter")
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        })
//
//    }
}