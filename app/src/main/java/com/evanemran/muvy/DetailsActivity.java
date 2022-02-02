package com.evanemran.muvy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.evanemran.muvy.Models.DetailsResponse;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    
    TextView textView_movie_detail_name, textView_movie_detail_year, textView_movie_detail_length, textView_movie_detail_rating, textView_movie_detail_votes, textView_movie_detail_plot;
    RecyclerView recycler_cast, recycler_specs;
    ImageView imageView_movie_detail_poster;
    DetailsAdapter detailsAdapter;
    SpecsAdapter specsAdapter;
    ScrollView details_container;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Movie Details");

        textView_movie_detail_name = findViewById(R.id.textView_movie_detail_name);
        textView_movie_detail_year = findViewById(R.id.textView_movie_detail_year);
        textView_movie_detail_length = findViewById(R.id.textView_movie_detail_length);
        textView_movie_detail_rating = findViewById(R.id.textView_movie_detail_rating);
        textView_movie_detail_votes = findViewById(R.id.textView_movie_detail_votes);
        textView_movie_detail_plot = findViewById(R.id.textView_movie_detail_plot);
        recycler_cast = findViewById(R.id.recycler_cast);
        recycler_specs = findViewById(R.id.recycler_specs);
        imageView_movie_detail_poster = findViewById(R.id.imageView_movie_detail_poster);
        details_container = findViewById(R.id.details_container);

        String id = getIntent().getStringExtra("movie_id");

        progressBar = (ProgressBar)findViewById(R.id.loader);
        Sprite anim = new Wave();
        progressBar.setIndeterminateDrawable(anim);
        
        RequestManager manager = new RequestManager(this);
        manager.getMovieDetails(listener, id);
    }

    private final OnFetchDetailListener listener = new OnFetchDetailListener() {
        @Override
        public void onFetched(DetailsResponse response, String message) {
            progressBar.setVisibility(View.GONE);
            if (response==null){
                Toast.makeText(DetailsActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);

        }

        @Override
        public void onError(String message) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(DetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showResult(DetailsResponse response) {
        textView_movie_detail_name.setText(response.getTitle());
        textView_movie_detail_year.setText("Year Released: "+response.getYear());
        textView_movie_detail_length.setText("Duration: "+response.getLength());
        textView_movie_detail_rating.setText(response.getRating());
        textView_movie_detail_votes.setText(response.getRating_votes()+" Votes");
        textView_movie_detail_plot.setText(response.getPlot());

        try{
            Picasso.get().load(response.getPoster()).into(imageView_movie_detail_poster);
        }catch (Exception e){
            e.printStackTrace();
        }

        recycler_cast.setHasFixedSize(true);
        recycler_cast.setLayoutManager(new GridLayoutManager(this, 1));
        detailsAdapter = new DetailsAdapter(this, response.getCast());
        recycler_cast.setAdapter(detailsAdapter);

        recycler_specs.setHasFixedSize(true);
        recycler_specs.setLayoutManager(new GridLayoutManager(this, 1));
        specsAdapter = new SpecsAdapter(this, response.getTechnical_specs());
        recycler_specs.setAdapter(specsAdapter);

        details_container.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}