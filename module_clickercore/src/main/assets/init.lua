-- 禁止全局变量！
local inner = {}

-- 打印
local function log(msg)
    print("lua init: " .. msg)
end

-- 获取实例
log("初始化Lua脚本...")
local _clazz_ = luajava.bindClass('top.littlefogcat.clickerx.core.Connector');
local _inst_ = _clazz_:getInstance();

--- 变量
inner.KEY_BACK = 1
inner.KEY_HOME = 2
inner.KEY_RECENT = 3
inner.KEY_NOTIFICATION = 4 -- 打开通知

inner.KEYCODE_ENTER = 66

inner.TRIGGER_TYPE_NOTIFICATION = 2

-- 通过包名启动应用
function inner.launchApp(pkgName)
    return _inst_:launchApp(pkgName);
end

-- 睡眠[millis]毫秒
function inner.delay(millis)
    _inst_:delay(millis)
end

-- 点击id为[id]的控件
function inner.clickById(id)
    _inst_:clickById(id)
end

-- 点击文字为[text]的控件
function inner.clickByText(text)
    _inst_:clickByText(text)
end

-- 点击坐标
function inner.clickAt(x, y)
    _inst_:clickAt(x, y);
end

-- 点击坐标
-- 这个坐标是相对于屏幕[baseWidth×baseHeight]来的，根据实际屏幕进行缩放
function inner.clickByBase(x, y, baseWidth, baseHeight)
    local rw = inner.getScreenWidth()     -- real width
    local rh = inner.getScreenHeight()    -- real height
    local rx = x * rw / baseWidth   -- real x
    local ry = y * rh / baseHeight  -- real y
    inner.clickAt(rx, ry);
end

-- 点击坐标
-- 其中纵坐标y是屏幕绝对值，横坐标x是相对于宽度为[baseX]屏幕的相对值。
function inner.clickByBaseX(x, y, baseX)
    local rx = x * inner.getScreenWidth() / baseX
    local ry = y
    inner.clickAt(rx, ry)
end

-- 点击坐标
-- 其中横坐标x是屏幕绝对值，纵坐标y是相对于高度为[baseY]屏幕的相对值。
function inner.clickByBaseY(x, y, baseY)
    local rx = x
    local ry = y * inner.getScreenHeight() / baseY
    inner.clickAt(rx, ry)
end

-- 点击屏幕百分比位置
function inner.clickAtPercent(x, y)
    _inst_:clickAtPercent(x, y);
end

--- 点击满足条件的控件，其中args为一个table，包含了所需要的条件
--- 例如：{ text="发送", clickable=true }
function inner.clickBy(args)
    _inst_:clickBy(args)
end

-- 按下实体键
function inner.pressKey(key)
    _inst_:pressKey(key)
end

-- 获取屏幕宽度
function inner.getScreenWidth()
    return _inst_:screenWidth();
end

-- 获取屏幕高度
function inner.getScreenHeight()
    return _inst_:screenHeight()
end

-- 模拟手指滑动操作
-- x1 - 按下位置x坐标
-- y1 - 按下位置y坐标
-- x2 - 手指离开屏幕位置的x坐标
-- y2 - 手指离开屏幕位置的y坐标
function inner.swipe(x1, y1, x2, y2, duration)
    _inst_:swipe(x1, y1, x2, y2, duration)
end

-- 设置默认每步之间的间隔时间，相当于在每2步操作之间插入了一个delay()函数。
-- 这样脚本中就不需要频繁的调用delay()函数了。
-- 但是如果两步操作之间调用了delay()函数，则以实际调用的为准。
function inner.setDefaultDelay(delay)
    _inst_:setDefaultDelay(delay)
end

-- 输入文字
function inner.input(text)
    _inst_:input(text)
end

function inner.inputKey(keyCode)
    _inst_:inputKey(keyCode)
end

-- 监听通知
function inner.listenNotification(pkgName, title, content, action)
    _inst_:listenNotification(pkgName, title, content, action)
end

function inner.setTrigger(name, triggerType, args, action)
    _inst_:setTrigger(name, triggerType, args, action)
end

--- 查找控件，其中args为一个table，包含了需要满足的条件
--- 如 { text="发送", id="btnSend" }
--- 返回查找到的控件
function inner.findView(args)
    return _inst_:findView(args)
end

function inner.findViewByText(text)
    return inner:findView({ text = text })
end

function inner.doUntilViewFound(args, maxTry, actionWhenNotFound)
    while (maxTry > 0) do
        local v = inner:findView(args)
        if v then
            return v
        end
        actionWhenNotFound()
        maxTry = maxTry - 1
    end
    return nil
end

-- 禁止写入全局变量
setmetatable(_G, {
    __index = inner,
    __newindex = {}
})
-- 禁用setmetatable
setmetatable = nil

function string:startsWith(pattern)
    local index = self:find(pattern)
    return index == 1
end

log("初始化脚本init.lua执行完毕。当前屏幕尺寸：" .. getScreenHeight() .. 'x' .. getScreenWidth())

return inner