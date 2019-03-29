package android.liuwei.architecturaldemo.adapter;

import android.content.Context;
import android.liuwei.architecturaldemo.R;
import android.liuwei.architecturaldemo.adapter.ListAdapter.Holder;
import android.liuwei.architecturaldemo.bean.News;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends Adapter<Holder>
{
    private List<News> mList = new ArrayList<>();

    public void setList(List<News> list)
    {
        mList.clear();

        mList.addAll(list);

        notifyDataSetChanged();
    }

    private LayoutInflater mLayoutInflater;

    public ListAdapter(Context context)
    {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        return new Holder(mLayoutInflater.inflate(R.layout.adapter_news, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i)
    {
        holder.setData(mList.get(i));
    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    static class Holder extends ViewHolder
    {
        private TextView title;
        private TextView description;
        private TextView date;
        private TextView free;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, hh:mm", Locale.US);

        Holder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            description = itemView.findViewById(R.id.news_description);
            date = itemView.findViewById(R.id.news_date);
            free = itemView.findViewById(R.id.news_free);
        }

        void setData(News news)
        {
            title.setText(news.getTitle());
            description.setText(news.getDescription());
            date.setText(dateFormat.format(news.getDate()));
            free.setText(news.isFree() ? "Free" : "NoFree");
        }
    }
}