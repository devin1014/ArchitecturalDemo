package android.liuwei.architecturaldemo.mvp.contract.imp;

import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailModel;
import android.liuwei.architecturaldemo.provider.NewsProvider;

public class DetailModel implements IDetailModel
{
    private NewsProvider mProvider = new NewsProvider();

    @Override
    public void start()
    {
    }

    @Override
    public void stop()
    {
    }

    @Override
    public void load(Callback callback)
    {
        mProvider.request(callback);
    }
}
