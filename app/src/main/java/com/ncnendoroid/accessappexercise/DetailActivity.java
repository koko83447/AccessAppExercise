package com.ncnendoroid.accessappexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {

    Table table;
    ImageView detail_image;
    TextView detail_name,detail_bio,detail_login,detail_staff,detail_location,detail_blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ArrayList<Table> table_array = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        table_array = (ArrayList<Table>) bundle.getSerializable("detailData");
        table = table_array.get(0);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getIntent().getExtras();
                Picasso.get().load(bundle.getString("image")).transform(new CircleTransform()).into(detail_image);
            }
        },1000);
    }

    private void init() {
        detail_image = findViewById(R.id.detail_image);
        detail_name = findViewById(R.id.detail_name);
        detail_bio = findViewById(R.id.detail_bio);
        detail_login = findViewById(R.id.detail_login);
        detail_staff = findViewById(R.id.detail_staff);
        detail_location = findViewById(R.id.detail_location);
        detail_blog = findViewById(R.id.detail_blog);

        detail_name.setText(table.getName());
        detail_bio.setText(table.getBio());
        detail_login.setText(table.getLogin());
        detail_location.setText(table.getLocation());
        detail_blog.setText(table.getBlog());
        if (table.isSite_admin()){
            detail_staff.setVisibility(View.VISIBLE);
        }else {
            detail_staff.setVisibility(View.GONE);
        }
    }
}
