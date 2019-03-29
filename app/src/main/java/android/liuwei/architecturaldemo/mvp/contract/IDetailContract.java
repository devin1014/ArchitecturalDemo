package android.liuwei.architecturaldemo.mvp.contract;

import android.liuwei.architecturaldemo.bean.News;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBaseModel;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBasePresenter;
import android.liuwei.architecturaldemo.mvp.contract.IBaseContract.IBaseView;

import java.util.List;

public interface IDetailContract
{
    interface IDetailModel extends IBaseModel
    {
        void load(Callback callback);

        interface Callback
        {
            void onTaskSuccess(List<News> list);

            void onTaskFailed();
        }
    }

    interface IDetailView extends IBaseView
    {
        void showList(List<News> list);

        void showErrorMsg(String msg);
    }

    interface IDetailPresenter extends IBasePresenter
    {
    }
}
