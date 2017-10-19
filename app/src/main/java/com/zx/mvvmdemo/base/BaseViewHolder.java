package com.zx.mvvmdemo.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * 作者： 周旭 on 2017年10月17日 0017.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    /**
     * ViewDataBinding
     */
    private B mBinding;

  
    public BaseViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    /**
     * @return viewDataBinding
     */
    public B getBinding() {
        return mBinding;
    }

}
