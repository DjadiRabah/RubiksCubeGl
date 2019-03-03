package iutfbleau.rubikscube.controllers.listeners;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class ActionBarDrawerToggler extends ActionBarDrawerToggle{

    public static boolean drawerLayoutClosed = true;
    private Activity activity;

    public ActionBarDrawerToggler(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes,
                                  int closeDrawerContentDescRes){
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        this.activity = activity;

    }

    //Close state
    public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        drawerLayoutClosed = true;
        Toast.makeText(activity.getApplicationContext(), ""+drawerLayoutClosed, Toast.LENGTH_SHORT).show();
    }

    //Open state
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        drawerLayoutClosed = false;
        Toast.makeText(activity.getApplicationContext(), ""+drawerLayoutClosed, Toast.LENGTH_SHORT).show();
    }

    //Sliding state
    public void onDrawerSlide(View drawerView,float slideOffset) {
        super.onDrawerSlide(drawerView, slideOffset);
        drawerLayoutClosed = false;
        Toast.makeText(activity.getApplicationContext(), "sliding"+drawerLayoutClosed, Toast.LENGTH_SHORT).show();
    }

}
