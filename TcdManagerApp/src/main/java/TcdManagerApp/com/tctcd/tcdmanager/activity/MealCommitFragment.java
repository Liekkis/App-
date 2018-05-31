package TcdManagerApp.com.tctcd.tcdmanager.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import TcdManagerApp.com.tctcd.tcdmanager.R;
import TcdManagerApp.com.tctcd.tcdmanager.entity.Pay;
import TcdManagerApp.com.tctcd.tcdmanager.tools.BmobTools;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class MealCommitFragment extends Fragment {
    private Pay mPay;
    private TextView teamText;
    private TextView spayText;
    private TextView payText;
    private TextView unPay;
    private EditText payEdit;
    private Button commitButton;
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BmobTools.QUERY_SUCCESS:
                    //通过查询到的数据更新界面
                    spayText.setText("应缴:" + mPay.getSpay());
                    payText.setText("实缴：" + (mPay.getPaid() == null?0:Integer.valueOf(mPay.getPaid())));
                    unPay.setText("未缴：" + mPay.getUnpaid());
                    payEdit.setText("");
                    break;
                case BmobTools.UPDATE_PAY_SUCCESS:
                    queryAsGroup();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meal_commit, container, false);
        teamText = view.findViewById(R.id.team);
        teamText.setText("Team:" + BmobTools.userEntity.getGroup());
        spayText = view.findViewById(R.id.app_should_pay);
        payText = view.findViewById(R.id.app_paid);
        unPay = view.findViewById(R.id.app_unpaid);
        payEdit = view.findViewById(R.id.pay_edit);
        commitButton = view.findViewById(R.id.commit);
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePay();
            }
        });
        queryAsGroup();
        return view;
    }

    private void updatePay() {
        final Pay pay = new Pay();
        Log.d("yangli",Integer.toString((mPay.getPaid() == null?0:Integer.valueOf(mPay.getPaid())) + Integer.valueOf(payEdit.getText().toString())));
        pay.setPaid(Integer.toString(
                (mPay.getPaid() == null?0:Integer.valueOf(mPay.getPaid()))
                        + Integer.valueOf(payEdit.getText().toString())));
        pay.setUnpaid(Integer.toString(Integer.valueOf(mPay.getUnpaid()) - Integer.valueOf(payEdit.getText().toString())));
        pay.update(mPay.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d("yangli",pay.getUpdatedAt()) ;
                    Message message = new Message();
                    message.what = BmobTools.UPDATE_PAY_SUCCESS;
                    mHandle.sendMessage(message);
                } else {

                }
            }
        });
    }

    public void queryAsGroup() {
        BmobQuery<Pay> query = new BmobQuery<Pay>("Pay");
        Log.d("yangli",BmobTools.userEntity.getGroup()) ;
        query.addWhereEqualTo("Group", BmobTools.userEntity.getGroup());
        query.findObjects(new FindListener<Pay>() {
            @Override
            public void done(List<Pay> list, BmobException e) {
                if (e == null) {
                    mPay = list.get(0);
                    Log.d("yangli",list.get(0).getPaid()) ;
                    Message message = new Message();
                    message.what = BmobTools.QUERY_SUCCESS;
                    mHandle.sendMessage(message);
                } else {
                    Toast.makeText(getContext(), "查询数据失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
