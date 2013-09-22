package de.htw.toto.moco.app.gui.games.rpssl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.communication.game.RPSSL.IRPSSLRequester;
import de.htw.toto.moco.app.communication.game.RPSSL.RPSSLRequester;
import de.htw.toto.moco.server.game.GameState;
import de.htw.toto.moco.server.game.rpssl.GameHand;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 22.09.13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
public class RPSSLGameActivity extends Activity implements IRPSSLRequester {
    private Button   rockButton;
    private Button   paperButton;
    private Button   scissorsButton;
    private Button   spockButton;
    private Button   lizardButton;
    private TextView resultText;
    private TextView gameStateText;
    private Button   playAgainButton;
    private int      gameID;
    private boolean  waitingForResult;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rpssl);

        rockButton = (Button) findViewById(R.id.rockButton);
        paperButton = (Button) findViewById(R.id.paperButton);
        scissorsButton = (Button) findViewById(R.id.scissorsButton);
        spockButton = (Button) findViewById(R.id.spockButton);
        lizardButton = (Button) findViewById(R.id.lizardButton);
        resultText = (TextView) findViewById(R.id.rpsslResult);
        gameStateText = (TextView) findViewById(R.id.gameState);
        playAgainButton = (Button) findViewById(R.id.playAgain);

        waitingForResult = false;
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!waitingForResult) {
                    waitingForResult = true;
                    //play Hand
                    RPSSLRequester.requestPlayHand(getBaseContext(), RPSSLGameActivity.this, gameID, GameHand.ROCK);
                    Log.e("DEBUG", "Played Hand: " + GameHand.ROCK.name());
                }
            }
        });
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!waitingForResult) {
                    waitingForResult = true;
                    //play Hand
                    RPSSLRequester.requestPlayHand(getBaseContext(), RPSSLGameActivity.this, gameID, GameHand.PAPER);
                    Log.e("DEBUG", "Played Hand: " + GameHand.PAPER.name());
                }
            }
        });
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!waitingForResult) {
                    waitingForResult = true;
                    //play Hand
                    RPSSLRequester.requestPlayHand(getBaseContext(), RPSSLGameActivity.this, gameID, GameHand.SCISSORS);
                    Log.e("DEBUG", "Played Hand: " + GameHand.SCISSORS.name());
                }
            }
        });
        spockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!waitingForResult) {
                    waitingForResult = true;
                    //play Hand
                    RPSSLRequester.requestPlayHand(getBaseContext(), RPSSLGameActivity.this, gameID, GameHand.SPOCK);
                    Log.e("DEBUG", "Played Hand: " + GameHand.SPOCK.name());
                }
            }
        });
        lizardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!waitingForResult) {
                    waitingForResult = true;
                    //play Hand
                    RPSSLRequester.requestPlayHand(getBaseContext(), RPSSLGameActivity.this, gameID, GameHand.LIZARD);
                    Log.e("DEBUG", "Played Hand: " + GameHand.LIZARD.name());
                }
            }
        });
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgainButton.setVisibility(View.INVISIBLE);
                RPSSLRequester.requestPollGameState(RPSSLGameActivity.this.getBaseContext(),
                                                    RPSSLGameActivity.this, gameID);
            }
        });
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //get in line for game
        RPSSLRequester.requestReadyForRPSSL(getBaseContext(), this);
    }

    @Override
    public void readyForRPSSLResult(String result) {
        if (result.equals("true")) {
            Log.e("DEBUG", "Registered to play RPSSL.");
        }
        //check if game is ready
        playAgainButton.setVisibility(View.INVISIBLE);
        RPSSLRequester.requestCheckInviteState(getBaseContext(), this);
    }

    @Override
    public void checkInviteStateResult(Integer inviteState) {
        if (inviteState < 0) {
            //game not yet ready, recheck
            RPSSLRequester.requestCheckInviteState(getBaseContext(), this);
        } else {
            //game is ready
            rockButton.setVisibility(View.VISIBLE);
            paperButton.setVisibility(View.VISIBLE);
            scissorsButton.setVisibility(View.VISIBLE);
            spockButton.setVisibility(View.VISIBLE);
            lizardButton.setVisibility(View.VISIBLE);
            resultText.setVisibility(View.VISIBLE);
            gameID = inviteState;
            waitingForResult = false;
            playAgainButton.setVisibility(View.INVISIBLE);
            Log.e("DEBUG", "Received GameID: " + inviteState);
        }
    }

    @Override
    public void pollGameStateResult(GameState gameState) {
        if (gameState == GameState.RESULT_READY) {
            //poll result
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    }
                    catch (Exception e) {
                    }
                    RPSSLRequester.requestGameResult(RPSSLGameActivity.this.getBaseContext(), RPSSLGameActivity.this,
                                                     gameID);
                }
            };
            t.start();
        } else {
            //wait for result, re-poll game state
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    }
                    catch (Exception e) {
                    }
                    RPSSLRequester.requestPollGameState(RPSSLGameActivity.this.getBaseContext(),
                                                        RPSSLGameActivity.this, gameID);
                }
            };
            t.start();
        }
    }

    @Override
    public void playHandResult(String result) {
        //start polling the game state
        RPSSLRequester.requestPollGameState(getBaseContext(), this, gameID);
    }

    @Override
    public void gameResult(Integer result) {
        if (result > 0) {
            //you won
            resultText.setText("Du hast gewonnen.");
        } else {
            if (result < 0) {
                resultText.setText("Du hast verloren.");
            } else {
                resultText.setText("Unentschieden.");
            }
        }
        playAgainButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void error(Throwable e) {
        Log.e("DEBUG", "ConnectionError", e);
    }
}