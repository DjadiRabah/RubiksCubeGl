package iutfbleau.rubikscube.controllers.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.controllers.activities.NavActivity;
import iutfbleau.rubikscube.controllers.listeners.PlayMenuCardViewListener;
import iutfbleau.rubikscube.controllers.listeners.RadioGroupListener;
import iutfbleau.rubikscube.controllers.listeners.ToolbarBackButtonListener;

public class PlayMenuFragment extends Fragment {

    private GridLayout mainGrid;
    private Dialog dialog;

    public PlayMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_playmenu, container, false);

        mainGrid = view.findViewById(R.id.mainGrid);

        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new PlayMenuCardViewListener(this));
        }

        ImageView settingsView = view.findViewById(R.id.settings);
        settingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, new PreferenceFragment());
                fragmentTransaction.commit();
            }
        });

        Toolbar toolbar = view.findViewById(R.id.playmenu_toolbar);
        toolbar.setTitle("");
        ((NavActivity) getActivity()).setSupportActionBar(toolbar);
        ((NavActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((NavActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.baseline_settings_black_24dp);
        toolbar.setOverflowIcon(drawable);

        PlayFragment playFragment = ((NavActivity) getActivity()).getPlayFragment();
        TextView textView = view.findViewById(R.id.lock_textView);
        ImageView imageView = view.findViewById(R.id.lock_imageView);

        if (playFragment.isCubeLocked()) {
            imageView.setBackground(getActivity().getResources().getDrawable(R.drawable.baseline_lock_open_black_24dp));
            textView.setText("Unlock Cube Rotation");
        } else {
            imageView.setBackground(getActivity().getResources().getDrawable(R.drawable.baseline_lock_black_24dp));
            textView.setText("Lock Cube Rotation");
        }

        toolbar.setNavigationOnClickListener(new ToolbarBackButtonListener(this));
        return view;
    }

    public void showRadioButtonDialog() {

        int min = 2;
        int max = 8;

        // custom dialog
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog);
        List<String> stringList = new ArrayList<>();  // here is list

        for (int i = min; i < max; i++) {

            stringList.add("Rubik's Cube " + (i + 1) + " x " + (i + 1));

        }
        RadioGroup rg = dialog.findViewById(R.id.radio_group);

        for (int i = 0; i < stringList.size(); i++) {
            RadioButton rb = new RadioButton(getActivity()); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }
        rg.setOnCheckedChangeListener(new RadioGroupListener(this));
        dialog.show();
    }

    public void updateSurfaceView(int nFaces) {
        Log.e("Size", String.valueOf(nFaces));
    }

    public void closeFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public Dialog getDialogFrame() {
        return dialog;
    }

    public void launchWebsiteIntent() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }
}
