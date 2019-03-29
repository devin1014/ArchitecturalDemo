package android.liuwei.architecturaldemo.mvp.contract.imp;

import android.liuwei.architecturaldemo.bean.User;
import android.liuwei.architecturaldemo.mvp.contract.BasePresenter;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountModel;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountModel.Callback;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountPresenter;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountView;

public class AccountPresenter extends BasePresenter<IAccountView, IAccountModel> implements IAccountPresenter
{
    public AccountPresenter(IAccountView view, IAccountModel model)
    {
        super(view, model);
    }

    @Override
    public void start()
    {
    }

    @Override
    public void stop()
    {
    }

    @Override
    public void signIn(String userName)
    {
        mView.showLoading();

        mModel.signIn(userName, new Callback()
        {
            @Override
            public void onTaskSuccess(User user)
            {
                mView.hideLoading();

                mView.showSignOutView(user);
            }

            @Override
            public void onTaskFailed(String error)
            {
                //TODO
            }
        });
    }

    @Override
    public void signOut()
    {
        mModel.signOut();

        mView.showSignInView();
    }
}
