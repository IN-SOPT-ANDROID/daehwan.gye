package org.sopt.sample.week1.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMyPageBinding
import org.sopt.sample.week1.common.Constants

class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMyPageNameValue.text = intent.getStringExtra(Constants.ID.value)
        binding.tvMyPageMbtiValue.text = intent.getSerializableExtra(Constants.MBTI.value).toString()
    }
}