# NavigationUI 

Navigation功能还是比较强大的 ，可以结合顶部应用栏、抽屉导航栏 、底部导航栏进行联动。

# 一、顶部应用栏

# 二、抽屉导航栏

# 三、底部导航栏

Navigation 结合BottomNavigationView 控件的使用。

activity布局

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.appcompat.widget.LinearLayoutCompat xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".bottoms.HomeActivity"
    android:orientation="vertical">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/container"
        android:name="androidx.navigation.fragment.NavHostFragment"
        app:defaultNavHost="true"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight = "1"
        app:navGraph="@navigation/nav_bottom_graph"
        />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottomNavigationView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/container"
        app:menu="@menu/menu_bottom_nav"/>

</androidx.appcompat.widget.LinearLayoutCompat>
```

导航图与菜单

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_bottom_graph"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/cartFragment"
        android:name="com.example.notenavigation.bottoms.CartFragment"
        tools:layout="@layout/fragment_cart"
        android:label="CartFragment"
        />

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.notenavigation.bottoms.HomeFragment"
        tools:layout="@layout/fragment_home2"
        android:label="HomeFragment"
        />

    <fragment
        android:id="@+id/profileFragment"
        android:name="com.example.notenavigation.bottoms.ProfileFragment"
        tools:layout="@layout/fragment_profile"
        android:label="ProfileFragment"
        />


</navigation>
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    <!--
    注意：

    与navigation进行联动时item的id要与底部对应fragment的id保持一致。

    当底部的每个item都对应一个导航图时，这里的itemId 要与 导航图的id保持一致。
    -->
    <item
        android:id="@+id/homeFragment"
        android:icon="@drawable/ic_home_tab"
        android:title="Home"
        tools:ignore="HardcodedText" />
    <item
        android:id="@+id/cartFragment"
        android:icon="@drawable/ic_cart_tab"
        android:title="Cart"
        tools:ignore="HardcodedText" />
    <item
        android:id="@+id/profileFragment"
        android:icon="@drawable/ic_profile_tab"
        android:title="Profile"
        tools:ignore="HardcodedText" />
</menu>
```

Activity中简单处理下逻辑

```kotlin
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val controller = navHostFragment.navController
        // 两控件联动
        bottomNavigationView.setupWithNavController(controller)
        // 想要主动添加监听也可
        controller.addOnDestinationChangedListener{controller,destination,args ->
             Log.i("HomeActivity","current destination:${destination.label}")
        }
    }
}
```

