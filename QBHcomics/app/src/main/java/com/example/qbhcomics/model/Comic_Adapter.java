package com.example.qbhcomics.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.qbhcomics.R;
import com.example.qbhcomics.UI.Details;

import java.util.List;

public class Comic_Adapter extends RecyclerView.Adapter<Comic_Adapter.ComicHolder> {
    private Context context;
    private List<Comic> comicList;
    public Comic_Adapter(Context context,List<Comic> list){
        this.context=context;
        this.comicList=list;
    }
    @NonNull
    @Override
    public ComicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comics,parent, false);

        return new ComicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicHolder holder, int position) {
        Comic comic =comicList.get(position);

        holder.name.setText(comic.getName());
        holder.author.setText(comic.getAuthor());
        holder.desc.setText(comic.getDescription());
        holder.content.setText(comic.getDescription());
        Glide.with(context).load(comic.getImage()).into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Details.class);

                Bundle bundle=new Bundle();
                bundle.putString("name",comic.getName());
                bundle.putString("author",comic.getAuthor());
                bundle.putString("desc",comic.getDescription());
                bundle.putString("img",comic.getImage());
                bundle.putString("content",comic.getContent());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public static class ComicHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,author,desc,content;
        LinearLayout linearLayout;
         public ComicHolder(@NonNull View itemView) {
             super(itemView);

             imageView=itemView.findViewById(R.id.imageview);
             name=itemView.findViewById(R.id.name);
             author=itemView.findViewById(R.id.author);
             desc=itemView.findViewById(R.id.desc);
             content=itemView.findViewById(R.id.content);
             linearLayout=itemView.findViewById(R.id.container);
         }
     }

}
