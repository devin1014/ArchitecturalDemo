package android.liuwei.architecturaldemo.mvvm.viewmodel.imp;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.liuwei.architecturaldemo.bean.User;
import android.liuwei.architecturaldemo.databinding.FragmentAccountVbBinding;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountModel;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountModel.Callback;
import android.liuwei.architecturaldemo.mvvm.viewmodel.BaseViewModel;
import android.widget.Toast;

public class AccountViewModel extends BaseViewModel
{
    private ObservableField<User> mUserField = new ObservableField<>();
    private ObservableField<String> mEditUserName = new ObservableField<>();
    private ObservableBoolean mLoading = new ObservableBoolean(false);

    private IAccountModel mModel;

    public AccountViewModel(Context context, IAccountModel model)
    {
        super(context);

        mModel = model;

        addOnPropertyChangedCallback(mUserField);
        addOnPropertyChangedCallback(mEditUserName);
        addOnPropertyChangedCallback(mLoading);
    }

    @Override
    public <T extends ViewDataBinding> void setViewBinding(T viewBinding)
    {
        ((FragmentAccountVbBinding) viewBinding).setViewModel(this);
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

    public boolean isLoading()
    {
        return mLoading.get();
    }

    public void signOut()
    {
        mModel.signOut();

        mUserField.set(null);
    }

    public void signIn()
    {
        mLoading.set(true);

        mModel.signIn(getEditUserName(), new Callback()
        {
            @Override
            public void onTaskSuccess(User user)
            {
                mLoading.set(false);

                mUserField.set(user);

                Toast.makeText(getContext(), "登入成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskFailed(String error)
            {
                mLoading.set(false);

                mUserField.set(null);

                Toast.makeText(getContext(), "登入失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
