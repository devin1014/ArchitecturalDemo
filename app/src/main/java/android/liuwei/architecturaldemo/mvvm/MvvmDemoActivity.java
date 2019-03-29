package android.liuwei.architecturaldemo.mvvm;

import android.liuwei.architecturaldemo.R;
import android.liuwei.architecturaldemo.mvp.MvpDetailFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MvvmDemoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_account_container, new MvvmAccountFragment())
                .replace(R.id.content_detail_container, new MvpDetailFragment())
                .commit();
    }
}
