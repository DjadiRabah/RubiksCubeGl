package iutfbleau.rubikscube.model;

import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Connection
{
    private static Connection connection = null;
    private static FirebaseAuth firebaseAuth;
    private static GoogleSignInClient googleClient;

    private Connection()
    {
    }

    public static void setConnection(GoogleSignInClient googleClient, FirebaseAuth firebaseAuth)
    {
        Connection.googleClient = googleClient;
        Connection.firebaseAuth = firebaseAuth;
    }

    public void signOut()
    {
        // Firebase sign out
       this.firebaseAuth.signOut();

        // Google sign out
        this.googleClient.signOut();
    }

    public static Connection getInstance()
    {
        if(Connection.connection == null)
        {
            Connection.connection = new Connection();
        }

        return Connection.connection;
    }

    public boolean hasSignedIn() {
        if(Connection.firebaseAuth.getCurrentUser() == null)
        {
            return false;
        }

        return true;
    }
}
