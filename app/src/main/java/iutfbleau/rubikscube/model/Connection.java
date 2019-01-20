package iutfbleau.rubikscube.model;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class Connection
{
    private static GoogleSignInClient googleClient; //memory leak ???

    private Connection(){
    }

    public static void setConnection(GoogleSignInClient googleClient)
    {
        Connection.googleClient = googleClient;
    }

    public static void signOut()
    {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut();
        // Google sign out
        FirebaseAuth.getInstance().signOut();
    }

    public static void revokeAccess()
    {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut();
        // Google revoke access
        googleClient.revokeAccess();
    }

    public static boolean userConnected(){

        return FirebaseAuth.getInstance().getCurrentUser() != null;

    }
}
