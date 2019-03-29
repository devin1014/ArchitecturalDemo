package android.liuwei.architecturaldemo.bean;

import java.util.Date;

public class News
{
    private String title;
    private String description;
    private Date date;
    private boolean free;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public boolean isFree()
    {
        return free;
    }

    public void setFree(boolean free)
    {
        this.free = free;
    }
}
