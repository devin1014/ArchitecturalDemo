package android.liuwei.architecturaldemo.mvp;

import android.liuwei.architecturaldemo.R;
import android.liuwei.architecturaldemo.bean.User;
import android.liuwei.architecturaldemo.mvp.contract.IAccountContract.IAccountView;
import android.liuwei.architecturaldemo.mvp.contract.imp.AccountModel;
import android.liuwei.architecturaldemo.mvp.contract.imp.AccountPresenter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MvpAccountFragment extends Fragment implements IAccountView
{
    private View mSignInContainerView;
    private EditText mUserNameEdit;
    private ProgressBar mLoading;
    private View mSignOutContainerView;
    private TextView mUserNameTv;
    private TextView mSkuTv;
    private TextView mVipTv;

    private AccountPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mSignInContainerView = view.findViewById(R.id.acc_sign_in_container);
        mUserNameEdit = view.findViewById(R.id.acc_edit_user_name);
        Button signInBtn = view.findViewById(R.id.acc_btn_sign_in);
        signInBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPresenter.signIn(mUserNameEdit.getText().toString());
            }
        });
        mLoading = view.findViewById(R.id.acc_loading);

        mSignOutContainerView = view.findViewById(R.id.acc_sign_out_container);
        mUserNameTv = view.findViewById(R.id.acc_tv_user_name);
        mSkuTv = view.findViewById(R.id.acc_tv_sku);
        mVipTv = view.findViewById(R.id.acc_tv_vip);
        Button signOut = view.findViewById(R.id.acc_btn_sign_out);
        signOut.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPresenter.signOut();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new AccountPresenter(this, new AccountModel());
        mPresenter.attachContext(getActivity());
    }

    @Override
    public void onDestroy()
    {
        mPresenter.detachContext();
        super.onDestroy();
    }

    @Override
    public void showSignInView()
    {
        mSignInContainerView.setVisibility(View.VISIBLE);
        mSignOutContainerView.setVisibility(View.GONE);
    }

    @Override
    public void showSignOutView(User user)
    {
        mSignInContainerView.setVisibility(View.GONE);
        mSignOutContainerView.setVisibility(View.VISIBLE);

        mUserNameTv.setText(String.format("Name:%s", user.getName()));
        mSkuTv.setText(String.format("Sku:%s", user.getSku()));
        mVipTv.setText(String.format("Vip:%s", user.isVip() ? "true" : "false"));
    }

    @Override
    public void showLoading()
    {
        mLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        mLoading.setVisibility(View.GONE);
    }
}
