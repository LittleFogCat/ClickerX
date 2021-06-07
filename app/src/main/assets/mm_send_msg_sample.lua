---
--- 微信发送消息脚本
---

-- 设计尺寸，用于按比例点击。
-- 例如，设计宽度为1080px，点击x=540；在宽度为720px的手机上，会转换成x=360。
local baseWidth = 1080

-- 设置每个动作直接最少间隔1秒
setDefaultDelay(1000)
-- 启动微信
launchApp("com.tencent.mm")
-- 考虑到冷启动时间，延迟5秒
delay(2000)
-- 点击通讯录
clickByText("通讯录")
-- 点击搜索按钮
clickBy({ desc = "搜索" })
-- 输入文字
input("巧克力")
-- 等待搜索结果
delay(1500)
-- 点击第一个结果
clickByBaseX(550, 400, baseWidth)
-- 输入消息文本
input("Hello ClickerX!")
-- 点击发送按钮
clickByText("发送")
-- 点击3次返回
pressKey(KEY_BACK)
pressKey(KEY_BACK)
pressKey(KEY_BACK)