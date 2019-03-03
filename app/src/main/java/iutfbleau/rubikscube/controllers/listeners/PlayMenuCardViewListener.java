package iutfbleau.rubikscube.controllers.listeners;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.fragments.PlayFragment;
import iutfbleau.rubikscube.controllers.fragments.PlayMenuFragment;

public class PlayMenuCardViewListener implements View.OnClickListener {

    private PlayMenuFragment playMenuFragment;

    public PlayMenuCardViewListener(PlayMenuFragment playMenuFragment) {
        this.playMenuFragment = playMenuFragment;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.retry:
                playMenuFragment.closeFragment();
                Toast.makeText(playMenuFragment.getActivity(), "Retry", Toast.LENGTH_SHORT).show();
                break;

            case R.id.randomize:
                playMenuFragment.closeFragment();
                Toast.makeText(playMenuFragment.getActivity(), "Randomize", Toast.LENGTH_SHORT).show();
                break;

            case R.id.size_selection:
                playMenuFragment.showRadioButtonDialog();
                break;

            case R.id.lock_rotation:

                playMenuFragment.closeFragment();

                PlayFragment playFragment = ((NavActivity) playMenuFragment.getActivity()).getPlayFragment();
                TextView textView = v.findViewById(R.id.lock_textView);
                ImageView imageView = v.findViewById(R.id.lock_imageView);

                if (!playFragment.isCubeLocked()) {

                    Toast.makeText(playMenuFragment.getActivity(), "Cube rotation locked.", Toast.LENGTH_SHORT).show();
                    //Lock method
                    imageView.setBackground(playMenuFragment.getActivity().getResources().getDrawable(R.drawable.baseline_lock_open_black_24dp));
                    textView.setText("Unlock Cube Rotation");

                } else {

                    Toast.makeText(playMenuFragment.getActivity(), "Cube rotation unlocked.", Toast.LENGTH_SHORT).show();
                    //Unlock method
                    imageView.setBackground(playMenuFragment.getActivity().getResources().getDrawable(R.drawable.baseline_lock_black_24dp));
                    textView.setText("Lock Cube Rotation");

                }

                playFragment.setCubeLockState(!playFragment.isCubeLocked());

                break;

            case R.id.website:
                playMenuFragment.launchWebsiteIntent();
                break;

            case R.id.credits:
                Toast.makeText(playMenuFragment.getActivity(), "Credits", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }


    }


}