package de.htw.toto.moco.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import de.htw.toto.moco.app.communication.ILoginListener;
import de.htw.toto.moco.app.communication.Login;

public class MainActivity extends Activity implements ILoginListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logintest);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String passwordHash = ((EditText) findViewById(R.id.password)).getText().toString();
                Login.getInstance().login(MainActivity.this, MainActivity.this, username, passwordHash);
            }
        });
    }

    @Override
    public void result(String result) {
        ((TextView) findViewById(R.id.loginResult)).setText(result);
    }
}
