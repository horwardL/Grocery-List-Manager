package edu.qc.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

//add item by searching type
public class AdditemByType extends AppCompatActivity {

    private ArrayList<String> itemTypes = null;
    private int glposition =  -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem_by_type);
        ListView listView = findViewById(R.id.typeview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Item Type");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        glposition = intent.getIntExtra("glposition", -1);
        GLMDatabase db = GLMDatabase.getInstance(MainActivity.context);
        itemTypes = db.getTypes();
        CustAdapter typeAdapter = new CustAdapter(this, android.R.layout.simple_list_item_1, itemTypes);
        listView.setAdapter(typeAdapter);
        //if type selected list it's items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdditemByType.this, Viewname_class.class);
                intent.putExtra("position", position);
                intent.putExtra("itemType", itemTypes.get(position));
                intent.putExtra("glposition", glposition);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    private class CustAdapter extends ArrayAdapter<String> {
        public CustAdapter(Context context, int textViewResourceId, ArrayList<String> itemTypes) {
            super(context, textViewResourceId, itemTypes);
        }
    }
}
