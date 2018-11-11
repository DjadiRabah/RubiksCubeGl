package fr.max_91490.rubikscubemenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseStepFragment extends Fragment {


    private View view;


    public ChooseStepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_choose_step, container, false);

        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        final PlayFragment playFragment = new PlayFragment();

        final Button button1 = (Button)view.findViewById(R.id.button1);
        final Button button2 = (Button)view.findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                v.startAnimation(buttonClick);
                setFragment(playFragment);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                v.startAnimation(buttonClick);
                setFragment(playFragment);
            }
        });

        return view;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
