package android.liuwei.architecturaldemo.provider;

import android.liuwei.architecturaldemo.bean.News;
import android.liuwei.architecturaldemo.mvp.contract.IDetailContract.IDetailModel.Callback;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NewsProvider
{
    private static List<News> sNewsList = new ArrayList<>();

    static
    {
        for (int i = 1; i <= 10; i++)
        {
            News news = new News();
            news.setTitle("title:" + i);
            news.setDescription("description:" + i);
            news.setDate(new Date());
            news.setFree(i % 3 != 0);

            sNewsList.add(news);
        }
    }

    private Handler mHandler = new Handler();
    private Random mRandom = new Random(System.currentTimeMillis());

    public void request(final Callback callback)
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
                            callback.onTaskFailed();
                        }
                        else
                        {
                            callback.onTaskSuccess(sNewsList);
                        }
                    }
                });
            }
        }).start();
    }

}
