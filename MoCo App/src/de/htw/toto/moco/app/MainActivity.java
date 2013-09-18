package de.htw.toto.moco.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import de.htw.toto.moco.app.communication.login.ILoginListener;
import de.htw.toto.moco.app.communication.message.IMessageListener;
import de.htw.toto.moco.app.communication.message.MessageRequester;
import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;

public class MainActivity extends Activity implements ILoginListener, IMessageListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String passwordHash = ((EditText) findViewById(R.id.password)).getText().toString();
                //LoginRequester.login(MainActivity.this, MainActivity.this, username, passwordHash);
                MessageRequester.requestChatMessages(MainActivity.this, MainActivity.this);
            }
        });
    }

    @Override
    public void result(String result) {
        ((TextView) findViewById(R.id.loginResult)).setText(result);
    }

    //@Override
    public void result(ChatMessageList chatMessages) {
        String text = "";
        for (ChatMessage cm : chatMessages.getMessages()) {
            text += "Sender: " + cm.getSender() + " Receiver: " + cm.getReceiver() + " Content: " + cm.getContent() + "\n";
        }
        ((TextView) findViewById(R.id.loginResult)).setText(text);
    }

    @Override
    public void error(Throwable e) {
        ((TextView) findViewById(R.id.loginResult)).setText(e.getMessage());
    }
}
