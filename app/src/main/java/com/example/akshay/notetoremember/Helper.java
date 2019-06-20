package com.example.akshay.notetoremember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Helper extends SQLiteOpenHelper{

    public int db_version = 1;
    public String db_name = "appdb";
    public String tb_name = "list_notes";
    public String db_id = "id";
    public String db_title = "title";
    public String db_description = "description";

    public Helper(Context context, String db_name, SQLiteDatabase.CursorFactory factory, int db_version) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table table_name (id primary key autoincrement, title varchar(250), description varchar(250))

        String que_create = "create table "+tb_name+" ("+db_id+" integer primary key, "+db_title+" text, "+db_description+" text);";
        sqLiteDatabase.execSQL(que_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+tb_name);
        onCreate(sqLiteDatabase);
    }

    public void AddList(Notes notes){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(db_title,notes.getNote_title());
        values.put(db_description,notes.getNote_description());
        db.insert(tb_name,null,values);
        db.close();
    }

    public int UpdateList(Notes notes){
        Log.d("maymay","called"+notes.getNote_title());

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(db_title,notes.getNote_title());
        values.put(db_description,notes.getNote_description());


        int n=database.update(tb_name,values,db_id+" =?",new String[]{String.valueOf(notes.getNote_id())});
        return n;
    }

    public void DeleteList(Notes notes){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_name,db_id+" =?",new String[]{String.valueOf(notes.getNote_id())});
        db.close();
    }

    public List<Notes> ReadWholeList(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Notes> nList = new ArrayList<>();
        String que_select = "select * from "+tb_name;
        Cursor cursor = db.rawQuery(que_select,null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Notes notes = new Notes();
                notes.setNote_id(Integer.parseInt(cursor.getString(0)));
                notes.setNote_title(cursor.getString(1));
                notes.setNote_description(cursor.getString(2));

                nList.add(notes);

            } while (cursor.moveToNext());
        }
        return nList;
    }
}
