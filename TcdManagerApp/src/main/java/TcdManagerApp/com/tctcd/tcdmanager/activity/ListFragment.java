package TcdManagerApp.com.tctcd.tcdmanager.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import TcdManagerApp.com.tctcd.tcdmanager.R;
import TcdManagerApp.com.tctcd.tcdmanager.adapter.RecyclerViewAdapter;
import TcdManagerApp.com.tctcd.tcdmanager.entity.Pay;
import TcdManagerApp.com.tctcd.tcdmanager.entity.Subsidies;
import TcdManagerApp.com.tctcd.tcdmanager.tools.BmobTools;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView loadingText;
    private RecyclerViewAdapter recyclerViewAdapter;
    private TextView teamText;
    private TextView spayText;
    private TextView payText;
    private TextView unPay;
    private List<Subsidies> mList = new ArrayList<>();
    private Pay mpay;
    private int openTag = -1;
    private View.OnClickListener mClickListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("yanglia","tag:"+v.getTag());
            Log.d("yanglia","openTag:"+openTag);
            if (openTag != -1) {
                recyclerView.getLayoutManager().findViewByPosition(openTag).findViewById(R.id.subsidies_details).setVisibility(View.GONE);
                if (Integer.valueOf(v.getTag().toString()) != openTag) {
                    v.findViewById(R.id.subsidies_details).setVisibility(View.VISIBLE);
                    openTag = Integer.valueOf(v.getTag().toString());
                } else {
                    openTag = -1;
                }

            } else {
                v.findViewById(R.id.subsidies_details).setVisibility(View.VISIBLE);
                openTag = Integer.valueOf(v.getTag().toString());
            }
        }

    };
    private Handler mhandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BmobTools.QUERY_SUCCESS:
                    recyclerViewAdapter.setList(mList);
                    loadingText.setVisibility(View.GONE);
                    recyclerViewAdapter.notifyDataSetChanged();
                    break;
                case BmobTools.QUERY_PAY_SUCCESS:
                    teamText.setText("Team:" + mpay.getGroup() + "(" + mpay.getPeopleCount() + "人)");
                    spayText.setText("应缴:"+mpay.getSpay());
                    payText.setText("实缴:"+mpay.getPaid());
                    unPay.setText("未缴："+mpay.getUnpaid());
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        loadingText = view.findViewById(R.id.loading);
        recyclerView = view.findViewById(R.id.recycler_view);
        teamText = view.findViewById(R.id.team_detail);
        teamText.setText("Team:");
        spayText = view.findViewById(R.id.should_pay);
        spayText.setText("应缴:");
        payText = view.findViewById(R.id.paid);
        payText.setText("实缴：");
        unPay = view.findViewById(R.id.unpaid);
        unPay.setText("未缴：");
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), mList, mClickListen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        //默认显示总的app
        queryAsGroup("Group", "");
        queryPayAsGroup("Group", "APP");
        openTag = -1;
        return view;
    }

    public void queryAsGroup(String Key, final String Value) {
        BmobQuery<Subsidies> query = new BmobQuery<Subsidies>("Subsidies");
        if (!Value.equals("")) {
            query.addWhereEqualTo(Key, Value);
        }
        query.findObjects(new FindListener<Subsidies>() {
            @Override
            public void done(List<Subsidies> list, BmobException e) {
                if (e == null) {
                    mList = list;
                    Message message = new Message();
                    message.what = BmobTools.QUERY_SUCCESS;
                    mhandle.sendMessage(message);
                    //Toast.makeText(getContext(), "查询数据成功"+list.size(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "查询数据失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void queryPayAsGroup(String Key, final String Value) {
        BmobQuery<Pay> query = new BmobQuery<Pay>("Pay");
        query.addWhereEqualTo(Key, Value);
        query.findObjects(new FindListener<Pay>() {
            @Override
            public void done(List<Pay> list, BmobException e) {
                if (e == null) {
                    mpay = list.get(0);
                    Message message = new Message();
                    message.what = BmobTools.QUERY_PAY_SUCCESS;
                    mhandle.sendMessage(message);
                    //Toast.makeText(getContext(), "查询数据成功"+list.size(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "查询数据失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
