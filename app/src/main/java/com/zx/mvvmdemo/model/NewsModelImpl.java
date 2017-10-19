package com.zx.mvvmdemo.model;

import android.os.Handler;
import android.util.Log;

import com.zx.mvvmdemo.base.BaseLoadListener;
import com.zx.mvvmdemo.bean.NewsBean;
import com.zx.mvvmdemo.bean.SimpleNewsBean;
import com.zx.mvvmdemo.http.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： 周旭 on 2017年10月18日 0018.
 * 邮箱：374952705@qq.com
 * 博客：http://www.jianshu.com/u/56db5d78044d
 */

public class NewsModelImpl implements INewsModel {

    private static final String TAG = "NewsModelImpl";
    List<SimpleNewsBean> simpleNewsBeanList = new ArrayList<SimpleNewsBean>();

    @Override
    public void loadNewsData(final int page, final BaseLoadListener<SimpleNewsBean> loadListener) {
        HttpUtils.getNewsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsBean>() {
                    @Override
                    public void onNext(@NonNull NewsBean newsBean) {
                        Log.i(TAG, "onNext: ");
                        List<NewsBean.OthersBean> othersBeanList = newsBean.getOthers();
                        simpleNewsBeanList.clear();
                        if (othersBeanList != null && othersBeanList.size() > 0) {
                            for (NewsBean.OthersBean othersBean : othersBeanList) {
                                String thumbnail = othersBean.getThumbnail();
                                String name = othersBean.getName();
                                String description = othersBean.getDescription();
                                Log.i(TAG, "thumbnail:---->" + thumbnail);
                                Log.i(TAG, "name:---->" + name);
                                Log.i(TAG, "description:---->" + description);

                                //构造Adapter所需的数据源
                                SimpleNewsBean simpleNewsBean = new SimpleNewsBean();
                                simpleNewsBean.thumbnail.set(thumbnail);
                                simpleNewsBean.name.set(name);
                                simpleNewsBean.description.set(description);
                                simpleNewsBeanList.add(simpleNewsBean);

                                if (page > 1) {
                                    //这个接口暂时没有分页的数据，这里为了模拟分页，通过取第1条数据作为分页的数据
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        Log.i(TAG, "onStart: ");
                        loadListener.loadStart();
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        Log.i(TAG, "onError: " + throwable.getMessage());
                        loadListener.loadFailure(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadListener.loadSuccess(simpleNewsBeanList);
                                loadListener.loadComplete();
                            }
                        }, 2000);
                    }
                });
    }
}
