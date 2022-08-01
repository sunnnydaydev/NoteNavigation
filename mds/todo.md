# 二、导航总结

###### 1、NavController的获取

###### 2、常见的导航方式

（1）Safe Args 进行导航

       navigate(directions: NavDirections) // safe args 方式可生成NavDirections对象。

（2）使用资源id进行导航

       navigate(@IdRes resId: Int) //直接通过资源Id进行导航。(支持actionId 或者 目的地fragment id)

（3）deeplink进行导航

       navigate(deepLink: Uri) // 通过uri进行导航

###### 3、导航选项

       NavOptions：此类包含从目标目的地往返的所有特殊配置，包括动画资源配置、弹出行为以及是否应在单一顶级模式下启动目的地。

###### 4、导航相关的返回栈

       基础
       支持多个返回栈

# 三、导航之间的数据传递

# 四、导航动画

# 五、NavigationUI

   ToolBar、BottomNavigationView、DrawerLayout 等控件UI联动。






            




