# 简介

Navigation采用的是单Activity多Fragment架构，优点很多：

- 处理 Fragment 事务。
- 默认情况下，正确处理往返操作。
- 为动画和转换提供标准化资源。
- 实现和处理深层链接。
- 包括导航界面模式（例如抽屉式导航栏和底部导航），用户只需完成极少的额外工作。
- Safe Args，可在目标之间导航和传递数据时提供类型安全的 Gradle 插件。
- ViewModel 支持，您可以将 ViewModel 的范围限定为导航图，以在图表的目标之间共享与界面相关的数据。


###### 传统fragment使用回顾

回一下我们之前是怎样使用fragment的？直接通过FragmentManager来进行管理的，如下：

![CommonFragment](https://gitee.com/sunnnydaydev/my-pictures/raw/master/github/navigation/CommonFragment.png)


那么简单看下navigation使用流程：

![nav](https://gitee.com/sunnnydaydev/my-pictures/raw/master/github/navigation/nav.png)

- 导航图:一个XML资源文件。在这里可以进行fragment的注册、fragment间跳转关系指定、HomeFragment指定、fragment跳转间接受参数类型指定等。
- 容器:一个特殊的FrameLayout容器，我们直接使用系统默认的即可。然后把NavHost指定到这里即可。
- NavHost:显示导航图中fragment的真正容器。navigation组件提供了一个默认类NavHostFragment实现了NavHost接口 可显示 Fragment。
- NavController:NavController是NavHost持有的一个管理类，管理应用导航。用户触发切换页面的逻辑时NavController会安排 NavHost 中fragment的交换。

# 简单使用

案例驱动+细节介绍