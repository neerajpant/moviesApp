package com.example.greedygame.ui.movDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greedygame.R
import com.example.greedygame.databinding.FragmentMoviesDetailsBinding
import com.example.greedygame.databinding.LayoutMovieListBinding
import com.example.greedygame.model.MovieDetailResponse
import com.example.greedygame.model.MovieResponse
import com.example.greedygame.model.MovieResponseData
import com.example.greedygame.model.Status
import com.example.greedygame.ui.movies.MoviesViewModel
import com.example.greedygame.ui.reviews.ReviewsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_details.*
import kotlinx.android.synthetic.main.layout_movies.*
import kotlinx.android.synthetic.main.layout_movies.recycle_productCompany
import kotlinx.android.synthetic.main.layout_movies.recycle_similarmovie

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MoviesDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var movieDetails:MovieResponse? =null
    private var movieId:Int? =-1
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MovieDetailViewModel>()
 private lateinit var adapter:ProductionCompanyAdapter
    private lateinit var similarrAdapter:ProductionCompanyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            movieDetails =it.getParcelable("moviesDetails")
             movieId=movieDetails?.id


        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesDetailsBinding.bind(view)
        viewModel.getMoviesDetailsTest(movieId)

    }
    private fun initView( movieResponseData: MovieDetailResponse?)
    {

        binding.textTitle.text=movieResponseData?.title
        binding.textPop.text=movieResponseData?.popularity.toString()
        binding.textVote.text=movieResponseData?.vote_count.toString()
        binding.textLanguage.text=movieResponseData?.original_language.toString()
        binding.textReleaseDate.text=movieResponseData?.release_date.toString()
        binding.textReleaseStatus.text=movieResponseData?.status.toString()
        binding.textOverViewDetail.text=movieResponseData?.overview.toString()

      val list=  movieResponseData?.production_companies?.toList()
        println("list size ${list!!.size}")
       val productionCompanyList= movieResponseData.let {
            it?.production_companies
        }
        println("MoviesDetailsFragment::size ${productionCompanyList!!.size}")
        adapter = if(!productionCompanyList.isNullOrEmpty()) {
            ProductionCompanyAdapter(list!!)
        } else {
            ProductionCompanyAdapter(emptyList())
        }
        recycle_productCompany.setHasFixedSize(true)
        recycle_productCompany.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)

        recycle_productCompany.adapter = adapter
        similarrAdapter = if(!productionCompanyList.isNullOrEmpty()) {
            ProductionCompanyAdapter(list!!)
        } else {
            ProductionCompanyAdapter(emptyList())
        }
        recycle_similarmovie.setHasFixedSize(true)
        recycle_similarmovie.layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)

        recycle_similarmovie.adapter = adapter


        _binding!!.review.setOnClickListener {
            transcateFragment(movieId)
        }
    }
    private fun transcateFragment(movieId:Int?)
    {
        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        val bundle =Bundle()
        bundle.putInt("movieId",movieId!!)
        val fragment=ReviewsFragment()
        fragment.arguments=bundle
        ft.replace(R.id.frame, fragment, "reviews")
        ft.commit()
        ft.addToBackStack("reviews")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel.getMoviesDetails(movieId)
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        println("Success Call  ${it.data?.status}")
                        initView(it.data)
                        // tweets_recycler_view.visibility = View.VISIBLE
                        //progress_bar.visibility = View.GONE
                        // error_image.visibility = View.GONE
                        // resource.data?.let { tweets -> retrieveList(tweets) }
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoviesDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}