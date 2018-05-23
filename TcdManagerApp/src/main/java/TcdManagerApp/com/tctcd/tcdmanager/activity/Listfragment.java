package TcdManagerApp.com.tctcd.tcdmanager.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import TcdManagerApp.com.tctcd.tcdmanager.R;

public class Listfragment extends Fragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment,container);
        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.Ad
        recyclerView.setAdapter();
        return view;
    }

}
