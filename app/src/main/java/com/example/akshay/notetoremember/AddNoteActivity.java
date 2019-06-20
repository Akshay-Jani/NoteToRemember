package com.example.akshay.notetoremember;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    EditText etTitle, etDescription;
    Button btnAdd;
    Helper helper = new Helper(AddNoteActivity.this,"appdb",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ntitle = etTitle.getText().toString();
                String ndesc = etDescription.getText().toString();
                if (!ntitle.isEmpty() && !ndesc.isEmpty()) {
                    helper.AddList(new Notes(ntitle, ndesc));
                    Toast.makeText(AddNoteActivity.this,"Added",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Fill up details",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}
