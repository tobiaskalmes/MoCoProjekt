package de.htw.toto.moco.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import de.htw.toto.moco.app.communication.login.ILoginListener;
import de.htw.toto.moco.app.communication.login.LoginRequester;
import de.htw.toto.moco.app.communication.register.IRegisterListener;
import de.htw.toto.moco.app.communication.register.RegisterRequester;
import de.htw.toto.moco.server.tools.ChecksumHandler;

public class MainActivity extends Activity implements ILoginListener, IRegisterListener {
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
                ChecksumHandler.getInstance(ChecksumHandler.Type.SHA1).update(
                        ((EditText) findViewById(R.id.password)).getText().toString());
                String passwordHash = ChecksumHandler.getInstance(ChecksumHandler.Type.SHA1).digest();
                LoginRequester.login(MainActivity.this, MainActivity.this, username, passwordHash);
            }
        });

        final Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                ChecksumHandler.getInstance(ChecksumHandler.Type.SHA1).update(
                        ((EditText) findViewById(R.id.password)).getText().toString());
                String passwordHash = ChecksumHandler.getInstance(ChecksumHandler.Type.SHA1).digest();
                RegisterRequester.register(MainActivity.this, MainActivity.this, username, passwordHash);
            }
        });
    }

    @Override
    public void result(String result) {
        ((TextView) findViewById(R.id.loginResult)).setText(result);
    }

    @Override
    public void registerResult(String token) {
        ((TextView) findViewById(R.id.loginResult)).setText("New user registered! " + token);
    }

    @Override
    public void error(Throwable e) {
        ((TextView) findViewById(R.id.loginResult)).setText(e.getMessage());
    }
}
