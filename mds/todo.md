# 一、设计导航图

介绍常见的导航如何设计。

###### 1、顶级导航图设计

若是采用navigation的单activity多fragment架构都应该遵循这种设计，其实也属于这种设计。

完整的导航图。可以清晰的看到起始目的地、应用中各个页面跳转关系。

###### 2、嵌套图

注册页面、app初次安装引导页可以使用嵌套图的形式

如HomePage屏幕启动后，可以检查是否是未注册用户。如果用户未注册，您可以将其转到注册页面。这种要结合条件导航。

 使用include进行嵌套，并且需要结合条件导航。


###### 3、在模块中进行导航

 （1）功能模块进行导航

 （2）多模块最佳做法

###### 4、全局操作

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






            




