package iutfbleau.rubikscube.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.model.GoogleAuthManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementsFragment extends Fragment {

    private View view;

    public AchievementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_achievements, container, false);

        TextView notconnected = view.findViewById(R.id.connected);

        if(GoogleAuthManager.userConnected()){

            //si l'utilisateur est connecté ==> affichage des classements/trophées...

        }else{

            notconnected.setVisibility(View.VISIBLE);
            notconnected.setText("You are not connected !");

        }

        return view;

    }

}
