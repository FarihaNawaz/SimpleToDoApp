package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.text);
        button=(Button)findViewById(R.id.btn);
        listView=(ListView)findViewById(R.id.List);
        items=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
    }
    //adding the items
    public void AddToDoItem(View view) {
        adapter.add(editText.getText().toString());
        editText.setText("");
    }
    //for removing the items
    private void setUpListViewListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
