package com.example.movieapp.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.models.MovieResult
import com.example.movieapp.mvvm.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = DetailsFragmentArgs.fromBundle(requireArguments()).id

        binding.videoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            //pass the video url
           // intent.data = Uri.parse("https://api.themoviedb.org/3/movie/${movieId}/videos")
            intent.data = Uri.parse("https://youtu.be/73_1biulkYk?si=LKHhM0qIXDzetG36")
            startActivity(intent)
        }
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

//        // Inside onCreate or onViewCreated
//        moviesViewModel.videoLiveData.observe(viewLifecycleOwner) { videoKey ->
//            if (videoKey != null) {
//                val videoUrl = "https://www.youtube.com/watch?v=$videoKey"
//                binding.videoTextView.setOnClickListener {
//                    val intent = Intent(Intent.ACTION_VIEW)
//                    intent.data = Uri.parse(videoUrl)
//                    intent.setPackage("com.android.chrome") // To open in Chrome
//                    startActivity(intent)
//                }
//            } else {
//                // Handle case where no video is available
//                binding.videoTextView.isEnabled = false
//            }
//        }

        // استدعاء دالة الحصول على تفاصيل الفيلم
        getMovieDetails(movieId)

        // ملاحظة بيانات الفيلم
        observeMovieDetails()
    }

    private fun getMovieDetails(movieId: Int) {
        moviesViewModel.getMovieDetails(movieId)
    }

    private fun observeMovieDetails() {
        moviesViewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer { movieDetails ->
            movieDetails?.let { details ->
                binding.apply {
                    // تحميل صورة البوستر
                    Glide.with(binding.root)
                        .load("https://image.tmdb.org/t/p/w500${details.poster_path}")
                        .into(moviePosterImageView)

                    // تعيين قيم الخصائص
                    releaseDateTextView.text = "Release Date: ${details.release_date ?: "N/A"}"
                    overviewTextView.text = details.overview ?: "Overview not available."
                    overviewTextView.maxLines = 5 // Limit lines for better UI
                    overviewTextView.ellipsize = TextUtils.TruncateAt.END // Add ellipsis for overflow
                    ratingTextView.text = "Rating:⭐   ${details.vote_average?.let { String.format("%.1f", it) } ?: "N/A"}/10"
                    voteCountTextView.text = "Vote Count: ${details.vote_count ?: 0}"
                    budgetTextView.text = "Budget: $${details.budget ?: "N/A"}" // Assuming budget is part of the details
                    genresTextView.text = "Genres: ${details.genres?.joinToString(", ") { genre -> genre.name!! } ?: "N/A"}"
                    originalLanguageTextView.text = "Original Language: ${details.original_language ?: "N/A"}"
                    movieTitleTextView.text = details.title ?: "Title not available."
                    videoButton.text = "Watch Video"
                }
            } ?: run {
                // إذا كانت التفاصيل غير موجودة، يمكنك إظهار رسالة أو التعامل مع الحالة هنا
                Toast.makeText(requireContext(), "Details not found", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
