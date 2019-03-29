package android.liuwei.architecturaldemo.mvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BaseViewModel extends BaseObservable
{
    private Reference<Context> mReference;

    public BaseViewModel(Context context)
    {
        mReference = new WeakReference<>(context);
    }

    protected final Context getContext()
    {
        return mReference.get();
    }

    protected final <T extends BaseObservable> void addOnPropertyChangedCallback(T t)
    {
        t.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
    }

    @SafeVarargs
    protected final <T extends BaseObservable> void addOnPropertyChangedCallback(T... array)
    {
        for (T t : array)
        {
            t.addOnPropertyChangedCallback(mOnPropertyChangedCallback);
        }
    }

    @SuppressWarnings("FieldCanBeLocal")
    private OnPropertyChangedCallback mOnPropertyChangedCallback = new OnPropertyChangedCallback()
    {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId)
        {
            BaseViewModel.this.onPropertyChanged(sender, propertyId);
        }
    };

    @SuppressWarnings("unused")
    protected void onPropertyChanged(Observable sender, int propertyId)
    {
        notifyChange();
    }

    public abstract <T extends ViewDataBinding> void setViewBinding(T viewBinding);

    public void start()
    {
    }

    public void stop()
    {
        mReference.clear();
    }
}
