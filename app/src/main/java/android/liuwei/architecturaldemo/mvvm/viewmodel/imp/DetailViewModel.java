package android.liuwei.architecturaldemo.mvvm.viewmodel.imp;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.liuwei.architecturaldemo.adapter.RecyclerViewAdapterFactory;
import android.liuwei.architecturaldemo.adapter.RecyclerViewAdapterFactory.NewsAdapterFactory;
import android.liuwei.architecturaldemo.bean.News;
import android.liuwei.architecturaldemo.databinding.FragmentDetailVbBinding;
import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailModel;
import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailModel.Callback;
import android.liuwei.architecturaldemo.mvvm.viewmodel.BaseViewModel;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;

import java.util.List;

public class DetailViewModel extends BaseViewModel
{
    private ObservableBoolean mLoading = new ObservableBoolean(true);
    private ObservableBoolean mError = new ObservableBoolean(false);
    private ObservableField<String> mErrorMsg = new ObservableField<>();
    private ObservableField<List<News>> mItems = new ObservableField<>();
    //TODO: should bind adapter&layout in xml!!!
    private RecyclerViewAdapterFactory mAdapterFactory = new NewsAdapterFactory();

    private IDetailModel mModel;

    public DetailViewModel(Context context, IDetailModel model)
    {
        super(context);

        mModel = model;
        addOnPropertyChangedCallback(mLoading);
        addOnPropertyChangedCallback(mError);
        addOnPropertyChangedCallback(mErrorMsg);
        addOnPropertyChangedCallback(mItems);
    }

    public <T extends ViewDataBinding> void setViewBinding(T viewBinding)
    {
        ((FragmentDetailVbBinding) viewBinding).setViewModel(this);
    }

    public boolean isLoading()
    {
        return mLoading.get();
    }

    public boolean isError()
    {
        return mError.get();
    }

    public String getErrorMsg()
    {
        return mErrorMsg.get();
    }

    public void start()
    {
        mModel.load(new Callback()
        {
            @Override
            public void onTaskSuccess(List<News> list)
            {
                mLoading.set(false);
                mItems.set(list);
            }

            @Override
            public void onTaskFailed()
            {
                mLoading.set(false);
                mError.set(true);
                mErrorMsg.set("Load news failed!");
            }
        });
    }

    public LayoutManager getLayoutManager()
    {
        return new LinearLayoutManager(getContext());
    }

    public RecyclerViewAdapterFactory getAdapterFactory()
    {
        return mAdapterFactory;
    }

    public List<News> getItems()
    {
        return mItems.get();
    }
}
