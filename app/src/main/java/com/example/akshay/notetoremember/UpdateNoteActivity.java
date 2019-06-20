package com.example.akshay.notetoremember;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class    UpdateNoteActivity extends AppCompatActivity {

    EditText etUp_title, etUp_description;
    Button btnUpdate;
    public int db_version = 1;
    public String db_name = "appdb";
    public String tb_name = "list_notes";
    public String db_id = "id";
    public String db_title = "title";
    public String db_description = "description";
    Helper helper = new Helper(UpdateNoteActivity.this,db_name,null,db_version);
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        etUp_title = findViewById(R.id.etUp_Title);
        etUp_description = findViewById(R.id.etUp_Description);
        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        id = intent.getIntExtra(db_id,0);

        etUp_title.setText(intent.getStringExtra(db_title));
        etUp_description.setText(intent.getStringExtra(db_description));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notes notes = new Notes();
                String title = etUp_title.getText().toString();
                String desc = etUp_description.getText().toString();
                if (!title.isEmpty() && !desc.isEmpty()) {
                    notes.setNote_id(id);
                    notes.setNote_title(title);
                    notes.setNote_description(desc);
                    int status = helper.UpdateList(notes);
                    Log.d("maymay", notes.note_id + notes.getNote_title());
                }
                else {
                    Toast.makeText(getApplicationContext(),"Fill up details",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}
