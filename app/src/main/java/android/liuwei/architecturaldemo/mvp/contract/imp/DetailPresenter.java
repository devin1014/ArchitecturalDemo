package android.liuwei.architecturaldemo.mvp.contract.imp;

import android.liuwei.architecturaldemo.bean.News;
import android.liuwei.architecturaldemo.mvp.contract.BasePresenter;
import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailModel;
import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailModel.Callback;
import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailPresenter;
import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailView;

import java.util.List;

public class DetailPresenter extends BasePresenter<IDetailView, IDetailModel> implements IDetailPresenter
{
    public DetailPresenter(IDetailView view, IDetailModel model)
    {
        super(view, model);
    }

    @Override
    public void start()
    {
        mView.showLoading();

        mModel.load(new Callback()
        {
            @Override
            public void onTaskSuccess(List<News> list)
            {
                mView.hideLoading();

                mView.showList(list);
            }

            @Override
            public void onTaskFailed()
            {
                mView.hideLoading();

                mView.showErrorMsg("Error");
            }
        });
    }

    @Override
    public void stop()
    {
    }
}
