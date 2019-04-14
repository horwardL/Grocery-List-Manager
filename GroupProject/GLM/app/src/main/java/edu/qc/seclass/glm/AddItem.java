package edu.qc.seclass.glm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {
    Button button;
    EditText quantity;
    TextView name;
    Item_list item;
    int glposition, position;
    GLMDatabase db;
    double que;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Add Item to List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = GLMDatabase.getInstance(MainActivity.context);
        glposition = getIntent().getIntExtra("glposition", -1);
        position = getIntent().getIntExtra("position", -1);
        item = Viewname_class.itemList.get(position);
        button = (Button)findViewById(R.id.addtolist);
        name = (TextView) findViewById(R.id.name);
        name.setText(item.getName());
        quantity = (EditText)findViewById(R.id.quantity);
        quantity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter quantity", Toast.LENGTH_LONG).show();
                }else {
                    que = Double.parseDouble(quantity.getText().toString());
                    item.setQuant(que);
                    db.addItem(item, glposition);
                    // When clicked, jump back to list manager
                    finish();
                }
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
}