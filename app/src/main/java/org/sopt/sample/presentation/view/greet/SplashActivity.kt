package org.sopt.sample.presentation.view.greet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 이메일 로그인 버튼 클릭 시
        binding.btnSplashGoToSignIn.setOnClickListener {
            // val toSignInView = Intent(this, SignInActivity::class.java)
            // startActivity(toSignInView)
            finish()
        }

        // 회원 가입 텍스트 클릭 시
        binding.txtSplashGoToSignUp.setOnClickListener {
            // val toSignUpView = Intent(this, SignUpActivity::class.java)
            // startActivity(toSignUpView)
            finish()
        }
    }
}