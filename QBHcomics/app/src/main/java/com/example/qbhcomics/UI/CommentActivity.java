package com.example.qbhcomics.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qbhcomics.R;
import com.example.qbhcomics.model.Comment;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    private ListView commentListView;
    private EditText nameEditText, contentEditText;
    private Button commentButton;
    private ArrayList<Comment> commentList;
    private ArrayAdapter<Comment> commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);

        commentListView = findViewById(R.id.comment_listview);
        nameEditText = findViewById(R.id.comment_name);
        contentEditText = findViewById(R.id.comment_content);
        commentButton = findViewById(R.id.comment_button);

        commentList = new ArrayList<>();
        commentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentList);

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String content = contentEditText.getText().toString();

                if (!name.isEmpty() && !content.isEmpty()) {
                    Comment comment = new Comment(name,content);
                    commentList.add(comment);
                    commentListView.setAdapter(commentAdapter);
                    commentAdapter.notifyDataSetChanged();
                }
                Toast.makeText(CommentActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

