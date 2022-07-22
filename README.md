# Navigation

要了解如下基本就能满足项目

1、基本使用流程
2、fragment事务管理
3、deeplink 用法

# 一、基本使用

1、概念

- 导航图:在一个集中位置包含所有导航相关信息的 XML 资源。这包括应用内所有单个内容区域（称为目标）以及用户可以通过应用获取的可能路径。
- NavHost:显示导航图中目标的空白容器。导航组件包含一个默认 NavHost 实现 (NavHostFragment)，可显示 Fragment 目标。
- NavController:在 NavHost 中管理应用导航的对象。当用户在整个应用中移动时，NavController 会安排 NavHost 中目标内容的交换。

# 二、Fragment事务管理
