package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitness.Connection.Server;
import com.example.fitness.Database.DataBase;
import com.example.fitness.Models.User;
import com.google.android.material.snackbar.Snackbar;

public class IngresandoActivity extends AppCompatActivity {
    private Server server;
    private DataBase db;
    private User user;
    TextView txtEmail;
    TextView txtPassword;
    private UserLoginTask mAuthTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresando);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        server = new Server();
        txtEmail = (EditText)findViewById(R.id.txtxEmail);
        txtPassword = (EditText)findViewById(R.id.txtpassword);

    }

    public void login(View view) {
        Log.v("fitlabel","attempting login");
        Server server = new Server();
        User user = new User();

        user.setEmail(txtEmail.getText().toString());
        user.setPassword(txtPassword.getText().toString());

        if(user.getEmail() == "") {
            Snackbar.make(view, "Contraseña en blanco", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        Log.v("fitlabel","before userlogin");
        mAuthTask = new UserLoginTask(user);
        mAuthTask.execute((Void) null);

    }
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private User user;

        UserLoginTask(User user) {
            this.user = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

//            try {
//                // Simulate network access.
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                return false;
//            }
            String result = "no result";
            server = new Server(user.getEmail(), user.getPassword());
            Log.v("Brian","nombre: "+user.getEmail());
            Log.v("Brian","Pass: "+user.getPassword());
            result = server.login(user.getEmail(), user.getPassword());
            Log.v("Brian","Resultado login: "+result);
            if(!result.equals(""))
            {
                return true;
            }
//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                finish();
                Intent intent = new Intent(IngresandoActivity.this, MainActivity.class);
                intent.putExtra("user", user.getEmail());
                intent.putExtra("epaas", user.getPassword());
                IngresandoActivity.this.startActivity(intent);
            } else {
                //mPasswordView.setError("Contrasenia incorrecta");
                //mPasswordView.requestFocus();
                Log.v("fitlabel","error on login");
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //showProgress(false);
        }
    }
}
