-- 启动Chrome
launchApp("com.android.chrome")
delay(300)

-- 可能提示权限，点击允许
clickByText("允许")
delay(2000)

-- 点击搜索框
clickById("com.android.chrome:id/url_bar")
delay(800)

-- 输入网址
input("https://www.baidu.com/s?wd=ClickerX")
delay(300)

-- 点击回车，一般处于屏幕右下角
-- 非Root无法直接直接输入键值
local h = getScreenHeight()
local w = getScreenWidth()
clickAt(w - 50, h - 50)