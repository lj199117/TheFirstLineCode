package com.example.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlUtil.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    Button create_database = null;
    SQLiteOpenHelper myDatabaseHelper = null;
    SQLiteDatabase sqLiteDatabase = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this,"BookStore.db",null,3);
        create_database = (Button)findViewById(R.id.create_database);
        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
            }
        });

        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                sqLiteDatabase.insert("Book", null, values);
                values.clear();

                values.put("name", "The xxxooo");
                values.put("author", "Dan Brown2");
                values.put("pages", 569);
                values.put("price", 16.96);
                sqLiteDatabase.insert("Book", null, values);
                values.clear();

                values.put("category_name", "Story");
                values.put("category_code", 454);
                sqLiteDatabase.insert("Category", null, values);
                values.clear();
                Toast.makeText(MainActivity.this, "addData succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 136.5);
                sqLiteDatabase.update("Book", values, "author=?", new String[]{"Dan Brown"});
                Toast.makeText(MainActivity.this, "updateData succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                sqLiteDatabase.delete("Book", "pages > ?", new String[]{"500"});
            }
        });

        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
// 查询Book表中所有的数据
                Cursor cursor = sqLiteDatabase.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
// 遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.
                                getColumnIndex("name"));
                        String author = cursor.getString(cursor.
                                getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex
                                ("pages"));
                        double price = cursor.getDouble(cursor.
                                getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        //不用封装的方法 直接用Sql语句
        Button query_data_bySQL = (Button)findViewById(R.id.query_data_bySQL);
        query_data_bySQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.rawQuery("select * from Book where pages > ?",
                        new String[]{"400"});
                if(cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.
                                getColumnIndex("name"));
                        String author = cursor.getString(cursor.
                                getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex
                                ("pages"));
                        double price = cursor.getDouble(cursor.
                                getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    } while (cursor.moveToNext());
                }

                cursor.close();
            }
        });

        Button replaceData = (Button) findViewById(R.id.replace_data);
        assert replaceData != null;
        replaceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                sqLiteDatabase.beginTransaction();//开启事务
                try {
                    sqLiteDatabase.execSQL("delete from Book");
                    if(true)
                        throw new NullPointerException();//手动抛出异常
                    sqLiteDatabase.execSQL(
                            "insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                            new String[] { "hahaha", "Dan Brown", "454", "16.96" });
                    sqLiteDatabase.setTransactionSuccessful();//事务执行成功
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    sqLiteDatabase.endTransaction();//结束事务
                    Cursor cursor = sqLiteDatabase.rawQuery("select * from Book",null);
                    if(cursor.moveToFirst()){
                        do{
                            String name = cursor.getString(cursor.
                                    getColumnIndex("name"));
                            String author = cursor.getString(cursor.
                                    getColumnIndex("author"));
                            int pages = cursor.getInt(cursor.getColumnIndex
                                    ("pages"));
                            double price = cursor.getDouble(cursor.
                                    getColumnIndex("price"));
                            Log.d("MainActivity", "book name is " + name);
                            Log.d("MainActivity", "book author is " + author);
                            Log.d("MainActivity", "book pages is " + pages);
                            Log.d("MainActivity", "book price is " + price);
                        }while(cursor.moveToNext());
                    }
                    cursor.close();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
