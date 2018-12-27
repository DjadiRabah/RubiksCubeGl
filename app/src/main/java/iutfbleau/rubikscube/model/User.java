package iutfbleau.rubikscube.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User
{
    private FirebaseUser currentUser;

    public User()
    {
        this.currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}
