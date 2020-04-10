package com.zx.mvvmdemo.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * @descripe 这里vm继承了ViewModel类，通知UI刷新，用的是LiveData，
 * 关于2者的细节，请参考我的文章：
 *
 *
 * @author zhouxu
 * @e-mail 374952705@qq.com
 * @time   2020/4/10
 */


class RealVm : ViewModel() {
    private val mModel by lazy {
        RealModel()
    }

    val liveData = MutableLiveData<String>()

    fun loadData() {
        var result = mModel.loadDataFromNet()
        liveData.value = result
    }
}