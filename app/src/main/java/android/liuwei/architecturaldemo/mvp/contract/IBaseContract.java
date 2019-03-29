package android.liuwei.architecturaldemo.mvp.contract;

import android.content.Context;

public interface IBaseContract
{
    interface IBaseModel
    {
        void start();

        void stop();
    }

    interface IBaseView
    {
        void showLoading();

        void hideLoading();
    }

    interface IBasePresenter
    {
        void attachContext(Context context);

        void detachContext();

        void start();

        void stop();
    }
}
