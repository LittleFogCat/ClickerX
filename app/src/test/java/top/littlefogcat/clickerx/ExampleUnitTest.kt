package top.littlefogcat.clickerx

import org.junit.Test

import org.junit.Assert.*
import java.io.FileOutputStream

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        val bytes = "hello world".toByteArray()
        val fos = FileOutputStream("C:\\users/littlefogcat/desktop/a.txt")
        fos.write(bytes)
        fos.flush()
        fos.close()
    }
}