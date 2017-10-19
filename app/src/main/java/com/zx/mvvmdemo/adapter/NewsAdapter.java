package com.zx.mvvmdemo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.zx.mvvmdemo.BR;
import com.zx.mvvmdemo.R;
import com.zx.mvvmdemo.base.BaseAdapter;
import com.zx.mvvmdemo.base.BaseViewHolder;
import com.zx.mvvmdemo.bean.SimpleNewsBean;
import com.zx.mvvmdemo.utils.ToastUtils;

/**
 * 作者： 周旭 on 2017年10月17日 0017.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class NewsAdapter extends BaseAdapter<SimpleNewsBean, BaseViewHolder> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.simpleNewsBean, mList.get(position));
        binding.setVariable(BR.position,position);
        binding.setVariable(BR.adapter,this);
        binding.executePendingBindings(); //防止闪烁
    }


    /**
     * 点赞
     *
     * @param simpleNewsBean
     * @param position
     */
    public void clickDianZan(SimpleNewsBean simpleNewsBean, int position) {
        if (simpleNewsBean.isGood.get()) {
            simpleNewsBean.isGood.set(false);
            ToastUtils.show(mContext, "取消点赞 position=" + position);
        } else {
            simpleNewsBean.isGood.set(true);
            ToastUtils.show(mContext, "点赞成功 position=" + position);
        }
    }
}
