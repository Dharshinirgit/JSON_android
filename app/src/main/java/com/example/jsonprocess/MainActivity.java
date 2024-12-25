package com.example.jsonprocess;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ListView listview;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listview=(ListView) findViewById(R.id.list);
        button=(Button) findViewById(R.id.btn);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this,)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonparse();
            }
        });
    }
    public void jsonparse()
    {
        try {
            JSONObject obj=new JSONObject(loadjson());
            JSONArray array=obj.getJSONArray("data");
            System.out.print("arr:"+array);
            int jLimit = array.length();
            Toast.makeText(this, "ABCD "+jLimit, Toast.LENGTH_SHORT).show();
            for(int i=0;i<jLimit;i++)
            {
                JSONObject data=array.getJSONObject(i);
                Toast.makeText(this, data.getString("Name"), Toast.LENGTH_SHORT).show();
                int id=data.getInt("id");
                String name=data.getString("Name");
                String email=data.getString("Email");
                //adapter.add("Test "+i);
                adapter.add("id:"+id+"\n"+"name:"+name+"\n"+"email:"+email);
            }
            listview.setAdapter(adapter);
        }
        catch (JSONException e)
        {
            Toast.makeText(MainActivity.this,"arr:"+e,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
    public String loadjson()
    {
        String json=null;
        try {
            InputStream stream=getAssets().open("myfile.json");
            int size=stream.available();
            byte[] byte_arr=new byte[size];
            stream.read(byte_arr);
            stream.close();
            json=new String(byte_arr,"UTF-8");
        }
        catch (IOException e)
        {
            Toast.makeText(MainActivity.this,"arr:"+e,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return json;
    }
}