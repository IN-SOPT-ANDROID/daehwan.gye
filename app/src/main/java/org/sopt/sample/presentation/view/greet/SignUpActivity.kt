package org.sopt.sample.presentation.view.greet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.interrupt.greet.SignUpNetworkInterrupt
import org.sopt.sample.system.util.editTextContent

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 회원가입 버튼 클릭 시
        binding.btnSignUp.setOnClickListener {
            val result = SignUpNetworkInterrupt(
                nickname = editTextContent(binding.etIdSignUpNickname),
                email = editTextContent(binding.etIdSignUpEmail),
                password = editTextContent(binding.etIdSignUpPassword),
                passwordValidation = editTextContent(binding.etIdSignUpPasswordValidation)
            ).sync()

            if(result.isSucceed()) {
                // val toSignInView = Intent(this, SignInActivity::class.java)
                // startActivity(toSignInView)
                finish()
            }
        }
    }
}