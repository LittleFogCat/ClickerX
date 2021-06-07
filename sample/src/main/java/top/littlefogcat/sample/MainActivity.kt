package top.littlefogcat.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OperationRecorder.attach(tvRecord)
    }

    fun onViewClicked(view: View) {
        val button = view as Button
        OperationRecorder.onClick(button.text)
        if (view.id == R.id.btn3) {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}