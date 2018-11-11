package fr.max_91490.rubikscubemenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class SolveFragment extends Fragment {

    View view;

    public SolveFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_solve, container, false);

        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        final PlayFragment playFragment = new PlayFragment();

        final Button button1 = (Button)view.findViewById(R.id.button3);
        final Button button2 = (Button)view.findViewById(R.id.button4);


        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                v.startAnimation(buttonClick);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                v.startAnimation(buttonClick);
                Intent in = new Intent(getActivity(), CameraScan.class);
                //in.putExtra("some", "som data");
                startActivity(in);
            }
        });

        return view;
    }

}
