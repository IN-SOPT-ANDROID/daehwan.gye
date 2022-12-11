package org.sopt.sample.presentation.view.greet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.presentation.interrupt.greet.SignInNetworkInterrupt
import org.sopt.sample.system.util.editTextContent

class SignInActivity : AppCompatActivity() {

    // TODO : Activity 들의 binding 및 onCreate() 과정을 일원화시켜야함
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // 로그인 버튼 클릭 시
        binding.btnSignIn.setOnClickListener {
            val result = SignInNetworkInterrupt(
                id = editTextContent(binding.etIdSignIn),
                pw = editTextContent(binding.etPwSignIn)
            ).sync()

            if (result.isSucceed()) {
                Toast.makeText(this.applicationContext, "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT)
                    .show()
                // val toMyPageView = Intent(this, MyPageActivity::class.java)
                // startActivity(toMyPageView)
                finish()
            } else {
                Toast.makeText(this.applicationContext, result.detail, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // 회원가입 버튼 클릭 시
        binding.btnSignUp.setOnClickListener {
            // val toSignUpView = Intent(this, SignUpActivity::class.java)
            // startActivity(toSignUpView)
        }
    }


}