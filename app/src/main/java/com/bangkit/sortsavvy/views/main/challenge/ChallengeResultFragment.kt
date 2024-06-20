package com.bangkit.sortsavvy.views.main.challenge

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.UserModel
import com.bangkit.sortsavvy.databinding.FragmentChallengeResultBinding
import com.bangkit.sortsavvy.views.main.profile.ProfileFragment

class ChallengeResultFragment : Fragment() {

    private lateinit var binding: FragmentChallengeResultBinding
    private val viewModel: ChallengeResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_challenge_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChallengeResultBinding.bind(view)

        val titleChallenge: String? = arguments?.getString(ChallengeFragment.EXTRA_CHALLENGE_TITLE)
        val userScore: Int? = arguments?.getInt(ChallengeFragment.EXTRA_USER_SCORE)
        val totalQuestions: Int? = arguments?.getInt(ChallengeFragment.EXTRA_TOTAL_QUESTIONS)

        if (titleChallenge != null && userScore != null && totalQuestions != null) {
            setUserChallengeDataToView(titleChallenge, userScore, totalQuestions)
        }

        setupButtonClickListener()
    }

    private fun setupButtonClickListener() {
        binding.backToChallengeHomeButton.setOnClickListener {
            println("back to challenge home button clicked")
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.navigation_challenge_home, true)
                .build()
            findNavController().navigate(R.id.navigation_challenge_home, null, navOptions)
        }
        binding.backToHomeButton.setOnClickListener {
            println("back to home button clicked")
//            val navOptions = NavOptions.Builder()
//                .setPopUpTo(R.id.navigation_home, true)
//                .build()
//            findNavController().navigate(R.id.navigation_home, null, navOptions)
            findNavController().navigate(R.id.navigation_home)
        }
    }

    private fun setUserChallengeDataToView(titleChallenge: String, userScore: Int, totalQuestions: Int) {

        binding.titleChallengeTextView.text = titleChallenge
        if (userScore != totalQuestions) binding.titleStatusResultTextView.text = "Yahh, kamu belum berhasil :("

        val scoreProgress = (userScore.toFloat() * 100 / totalQuestions.toFloat()).toInt()
        binding.scoreCircularProgressIndicator.progress = scoreProgress
        binding.percentageScoreTextView.text = "$scoreProgress%"

        binding.resultDescriptionTextView.text = "Kamu menjawab\n$userScore soal benar dari $totalQuestions soal"

        // nanti atur untuk image badge level user

        // nanti atur level akun user apa "sort explorer" atau yg lain
    }

    override fun onDestroy() {
        super.onDestroy()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.navigation_challenge_home, true)
            .build()
        findNavController().navigate(R.id.navigation_challenge_home, null, navOptions)
    }

    companion object {
        fun newInstance() = ChallengeResultFragment()
    }

}