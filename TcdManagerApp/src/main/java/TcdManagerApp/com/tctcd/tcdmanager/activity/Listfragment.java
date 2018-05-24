package TcdManagerApp.com.tctcd.tcdmanager.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    private TextView loadingText;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Subsidies> mList = new ArrayList<>();
    private Handler mhandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case QUERY_SUCCESS:
                    recyclerViewAdapter.setList(mList);
                    loadingText.setVisibility(View.GONE);
                    recyclerViewAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container,false);
        loadingText = view.findViewById(R.id.loading);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        queryAsGroup("Group","");
        return view;
    }

    public void queryAsGroup(String Key,String Value) {
        BmobQuery<Subsidies> query = new BmobQuery<Subsidies>("Subsidies");
        if(!Value.equals("")){
            query.addWhereEqualTo(Key,Value);
        }
        query.findObjects(new FindListener<Subsidies>() {
            @Override
            public void done(List<Subsidies> list, BmobException e) {
                if (e == null) {
                    mList = list;
                    Message message = new Message();
                    message.what = QUERY_SUCCESS;
                    mhandle.sendMessage(message);
                    //Toast.makeText(getContext(), "查询数据成功"+list.size(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "查询数据失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
