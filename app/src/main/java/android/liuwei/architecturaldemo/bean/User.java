package android.liuwei.architecturaldemo.bean;

public class User
{
    private String name;

    private String email;

    private String sku;

    private boolean vip;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public boolean isVip()
    {
        return vip;
    }

    public void setVip(boolean vip)
    {
        this.vip = vip;
    }
}
