package com.ryanluo.prefloppal.data

data class TermItem(
    val term: String,
    val definition: String,
    var isExpanded: Boolean = false
)

fun getTermItems(): List<TermItem> {
    return listOf(
        TermItem(
            term = "Ante",
            definition = "A small bet all players are required to make before a hand is dealt. An ante is similar to a blind, but everyone has to contribute it before a hand commences. Antes give the pot a value right off the bat."
        ),
        TermItem(
            "All-In",
            "A bet that places all of a player's chips into the pot."
        ),
        TermItem(
            "Backdoor",
            "Hitting your needed cards on the turn and the river to make your hand. For example, if there's one heart on the board and you have two in your hand and two more hearts show up on the turn and river, you've hit a \"backdoor\" flush."
        ),
        TermItem(
            "Bad Beat",
            "When a player who initially had a substantial statistical lead over an opponent loses his hand to that opponent after the flop, turn, or river."
        ),
        TermItem(
            "Bad Beat Story",
            "A retelling or recollection of a bad beat. These are often boring tales you already know the end of. For example: \"I had ace-king, and my opponent had 2-3. There was an ace on the board. The turn was a 4, and then - Can you believe it? - the river was a 5!\""
        ),
        TermItem(
            "Big Blind",
            "The amount of chips the second player to the left of the dealer has to bet. The amount depends on the stakes. Like an ante, it is a posted amount that makes the pot worth playing for before the action begins. It is equivalent to one complete first round bet. It's called a blind because it amounts to placing a bet without seeing the cards first."
        ),
        TermItem(
            "Blind",
            "The generic term for either the big blind or the small blind. If you are one of the blinds, you are sitting either immediately to the left of the dealer button (small blind position) or one position farther left (big blind)."
        ),
        TermItem(
            "Board",
            "The community cards that everyone uses in combination with their pocket cards to form the best hand."
        ),
        TermItem(
            "Burn",
            "The discarding of the top card before each betting round. In the case that there is a distinguishing mark on the top card, the burn card keeps the next card to be dealt concealed before it comes out. That way no unfair information is being intentionally or unintentionally conveyed."
        ),
        TermItem(
            "Button",
            "The position of the dealer. In live poker, it's usually denoted by placing a plastic disk in front of the dealer. It rotates clockwise each time the dealer shuffles for a new hand. The button is in an advantageous position, for they act last in a betting round."
        ),
        TermItem(
            "Call",
            "To contribute the minimum amount of money to the pot necessary to continue playing a hand."
        ),
        TermItem(
            "Check",
            "To pass on betting. If there's no action (bet) to you, there's nothing to call. If you don't want to bet, you can just \"check.\" If there's subsequent action from your fellow players in the betting round, then the action will come back to you to either call, fold, or raise."
        ),
        TermItem(
            "Check-Raise",
            "A check-raise is made when a player checks on the first opportunity to bet and later raises any subsequent bet in the same betting round."
        ),
        TermItem(
            "Cold Call",
            "To call two or more bets on your turn. If a pot has been bet and raised before it gets to you, and then you call, you're cold calling."
        ),
        TermItem(
            "Connector",
            "Sequential pocket cards. A 5 of clubs and 6 of hearts would be connectors. If the connectors are the same suit, they are \"suited connectors\" - e.g., 5 and 6 of clubs."
        ),
        TermItem(
            "Community Cards",
            "Cards that are dealt face up in the center of the table, available for all players to use in making a hand."
        ),
        TermItem(
            "Counterfeit",
            "A duplicate card on the board that greatly devalues your hand. If you have a pair of 6's in your hand, and the board is ace-ace-7-4, and the river card is a 7, you've been \"counterfeited.\" You had two pairs, but now the board has two better pairs. Any other player with a card higher than a 6 in their hand now beats your hand."
        ),
        TermItem(
            "Cut-Off",
            "The position to the immediate right of the button."
        ),
        TermItem(
            "Dealer",
            "The player who shuffles the deck and deals the cards."
        ),
        TermItem(
            "Dealer Button",
            "The button (often a plastic disk in live poker) that indicates the dealer. It is passed clockwise after every hand."
        ),
        TermItem(
            "Draw",
            "Remaining in a hand in the hopes of improving it. For example, you don't have anything concrete yet, but need one or more cards for a straight or a flush. If you call (or raise) a round of betting to see if the needed card(s) come, you are said to be \"drawing.\" The two most common draws are flush draws (drawing for a flush) and straight draws (drawing for a straight). You can also draw for a three of a kind, full house, or better."
        ),
        TermItem(
            "Drawing Dead",
            "You're drawing, but it's futile because there is not one card in the deck that will create a winning hand for you. If you have two pairs and hope to make a full house on the river, but your opponent already has four of a kind, you are \"drawing dead.\""
        ),
        TermItem(
            "Flop",
            "The first three community cards dealt out after the first round of betting is complete."
        ),
        TermItem(
            "Flush",
            "A poker hand consisting of five cards of the same suit."
        ),
        TermItem(
            "Fold",
            "To give up by placing your cards face down on the table, losing whatever you have bet so far. You only fold when you think your hand is too weak to compete against the other players."
        ),
        TermItem(
            "Four of a Kind",
            "A hand containing all four cards of the same rank."
        ),
        TermItem(
            "Full House",
            "A hand consisting of a three of a kind and a (different) pair."
        ),
        TermItem(
            "Gutshot",
            "A straight completed from \"inside\" by one possible card. For example, if your pocket cards are 5 and 6 and the flop shows 4-8-king, a 7 and only a 7 on the turn or river would complete your \"gutshot\" straight. It is the opposite of an open-ended straight, which is completed by any one of two cards from the outside. A gutshot is half as likely to hit as an open-ended straight."
        ),
        TermItem(
            "Hand",
            "Five cards, made of a player's pocket cards and the community cards."
        ),
        TermItem(
            "Heads-Up",
            "Playing a pot or tournament against only one other player."
        ),
        TermItem(
            "High Card",
            "In a hand of poker that has 5 different cards that do not form any kind of match, the highest card is the high card and it is only useful against another hand of 5 unmatched cards if a showdown takes place."
        ),
        TermItem(
            "Implied Odds",
            "Taking future calls from your fellow players into consideration when you are drawing to something. If you draw successfully, you expect they'll call with their hands. These funds are speculative and not concrete, as they aren't in the middle yet and won't be unless you hit your card and they call your bets - hence, \"implied.\""
        ),
        TermItem(
            "Kicker",
            "If you have the same hand as another player at showdown, the one with the highest kicker wins the pot. For example, if the board is 7-7-5-5-2, and you have ace-king and your opponent has king-queen, you win because your ace beats his king. Your ace is the \"kicker.\""
        ),
        TermItem(
            "Late Position",
            "Position on a round of betting where the player must act after most of the other players have acted (usually considered to be the two positions next to the button)."
        ),
        TermItem(
            "Limp",
            "Slang word for calling, implying it's not an aggressive move."
        ),
        TermItem(
            "Muck",
            "All the discarded cards in a hand. If a player folds, he tosses his hand \"into the muck.\""
        ),
        TermItem(
            "Nuts",
            "The best possible hand one can have at a given moment. For example, if you have pocket 7's, and the flop is 7-6-2, you have the \"nuts\" at this point, as trip 7's would be the best possible hand. If the turn card is a 5, you would no longer have the nuts, as that honor now goes to anyone holding 8-9, making a straight. If the river is the last 7, you'd again have the nuts, as your hand is once again the best possible hand."
        ),
        TermItem(
            "Off-suit",
            "Holding pocket cards of different suits."
        ),
        TermItem(
            "Over-Pair",
            "In hold'em, a pair in the hole that is larger than any community card on the board."
        ),
        TermItem(
            "Open-Ended",
            "A straight completed from the outside by one of two possible cards. For example, if your pocket cards are 5-6 and the flop shows 4-7-king, either a 3 or an 8 on the turn or river would complete your open-ended straight. An open-ended straight is twice as likely to hit as a \"gutshot.\""
        ),
        TermItem(
            "Orbit",
            "After each player at a table has served as the dealer for a hand. Each time the button passes you is a complete orbit."
        ),
        TermItem(
            "Out",
            "A card that will improve your hand. If all the money is in the middle, and you turn over a pair of kings and your opponent has a pair of aces, you need one of the two remaining kings - your two \"outs\" - to beat your opponent."
        ),
        TermItem(
            "Over-cards",
            "Having cards higher than the board cards or your opponent's pocket. For example, if it's heads up and someone's all-in, the two remaining players would expose their cards. If it is a pair of sevens versus ace-king, the ace and king are referred to as \"over-cards.\""
        ),
        TermItem(
            "Pair",
            "Two cards of the same rank."
        ),
        TermItem(
            "Pocket Cards",
            "The cards in your hand that are not part of the community cards. In hold'em, it's your two down cards. In Omaha, it's your four down cards. Also known as hole cards."
        ),
        TermItem(
            "Pot",
            "The place in the center of the poker table where wagered chips are placed. The winner of the hand wins all the chips in the pot."
        ),
        TermItem(
            "Pot-Committed",
            "A situation that likely requires you to call due to the amount of money in the pot vis-a-vis your remaining stack of chips. In these situations, it makes no sense to fold."
        ),
        TermItem(
            "Pot Odds",
            "The ratio of money in the pot compared to what you need to call to keep playing. For example, suppose there is $100 in the pot. Somebody bets $10, so the pot now contains $110. It costs you $10 to call, so your pot odds are 11-to-1. If your odds of winning are better than 11-to-1, you should call."
        ),
        TermItem(
            "Quads",
            "Four of a kind."
        ),
        TermItem(
            "Rainbow",
            "In flop games, a flop in which no two cards are of the same suit. E.g., \"The flop was an ace-9-7 rainbow.\""
        ),
        TermItem(
            "Rake",
            "The amount that the house takes out of a poker hand."
        ),
        TermItem(
            "River",
            "The final of the five community cards."
        ),
        TermItem(
            "Rock",
            "Slang for a \"tight\" player. A rock can sit at a table orbit after orbit without playing for a pot. When he enters a pot, you know he's got the goods."
        ),
        TermItem(
            "Raise",
            "To wager more than the minimum required to call, forcing other players to put in more money as well."
        ),
        TermItem(
            "Royal Flush",
            "An ace-high straight flush, the best possible hand in standard poker."
        ),
        TermItem(
            "Semi-Bluff",
            "A bluff with a hand that has the potential to improve should the bluff itself be ineffective."
        ),
        TermItem(
            "Set",
            "Having a pocket pair that hits on the board, making three of a kind."
        ),
        TermItem(
            "Short Stack",
            "Having fewer chips than the rest of the players at the table or in the tournament."
        ),
        TermItem(
            "Showdown",
            "When, after the final round of betting, players turn their hands face-up. A poker hand will only reach a showdown if there are callers in the last round of betting, or if someone is all-in prior to the last betting round."
        ),
        TermItem(
            "Side Pot",
            "Separate from the main pot. If one or more players is all-in, the pot to which the all-in players contributed is the main pot. A side pot is created from any additional money bet by the remaining players. There can be many side pots if there is more than one all-in player. An all-in player is only eligible to win a pot to which they have contributed."
        ),
        TermItem(
            "Slow Play",
            "When, in an attempt to have other players stick around and possibly call your bets, you play your hand less aggressively than necessary. For example, if you flop a full house, it is unlikely anyone is going to beat your hand. Slow-playing the hand may allow the other players to make their hands and therefore continue to call your bets."
        ),
        TermItem(
            "Small Blind",
            "The smaller of two blind bets. The position to the immediate left of the dealer button position, and to the right of the big blind position."
        ),
        TermItem(
            "Split Pot",
            "When two or more players make the same hand and the pot is divided between equivalent high hands."
        ),
        TermItem(
            "Straddle",
            "An optional pre-deal bet, typically made by the player to the left of the big blind. The straddle amount is twice the big blind (same as a legal raise). The straddler earns the \"option\" from the big blind. They may re-raise when the action comes around. A straddle is a cash game convention and is not usually permitted in a tournament."
        ),
        TermItem(
            "String Bet",
            "Placing a bet on the table in a staggered motion or multiple motions. String bets are not allowed, and the dealer will remove the added amount of the bet if it is determined to be a string bet. It's not permitted because it could be used to gauge the reaction of other players before you commit the entire intended amount of the raise."
        ),
        TermItem(
            "Straight",
            "A hand consisting of 5 cards in sequence but not in suit."
        ),
        TermItem(
            "Straight Flush",
            "A hand consisting of 5 cards in sequence and the same suit."
        ),
        TermItem(
            "Tilt",
            "Usually the result of taking a bad beat or series of bad beats, a player is said to be \"on tilt\" when they play with reckless abandon. The term likely derives from tilting a pinball machine."
        ),
        TermItem(
            "Top Pair",
            "A pair with the highest card on the board. For example, if you have an ace and 7 in the hole, and the flop shows 3-4-7, you've got a \"top pair\" with an ace kicker."
        ),
        TermItem(
            "Trips",
            "Slang for three of a kind."
        ),
        TermItem(
            "Turn",
            "The fourth community card. Put out face-up, by itself. Also known as \"Fourth Street.\""
        ),
        TermItem(
            "Under the Gun",
            "Player sitting in the first-to-act position. It's the position immediately to the left of the big blind pre-flop, and to the left of the button for subsequent betting rounds."
        )
    )
}