
public class TennisGame1 implements TennisGame {
    
    private int playerScore1 = 0;
    private int playerScore2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equalsIgnoreCase(player1Name))
            playerScore1 += 1;

        if (playerName.equalsIgnoreCase(player2Name))
            playerScore2 += 1;
    }

    public String getScore(){
        if(arePlayersTied())
            return getResponseTiedScore();
        if(isThereAnyPlayerAboutToWin())
            return decideWinner();

        return getPreFinalResult();
    }

    private String decideWinner(){
        String score;
        int minusResult = getDiffScore();
        score = getPlayerWithAdvantage(minusResult);
        if(minusResult >= 2) score = "Win for ".concat(player1Name);
        if(minusResult <= -2) score = "Win for ".concat(player2Name);
        return score;
    }

    private String getPlayerWithAdvantage(int minusResult)
    {
        if(minusResult == 1)
            return "Advantage ".concat(player1Name);
        return "Advantage ".concat(player2Name);
    }
    private String getPreFinalResult(){
        int tempScore = 0;
        String score = "";
        for(int i = 1; i < 3; i++ )
        {
            if(i == 2)
                score = score.concat("-");

            tempScore = getPlayerTempScore(i);
            score = getTempResponseScore(score, tempScore);
        }
        return score;
    }
    private int getPlayerTempScore(int player){
        if(player == 1)
            return playerScore1;

        return playerScore2;
    }



    private int getDiffScore(){
        return playerScore1-playerScore2;
    }
    private boolean arePlayersTied(){
        return playerScore1 == playerScore2;
    }

    private boolean isThereAnyPlayerAboutToWin(){
        return playerScore1 >= 4 || playerScore2 >=4;
    }


    private String getResponseTiedScore(){
        switch (playerScore1)
        {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            default:
                return "Deuce";
        }
    }

    private String getTempResponseScore(String score, int tempScore) {
        switch(tempScore)
        {
            case 0:
                score = score.concat("Love");
                break;
            case 1:
                score = score.concat("Fifteen");
                break;
            case 2:
                score = score.concat("Thirty");
                break;
            case 3:
                score = score.concat("Forty");
        }
        return score;
    }

}
