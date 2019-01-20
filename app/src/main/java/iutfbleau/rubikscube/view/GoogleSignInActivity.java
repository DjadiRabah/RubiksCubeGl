package iutfbleau.rubikscube.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controler.GoogleSignInListener;
import iutfbleau.rubikscube.model.Connection;

/**
 * Demonstrate Firebase Authentication using a Google ID Token.
 */
public class GoogleSignInActivity extends BaseActivity {

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    public static boolean UNAMEINCORRECT = false;
    public static boolean UNAMECORRECT = true;
    public static boolean AUTHFAIL = false;
    public static boolean AUTHSUCCESS = true;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;

    private TextView authInfoText, usernameInfoText;
    private EditText usernameEditText;

    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        // Button listeners
        findViewById(R.id.signInButton).setOnClickListener(new GoogleSignInListener(this));

        //Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        Connection.setConnection(this.mGoogleSignInClient);

        final Button authenticate = findViewById(R.id.authenticate);
        authenticate.setOnClickListener(new GoogleSignInListener(this));

        authInfoText = findViewById(R.id.infoAuth);
        usernameInfoText = findViewById(R.id.infoUsername);
        usernameEditText = findViewById(R.id.usernameEditText);

    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //    updateUI(currentUser);
    }
    // [END on_start_check_user]

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateGoogleAuthInfoText(AUTHFAIL);
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.e(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateGoogleAuthInfoText(AUTHSUCCESS);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateGoogleAuthInfoText(AUTHFAIL);

                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]

    public void updateUsernameInfoText(boolean validUname) {

        hideProgressDialog();

        if (validUname) {

            usernameInfoText.setTextColor(getResources().getColor(R.color.green));
            usernameInfoText.setText("Username valid !");
            usernameInfoText.setVisibility(View.VISIBLE);

        }else{

            usernameInfoText.setTextColor(getResources().getColor(R.color.red));
            usernameInfoText.setText("Username invalid (it must be at least 4 characters long) !");
            usernameInfoText.setVisibility(View.VISIBLE);

        }
    }

    public void updateGoogleAuthInfoText(boolean connectedU) {

        hideProgressDialog();

        if (connectedU) {

            authInfoText.setTextColor(getResources().getColor(R.color.green));
            authInfoText.setText("Google authentication has been successful !");
            authInfoText.setVisibility(View.VISIBLE);

        } else {

            authInfoText.setTextColor(getResources().getColor(R.color.red));
            authInfoText.setText("An error occurred during authentication (if the problem persists, check your internet connection) !");
            authInfoText.setVisibility(View.VISIBLE);

        }
    }

    public String getEnteredUsername() {

        return this.usernameEditText.getText().toString();

    }

    public void launchGameView() {

        Intent i = new Intent(getApplicationContext(), NavActivity.class);
        startActivity(i);

    }
}