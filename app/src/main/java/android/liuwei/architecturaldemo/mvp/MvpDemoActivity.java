package android.liuwei.architecturaldemo.mvp;

import android.liuwei.architecturaldemo.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MvpDemoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_account_container, new MvpAccountFragment())
                .replace(R.id.content_detail_container, new MvpDetailFragment())
                .commit();
    }
}
