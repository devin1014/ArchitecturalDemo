package android.liuwei.architecturaldemo.provider;

import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountModel.Callback;
import android.os.Handler;

import java.util.Random;

public class AccountProvider
{
    private Handler mHandler = new Handler();
    private Random mRandom = new Random(System.currentTimeMillis());

    public void logIn(final String userName, final Callback callback)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000L);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        int num = mRandom.nextInt(100);
                        if (num > 90)
                        {
                            callback.onTaskFailed("error");
                        }
                        else
                        {
                            callback.onTaskSuccess(AccountManager.getInstance().newInstance(userName));
                        }
                    }
                });
            }
        }).start();
    }

    public void logOut()
    {
        AccountManager.getInstance().setUser(null);
    }
}