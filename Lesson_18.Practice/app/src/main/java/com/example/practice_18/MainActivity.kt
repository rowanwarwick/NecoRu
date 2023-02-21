package com.example.practice_18

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.practice_18.databinding.ActivityMainBinding
import com.example.practice_18.enums.Const

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var input: ActivityResultLauncher<Intent>? = null
    private var register: ActivityResultLauncher<Intent>? = null
    private val forLogin = "bumbleBee"
    private val forPassword = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        input = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                binding.info.text = if (it.data?.getStringExtra(Const.LOGIN.text) == forLogin && it.data?.getStringExtra(
                        Const.PASSWORD.text
                    ) == forPassword
                ) "проверка пройдена" else "проверка не пройдена"
                binding.registr.visibility = View.GONE
                binding.signIn.text = getString(R.string.exit)
            }
        }
        register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val build = StringBuffer()
                build.appendLine(it.data?.getStringExtra(Const.NAME.text))
                    .appendLine(it.data?.getStringExtra(Const.SECNAME.text))
                    .appendLine(it.data?.getStringExtra(Const.THNAME.text))
                binding.info.text = build
                binding.imageView.visibility = View.VISIBLE
                binding.imageView.setImageResource(it.data!!.getIntExtra(Const.AVATAR.text, 0))
                binding.registr.visibility = View.GONE
                binding.signIn.text = getString(R.string.exit)
            }
        }

        binding.registr.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            intent.putExtra(Const.STATE.text, Const.REG.text)
            register?.launch(intent)
        }

        binding.signIn.setOnClickListener {
            if ((it as Button).text == getString(R.string.exit)) {
                it.text = getString(R.string.sigh_in)
                binding.imageView.visibility = View.INVISIBLE
                binding.registr.visibility = View.VISIBLE
                binding.info.text = ""
            } else {
                val intent = Intent(this, TestActivity::class.java)
                intent.putExtra(Const.STATE.text, Const.IN.text)
                input?.launch(intent)
            }
        }
    }
}