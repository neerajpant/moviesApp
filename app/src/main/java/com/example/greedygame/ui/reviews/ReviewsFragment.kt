package com.example.greedygame.ui.reviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greedygame.R
import com.example.greedygame.model.ReviewResponse
import com.example.greedygame.model.Status
import com.example.greedygame.ui.movDetail.MovieDetailViewModel
import com.example.greedygame.ui.movDetail.ProductionCompanyAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_details.*
import kotlinx.android.synthetic.main.fragment_reviews.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ReviewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel by viewModels<MovieDetailViewModel>()
    private lateinit var adapter: ReviewsAdapter
    private var movieId: Int? = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            movieId = it.getInt("movieId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getReviewDetails(movieId)
        adapter = ReviewsAdapter(arrayListOf())
        recycle_review.setHasFixedSize(true)
        recycle_review.layoutManager = LinearLayoutManager(context)
        recycle_review.adapter = adapter

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.reviewDetails.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        println("Success Call  ${it.data}")
                       // initView(it.data)
                        // tweets_recycler_view.visibility = View.VISIBLE
                        //progress_bar.visibility = View.GONE
                        // error_image.visibility = View.GONE
                         resource.data?.let {
                                 reviews -> retrieveList(it.data) }

                    }
                    Status.ERROR -> {
                        println("MovieDetailsFragment ${it.message}")
                        //  tweets_recycler_view.visibility = View.VISIBLE
                        // progress_bar.visibility = View.GONE
                        //error_image.visibility = View.VISIBLE
                        ///someFunction

                    }
                    Status.LOADING -> {

                        //  progress_bar.visibility = View.VISIBLE
                        // tweets_recycler_view.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(data: List<ReviewResponse>?) {
        adapter.apply {
            addResult(data)
            notifyDataSetChanged()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReviewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}