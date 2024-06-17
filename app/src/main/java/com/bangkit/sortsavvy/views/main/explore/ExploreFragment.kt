package com.bangkit.sortsavvy.views.main.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bangkit.sortsavvy.R

class ExploreFragment : Fragment() {

    private val viewModel: ExploreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        viewModel.exploreData.observe(viewLifecycleOwner, Observer { exploreData ->
            if (exploreData != null && exploreData.size == 4) {
                // Pengenalan Card
                val pengenalanCardView = view.findViewById<View>(R.id.pengenalanCardInclude)
                val pengenalanTitle = pengenalanCardView.findViewById<TextView>(R.id.itemTitleObjectTextView)
                val pengenalanDescription = pengenalanCardView.findViewById<TextView>(R.id.itemDescriptionObjectTextView)
                val pengenalanImage = pengenalanCardView.findViewById<ImageView>(R.id.thumbnailImageView)
                pengenalanTitle.text = exploreData[0].title
                pengenalanDescription.text = exploreData[0].description
                pengenalanImage.setImageResource(exploreData[0].exploreImage)

                // Organik Card
                val organikCardView = view.findViewById<View>(R.id.organikCardInclude)
                val organikTitle = organikCardView.findViewById<TextView>(R.id.itemTitleObjectTextView)
                val organikDescription = organikCardView.findViewById<TextView>(R.id.itemDescriptionObjectTextView)
                val organikImage = organikCardView.findViewById<ImageView>(R.id.thumbnailImageView)
                organikTitle.text = exploreData[1].title
                organikDescription.text = exploreData[1].description
                organikImage.setImageResource(exploreData[1].exploreImage)

                // Anorganik Card
                val anorganikCardView = view.findViewById<View>(R.id.anorganikCardInclude)
                val anorganikTitle = anorganikCardView.findViewById<TextView>(R.id.itemTitleObjectTextView)
                val anorganikDescription = anorganikCardView.findViewById<TextView>(R.id.itemDescriptionObjectTextView)
                val anorganikImage = anorganikCardView.findViewById<ImageView>(R.id.thumbnailImageView)
                anorganikTitle.text = exploreData[2].title
                anorganikDescription.text = exploreData[2].description
                anorganikImage.setImageResource(exploreData[2].exploreImage)

                // Penanganan Card
                val penangananCardView = view.findViewById<View>(R.id.penangananCardInclude)
                val penangananTitle = penangananCardView.findViewById<TextView>(R.id.itemTitleObjectTextView)
                val penangananDescription = penangananCardView.findViewById<TextView>(R.id.itemDescriptionObjectTextView)
                val penangananImage = penangananCardView.findViewById<ImageView>(R.id.thumbnailImageView)
                penangananTitle.text = exploreData[3].title
                penangananDescription.text = exploreData[3].description
                penangananImage.setImageResource(exploreData[3].exploreImage)
            }
        })

        return view
    }

    companion object {
        fun newInstance() = ExploreFragment()
    }
}
