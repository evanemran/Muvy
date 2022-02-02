package com.evanemran.muvy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.evanemran.muvy.Models.APIResponse;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class MainActivity extends AppCompatActivity implements OnClickedListener{
    RecyclerView recyclerView_movies;
    SearchView searchView;
    EditText editText_movie_name;
    Button button_search;
    MovieAdapter movieAdapter;
    ProgressBar progressBar;
    ImageView img_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView_movies = findViewById(R.id.recycler_movies);
        searchView = findViewById(R.id.searchView);
        editText_movie_name = findViewById(R.id.editText_movie_name);
        button_search = findViewById(R.id.button_search);
        img_home = findViewById(R.id.img_home);

        progressBar = (ProgressBar)findViewById(R.id.loader);
        Sprite anim = new Wave();
        progressBar.setIndeterminateDrawable(anim);

        RequestManager manager = new RequestManager(this);

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movie = editText_movie_name.getText().toString().trim();
                if (!movie.equals("")){
                    hideSoftKeyboard(MainActivity.this, view);
                    img_home.setVisibility(View.GONE);
                    recyclerView_movies.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    manager.getMovieNames(listener, movie);
                }else{
                    Toast.makeText(MainActivity.this, "Enter a Movie Name!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                manager.getMovieNames(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetched(APIResponse response, String message) {
            progressBar.setVisibility(View.GONE);
            if (response==null){
                img_home.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                img_home.setImageResource(R.drawable.no_results);
                return;
            }
            showResult(response);

        }

        @Override
        public void onError(String message) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showResult(APIResponse response) {
        if (response.getTitles().isEmpty()){
            img_home.setVisibility(View.VISIBLE);
            img_home.setImageResource(R.drawable.no_results);
            return;
        }
        recyclerView_movies.setHasFixedSize(true);
        recyclerView_movies.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter(this, response.getTitles(), this);
        recyclerView_movies.setAdapter(movieAdapter);
        recyclerView_movies.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClicked(String id) {
//        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
        .putExtra("movie_id", id));
    }

    public static void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}