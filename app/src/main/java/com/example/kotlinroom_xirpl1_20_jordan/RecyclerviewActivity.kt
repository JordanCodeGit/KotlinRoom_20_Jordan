package com.example.kotlinroom_xirpl1_20_jordan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinroom_xirpl1_20_jordan.room.Constant
import com.example.kotlinroom_xirpl1_20_jordan.room.Movie
import com.example.kotlinroom_xirpl1_20_jordan.room.MovieDB
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.recyclerview.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerviewActivity : AppCompatActivity() {

    val db by lazy { MovieDB (this) }
    lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.recyclerview)
        setupListener()
        setupRecyclerView()

        val intentButton: ImageView = findViewById(R.id.leftarrow)
        intentButton.setOnClickListener { viewDetail() }
    }
    private fun viewDetail() {
        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
    }

    override fun onStart(){
        super.onStart()
        loadMovie()
            }

    fun loadMovie() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.movieDao().getMovies()
            Log.d("RecyclerviewActivity", "dbresponse: $movies")
            withContext(Dispatchers.Main) {
                movieAdapter.setData(movies)
            }
        }
    }
    fun setupListener(){
        add_movie.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(movieId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddActivity::class.java)
                .putExtra("intent_id", movieId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun setupRecyclerView(){
        movieAdapter = MovieAdapter(arrayListOf(), object : MovieAdapter.OnAdapterListener{
            override fun onClick(movies: Movie) {
                // Read Detail
                intentEdit(movies.id, Constant.TYPE_READ)
            }

            override fun onUpdate(movies: Movie) {
                intentEdit(movies.id, Constant.TYPE_UPDATE)
            }

            override fun onDelete(movies: Movie) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.movieDao().deleteMovie(movies)
                    loadMovie()
                }
            }
        })
        rv_movie.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}