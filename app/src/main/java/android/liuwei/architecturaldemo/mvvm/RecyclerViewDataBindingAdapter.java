package android.liuwei.architecturaldemo.mvvm;

import android.databinding.BindingAdapter;
import android.liuwei.architecturaldemo.adapter.ListBindingAdapter;
import android.liuwei.architecturaldemo.adapter.RecyclerViewAdapterFactory;
import android.liuwei.architecturaldemo.bean.News;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

import java.util.List;

public class RecyclerViewDataBindingAdapter
{
    @BindingAdapter(value = {"manager", "adapterFactory", "items"}, requireAll = false)
    public static void setAdapterFactory(RecyclerView recyclerView, LayoutManager layoutManager, RecyclerViewAdapterFactory factory, List<News> list)
    {
        if (recyclerView.getAdapter() == null)
        {
            if (layoutManager != null)
            {
                recyclerView.setLayoutManager(layoutManager);
            }

            if (factory != null)
            {
                recyclerView.setAdapter(factory.createAdapter(recyclerView));
            }
        }

        if (recyclerView.getAdapter() != null && list != null)
        {
            ((ListBindingAdapter) recyclerView.getAdapter()).setList(list);
        }
    }
}
