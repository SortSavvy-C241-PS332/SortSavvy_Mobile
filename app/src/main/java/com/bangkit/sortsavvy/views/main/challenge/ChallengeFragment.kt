package com.bangkit.sortsavvy.views.main.challenge

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.bangkit.sortsavvy.R
import com.bangkit.sortsavvy.data.model.QuestionModel
import com.bangkit.sortsavvy.databinding.FragmentChallengeBinding
import com.bangkit.sortsavvy.utils.ViewComponentUtil

class ChallengeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentChallengeBinding
    private val viewModel: ChallengeViewModel by viewModels()

    private var currentQuestionIndex = 0
    private var selectedAnswer = ""
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChallengeBinding.bind(view)

        binding.apply {
            option0Button.setOnClickListener(this@ChallengeFragment)
            option1Button.setOnClickListener(this@ChallengeFragment)
            option2Button.setOnClickListener(this@ChallengeFragment)
            nextButton.setOnClickListener(this@ChallengeFragment)
        }
        loadQuestions()

        binding.backBtnImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun loadQuestions() {
        selectedAnswer = ""
        if (currentQuestionIndex == questionModelList.size) {
            finishChallenge()
            return
        }

        binding.apply {
            questionNumberTextView.text = "${currentQuestionIndex+1}/${questionModelList.size} Soal"
            questionProgressIndicator.progress = (currentQuestionIndex.toFloat() * 100 / questionModelList.size.toFloat()).toInt()

            if (questionModelList.isNotEmpty()) {
                questionTextView.text = questionModelList[currentQuestionIndex].question
                option0Button.text = questionModelList[currentQuestionIndex].options[0]
                option1Button.text = questionModelList[currentQuestionIndex].options[1]
                option2Button.text = questionModelList[currentQuestionIndex].options[2]
            }
        }
    }

    override fun onClick(view: View?) {

        binding.apply {
            option0Button.setBackgroundColor(resources.getColor(R.color.background_white_gray_2))
            option1Button.setBackgroundColor(resources.getColor(R.color.background_white_gray_2))
            option2Button.setBackgroundColor(resources.getColor(R.color.background_white_gray_2))
        }

        val clickedButton = view as Button
        when (clickedButton.id) {
            R.id.nextButton -> {

                if (selectedAnswer.isEmpty()) {
                    ViewComponentUtil.showToast(requireContext(), "Pilih jawaban terlebih dahulu")
                    return
                }

                if (selectedAnswer == questionModelList[currentQuestionIndex].correctAnswer) {
                    score++
                    println("score of quiz: $score")
                }

                currentQuestionIndex++
//                if (currentQuestionIndex < questionModelList.size) {
                    loadQuestions()
//                }
            }
            else -> {
                println(selectedAnswer)
                clickedButton.setBackgroundColor(resources.getColor(R.color.background_emerald_green))
                selectedAnswer = clickedButton.text.toString()
//                val selectedOption = clickedButton.text.toString()
//                val correctAnswer = questionModelList[currentQuestionIndex].correctAnswer
//                if (selectedOption == correctAnswer) {
//                    clickedButton.setBackgroundResource(R.drawable.bg_button_correct)
//                } else {
//                    clickedButton.setBackgroundResource(R.drawable.bg_button_wrong)
//                }
            }
        }
    }

    private fun finishChallenge() {
        val totalQuestions = questionModelList.size
        val scorePercentage = (score.toFloat() / totalQuestions.toFloat()) * 100

        val bundle = Bundle()
        bundle.putString(EXTRA_CHALLENGE_TITLE, titleChallenge)
        bundle.putInt(EXTRA_USER_SCORE, score)
        bundle.putInt(EXTRA_TOTAL_QUESTIONS, totalQuestions)
        findNavController().navigate(R.id.action_navigation_challenge_to_navigation_challenge_result, bundle)
    }

    companion object {
        fun newInstance() = ChallengeFragment()
        var questionModelList: List<QuestionModel> = listOf()
        var titleChallenge: String = ""

        const val EXTRA_USER_SCORE = "extra_user_score"
        const val EXTRA_TOTAL_QUESTIONS = "extra_total_questions"
        const val EXTRA_CHALLENGE_TITLE = "extra_challenge_title"
    }
}