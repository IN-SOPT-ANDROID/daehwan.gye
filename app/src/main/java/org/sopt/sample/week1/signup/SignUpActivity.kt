package org.sopt.sample.week1.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.week1.common.Constants
import org.sopt.sample.week1.common.MBTI
import org.sopt.sample.week1.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    companion object {
        private val WRONG_PASSWORD = "패스워드가 잘못되었습니다."
        private val WRONG_ID = "아이디가 잘못되었습니다."
        private val WRONG_MBTI = "없는 MBTI 가 입력되었습니다."
        private val SIGNUP_SUCCESS = "회원가입이 완료되었습니다."
    }

    private lateinit var binding: ActivitySignUpBinding
    private val signUpPolicy = SignUpPolicy(idMin = 6, idMax = 10, pwMin = 8, pwMax = 12)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUpComplete.setOnClickListener {

            if (signUpPolicy.passwordInvalid(binding)) {
                // only password wrong
                Snackbar.make(binding.root, WRONG_PASSWORD, Snackbar.LENGTH_SHORT).show()
            } else if (signUpPolicy.idInvalid(binding)) {
                // id wrong or both wrong
                Snackbar.make(binding.root, WRONG_ID, Snackbar.LENGTH_SHORT).show()
            } else {
                // valid
                val toLoginView = Intent(this, LoginActivity::class.java)
                toLoginView.putExtra(Constants.ID.value, binding.signupEtId.text.toString())
                toLoginView.putExtra(
                    Constants.PASSWORD.value,
                    binding.signupEtPassword.text.toString()
                )
                toLoginView.putExtra(
                    Constants.MBTI.value,
                    MBTI.from(binding.signupEtMbti.text.toString())
                )

                // for version 28+
                if (toLoginView.getSerializableExtra(
                        Constants.MBTI.value
                    ) == MBTI.INVALID
                ) {
                    Snackbar.make(binding.root, WRONG_MBTI, Snackbar.LENGTH_SHORT).show()
                } else {
                    // alert
                    Snackbar.make(binding.root, SIGNUP_SUCCESS, Snackbar.LENGTH_SHORT).show()

                    startActivity(toLoginView)
                }
            }
        }
    }
}