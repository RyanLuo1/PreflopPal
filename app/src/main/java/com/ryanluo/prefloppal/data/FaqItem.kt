package com.ryanluo.prefloppal.data

data class FaqItem(
    val question: String,
    val answer: String,
    var isExpanded: Boolean = false
)

fun getFaqItems(): List<FaqItem> {
    return listOf(
        FaqItem(
            "What are the Hand Rankings?",
            """
                <b>Royal Flush:</b> A, K, Q, J, 10, all of the same suit.<br><br>
                <b>Straight Flush:</b> Five consecutive cards of the same suit.<br><br>
                <b>Four of a Kind:</b> Four cards of the same rank.<br><br>
                <b>Full House:</b> Three of a kind combined with a pair.<br><br>
                <b>Flush:</b> Any five cards of the same suit, not in sequence.<br><br>
                <b>Straight:</b> Five consecutive cards of different suits.<br><br>
                <b>Three of a Kind:</b> Three cards of the same rank.<br><br>
                <b>Two Pair:</b> Two different pairs.<br><br>
                <b>One Pair:</b> Two cards of the same rank.<br><br>
                <b>High Card:</b> When you don't have any of the above, your highest card plays.
                """.trimIndent()
        ),
        FaqItem(
            "Why is Positioning Important in Poker?",
            """
                In poker, your position at the table relative to the dealer is crucial.</b> The later your position, the more information you have about how other players are acting, allowing you to make better-informed decisions.
                <br><br>
                <b>Early Position:</b> The first few players to act are in an early position and are at a disadvantage because they must make decisions with little information.
                <br><br>
                <b>Middle Position:</b> Players in the middle have more information but still need to be cautious.
                <br><br>
                <b>Late Position:</b> The last few players to act are in a late position and have the advantage of seeing how others have bet, allowing them to make more strategic decisions.
                """.trimIndent()
        ),
        FaqItem(
            "What is Preflop in Poker?",
            """
                <b>Preflop</b> is the phase in poker before any community cards (the flop) are dealt. During this stage, players assess their hole cards (the two private cards dealt to each player) and decide whether to call, raise, or fold. These initial decisions are based on the strength of their hand and their position at the table. <br><br>
                
                This phase is crucial as it sets the foundation for the rest of the hand and significantly impacts the potential outcome of the game.
                """.trimIndent()
        ),
        FaqItem(
            "What is Postflop in Poker?",
            """
                <b>Postflop</b> refers to all phases in poker after the initial community cards, or the flop, have been dealt. This includes the Flop, Turn, and River betting rounds, where players use these community cards along with their hole cards to build the best hand possible. <br><br>
            
                Decisions made during postflop play are crucial and rely on analyzing hand strength, board texture, and opponents' potential ranges. This stage of the game demands strong skills in betting strategy and reading opponents.
                """.trimIndent()
        ),
        FaqItem(
            "What is Bluffing in Poker?",
            """
                In poker, <b>bluffing</b> is a strategy where a player bets or acts confidently with a weak or low-value hand to mislead opponents into thinking they hold a stronger hand. 
                The goal is to induce opponents to fold better hands, allowing the bluffer to win the pot without needing the best cards.<br><br>
                
                <b>Semi-Bluff:</b> A safer form of bluffing where you have a decent chance of improving to a strong hand if your bluff gets called.<br><hr>
                <br><br>
                <b>Pure Bluff:</b> When you have a weak hand with little chance of improving, and you’re relying solely on your opponent folding.
                """.trimIndent()
        ),
        FaqItem(
            "What are typical betting sizes in poker?",
            """
                <b>Preflop:</b><br><br>
                &bull; <b>Open Raise:</b> 2x-3x the big blind is common in most games.<br><br>
                &bull; <b>3-Bet (re-raise):</b> Typically 3-4x the initial raise size, or about 2.5x if you're in position (acting after the other player).<br><br>
                <hr>
                <b>Postflop:</b><br><br>
                &bull; <b>C-Bet (Continuation Bet):</b> 50%-75% of the pot size is common after the flop, depending on the board texture.<br><br>
                &bull; <b>Value Bet:</b> 60%-100% of the pot to maximize winnings when you have a strong hand.<br><br>
                &bull; <b>Bluff Bet:</b> 50%-70% of the pot is often used to try to push opponents off weaker hands.<br><br>
                
                These guidelines vary based on experience, table dynamics, and player style but are consistent and strategically sound bets.
                """.trimIndent()
        ),
        FaqItem(
            "What is Bankroll Management?",
            """
                Bankroll management is essential for sustaining long-term success in poker. Your bankroll is the money set aside solely for poker, and managing it carefully helps you handle the natural swings of the game.
                <<br><br>
                <b>Set Limits:</b> Decide beforehand how much you're willing to lose in a session and stick firmly to that limit.<br><br>
                
                <b>Play Within Your Means:</b> Avoid risking your entire bankroll in one game or hand. A common rule of thumb is to only risk 1-2% of your bankroll per session.<br><br>
                
                Following these guidelines helps protect your bankroll and keeps you in the game longer, even during unlucky stretches.
                """.trimIndent()
        ),
    )
}