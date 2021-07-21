package com.mad.navigation_fragment_demo_102

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.mad.navigation_fragment_demo_102.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {
    private lateinit var binding: FragmentQuestionBinding

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    var questionIndex = 0
    var score: Int = 0

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text_question = "what is mco stand for ?",
            question_choice = listOf(
                "Movement Control Order",
                "Multiple Choice Order",
                "More Coffee Order "
            )
        ),
        Question(
            text_question = "What is FMCO?",
            question_choice = listOf(
                "Full Movement Control Oder",
                "Fun Movement Control Oder",
                "Foreve Movement Control Oder"
            )
        )
    )


    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.question_choice.toMutableList()

        answers.shuffle()

        binding.tvQuestion.text = currentQuestion.text_question
        binding.option1.text= answers[0]
        binding.option2.text = answers[1]
        binding.option3.text = answers[2]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_question,container,false)
        setQuestion();
        binding.nextBtn.setOnClickListener() {

            val checkedId = binding.radioGroup.checkedRadioButtonId

            if (checkedId != -1) {
                var answerIndex = 0

                when (checkedId) {
                    R.id.option1 -> answerIndex = 0
                    R.id.option2 -> answerIndex = 1
                    R.id.option3 -> answerIndex = 2
                }
                if (answers[answerIndex] == currentQuestion.question_choice[0]) {
                    score += 1

                }
                if (questionIndex < 1) {

                    questionIndex += 1
                    binding.radioGroup.clearCheck()
                    setQuestion()

                } else {
                    val percenatage : Float=(score/2.0f)*100
                    // todo:: navigate to thankyou fragment
                    val action : NavDirections = QuestionFragmentDirections.actionQuestionFragmentToThankyouFragment(percenatage)
                    //Navigation.findNavController(it).navigate(R.id.action_questionFragment_to_thankyouFragment)
                    Navigation.findNavController(it).navigate(action)
                }
            } else {
                Toast.makeText(context, "please select answer", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }


}


