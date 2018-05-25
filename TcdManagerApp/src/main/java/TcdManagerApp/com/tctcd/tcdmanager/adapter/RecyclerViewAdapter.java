package TcdManagerApp.com.tctcd.tcdmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import TcdManagerApp.com.tctcd.tcdmanager.R;
import TcdManagerApp.com.tctcd.tcdmanager.entity.Subsidies;
import TcdManagerApp.com.tctcd.tcdmanager.tools.BmobTools;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context mContext;
    private List<Subsidies> mList;
    private View.OnLongClickListener mlongClickListen;
    public RecyclerViewAdapter(Context context, List<Subsidies> list,View.OnLongClickListener longClickListen) {
        this.mList = list;
        this.mContext = context;
        this.mlongClickListen = longClickListen;
    }

    public void setList(List<Subsidies> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view,mlongClickListen);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //登录用户默认高亮
        if(BmobTools.userEntity.getName().equals(mList.get(position).getName())){
            holder.itemView.setBackground(mContext.getDrawable(R.drawable.list_item_bg_highlight));
        } else {
            //viewhold会重用，所以需要设置回去
            holder.itemView.setBackground(mContext.getDrawable(R.drawable.list_item_bg));
        }
        holder.itemView.setTag(position);
        Log.d("yangli","position:"+position);
        holder.nameText.setText(mList.get(position).getName());
        holder.monthText.setText(mList.get(position).getMonth());
        holder.moneyText.setText(mList.get(position).getMoney());
        holder.dinnercount.setText("加班到7:30后次数："+mList.get(position).getDinnerCount()+"次");
        holder.weekendcount.setText("周末加班次数："+mList.get(position).getWeekendCount()+"次");
        holder.packing.setText("停车费："+mList.get(position).getParking()+"元");
        holder.taxi.setText("打车费："+mList.get(position).getTaxiMoney()+"元");
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView nameText;
        private TextView monthText;
        private TextView moneyText;
        private TextView dinnercount;
        private TextView weekendcount;
        private TextView packing;
        private TextView taxi;
        public MyViewHolder(View itemView,View.OnLongClickListener listen) {
            super(itemView);
            this.itemView = itemView;
            itemView.setOnLongClickListener(listen);
            this.nameText = itemView.findViewById(R.id.name);
            this.monthText = itemView.findViewById(R.id.month);
            this.moneyText = itemView.findViewById(R.id.money);
            this.dinnercount = itemView.findViewById(R.id.dinner_count);
            this.weekendcount = itemView.findViewById(R.id.weekend_count);
            this.packing = itemView.findViewById(R.id.packing);
            this.taxi = itemView.findViewById(R.id.taxi);
        }
    }

}
