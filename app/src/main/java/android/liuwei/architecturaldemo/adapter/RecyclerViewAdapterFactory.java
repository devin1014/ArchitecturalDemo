package android.liuwei.architecturaldemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;

public interface RecyclerViewAdapterFactory<V extends Adapter>
{
    V createAdapter(RecyclerView recyclerView);

    // ------------------------------------------------------------------------
    // - Factory ----
    // ------------------------------------------------------------------------
    class NewsAdapterFactory implements RecyclerViewAdapterFactory<ListBindingAdapter>
    {
        @Override
        public ListBindingAdapter createAdapter(RecyclerView recyclerView)
        {
            return new ListBindingAdapter(recyclerView.getContext());
        }
    }
}
