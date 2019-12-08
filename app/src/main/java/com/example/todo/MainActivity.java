package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    EditText editText;
    Button button;
    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    public static final String FILENAME="ListInfo.data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        items=readData(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.text1);
        button=findViewById(R.id.btn);
        listView=findViewById(R.id.List);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        button.setOnClickListener(this);
        listView.setOnItemLongClickListener(this);


    }

    private ArrayList<String> readData(Context context) {
        ArrayList<String> itemsList=null;
        try {
            FileInputStream fileInputStream=context.openFileInput(FILENAME);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            itemsList=(ArrayList<String>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                String text=editText.getText().toString();
                items.add(text);
                adapter.notifyDataSetChanged();
                editText.setText("");
                WriteData(this,items);
                Toast.makeText(this, "New Item Added", Toast.LENGTH_SHORT).show();
        }
    }

    private void WriteData(Context context, ArrayList<String> itemsAdd) {
        try{
            FileOutputStream fileOutputStream=context.openFileOutput(FILENAME,Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(itemsAdd);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        WriteData(this,items);
        Toast.makeText(this,"The Item Has Been Deleted",Toast.LENGTH_LONG).show();
        return true;
    }

}
