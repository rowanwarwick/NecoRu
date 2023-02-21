package com.example.practice_18

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.practice_18.databinding.ActivityTestBinding
import com.example.practice_18.enums.Const


class TestActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val state = intent.getStringExtra(Const.STATE.text)
        if (state == Const.IN.text) {
            binding.name.visibility = View.GONE
            binding.secName.visibility = View.GONE
            binding.thName.visibility = View.GONE
            binding.avatar.visibility = View.INVISIBLE
            binding.buttonAvatar.visibility = View.INVISIBLE
        }

        binding.buttonAvatar.setOnClickListener {
            binding.avatar.visibility = View.VISIBLE
        }

        binding.gone.setOnClickListener {
            if (state == Const.REG.text) {
                intent.putExtra(Const.NAME.text, binding.name.text.toString())
                intent.putExtra(Const.LOGIN.text, binding.login.text.toString())
                intent.putExtra(Const.PASSWORD.text, binding.password.text.toString())
                intent.putExtra(Const.SECNAME.text, binding.secName.text.toString())
                intent.putExtra(Const.THNAME.text, binding.thName.text.toString())
                if (binding.avatar.visibility == View.VISIBLE) intent.putExtra(Const.AVATAR.text, R.drawable.face_png_42647)
            } else {
                intent.putExtra(Const.LOGIN.text, binding.login.text.toString())
                intent.putExtra(Const.PASSWORD.text, binding.password.text.toString())
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}