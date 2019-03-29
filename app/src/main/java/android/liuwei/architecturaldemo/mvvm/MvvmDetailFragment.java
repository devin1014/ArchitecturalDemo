package android.liuwei.architecturaldemo.mvvm;

import android.databinding.DataBindingUtil;
import android.liuwei.architecturaldemo.R;
import android.liuwei.architecturaldemo.databinding.FragmentDetailVbBinding;
import android.liuwei.architecturaldemo.mvp.contract.imp.DetailModel;
import android.liuwei.architecturaldemo.mvvm.viewmodel.imp.DetailViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MvvmDetailFragment extends Fragment
{
    private DetailViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_detail_vb, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        FragmentDetailVbBinding viewBinding = DataBindingUtil.bind(view);

        mViewModel = new DetailViewModel(getActivity(), new DetailModel());

        mViewModel.setViewBinding(viewBinding);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        mViewModel.start();
    }

    @Override
    public void onDestroy()
    {
        mViewModel.stop();

        super.onDestroy();
    }
}
