# My_Frist_APP_News
2020寒假实验室考核

# 基本样式
## 新闻页面


# 实现过程
> ToolBar部分：
> > AppBarLayout内嵌套ToolBar
> > ToolBar内嵌套CircleImageView[圆形图片框(第三方)]  
> > ToolBar内嵌套CardView{为实现搜索框圆角化} 再嵌套RelativeLayout  
> > RelativeLayout嵌套ImageView[显示搜索图标]   EditText[实现内容输入]  
> 
> 
> 

# 遇到的问题
> 已解决：
> > Only the original thread that created a view hierarchy can touch its views.[解决方案：activity?.runOnUiThread{}]  
> > 
> >
> 未解决：
> > 每日API(100次)请求完后，APP闪退，多次打开才可正常显示为空白  
> > 手机暗夜模式下 ToolBar 上方标题仍显示[正常情况不显示]  
> >

# 所用的第三方包
> com.github.bumptech.glide:glide:4.12.0  com.github.bumptech.glide:compiler:4.12.0  [用于加载网络图片]  
> androidx.navigation:navigation-fragment-ktx:2.2.2      androidx.navigation:navigation-ui-ktx:2.2.2  [实现切换页面]  
> androidx.swiperefreshlayout:swiperefreshlayout:1.0.0 [用于实现下拉刷新]  
> 
>
>
>
>

# 缺陷
> 数据未本地化
> 每日API(100次)请求完后，APP闪退，多次打开才可正常显示为空白
> 手机暗夜模式下 ToolBar 上方标题仍显示[正常情况不显示]
> 好多功能没写(例如：搜索，测试页，侧滑栏功能...)
