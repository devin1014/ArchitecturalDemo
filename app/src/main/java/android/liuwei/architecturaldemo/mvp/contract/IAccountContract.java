package android.liuwei.architecturaldemo.mvp.contract;

import android.liuwei.architecturaldemo.bean.User;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBaseModel;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBasePresenter;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBaseView;

public interface IAccountContract
{
    interface IAccountView extends IBaseView
    {
        void showSignInView();

        void showSignOutView(User user);
    }

    interface IAccountModel extends IBaseModel
    {
        void signIn(String userName, Callback callback);

        void signOut();

        interface Callback
        {
            void onTaskSuccess(User user);

            void onTaskFailed(String error);
        }
    }

    interface IAccountPresenter extends IBasePresenter
    {
        void signIn(String userName);

        void signOut();
    }
}
