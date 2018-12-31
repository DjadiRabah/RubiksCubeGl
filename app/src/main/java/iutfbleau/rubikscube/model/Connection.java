package iutfbleau.rubikscube.model;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

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

    public static void signOut()
    {
        // Firebase sign out
       firebaseAuth.signOut();

        // Google sign out
        googleClient.signOut();
    }

    public static void revokeAccess()
    {

        // Firebase sign out
        firebaseAuth.signOut();

        // Google revoke access
        googleClient.revokeAccess();

    }

    public static boolean isSignedIn() {
        return Connection.firebaseAuth.getCurrentUser() != null;
    }

    public static Connection getInstance()
    {
        if(Connection.connection == null)
        {
            Connection.connection = new Connection();
        }

        return Connection.connection;
    }

}
