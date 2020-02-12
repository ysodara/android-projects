package com.sodaray.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private var popularMoviesPage = 1

    private lateinit var topRatedMovies: RecyclerView
    private lateinit var topRatedMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
    private var topRatedMoviesPage = 1

    private lateinit var upcomingMovies: RecyclerView
    private lateinit var upcomingMoviesAdapter: MovieAdapter
    private lateinit var upcomingMoviesLayoutMgr: LinearLayoutManager
    private var upcomingMoviesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        popularMovies = findViewById(R.id.popular_movies)
        popularMoviesLayoutMgr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MovieAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        popularMovies.adapter = popularMoviesAdapter


        getPopularMovies()

        topRatedMovies = findViewById(R.id.top_movies)
        topRatedMoviesLayoutMgr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topRatedMovies.layoutManager = topRatedMoviesLayoutMgr
        topRatedMoviesAdapter = MovieAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        topRatedMovies.adapter = topRatedMoviesAdapter

        getTopRatedMovies()


        upcomingMovies = findViewById(R.id.soon_movies)
        upcomingMoviesLayoutMgr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        upcomingMovies.layoutManager = upcomingMoviesLayoutMgr
        upcomingMoviesAdapter = MovieAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        upcomingMovies.adapter = upcomingMoviesAdapter

        getUpcomingMovies()


    }

    private fun getPopularMovies() {
        MovieRepository.getNowPlayingMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    private fun getTopRatedMovies() {
        MovieRepository.getLatest(
            topRatedMoviesPage,
            ::onTopRatedMoviesFetched,
            ::onError
        )
    }

    private fun getUpcomingMovies() {
        MovieRepository.getUpcoming(
            upcomingMoviesPage,
            ::onUpcomingMoviesFetched,
            ::onError
        )
    }

    private fun attachPopularMoviesOnScrollLister() {
        popularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = popularMoviesLayoutMgr.itemCount
                val visibleItemCount: Int = popularMoviesLayoutMgr.childCount
                val firstVisibleItem: Int = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovies.removeOnScrollListener(this)
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }

    private fun attachTopRatedMoviesOnScrollLister() {
        topRatedMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = topRatedMoviesLayoutMgr.itemCount
                val visibleItemCount: Int = topRatedMoviesLayoutMgr.childCount
                val firstVisibleItem: Int = topRatedMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedMovies.removeOnScrollListener(this)
                    topRatedMoviesPage++
                    getTopRatedMovies()
                }
            }
        })

    }

    private fun attachUpComingMoviesOnScrollLister() {
        upcomingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = upcomingMoviesLayoutMgr.itemCount
                val visibleItemCount: Int = upcomingMoviesLayoutMgr.childCount
                val firstVisibleItem: Int = upcomingMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    upcomingMovies.removeOnScrollListener(this)
                    upcomingMoviesPage++
                    getUpcomingMovies()
                }

            }
        })
    }


    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
        attachPopularMoviesOnScrollLister()
    }

    private fun onTopRatedMoviesFetched(movies: List<Movie>) {
        topRatedMoviesAdapter.appendMovies(movies)
        attachTopRatedMoviesOnScrollLister()
    }

    private fun onUpcomingMoviesFetched(movies: List<Movie>) {
        upcomingMoviesAdapter.appendMovies(movies)
        attachUpComingMoviesOnScrollLister()
    }

    private fun onError() {
        Toast.makeText(this, "please check your internet connection", Toast.LENGTH_SHORT).show()
    }

    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RATING, movie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        startActivity(intent)
    }
}


