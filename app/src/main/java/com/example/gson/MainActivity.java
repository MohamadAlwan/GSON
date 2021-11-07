package com.example.gson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    public static final String DATA = "DATA";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupSharedPrefs();
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    public void btnOnClickSave(View view) {
        Book[] books = new Book[2];
        books[0]=new Book("java script","john");
        books[1]=new Book("c#","mark");


        Gson gson = new Gson();
        String booksString = gson.toJson(books); // حولي الاري اوف اوبجيكتس (books) ل JSON String

        editor.putString(DATA,booksString);
        editor.commit();

        Toast.makeText(this,"Data Saved Successfully: \n" + booksString,Toast.LENGTH_SHORT).show();

    }

    public void btnOnClickRead(View view) {
      Gson gson = new Gson();
      String str = prefs.getString(DATA,"");
      if (!str.equals("")){
          Book [] books = gson.fromJson(str,Book[].class); //Book[].class عشان يعرف انو الاوبجيكت اللي بيشتغل علي حكتلو هيك
          Toast.makeText(this,"Number of books" + books.length
                  ,Toast.LENGTH_SHORT).show();

      }
      else{
          Toast.makeText(this,"Data Not Founded "
                  ,Toast.LENGTH_SHORT).show();
      }
    }
}