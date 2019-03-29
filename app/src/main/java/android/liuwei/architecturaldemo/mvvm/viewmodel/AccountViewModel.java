package android.liuwei.architecturaldemo.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.liuwei.architecturaldemo.bean.User;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountModel;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountModel.Callback;
import android.util.Log;
import android.view.View;

public class AccountViewModel extends BaseObservable
{
    private ObservableField<User> mUserField = new ObservableField<>();

    private ObservableField<String> mEditUserName = new ObservableField<>();

    private ObservableField<Boolean> mSignInLoading = new ObservableField<>();

    private IAccountModel mModel;

    public AccountViewModel(IAccountModel model)
    {
        mModel = model;

        mUserField.addOnPropertyChangedCallback(new OnPropertyChangedCallback()
        {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId)
            {
                Log.i("DataBinding", "onPropertyChanged:" + propertyId);

                User user = mUserField.get();

                if (user == null)
                {
                    Log.i("DataBinding", "user=null");
                }
                else
                {
                    Log.i("DataBinding", "user=" + user.getName());
                }
            }
        });

        mEditUserName.set("");

        mSignInLoading.set(false);
    }

    public void setUser(User user)
    {
        mUserField.set(user);
    }

    public void setEditUserName(String string)
    {
        mEditUserName.set(string);
    }

    public String getEditUserName()
    {
        return mEditUserName.get();
    }

    public boolean isAuth()
    {
        return mUserField.get() != null;
    }

    public String getUserName()
    {
        User user = mUserField.get();

        return user != null ? user.getName() : "";
    }

    public String getSku()
    {
        User user = mUserField.get();

        return user != null ? user.getSku() : "";
    }

    public boolean isVip()
    {
        User user = mUserField.get();

        return user != null && user.isVip();
    }

    public boolean isSignInLoading()
    {
        return mSignInLoading.get() != null && mSignInLoading.get();
    }

    public void signOut(View view)
    {
        mModel.signOut();

        notifyChange();
        setUser(null);
    }

    public void signIn(View view)
    {
        notifyChange();
        mSignInLoading.set(true);
        mModel.signIn(getEditUserName(), new Callback()
        {
            @Override
            public void onTaskSuccess(User user)
            {
                notifyChange();
                mSignInLoading.set(false);
                setUser(user);
            }

            @Override
            public void onTaskFailed(String error)
            {
                notifyChange();
                mSignInLoading.set(false);
                setUser(null);
            }
        });
    }
}
