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
    app:startDestination="@id/fragment_main">
    <!--
       本文件主要是进行一些页面之间的设置，如页面间跳转关系，跳转参数类型、个数规定、页面间跳转动画等。

       1、起始目的地设置:     通过 app:startDestination 来设置起始目的地。
       2、页面间的跳转关系:   通过 action标签进行配置
       3、参数类型配置:      通过 argument来进行配置

       ps：

       导航图中注册的fragment都必须有id，否则编译不通过。

       tools:layout标签如果不配置，我们无法在Design视图看到页面预览效果

    -->
    <fragment
        android:id="@+id/fragment_main"
        android:name="com.example.notenavigation.fragments.MainFragment"
        android:label="@string/home_page"
        tools:layout="@layout/fragment_main">
        <!--可以从本页面跳转到哪个页面，action可以有多个。action也要有id-->
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

  2、name：指定NavHost，导航宿主类必须实现NavHost，这里使用Navigation组件提供的默认实现类即可（NavHostFragment）

  3、navGraph：指定导航图

  4、defaultNavHost = true：属性会让 NavHostFragment 拦截系统返回按钮。
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

###### 导航到目标页

Navigation 的导航是通过NavController来控制值，想要获取NavController对象可以通过如下方式：

```kotlin
Fragment.findNavController()
View.findNavController()
Activity.findNavController(viewId)
```

注意使用 FragmentContainerView 创建 NavHostFragment，或通过 FragmentTransaction 手动将 NavHostFragment 添加到您的 Activity 时，使用Navigation.findNavController(@IdRes int) 方式获取 NavController是会获取失败的。应改为直接从 NavHostFragment来NavController。
栗子如下：

```kotlin
        val navHostFragment =
            //1、获取FragmentContainerView中name指定的fragment，并强转为NavHostFragment
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
           // 2、获取NavHostFragment的成员属性navController
        val navController = navHostFragment.navController
```

此时我们就可以在MainActivity中书写按钮的逻辑了：点击按钮从MainFragment跳转到BlankFragment，但这时navigation不建议我们直接
通过navController直接进行跳转，而是建议使用 Safe Args 确保类型安全。

这时我们需要引入Gradle插件：

```groovy
// build.gradle
buildscript {
    repositories {
        google()
    }
    dependencies {
        def nav_version = "2.5.0"
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
    }
}
// app/build.gradle
plugins {
    // 生成适用于 Java 模块或 Java 和 Kotlin 混合模块的 Java 语言代码
    id 'androidx.navigation.safeargs'

    // 生成仅适用于 Kotlin 模块的 Kotlin 语言代码
    id 'androidx.navigation.safeargs.kotlin'
}
```

该插件可以生成简单的对象和构建器类，这些类支持在目的地之间进行类型安全的导航和参数传递。

启用 Safe Args 后，该插件会生成代码，其中包含您定义的每个操作的类和方法。对于每个操作，Safe Args 还会为每个源目的地（生成相应操作的目的地）生成一个类。生成的类的名称由源目的地类的名称和“Directions”一词组成。例如，如果目的地的名称为 MainFragment，生成的类的名称为 MainFragmentDirections。生成的类为源目的地中定义的每个操作提供了一个静态方法。该方法会将任何定义的操作参数作为参数，并返回可传递到 navigate() 的 NavDirections 对象。

Safe Args 会生成一个 MainFragmentDirections 类，其中只包含一个 actionFragmentMainToFragmentBlank() 方法（该方法会返回 NavDirections 对象）。然后，您可以将返回的 NavDirections 对象直接传递到 navigate()，如以下示例所示：

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            //1、获取FragmentContainerView中name指定的fragment，并强转为NavHostFragment
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
           // 2、获取NavHostFragment的成员属性navController
        val navController = navHostFragment.navController

        // 3、 使用safe args 进行跳转
        btnTest.setOnClickListener {
            val action = MainFragmentDirections.actionFragmentMainToFragmentBlank(userName = "Serendipity")
            navController.navigate(action)
        }
    }
}
```