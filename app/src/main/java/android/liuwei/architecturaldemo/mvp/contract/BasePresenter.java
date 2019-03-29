package android.liuwei.architecturaldemo.mvp.contract;

import android.content.Context;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBaseModel;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBasePresenter;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IBaseView, M extends IBaseModel> implements IBasePresenter
{
    private Reference<Context> mReference;
    protected V mView;
    protected M mModel;

    public BasePresenter(V view, M model)
    {
        mView = view;
        mModel = model;
    }

    @Override
    public void attachContext(Context context)
    {
        mReference = new WeakReference<>(context);
    }

    @Override
    public void detachContext()
    {
        if (mReference != null)
        {
            mReference.clear();
        }

        if (mModel != null)
        {
            mModel.stop();
        }
    }

    protected Context getContext()
    {
        return mReference != null ? mReference.get() : null;
    }
}
