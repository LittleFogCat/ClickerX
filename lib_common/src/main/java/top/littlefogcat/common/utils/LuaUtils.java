package top.littlefogcat.common.utils;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

import java.util.HashMap;
import java.util.Map;

/**
 * 将LuaValue对象转换成Java中的数据结构。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
public class LuaUtils {
    /**
     * 将LuaValue转换成对应的Java类型。
     * <p>
     * nil - null;
     * number - float;
     * string - String;
     * boolean - boolean;
     * table - Map;
     * 其他 - 不转换;
     */
    public static Object parseLuaValue(LuaValue luaValue) {
        switch (luaValue.type()) {
            case LuaValue.TNIL:
                return null;
            case LuaValue.TTABLE:
                return luaTableToMap((LuaTable) luaValue);
            case LuaValue.TSTRING:
                return luaValue.tojstring();
            case LuaValue.TBOOLEAN:
                return luaValue.toboolean();
            case LuaValue.TNUMBER:
                return luaValue.tofloat();
            default:
                return luaValue;
        }
    }

    /**
     * LuaTable转换为HashMap
     */
    public static Map<Object, Object> luaTableToMap(LuaTable table) {
        Map<Object, Object> map = new HashMap<>();
        LuaValue k = LuaValue.NIL;
        while (true) {
            Varargs n = table.next(k);
            if ((k = n.arg1()).isnil())
                break;
            LuaValue v = n.arg(2);
            Object jk = parseLuaValue(k);
            Object vk = parseLuaValue(v);
            map.put(jk, vk);
        }
        return map;
    }
}
