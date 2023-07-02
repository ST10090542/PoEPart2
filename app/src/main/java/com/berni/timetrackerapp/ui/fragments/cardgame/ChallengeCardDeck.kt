package com.berni.timetrackerapp.ui.fragments.cardgame

class ChallengeCardDeck {
    private val challengeCards: MutableList<ChallengeCard> = mutableListOf()

    init {
        // Add challenge cards to the deck
        challengeCards.add(ChallengeCard("Organize Your Workspace", "Spend 15 minutes tidying up your workspace."))
        challengeCards.add(ChallengeCard("Learn Something New", "Dedicate 30 minutes to learning a new skill or topic."))
        challengeCards.add(ChallengeCard("Practice Mindfulness", "Take 10 minutes to practice mindfulness or meditation."))
        challengeCards.add(ChallengeCard("Plan Your Day", "Spend 15 minutes planning and organizing your tasks for the day."))
        challengeCards.add(ChallengeCard("Take a Stretch Break", "Set a timer for 5 minutes and do some stretching exercises."))
        challengeCards.add(ChallengeCard("Try a Pomodoro Technique", "Use the Pomodoro Technique and work in focused 25-minute intervals with 5-minute breaks. Repeat for 4 cycles."))
        challengeCards.add(ChallengeCard("Read a Productivity Book", "Allocate 30 minutes to read a book on productivity or time management."))
        challengeCards.add(ChallengeCard("Write a Gratitude Journal", "Take 5 minutes to write down three things you're grateful for today."))
        challengeCards.add(ChallengeCard("Complete a Quick Declutter", "Choose a small area to declutter, like a drawer or a shelf, and spend 10 minutes organizing it."))
    }

    fun drawChallengeCard(): ChallengeCard? {
        if (challengeCards.isNotEmpty()) {
            val randomIndex = (0 until challengeCards.size).random()
            return challengeCards.removeAt(randomIndex)
        }
        return null
    }
}