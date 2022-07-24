# 简介

Navigation采用的是单Activity多Fragment架构，优点很多：

- 处理 Fragment 事务。
- 默认情况下，正确处理往返操作。
- 为动画和转换提供标准化资源。
- 实现和处理深层链接。
- 包括导航界面模式（例如抽屉式导航栏和底部导航），用户只需完成极少的额外工作。
- Safe Args，可在目标之间导航和传递数据时提供类型安全的 Gradle 插件。
- ViewModel 支持，您可以将 ViewModel 的范围限定为导航图，以在图表的目标之间共享与界面相关的数据。

###### 环境引入

Navigation 组件需要 Android Studio 3.3 或更高版本，并且依赖于 Java 8 语言功能。

如需在您的项目中添加 Navigation 支持，请向应用的 build.gradle 文件添加以下依赖项：

```groovy
dependencies {
    def nav_version = "2.5.0"

    // Java language implementation
    implementation "androidx.navigation:navigation-fragment:$nav_version"
    implementation "androidx.navigation:navigation-ui:$nav_version"

    // Kotlin
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    // Feature module Support
    implementation "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"

    // Testing Navigation
    androidTestImplementation "androidx.navigation:navigation-testing:$nav_version"

    // Jetpack Compose Integration
    implementation "androidx.navigation:navigation-compose:$nav_version"
}
```

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

案例驱动+细节介绍,以两个Fragment的使用为例子，MainFragment为主页，BlankFragment为测试跳转页，触发跳转逻辑时从MainFragment。

###### 创建导航图

一般是res下新建立个"Android Resource Directory"，然后在文件夹下建立个xml配置文件，文件的根节点是navigation：

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph">

</navigation>
```

创建了导航图之后就可以在这里注册fragment了。

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@layout/fragment_main">
    <!--
       本文件主要是进行一些页面之间的设置，如页面间跳转关系，跳转参数类型、个数规定、页面间跳转动画等。
    
       1、起始目的地设置:    通过 app:startDestination 来设置起始目的地。
       2、页面间的跳转关系:   
       ps：
    
    -->
    <fragment
        android:id="@+id/fragment_main"
        android:name="com.example.notenavigation.fragments.MainFragment"
        android:label="@string/home_page"
        tools:layout="@layout/fragment_main">
       <!--可以从本页面跳转到哪个页面，action可以有多个-->
        <action
            android:id="@+id/action_fragment_main_to_fragment_blank"
            app:destination="@id/fragment_blank" />
    </fragment>

    <fragment
        android:id="@+id/fragment_blank"
        android:name="com.example.notenavigation.fragments.BlankFragment"
        android:label="@string/hello_blank_fragment"
        tools:layout="@layout/fragment_blank">
       <!--跳转到本页面时需要传递过来的参数，argument可有多个-->
        <argument
            android:name="userName"
            app:argType="string" />
    </fragment>

</navigation>

```

###### Activity中进行配置

 主要有如下配置：

（1）添加FragmentContainerView特殊容器
（2）配置NavHost
（3）配置导航图

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!--
  导航容器：控制导航

  1、当进行导航过程中，目标fragment 会在这个容器中交换进出。

  2、NavHost导航宿主通过name指定，且导航宿主类必须实现NavHost，这里使用Navigation
  组件提供的默认实现类即可（NavHostFragment）

  3、navGraph属性可以关联导航图

  4、defaultNavHost = true：属性确保您的 NavHostFragment 会拦截系统返回按钮。
  -->
    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/nav_graph" />

    <androidx.appcompat.widget.AppCompatButton
        android:id="@+id/btnTest"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="跳转测试"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        tools:ignore="HardcodedText" />

</androidx.constraintlayout.widget.ConstraintLayout>
```