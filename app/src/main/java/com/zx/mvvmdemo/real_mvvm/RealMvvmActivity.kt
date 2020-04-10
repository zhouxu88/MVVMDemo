package com.zx.mvvmdemo.real_mvvm

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zx.mvvmdemo.R
import com.zx.mvvmdemo.databinding.ActivityRealMvvmBinding
import com.zx.mvvmdemo.viewmodel.RealVm

/**
 * 相对规范的mvvm应该这样写
 *
 * 1. mvvm和MVP比较大的区别是：vm和v是单向引用，
 * 只有activity持有vm引用，vm是不持有view的引用的，所以vm的构造方法中不能传入视图相关的对象
 *
 * 2.数据驱动。在常规的开发模式中，数据变化需要更新UI的时候，需要先获取UI控件的引用，然后再更新UI。
 * 获取用户的输入和操作也需要通过UI控件的引用。在MVVM中，这些都是通过数据驱动来自动完成的，
 * 数据变化后会自动更新UI，UI的改变也能自动反馈到数据层，数据成为主导因素。这样MVVM层在业务逻辑处理中
 * 只要关心数据，不需要直接和UI打交道，在业务处理过程中简单方便很多。
 *
 * 3.mvvm解决了mvp中接口繁杂、内存泄漏等疑难杂症。
 */
class RealMvvmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRealMvvmBinding
    private lateinit var realVm: RealVm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_real_mvvm)

        realVm = RealVm()
        realVm.loadData()
        realVm.liveData.observe(this, object : Observer<String> {
            override fun onChanged(result: String?) {
                binding.textView.text = result
            }
        })
    }


}
