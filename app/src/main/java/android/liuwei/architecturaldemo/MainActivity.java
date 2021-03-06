package android.liuwei.architecturaldemo;

import android.content.Intent;
import android.liuwei.architecturaldemo.mvp.MvpDemoActivity;
import android.liuwei.architecturaldemo.mvvm.MvvmDemoActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mvp).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, MvpDemoActivity.class));
            }
        });

        findViewById(R.id.mvvm).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, MvvmDemoActivity.class));
            }
        });
    }
}
