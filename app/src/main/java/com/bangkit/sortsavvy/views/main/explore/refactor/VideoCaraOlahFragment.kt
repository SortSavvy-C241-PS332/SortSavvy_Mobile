package com.bangkit.sortsavvy.views.main.explore.refactor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.adapter.SectionsCaraOlahPagerAdapter
import com.bangkit.sortsavvy.databinding.FragmentVideoCaraOlahBinding

class VideoCaraOlahFragment : Fragment() {

    private lateinit var binding: FragmentVideoCaraOlahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_cara_olah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideoCaraOlahBinding.bind(view)

        val linkUrlContohCaraOlah: String? = arguments?.getString(SectionsCaraOlahPagerAdapter.EXTRA_LINK_URL_CONTOH_CARA_OLAH)

        if (linkUrlContohCaraOlah != null) {
            loadLinkUrlVideo(linkUrlContohCaraOlah)
        }
    }

    private fun loadLinkUrlVideo(linkUrlContohCaraOlah: String) {
        binding.youtubeWebview.settings.javaScriptEnabled = true

        binding.youtubeWebview.webViewClient = WebViewClient()

        val iframeHtml = """
            <html>
            <body>
            <iframe width="100%" height="100%" src="$linkUrlContohCaraOlah" title="Plant Pots made from Recycled Plastic" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
            </body>
            </html>
        """
        binding.youtubeWebview.loadData(iframeHtml, "text/html", "utf-8")
    }

    companion object {
        fun newInstance() = VideoCaraOlahFragment()
    }
}