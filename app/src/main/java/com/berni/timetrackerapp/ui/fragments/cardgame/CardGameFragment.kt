package com.berni.timetrackerapp.ui.fragments.cardgame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.berni.timetrackerapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var challengeCardDeck: ChallengeCardDeck
    private lateinit var btnDrawChallenge: Button
    private lateinit var tvChallengeTitle: TextView
    private lateinit var tvChallengeDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        challengeCardDeck = ChallengeCardDeck()

        btnDrawChallenge = findViewById(R.id.btnDrawChallenge)
        tvChallengeTitle = findViewById(R.id.tvChallengeTitle)
        tvChallengeDescription = findViewById(R.id.tvChallengeDescription)

        btnDrawChallenge.setOnClickListener {
            val challengeCard = challengeCardDeck.drawChallengeCard()
            if (challengeCard != null) {
                tvChallengeTitle.text = challengeCard.title
                tvChallengeDescription.text = challengeCard.description
            } else {
                tvChallengeTitle.text = "No Challenge Card Remaining"
                tvChallengeDescription.text = ""
            }
        }
    }
}