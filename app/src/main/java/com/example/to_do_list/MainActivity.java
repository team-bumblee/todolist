package com.example.to_do_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText text;
    private Button btn;
    private ListView list;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.Etext);
        btn=findViewById(R.id.submit);
        list=findViewById(R.id.list_view);

        arrayList=filehelper.readData( this);
        adapter = new ArrayAdapter<String>(  this,android.R.layout.simple_list_item_1,arrayList );
        list.setAdapter(adapter);

         btn.setOnClickListener(this);
         list.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
            String itemEntered=text.getText().toString();
            adapter.add(itemEntered);
            text.setText(" ");

            filehelper.writeData(arrayList ,this);

                Toast.makeText(this, "item Added", Toast.LENGTH_SHORT).show();



        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        arrayList.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }
}
