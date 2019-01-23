package iutfbleau.rubikscube.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import iutfbleau.rubikscube.view.AchievementsFragment;
import iutfbleau.rubikscube.view.RankingFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int tabsNumber;

    public PagerAdapter(FragmentManager fm, int tabsNumber) {

        super(fm);
        this.tabsNumber = tabsNumber;

    }


    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0 :
                AchievementsFragment achievementsFragment = new AchievementsFragment();
                return achievementsFragment;

            case 1 :
                RankingFragment rankingFragment = new RankingFragment();
                return rankingFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
