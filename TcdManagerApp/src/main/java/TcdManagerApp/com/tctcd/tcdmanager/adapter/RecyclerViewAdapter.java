package TcdManagerApp.com.tctcd.tcdmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
        holder.nameText.setText(mList.get(position).getName());
        holder.monthText.setText(mList.get(position).getMonth());
        holder.moneyText.setText(mList.get(position).getMoney());
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
        public MyViewHolder(View itemView,View.OnLongClickListener listen) {
            super(itemView);
            this.itemView = itemView;
            itemView.setOnLongClickListener(listen);
            this.nameText = itemView.findViewById(R.id.name);
            this.monthText = itemView.findViewById(R.id.month);
            this.moneyText = itemView.findViewById(R.id.money);
        }
    }

}
