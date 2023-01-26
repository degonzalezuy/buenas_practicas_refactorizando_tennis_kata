package co.com.sofka;

public class TennisGame2 implements TennisGame
{
    private int player1Points = 0;
    private int player2Points = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame2(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public String getScore(){
        if(arePlayersTied())
            return getResponseTiedScore();
        if(isThereAnyPlayerAboutToWin())
            return decideWinner();

        return getPreFinalResult();
    }

    private boolean isThereAnyPlayerAboutToWin(){
        return player1Points >= 4 || player2Points >= 4;
    }

    private boolean arePlayersTied(){
        return player1Points == player2Points;
    }

    private String getPlayerWithAdvantage(int minusResult)
    {
        if(minusResult == 1)
            return "Advantage ".concat(player1Name);
        return "Advantage " .concat(player2Name);
    }

    private String decideWinner(){
        String score;
        int minusResult = getDiffSCore();
        score = getPlayerWithAdvantage(minusResult);
        if(minusResult >= 2) score = "Win for ".concat(player1Name);
        if(minusResult <= -2) score = "Win for ".concat(player2Name);
        return score;
    }

    private int getDiffSCore(){
        return player1Points-player2Points;
    }

    private String getPreFinalResult(){
        int tempScore = 0;
        String score = "";
        for(int i = 1; i < 3; i++)
        {
            if(i == 2)
                score = score.concat("-");

                tempScore=getPlayerTempScore(i);
                score = getTempResponseScore(score, tempScore);
        }
        return score;
    }

    private int getPlayerTempScore(int player){
        if(player == 1)
            return player1Points;

        return player2Points;
    }

    private String getTempResponseScore(String score, int tempScore){
        switch (tempScore)
        {
            case 0:
                score= score.concat("Love");
                break;
            case 1:
                score = score.concat("Fifteen");
                break;
            case 2:
                score = score.concat("Thirty");
                break;
            case 3:
                score= score.concat("Forty");
        }
        return score;
    }

    private String getResponseTiedScore() {
        switch (player1Points){
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

    public void addPlayer1Score(){
        player1Points++;
    }

    public void addPlayer2Score(){
        player2Points++;
    }

    public void wonPoint(String player){
        if(player.equalsIgnoreCase(player1Name))
            addPlayer1Score();
        if(player.equalsIgnoreCase(player2Name))
            addPlayer2Score();
    }

}