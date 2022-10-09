package org.sopt.sample.week1.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.week1.common.Constants
import org.sopt.sample.week1.common.Mbti
import org.sopt.sample.week1.mypage.MyPageActivity
import org.sopt.sample.week1.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var insertedId: String
    private lateinit var insertedPassword: String
    private lateinit var insertedMbti: Mbti

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intent 에 값이 로그인 정보가 존재하는 경우
        if (intent.hasExtra(Constants.ID.value) && intent.hasExtra(Constants.PASSWORD.value)) {
            insertedId = intent.getStringExtra(Constants.ID.value)
                ?: throw java.lang.IllegalArgumentException("id expected")
            insertedPassword = intent.getStringExtra(Constants.PASSWORD.value)
                ?: throw java.lang.IllegalArgumentException("password expected")

            // for version 28+
            insertedMbti = (intent.getSerializableExtra(Constants.MBTI.value)
                ?: throw java.lang.IllegalArgumentException("mbti expected")) as Mbti
        }


        // click signUp
        binding.btnSignUp.setOnClickListener {
            val toSignUpView = Intent(this, SignUpActivity::class.java)
            startActivity(toSignUpView)
        }

        // click login
        binding.btnLogin.setOnClickListener {

            // 회원가입된 정보가 로그인 페이지로 전달되지 않은 경우
            if (!intent.hasExtra(Constants.ID.value) || !intent.hasExtra(Constants.PASSWORD.value)) {
                Toast.makeText(this.applicationContext, "등록되지 않은 회원입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.etIdInsertion.text.toString() == insertedId &&
                binding.etPasswordInsertion.text.toString() == insertedPassword
            ) {
                // login success
                Toast.makeText(this.applicationContext, "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                val toMyPageView = Intent(this, MyPageActivity::class.java)
                toMyPageView.putExtra(Constants.ID.value, insertedId)
                toMyPageView.putExtra(Constants.MBTI.value, insertedMbti)
                startActivity(toMyPageView)
                finish()
            } else {
                // login failed
                Toast.makeText(this.applicationContext, "로그인 정보가 올바르지 못합니다.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}