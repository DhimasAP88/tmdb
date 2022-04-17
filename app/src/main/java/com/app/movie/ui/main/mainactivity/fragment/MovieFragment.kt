package com.app.movie.ui.main.mainactivity.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.app.movie.BuildConfig
import com.app.movie.R
import com.app.movie.data.model.Film
import com.app.movie.ui.main.filmdetail.FilmDetailActivity
import com.app.movie.ui.main.mainactivity.adapter.FilmAdapter
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private val listFilmTrending = ArrayList<Film>()
    private val listFilmDiscovery = ArrayList<Film>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTrending(listFilm: List<Film>) {
        this.listFilmTrending.addAll(listFilm)
        list_trending.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDiscover(listFilm: List<Film>) {
        listFilmDiscovery.addAll(listFilm)
        list_discovery.adapter?.notifyDataSetChanged()
    }

    fun setDetail(film: Film) {
        title_film.text = film.title
        Glide.with(requireContext()).load(BuildConfig.URL_IMAGE.plus(film.backdrop_path))
            .centerCrop().into(image_film)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmTrendingAdapter = FilmAdapter(listFilmTrending, requireActivity())
        filmTrendingAdapter.setListener(object : FilmAdapter.OnClick{
            override fun onClickFilm(film: Film) {
                openFilmDetail(film)
            }
        })
        list_trending.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = filmTrendingAdapter
        }

        val filmDiscoveryAdapter = FilmAdapter(listFilmDiscovery, requireActivity())
        filmDiscoveryAdapter.setListener(object  : FilmAdapter.OnClick{
            override fun onClickFilm(film: Film) {
                openFilmDetail(film)
            }
        })

        list_discovery.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = filmDiscoveryAdapter
        }
    }

    private fun openFilmDetail(film: Film) {
        val intent = Intent(activity, FilmDetailActivity::class.java)
        intent.putExtra(FilmDetailActivity.MOVIE_ID, film.id)
        startActivity(intent)
    }
}