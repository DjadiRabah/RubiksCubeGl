package iutfbleau.rubikscube.model;

import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import iutfbleau.rubikscube.R;

public class GoogleAuthManager {

    private static GoogleSignInClient googleClient; //memory leak ???

    public GoogleAuthManager(AppCompatActivity activity) {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleClient = GoogleSignIn.getClient(activity, gso);
    }

    public static void signOut() {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut();
        // Google sign out
        FirebaseAuth.getInstance().signOut();
    }

    public static void revokeAccess() {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut();
        // Google revoke access
        googleClient.revokeAccess();
    }

    public static FirebaseAuth getFirebaseInstance(){

        return FirebaseAuth.getInstance();

    }

    public static FirebaseUser getFirebaseUser(){

        return FirebaseAuth.getInstance().getCurrentUser();

    }

    public static boolean userConnected(){

        return getFirebaseInstance().getCurrentUser() != null;

    }

    public static GoogleSignInClient getSignInClient(){

        return GoogleAuthManager.googleClient;

    }
}
