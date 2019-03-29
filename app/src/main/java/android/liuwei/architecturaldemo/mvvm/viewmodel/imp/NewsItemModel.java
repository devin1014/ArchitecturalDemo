package android.liuwei.architecturaldemo.mvvm.viewmodel.imp;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.liuwei.architecturaldemo.bean.News;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewsItemModel extends BaseObservable
{
    private ObservableField<String> title = new ObservableField<>();
    private ObservableField<String> description = new ObservableField<>();
    private ObservableField<Date> date = new ObservableField<>(new Date());
    private ObservableBoolean free = new ObservableBoolean();

    public NewsItemModel(News news)
    {
        setTitle(news.getTitle());
        setDescription(news.getDescription());
        setDate(news.getDate());
        setFree(news.isFree());
    }

    public String getTitle()
    {
        return title.get();
    }

    public void setTitle(String title)
    {
        this.title.set(title);
    }

    public String getDescription()
    {
        return description.get();
    }

    public void setDescription(String description)
    {
        this.description.set(description);
    }

    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd, mm-dd", Locale.US);

    public String getDate()
    {
        return mDateFormat.format(date.get());
    }

    public void setDate(Date date)
    {
        this.date.set(date);
    }

    public String getFree()
    {
        return free.get() ? "Free" : "NoFree";
    }

    public void setFree(boolean free)
    {
        this.free.set(free);
    }
}
