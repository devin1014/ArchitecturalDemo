package android.liuwei.architecturaldemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.liuwei.architecturaldemo.BR;
import android.liuwei.architecturaldemo.R;
import android.liuwei.architecturaldemo.adapter.ListBindingAdapter.BindingHolder;
import android.liuwei.architecturaldemo.bean.News;
import android.liuwei.architecturaldemo.mvvm.viewmodel.imp.NewsItemModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ListBindingAdapter extends Adapter<BindingHolder>
{
    private List<NewsItemModel> mList = new ArrayList<>();

    public void setList(List<News> list)
    {
        mList.clear();

        for (News n : list)
        {
            mList.add(new NewsItemModel(n));
        }

        notifyDataSetChanged();
    }

    private LayoutInflater mLayoutInflater;

    public ListBindingAdapter(Context context)
    {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        return new BindingHolder(DataBindingUtil.inflate(mLayoutInflater, R.layout.adapter_news_vb, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int i)
    {
        holder.viewDataBinding.setVariable(BR.viewModel, mList.get(i));
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    // ---------------------------------------------------------------------------------
    // - Holder ----
    // ---------------------------------------------------------------------------------
    static class BindingHolder extends ViewHolder
    {
        final ViewDataBinding viewDataBinding;

        BindingHolder(ViewDataBinding viewDataBinding)
        {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }
    }
}