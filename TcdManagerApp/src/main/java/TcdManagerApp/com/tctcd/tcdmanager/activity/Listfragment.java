package TcdManagerApp.com.tctcd.tcdmanager.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import TcdManagerApp.com.tctcd.tcdmanager.R;
import TcdManagerApp.com.tctcd.tcdmanager.adapter.RecyclerViewAdapter;
import TcdManagerApp.com.tctcd.tcdmanager.entity.Subsidies;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Listfragment extends Fragment {

    public static final int QUERY_SUCCESS = 0X00;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Subsidies> mList;
    private Handler mhandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case QUERY_SUCCESS:
                    recyclerViewAdapter = new RecyclerViewAdapter(getContext(),mList);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container);
        recyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    private void query() {
        BmobQuery<Subsidies> query = new BmobQuery<Subsidies>("Subsidies");
        query.findObjects(new FindListener<Subsidies>() {
            @Override
            public void done(List<Subsidies> list, BmobException e) {
                if (e == null) {
                    mList = list;
                    Message message = new Message();
                    message.what = QUERY_SUCCESS;
                    mhandle.sendMessage(message);
                } else {
                    Toast.makeText(getContext(), "查询数据失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
