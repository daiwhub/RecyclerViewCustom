package com.daiw.recyclerviewcustom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.daiw.recyclerviewcustom.R;

import java.util.ArrayList;
import java.util.List;

/****************************
 * 类描述：
 *
 * @author: daiw
 * @time: 2021/9/1  10:32 下午
 *
 *         ***************************
 */

public class SlideAdapter extends BaseQuickAdapter<String,SlideAdapter.SlideViewHolder> {
    private Context mContext;

    public SlideAdapter(Context context) {
        super(R.layout.recycle_item_slide_menu);
        for (int i=1;i<=20;i++){
            getData().add("消息"+i);
        }
        mContext = context;
    }

    @Override
    protected void convert(@NonNull SlideViewHolder slideViewHolder, String string) {
        slideViewHolder.mMsgTv.setText(string);
        if (!slideViewHolder.mDeleteTv.hasOnClickListeners()) {
            slideViewHolder.mDeleteTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData().remove(slideViewHolder.getAdapterPosition());
                    notifyItemRemoved(slideViewHolder.getAdapterPosition());
                }
            });
        }
    }

    public class SlideViewHolder extends BaseViewHolder {
        private TextView mDeleteTv;
        private TextView mMsgTv;
        private TextView mCollectTv;
        private SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            mDeleteTv = itemView.findViewById(R.id.tv_delete);
            mMsgTv = itemView.findViewById(R.id.tv_msg);
            mCollectTv = itemView.findViewById(R.id.tv_collect);
            mMsgTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                }
            });
            mCollectTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

