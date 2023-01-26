package co.com.sofka;

public class TennisGame3 implements TennisGame {
    private int playerScore1;
    private int playerScore2;
    private String playerName1;
    private String playerName2;

    public TennisGame3(String playerName1, String playerName2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
    }

    public String getScore() {
        if (isNormalSet()) {
            String[] scoreResult = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
            return getResponseScore(scoreResult[playerScore1], scoreResult);
        }

        if (arePlayersTied())
            return "Deuce";

        return getPlayersInDeuceScore();
    }

    private boolean isNormalSet() {
        return !isThereAnyPlayerAboutToWin() && !arePlayersInDeuce();
    }

    private boolean isThereAnyPlayerAboutToWin() {
        return playerScore1 >= 4 || playerScore2 >= 4;
    }

    private String getPlayersInDeuceScore() {
        String player = getForwardedPlayer();
        return arePlayersStillInDeuce() ? "Advantage ".concat(player) : "Win for ".concat(player);
    }

    private String getForwardedPlayer() {
        return playerScore1 > playerScore2 ? playerName1 : playerName2;
    }

    private String getResponseScore(String score, String[] scoreResult) {
        return arePlayersTied() ? score.concat("-All") : score.concat("-") + scoreResult[playerScore2];
    }

    private boolean arePlayersTied() {
        return playerScore1 == playerScore2;
    }

    private boolean arePlayersStillInDeuce() {
        return (playerScore1 - playerScore2)*(playerScore1 - playerScore2) == 1;
    }

    private boolean arePlayersInDeuce() {
        return playerScore1 + playerScore2 == 6;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(playerName1))
            this.playerScore1 += 1;

        if(playerName.equalsIgnoreCase(playerName2))
            this.playerScore2 += 1;
    }
}
