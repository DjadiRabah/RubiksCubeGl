package iutfbleau.rubikscube.controllers.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.GeneratedCubeDemoActivity;
import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.activities.SolverActivity;
import iutfbleau.rubikscube.controllers.fragments.PlayFragment;
import iutfbleau.rubikscube.controllers.fragments.GameMenuFragment;
import iutfbleau.rubikscube.models.Array3DTransmitter;

public class PlayMenuCardViewListener implements View.OnClickListener {

    private GameMenuFragment gameMenuFragment;

    public PlayMenuCardViewListener(GameMenuFragment gameMenuFragment) {
        this.gameMenuFragment = gameMenuFragment;
    }


    @Override
    public void onClick(View v) {

        PlayFragment playFragment = ((NavActivity) gameMenuFragment.getActivity()).getPlayFragment();

        switch (v.getId()) {

            case R.id.retry:
                gameMenuFragment.closeFragment();
                Toast.makeText(gameMenuFragment.getActivity(), "Retry", Toast.LENGTH_SHORT).show();
                break;

            case R.id.randomize:
                gameMenuFragment.closeFragment();
                Toast.makeText(gameMenuFragment.getActivity(), "Randomize", Toast.LENGTH_SHORT).show();
                break;

            case R.id.size_selection:
                gameMenuFragment.showRadioButtonDialog();
                break;

            case R.id.lock_rotation:

                gameMenuFragment.closeFragment();

                TextView textView = v.findViewById(R.id.lock_textView);

                if (!playFragment.isCubeLocked()) {

                    Toast.makeText(gameMenuFragment.getActivity(), "Cube rotation locked.", Toast.LENGTH_SHORT).show();
                    //Lock method
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, gameMenuFragment.getActivity().getResources().getDrawable(R.drawable.baseline_lock_open_black_24dp), null, null);
                    textView.setText("Unlock Cube Rotation");

                } else {

                    Toast.makeText(gameMenuFragment.getActivity(), "Cube rotation unlocked.", Toast.LENGTH_SHORT).show();
                    //Unlock method
                    textView.setCompoundDrawablesWithIntrinsicBounds(null, gameMenuFragment.getActivity().getResources().getDrawable(R.drawable.baseline_lock_black_24dp), null, null);
                    textView.setText("Lock Cube Rotation");

                }

                playFragment.setCubeLockState(!playFragment.isCubeLocked());

                break;

            case R.id.launch_solver:

                Intent intent = new Intent(gameMenuFragment.getActivity(), SolverActivity.class);
                intent.putExtra("task", GeneratedCubeDemoActivity.BUILD_FROM_ARRAY);
                intent.putExtra("cubeDescriptor", new Array3DTransmitter(playFragment.getPlayFragmentCubeFaces()));
                gameMenuFragment.startActivity(intent);

                break;

            case R.id.website:
                gameMenuFragment.launchWebsiteIntent();
                break;

            default:
                break;
        }
    }
}