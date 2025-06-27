package com.mec.myapplication

import ai.guiji.duix.sdk.client.Constant
import ai.guiji.duix.sdk.client.DUIX
import ai.guiji.duix.sdk.client.render.DUIXRenderer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mec.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val baseDir = "D:\\ZhushiWorkspace\\Heygem\\storage\\gj_dh_res";
        val modelDir = "D:\\ZhushiWorkspace\\Heygem\\storage\\model_hazel";

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val renderer = DUIXRenderer(this, binding.glTextureView);

        val duix = DUIX(this, baseDir, modelDir, renderer) { event, msg, info ->
            when (event) {
                Constant.CALLBACK_EVENT_INIT_READY -> {
                    print("duix has been init")
                }

                Constant.CALLBACK_EVENT_INIT_ERROR -> {
                    print("duix init error")
                }
                // ...

            }
        }
        // 异步回调结果
        duix.init()

    }
}