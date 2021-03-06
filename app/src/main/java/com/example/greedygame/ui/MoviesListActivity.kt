package com.example.greedygame.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.greedygame.R
import com.example.greedygame.ui.movies.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesListActivity : AppCompatActivity() {

    private lateinit var frameLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        initaliseFrame()
    }
    fun initaliseFrame()
    {

        frameLayout=findViewById(R.id.frame)
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame, MoviesFragment(), "MovieList")
        ft.commit()
        ft.addToBackStack("MovieList")

    }
}