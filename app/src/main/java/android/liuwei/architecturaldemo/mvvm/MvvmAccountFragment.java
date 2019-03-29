package android.liuwei.architecturaldemo.mvvm;

import android.databinding.DataBindingUtil;
import android.liuwei.architecturaldemo.R;
import android.liuwei.architecturaldemo.databinding.FragmentAccountVbBinding;
import android.liuwei.architecturaldemo.mvp.contract.imp.AccountModel;
import android.liuwei.architecturaldemo.mvvm.viewmodel.imp.AccountViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MvvmAccountFragment extends Fragment
{
    private AccountViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_account_vb, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        FragmentAccountVbBinding viewBinding = DataBindingUtil.bind(view);

        mViewModel = new AccountViewModel(getActivity(), new AccountModel());

        mViewModel.setViewBinding(viewBinding);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        mViewModel.start();
    }
}
