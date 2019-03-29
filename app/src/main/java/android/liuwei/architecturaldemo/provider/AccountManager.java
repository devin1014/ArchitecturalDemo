package android.liuwei.architecturaldemo.provider;

import android.liuwei.architecturaldemo.bean.User;

public class AccountManager
{
    private static class Holder
    {
        private static final AccountManager INSTANCE = new AccountManager();
    }

    public static AccountManager getInstance()
    {
        return Holder.INSTANCE;
    }

    private AccountManager()
    {
    }

    public User newInstance(String userName)
    {
        User user = new User();

        user.setName(userName);

        user.setEmail("aaa@bb.com");

        user.setSku("free");

        user.setVip(false);

        return user;
    }

    private User mUser;

    public void setUser(User user)
    {
        mUser = user;
    }

    public User getUser()
    {
        return mUser;
    }
}
