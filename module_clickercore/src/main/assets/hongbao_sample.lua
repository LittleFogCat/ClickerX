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
