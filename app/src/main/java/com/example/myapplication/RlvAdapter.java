package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.example.myapplication.vo.ReclcyerViewSelectVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.example.myapplication
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2019.02.22 上午 11:22
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.RlvViewHolder> {
    private Context mContext;
    private List<?> mListDatas;
    private LayoutInflater mInflater;
    private final List<ReclcyerViewSelectVo> mVos;

    public RlvAdapter(Context mContext, List<?> mListDatas) {
        this.mContext = mContext;
        this.mListDatas = mListDatas;
        mInflater = LayoutInflater.from(mContext);
        mVos = new ArrayList<>();
        for (int i = 0; i < mListDatas.size(); i++) {
            ReclcyerViewSelectVo vo = new ReclcyerViewSelectVo();
            vo.setSelect(false);
            mVos.add(vo);
        }
    }

    @NonNull
    @Override
    public RlvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item, null);
        RlvViewHolder holder = new RlvViewHolder(inflate);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final RlvViewHolder holder, final int position) {
        String s = (String) mListDatas.get(position);
        holder.mChv.setText(s);
        ReclcyerViewSelectVo vo = mVos.get(position);
        holder.mChv.setChecked(vo.isSelect());
        holder.mChv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReclcyerViewSelectVo vo = mVos.get(position);
                vo.setSelect(true);
                holder.mChv.setChecked(true);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListDatas.size();
    }

    public class RlvViewHolder extends RecyclerView.ViewHolder {
        public CheckedTextView mChv;

        public RlvViewHolder(View itemView) {
            super(itemView);
            this.mChv = (CheckedTextView) itemView.findViewById(R.id.chv);
        }
    }


}
