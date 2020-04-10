# MVVMDemo
Android MVVM模式的RecyclerView Demo

###【更新说明】
老版本的mvvm demo出了一个重大错误，在vm中持有view的引用，之所以会有这种写法，是受限于当时的技术水平和网络论调，在那个时代，网上绝大多数人
  都是加了databinding就认为是mvvm了，实际上不是这样的，MVVM是一种架构模式，而DataBinding是一个实现数据和UI绑定的框架，是构建MVVM模式的一个工具
  **这段代码，我就保留，算是给大家一个"错误"的典范。正规的mvvm可以参考real_mvvm 包中的代码**
  
  规范的写法，以及详细说明参考代码内部新增package下的解释说明
  
整体架构MVVM，网络请求用的是retrofit2+rxjava2,图片加载用的Glide，列表用的xRecyclerView库

`效果图`

![新闻列表](https://github.com/zhouxu88/MVVMDemo/blob/master/screenshots/demo.gif)

