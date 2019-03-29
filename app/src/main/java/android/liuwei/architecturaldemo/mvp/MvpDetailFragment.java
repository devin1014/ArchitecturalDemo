package android.liuwei.architecturaldemo.mvp;

import android.liuwei.architecturaldemo.R;
import android.liuwei.architecturaldemo.adapter.ListAdapter;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

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
        recyclerView.setAdapter(mListAdapter = new ListAdapter(getActivity()));
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
}
