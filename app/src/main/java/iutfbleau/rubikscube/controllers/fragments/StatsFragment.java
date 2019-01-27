package iutfbleau.rubikscube.controllers.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.models.ViewPagerAdapter;

public class StatsFragment extends Fragment {

    public StatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        ViewPager viewPager = view.findViewById(R.id.pager);
        setupViewPager(viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AchievementsFragment(), "Achievements");
        adapter.addFragment(new RankingFragment(), "Ranking");
        setRetainInstance(true);
        viewPager.setAdapter(adapter);

    }
}
