package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listView);

        // Sample data
//        List<CustomItem> itemList = new ArrayList<>();
//        itemList.add(new CustomItem("Item 1"));
//        itemList.add(new CustomItem("Item 2"));

    }

    @Override
    protected void onStart() {
        super.onStart();
        setListView();
    }

    public  void setListView(){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<CustomItem> itemList = dbHelper.getAllData();
        // Add more items as needed
        adapter = new CustomAdapter(this, itemList);
        listView.setAdapter(adapter);
    }

    public class CustomAdapter extends BaseAdapter {
        private Context context;
        private List<CustomItem> itemList;

        public CustomAdapter(Context context, List<CustomItem> itemList) {
            this.context = context;
            this.itemList = itemList;
        }

        @Override
        public CharSequence[] getAutofillOptions() {
            return super.getAutofillOptions();
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.custom_list_item, parent, false);
            }

            final CustomItem currentItem = (CustomItem) getItem(position);

            TextView textViewName = convertView.findViewById(R.id.name);
            TextView textViewAge = convertView.findViewById(R.id.age);
            Button deletBtn = convertView.findViewById(R.id.delete);
            Button updateBtn = convertView.findViewById(R.id.edit);
            TextView id = convertView.findViewById(R.id.id);

            textViewName.setText(currentItem.getName());
            textViewAge.setText(String.valueOf(currentItem.getAge()));
            id.setText(String.valueOf(position+1));

            convertView.setOnClickListener( View -> {
                        // Show ID in a Toast message
                        Toast.makeText(context, "Clicked item ID: " + position, Toast.LENGTH_SHORT).show();
                    }
            );

            updateBtn.setOnClickListener(View -> {
                Intent i = new Intent(context,UpdateActivity.class);
                i.putExtra("id",String.valueOf(currentItem.getId()));
                i.putExtra("name",currentItem.getName());
                i.putExtra("age",String.valueOf(currentItem.getAge()));
                startActivity(i);
            });

            deletBtn.setOnClickListener(View ->{
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                dbHelper.deleteData(currentItem.getId());
                setListView();
            });

            return convertView;
        }

    }

}