package com.example.akshay.notetoremember;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Notes> notesList;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    public int db_version = 1;
    public String db_name = "appdb";
    public String tb_name = "list_notes";
    public String db_id = "id";
    public String db_title = "title";
    public String db_description = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        displayData();

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Notes notes = notesList.get(i);
                String title = notes.getNote_title();
                String desc = notes.getNote_description();

                Intent intent = new Intent(MainActivity.this,DisplayNoteActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("desc",desc);
                startActivity(intent);

                //overridePendingTransition(R.anim.slide_in_out,R.anim.slide_in_out);  entry and exit animation
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_note){
            Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void displayData() {
        arrayList.clear();
        ListAdapter adapter;
        Helper helper = new Helper(getApplicationContext(), db_name, null, db_version);
        notesList = helper.ReadWholeList();

        for (Notes n : notesList) {

            HashMap<String, String> map = new HashMap<>();
            map.put(db_title, n.getNote_title());
            map.put(db_description, n.getNote_description());
            arrayList.add(map);
            adapter = new SimpleAdapter(MainActivity.this, arrayList, R.layout.custom_notes_list, new String[]{db_title, db_description}, new int[]{R.id.tvTitle, R.id.tvDescription});
            listView.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose an Operation");
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int selected_pos = info.position;
        final Notes notes = notesList.get(selected_pos);
        switch (item.getItemId()){
            case R.id.update_note:
                Intent intent = new Intent(MainActivity.this,UpdateNoteActivity.class);
                intent.putExtra(db_id,notes.getNote_id());
                Toast.makeText(this, " "+notes.getNote_id(), Toast.LENGTH_SHORT).show();
                intent.putExtra(db_title,notes.getNote_title());
                intent.putExtra(db_description,notes.getNote_description());
                startActivity(intent);
                break;

            case R.id.delete_note:
                Helper helper = new Helper(MainActivity.this,db_name,null,db_version);
                helper.DeleteList(notes);
                displayData();
                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

}

//splash activity //////////////////////////////////////////////////////////////////////////////