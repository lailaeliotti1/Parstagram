package com.example.flixster2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster2.databinding.ActivityMovieDetailsBinding;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    // the view objects
    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;
    RatingBar rbVoteAverage;
    TextView tvPopularity;
    ActivityMovieDetailsBinding activityMovieDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieDetailsBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());

        // layout of activity is stored in a special property called root
        View view = activityMovieDetailsBinding.getRoot();
        setContentView(view);

        //setContentView(R.layout.activity_movie_details);
        // resolve the view objects




//        tvTitle = (TextView) findViewById(R.id.tvTitle);
//        tvOverview = (TextView) findViewById(R.id.tvOverview);
//        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
//        ivPoster = (ImageView) findViewById((R.id.ivPoster));
//        tvPopularity = (TextView) findViewById(R.id.tvPopularity);


        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        activityMovieDetailsBinding.tvTitle.setText(movie.getTitle());
        activityMovieDetailsBinding.tvOverview.setText(movie.getOverview());

        // set the title and overview
//        tvTitle.setText(movie.getTitle());
//        tvOverview.setText(movie.getOverview());
        Glide.with(this).load(movie.getBackdropPath()).placeholder(R.drawable.flicks_movie_placeholder).into(activityMovieDetailsBinding.ivPoster);


        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        activityMovieDetailsBinding.rbVoteAverage.setRating(voteAverage / 2.0f);

        Double pop = movie.getPopularity();
        activityMovieDetailsBinding.tvPopularity.setText("Popularity: " + pop);
    }
}