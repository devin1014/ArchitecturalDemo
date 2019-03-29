package android.liuwei.architecturaldemo.mvp.contract.imp;

import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountModel;
import android.liuwei.architecturaldemo.provider.AccountProvider;

public class AccountModel implements IAccountModel
{
    private AccountProvider mProvider = new AccountProvider();

    @Override
    public void signIn(String userName, Callback callback)
    {
        mProvider.logIn(userName, callback);
    }

    @Override
    public void signOut()
    {
        mProvider.logOut();
    }

    @Override
    public void start()
    {
    }

    @Override
    public void stop()
    {
    }
}
