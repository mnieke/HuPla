package de.n1eke.hupla;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import de.n1eke.hupla.data.HuPlaEntry;
import de.n1eke.hupla.data.HuPlaTime;
import de.n1eke.hupla.data.HuPlaType;
import de.n1eke.hupla.data.HuPlaEntryDataSource;

/**
 * Created by michi on 02.08.14.
 */
public class MonthFragment extends Fragment{

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MonthFragment newInstance(int sectionNumber) {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MonthFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_month, container, false);

        HuPlaEntryDataSource dataSource = new HuPlaEntryDataSource(getActivity());
        dataSource.open();
        HuPlaEntry huPlaEntry = dataSource.createHuPlaEntry(new Date(System.currentTimeMillis()), HuPlaTime.MORNING, HuPlaType.WOLF);
        List<HuPlaEntry> entryList = dataSource.getAllHuPlaEntries();
        boolean success = dataSource.deleteHuPlaEntry(huPlaEntry);
        entryList = dataSource.getAllHuPlaEntries();
        int k = 1;

        return rootView;
    }
}
