package top.littlefogcat.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn1.setOnClickListener {
            OperationRecorder.onClick(btn1.text)
            Toast.makeText(this, "bingo", Toast.LENGTH_SHORT).show()
        }
    }
}