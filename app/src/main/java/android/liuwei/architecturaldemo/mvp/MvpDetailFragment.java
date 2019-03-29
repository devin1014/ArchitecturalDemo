package android.liuwei.architecturaldemo.mvp;

import android.liuwei.architecturaldemo.R;
import android.liuwei.architecturaldemo.bean.News;
import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailView;
import android.liuwei.architecturaldemo.mvp.contract.imp.DetailModel;
import android.liuwei.architecturaldemo.mvp.contract.imp.DetailPresenter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MvpDetailFragment extends Fragment implements IDetailView
{
    private ProgressBar mLoading;
    private TextView mErrorMsg;
    private ListAdapter mListAdapter;

    private DetailPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.detail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mListAdapter = new ListAdapter());
        mLoading = view.findViewById(R.id.detail_loading);
        mErrorMsg = view.findViewById(R.id.detail_error_msg);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new DetailPresenter(this, new DetailModel());
        mPresenter.attachContext(getActivity());
    }

    @Override
    public void onDestroy()
    {
        mPresenter.detachContext();
        super.onDestroy();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showLoading()
    {
        mLoading.setVisibility(View.VISIBLE);
        mErrorMsg.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading()
    {
        mLoading.setVisibility(View.GONE);
    }

    @Override
    public void showList(List<News> list)
    {
        mListAdapter.setList(list);
        mErrorMsg.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String msg)
    {
        mErrorMsg.setText(msg);
        mErrorMsg.setVisibility(View.GONE);
    }

    // ----------------------------------------------------------------
    // - Adapter
    // ----------------------------------------------------------------
    private class ListAdapter extends Adapter<Holder>
    {
        private List<News> mList = new ArrayList<>();

        void setList(List<News> list)
        {
            mList.clear();

            mList.addAll(list);

            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            return new Holder(getLayoutInflater().inflate(R.layout.adapter_news, viewGroup, false));
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
    }

    private class Holder extends ViewHolder
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
