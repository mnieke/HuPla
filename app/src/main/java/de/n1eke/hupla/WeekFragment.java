package de.n1eke.hupla;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import de.n1eke.hupla.data.DataHolder;

/**
 * Created by michi on 02.08.14.
 */
public class WeekFragment extends Fragment{

    ArrayList<Drawable> drawableList;
    View rootView;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static WeekFragment newInstance(int sectionNumber) {
        WeekFragment fragment = new WeekFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public WeekFragment() {
        drawableList = new ArrayList<Drawable>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_week, container, false);

        checkDrawables();
        return rootView;
    }

    private void checkDrawables() {
        DataHolder.getInstance();
    }
}
