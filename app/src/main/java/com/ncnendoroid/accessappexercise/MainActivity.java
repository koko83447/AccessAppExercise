package com.ncnendoroid.accessappexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String response, response_detail,path;
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path = "https://api.github.com/users?since="+page;

        receive();
        my_view();
    }

    private void my_view(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyAdapter myAdapter = new MyAdapter(MainActivity.this);
                myAdapter.tableList(Json.getTable(response));
                RecyclerView recyclerView = findViewById(R.id.recycle_view_main);
                recyclerView.setAdapter(myAdapter);

                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                myAdapter.setListener(new MyAdapter.Listener() {
                    public void onClick(int position) {
                        final Table table = Json.getTable(response).get(position);

                        path = "https://api.github.com/users/"+table.getLogin();
                        receive_detail();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                ArrayList<Table> detailList = new ArrayList<>();
                                detailList.addAll(Json_Detail.getTable(response_detail));

                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("detailData", detailList);
                                bundle.putString("image", table.getAvatar_url());
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }
                        },1000);


                    }
                });
            }
        },2000);
    }

    private void receive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(path);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == 200){
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream())
                        );
                        String inputline = null;
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((inputline = bufferedReader.readLine()) != null){
                            stringBuffer.append(inputline+"\n");
                        }
                        bufferedReader.close();
                        response = stringBuffer.toString();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void receive_detail() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(path);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    if (connection.getResponseCode() == 200){
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream())
                        );
                        String inputline = null;
                        StringBuffer stringBuffer = new StringBuffer();
                        while ((inputline = bufferedReader.readLine()) != null){
                            stringBuffer.append(inputline+"\n");
                        }
                        bufferedReader.close();
                        response_detail = stringBuffer.toString();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void btn_back(View view) {
        if (page == 0) {
            Toast.makeText(MainActivity.this, "First Page", Toast.LENGTH_LONG).show();
            return;
        }
        page -= 20;;
        path = "https://api.github.com/users?since="+page;
        receive();
        my_view();
    }

    public void btn_next(View view) {
        if (page == 80) {
            Toast.makeText(MainActivity.this, "Last Page", Toast.LENGTH_LONG).show();
            return;
        }
        page += 20;;
        path = "https://api.github.com/users?since="+page;
        receive();
        my_view();
    }
}
