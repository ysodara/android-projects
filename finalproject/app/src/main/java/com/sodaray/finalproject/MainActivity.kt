package com.sodaray.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var popularTvShows: RecyclerView
    private lateinit var popularTvShowsAdapter: TvShowAdapter
    private lateinit var popularTvShowsLayoutMgr: LinearLayoutManager

    private var popularTvShowsPage = 1

    private var btnLogOut: Button? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.my_profile){
            val intent = Intent(this, showProfile::class.java)

            startActivity(intent)
        } else if (item.itemId == R.id.tv){
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        } else {
            mAuth = FirebaseAuth.getInstance()
            logoutUser()
        }
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        popularTvShows = findViewById(R.id.popular_tv_shows)
        popularTvShowsLayoutMgr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        popularTvShows.layoutManager = popularTvShowsLayoutMgr
        popularTvShowsAdapter = TvShowAdapter(mutableListOf()) { tvshow -> showTvShowDetails(tvshow)}
        popularTvShows.adapter = popularTvShowsAdapter

        getPopularTvShows()

        btnLogOut = findViewById<View>(R.id.btn_logout) as Button
        btnLogOut!!.setOnClickListener { logoutUser() }
        mAuth = FirebaseAuth.getInstance()
    }

    private fun logoutUser()
    {
        mAuth!!.signOut()
        updateUI()

    }

    private fun updateUI() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun getPopularTvShows() {
        TvShowRepository.getPopularTvShows(
            popularTvShowsPage,
            onSuccess = ::onPopularTvShowsFetched,
            onError = ::onError
        )
    }

    private fun onPopularTvShowsFetched(tvShow: List<TvShow>) {
        popularTvShowsAdapter.appendTvShows(tvShow)
        attachPopularTvShowsOnScrollListener()
    }
    private fun attachPopularTvShowsOnScrollListener(){
        popularTvShows.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView,dx:Int, dy: Int){
                val totalItemCount = popularTvShowsLayoutMgr.itemCount
                val visibleItemCount = popularTvShowsLayoutMgr.childCount
                val firstVisbleItem = popularTvShowsLayoutMgr.findFirstCompletelyVisibleItemPosition()

                if(firstVisbleItem + visibleItemCount >= totalItemCount / 2){
                    popularTvShows.removeOnScrollListener(this)
                    popularTvShowsPage++
                    getPopularTvShows()
                }
            }
        })
    }
    private fun showTvShowDetails(tvshow: TvShow) {
        val intent = Intent(this, TvShowsDetailsActivity::class.java)
        intent.putExtra(TV_SHOW_BACKDROP, tvshow.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, tvshow.posterPath)
        intent.putExtra(TV_SHOW_TITLE, tvshow.title)
        intent.putExtra(TV_SHOW_RATING, tvshow.rating)
        intent.putExtra(TV_SHOW_EPISODE_COUNT, tvshow.episodeCount)
        intent.putExtra(TV_SHOW_OVERVIEW, tvshow.overview)
        startActivity(intent)
    }
    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_tv_shows), Toast.LENGTH_SHORT).show()
    }
}
