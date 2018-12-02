package fr.max_91490.rubikscubemenu;

import android.content.Intent;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View view;
    private GLSurfaceView glSurfaceView;

    public PlayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_play, container, false);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.undo_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Clic sur retour !", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        // 1 - Configure Toolbar
        this.toolbar = (Toolbar) view.findViewById(R.id.play_toolbar);
       ((NavActivity)getActivity()).setSupportActionBar(this.toolbar);
       ((NavActivity)getActivity()).getSupportActionBar().setTitle(null);

        // 2 - Configure Drawer Layout
        this.drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 3 - Configure NavigationView
        this.navigationView = (NavigationView) view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);



        glSurfaceView = (GLSurfaceView) view.findViewById(R.id.glsurfaceview);
        glSurfaceView.setRenderer(new OpenGLRenderer());

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.nav_restart :
                Toast.makeText(getActivity(), "Recommencer le Rubik's Cube !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_mix:
                Toast.makeText(getActivity(), "Mélanger le Rubik's Cube !", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rbsize:
                Toast.makeText(getActivity(), "Choisir taille", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_lockrotation:
                Toast.makeText(getActivity(), "Rotation bloquée", Toast.LENGTH_SHORT).show();
                break;
            case R.id.play_website:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
                break;
            case R.id.play_credits:
                Toast.makeText(getActivity(), "À propos", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
