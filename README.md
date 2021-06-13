# ClickerX

ClickerX是Android平台上一款免root模拟触摸和按键的工具。通过编写脚本，可以实现自动执行一系列的操作，解放双手。

ClickerX类似于电脑上的按键精灵，可以模拟用户的操作、完成重复的动作，例如：自动化测试、自动回复、抢红包等。ClickerX脚本是基于Lua的，封装了一系列模拟操作的函数。

脚本分为主动执行脚本与触发脚本。主动执行脚本即是普通的脚本，需要手动执行或者预约时间执行，不需要触发事件，例如App的签到；触发脚本指的是需要事件触发才能执行的脚本，需要特定事件的触发以执行一系列的任务，例如微信抢红包。

# ClickerX API v0.0.0.0

函数   | 作用 | 参数 
----  | ----  |----  
launchApp(pkgName)  | 启动/跳转到包名为`pkgName`的应用 | pkgName: string
delay(millis)  | 暂停`millis`毫秒 | millis: number
clickById(id) | 点击id为`id`的控件 | id: string
clickByText(text) | 点击文字为`text`的控件 | text: string
clickAt(x, y) | 点击屏幕上坐标(`x`, `y`)的点 | x: number, y: number
clickBy(args) | 点击满足条件`args`的控件，可选参数`text`、`id`、`desc`（contentDescription） | args: table
pressKey(key) | 按下实体键`key`，可选项有`KEY_BACK`、`KEY_HOME`、`KEY_RECENT`、`KEY_NOTIFICATION` | key: number
getScreenWidth() | 获取屏幕宽度 | 
getScreenHeight() | 获取屏幕高度
swipe(x1, y1, x2, y2, duration) | 模拟从(`x1`, `y1`)滑动到(`x2`, `y2`)的操作，时长`duration` | x1, y1, x2, y2, duration: number
setDefaultDelay(delay) | 设置该脚本中相邻两条指令的默认间隔为`delay`毫秒 | delay: number
input(text) | 输入文字`text` | text: string
setTrigger(name, triggerType, args, action) | 设置名称为`name`、类型为`triggerType`的触发器。<br>其中`args`为参数；当`triggetType`为`TRIGGER_TYPE_NOTIFICATION`时，<br>表示该脚本由通知触发，可选参数有`title` - 通知标题、`content` - 通知的内容、`pkgName` - 发送通知的包名；当不添加对应参数，表示监听所有。`action`为脚本被触发后需要执行的操作。 | name: string, triggetType: number, args: table, action: function


# 开发进度

**2021年6月7日 v0.0.0.0**

初始版本。完成了核心功能（解析Lua脚本并执行自动化操作流程、按照触发器执行触发脚本）以及必要的UI。

# 脚本示例

## 1. 自动发消息

```lua
---
--- 微信发送消息脚本
---

-- 设计尺寸，用于按比例点击。
-- 例如，设计宽度为1080px，点击x=540；在宽度为720px的手机上，会转换成x=360。
local baseWidth = 1080

setDefaultDelay(1000) -- 设置每个动作直接最少间隔1秒

launchApp("com.tencent.mm") -- 启动微信
delay(2000) -- 考虑到冷启动时间，延迟5秒

clickByText("通讯录") -- 点击通讯录
clickBy({ desc = "搜索" }) -- 点击搜索按钮
input("巧克力") -- 输入发送对象的名字
delay(1500) -- 等待搜索结果
clickByBaseX(550, 400, baseWidth) -- 点击用户头像进入聊天

input("晚安") -- 输入要发送的消息
clickByText("发送") -- 点击发送按钮

pressKey(KEY_BACK) -- 点击3次返回，回到桌面
pressKey(KEY_BACK)
pressKey(KEY_BACK)
```

## 2. 自动抢红包（触发脚本）

```lua
-- 抢红包脚本

-- 触发器名称
local triggerName = "微信抢红包"

-- 监听类型：通知
local triggerType = TRIGGER_TYPE_NOTIFICATION

-- 参数：包名=微信
local params = {
    pkgName = "com.tencent.mm"
}

-- 动作
-- notification: 满足条件的通知
local action = function(notification)
    local content = notification:getContent() -- 获取通知内容
    local title = notification:getTitle() -- 通知标题
    print("监听到通知：标题=" .. title .. ", 内容=" .. content)
    -- 汉字和特殊符号前面需要加%
    local found = content:find("%[%微%信%红%包%]")
    if found then
        -- 如果消息中包含[微信红包]
        print("抢红包脚本触发！")
        -- 这是一个红包消息
        notification:click() -- 点击通知进入对应界面
        delay(1000) -- 间隔1秒
        clickAt(200, 1750) -- 点击最新消息坐标
        delay(1000)
        clickBy({ desc = "开" }) -- 点击开红包
        delay(3000)
        pressKey(KEY_HOME) -- 10秒后返回桌面
    end
end

-- 调用setTrigger开始监听
setTrigger(triggerName, triggerType, params, action)
```
