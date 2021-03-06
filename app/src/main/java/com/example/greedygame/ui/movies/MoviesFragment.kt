package com.example.greedygame.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greedygame.R
import com.example.greedygame.databinding.LayoutMovieListBinding
import com.example.greedygame.model.MovieResponse
import com.example.greedygame.model.MovieResponseData
import com.example.greedygame.model.Status
import com.example.greedygame.paging.MoviesPagingAdapter
import com.example.greedygame.ui.movDetail.MoviesDetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_movie_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MoviesFragment : Fragment(),
    MoviesPagingAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel by viewModels<MoviesViewModel>()
    private var _binding: LayoutMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MoviesPagingAdapter
    private lateinit var viewPagerAdapter: MoviesPagerAdapter
    private lateinit var pagingDataViewPager: PagingData<MovieResponse>
    private lateinit var responseTweet: List<MovieResponse>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LayoutMovieListBinding.bind(view)

        viewModel.getMoviesViewPager()
        setUpViewPager()
        val adapter = MoviesPagingAdapter(this)
        binding.apply {
            movie_list.setHasFixedSize(true)
            movie_list.layoutManager = LinearLayoutManager(context)
            movie_list.itemAnimator = null
            movie_list.adapter = adapter

        }
        viewModel.moviesData.observe(viewLifecycleOwner, Observer {

            adapter.submitData(lifecycle, it)
        })

    }


    private fun setUpViewPager() {

        viewModel.movieViewPagerDetails.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        println("setUpViewPager::Success Call  ${it.data}")
                        setUpViewPagerData(it.data)

                        binding.progressBar.visibility = View.GONE
                        binding.movieList.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        println("MovieDetailsFragment ${it.message}")

                        binding.progressBar.visibility = View.GONE


                    }
                    Status.LOADING -> {

                          binding.progressBar.visibility = View.VISIBLE
                         binding.movieList.visibility = View.GONE
                    }
                }
            }
        })

    }

    private fun setUpViewPagerData(data: MovieResponseData?) {
        val response=data?.movieResponse?.take(3)
        viewPagerAdapter=MoviesPagerAdapter(requireFragmentManager(), response)
        _binding?.viewPager2?.adapter=viewPagerAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpObserver()
    }

    private fun setUpObserver() {
        /*  viewModel.moviesData.observe(viewLifecycleOwner, Observer {

              adapter.submitData(lifecycle,it)
          })*/
        /*viewModel.moviesData.observe(viewLifecycleOwner, Observer {
            println("observerCalled")
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val pagingData = it.data?.value
                        if (pagingData != null) {
                            adapter.submitData(lifecycle, pagingData)
                        }
                        println("Success Call")
                        // tweets_recycler_view.visibility = View.VISIBLE
                        //progress_bar.visibility = View.GONE
                        // error_image.visibility = View.GONE
                        // resource.data?.let { tweets -> retrieveList(tweets) }
                    }
                    Status.ERROR -> {
                        println("TweetFragmentError ${it.message}")
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

        })*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(movieDetail: MovieResponse) {

        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        val bundle = Bundle()
        bundle.putParcelable("moviesDetails", movieDetail)
        val fragment = MoviesDetailsFragment()
        fragment.arguments = bundle
        ft.replace(R.id.frame, fragment, "MovieDetail")
        ft.commit()
        ft.addToBackStack("MovieDetail")
    }
}