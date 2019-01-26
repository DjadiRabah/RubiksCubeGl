package iutfbleau.rubikscube.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.adapters.AchievementItemAdapter;
import iutfbleau.rubikscube.model.AchievementItem;

public class AchievementsFragment extends Fragment {

    public AchievementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achievements, container, false);

        //list of items

        List<AchievementItem> achievementItems = new ArrayList<>();
        achievementItems.add(new AchievementItem("First Time !", "onerb", "Solve a Rubik's Cube one time.", true));
        achievementItems.add(new AchievementItem("Easy", "fiverb", "Solve a Rubik's Cube five times.", true));
        achievementItems.add(new AchievementItem("Quick !", "underfive", "Complete a Rubik's Cube in less than 5 minutes.",false));

        // get list view

        ListView listView = view.findViewById(R.id.achievements_list_view);
        listView.setAdapter(new AchievementItemAdapter(getContext(), achievementItems));

        return view;
    }
}
