package com.zx.mvvmdemo.viewmodel;

import com.zx.mvvmdemo.adapter.NewsAdapter;
import com.zx.mvvmdemo.base.BaseLoadListener;
import com.zx.mvvmdemo.bean.SimpleNewsBean;
import com.zx.mvvmdemo.constant.MainConstant;
import com.zx.mvvmdemo.model.INewsModel;
import com.zx.mvvmdemo.model.NewsModelImpl;
import com.zx.mvvmdemo.view.INewsView;

import java.util.List;

/**
 * 作者： 周旭 on 2017年10月18日 0018.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class NewsVM implements BaseLoadListener<SimpleNewsBean> {
    private static final String TAG = "NewsVM";
    private INewsModel mNewsModel;
    private INewsView mNewsView;
    private NewsAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    /**
     * 注意：这里传入Adapter、View是不对的，mvvm和MVP比较大的区别是：vm和v是单向引用，
     * 只有activity持有vm引用，vm是不持有view的引用的，所以vm的构造方法中不能传入视图相关的对象
     * ，所以这里的view接口和adapter都不应该传。
     *
     * 之所以会有这种写法，是受限于当时的技术水平和网络论调，在那个时代，网上绝大多数人
     * 都是加了databinding就认为是mvvm了，实际上不是这样的，MVVM是一种架构模式，
     * 而DataBinding是一个实现数据和UI绑定的框架，是构建MVVM模式的一个工具。
     *
     * 这段代码，我就保留，算是给大家一个"错误"的典范。正规的mvvm可以参考real_mvvm 包中的代码
     * @param mNewsView
     * @param mAdapter
     */
    public NewsVM(INewsView mNewsView, NewsAdapter mAdapter) {
        this.mNewsView = mNewsView;
        this.mAdapter = mAdapter;
        mNewsModel = new NewsModelImpl();
        getNewsData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mNewsModel.loadNewsData(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currPage = 1;
        mNewsModel.loadNewsData(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currPage++;
        mNewsModel.loadNewsData(currPage, this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {
        if (currPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String message) {
        // 加载失败后的提示
        if (currPage > 1) {
            //加载失败需要回到加载之前的页数
            currPage--;
        }
        mNewsView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mNewsView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mNewsView.loadComplete();
    }
}

