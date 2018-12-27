package iutfbleau.rubikscube.model;

import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Connection
{
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleClient;

    public Connection(GoogleSignInClient googleClient, FirebaseAuth firebaseAuth)
    {
        this.googleClient = googleClient;
        this.firebaseAuth = firebaseAuth;
    }

    private void signOut()
    {
        // Firebase sign out
       this.firebaseAuth.signOut();

        // Google sign out
        this.googleClient.signOut();
    }
}
