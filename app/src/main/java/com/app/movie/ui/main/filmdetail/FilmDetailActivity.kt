package com.app.movie.ui.main.filmdetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.movie.BuildConfig
import com.app.movie.R
import com.app.movie.utils.Status
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_film_detail.*

@AndroidEntryPoint
class FilmDetailActivity : AppCompatActivity() {
    companion object  {
        const val MOVIE_ID = "MOVIE_ID"
    }

    private val viewModel : FilmDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        back_film.setOnClickListener {
            onBackPressed()
        }
        intent.getStringExtra(MOVIE_ID)?.let { viewModel.getMovieDetail(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun setupObserver() {
        with(viewModel) {
            filmDetail.observe(this@FilmDetailActivity, {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { data ->
                            title_film.text = data.title
                            Glide.with(this@FilmDetailActivity).load(BuildConfig.URL_IMAGE.plus(data.backdrop_path))
                                .centerCrop()
                                .into(film_background)
                            overview.text = data.overview
                            release_date.text = data.release_date
                            vote_average.text = "Vote average ${data.vote_average} / ${data.vote_count}"
                        }

                    }
                    else -> {}
                }
            })

            filmReview.observe(this@FilmDetailActivity, {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { data ->
                            Glide.with(this@FilmDetailActivity).load(data.author_details.avatar_path.drop(1))
                                .circleCrop()
                                .into(avatar_review)
                            review_name.text = data.author
                            contain_review.text = data.content
                        }
                    }
                    else -> {}
                }
            })
        }
    }
}