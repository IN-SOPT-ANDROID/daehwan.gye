package org.sopt.sample.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.ApiPool
import org.sopt.sample.Response
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.login.LoginActivity
import java.util.function.Consumer

class SignUpActivity : AppCompatActivity() {
    private val signUpApi = ApiPool.signUpApi

    private lateinit var binding: ActivitySignUpBinding
    private val signUpPolicy = SignUpPolicy(idMin = 6, idMax = 10, pwMin = 8, pwMax = 12)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUpComplete.setOnClickListener {

            if (signUpPolicy.passwordInvalid(binding)) {
                // only password wrong
                Snackbar.make(binding.root, WRONG_PASSWORD, Snackbar.LENGTH_SHORT).show()
            } else if (signUpPolicy.idInvalid(binding)) {
                // id wrong or both wrong
                Snackbar.make(binding.root, WRONG_ID, Snackbar.LENGTH_SHORT).show()
            } else if (signUpPolicy.mbtiInvalid(binding)) {
                Snackbar.make(binding.root, WRONG_MBTI, Snackbar.LENGTH_SHORT).show()
            } else {
                // valid
                signUpApi.singUp(
                    SignUpRequest(
                        binding.etSignupId.text.toString(),
                        binding.etSignupMbti.text.toString(),
                        binding.etSignupPassword.text.toString()
                    )
                ).enqueue(
                    SignUpCallBack(
                        {
                            Snackbar.make(binding.root, SIGNUP_SUCCESS, Snackbar.LENGTH_SHORT)
                                .show()
                        },
                        // Client error code 에 대한 행위 정의 //
                        { clientError ->
                            if (clientError.toString().startsWith("4")) {
                                when (clientError) {
                                    400 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "올바르지 못한 형태의 값이 입력되었습니다.",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                    404 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "올바르지 못한 주소로의 요청입니다. 관리자에게 문의하세요. ",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                    else -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "정의되지 않은 클라이언트 오류입니다. 관리자에게 문의하세요. ",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()

                                        Log.e(
                                            "INVALID STATUS ERROR",
                                            "status : ${clientError}" +
                                                    "body : 정의되지 않은 에러코드입니다."
                                        )
                                    }
                                }
                            }
                        },

                        // server error code 에 대한 행위 정의 //
                        { serverError ->
                            if (serverError.toString().startsWith("5")) {
                                when (serverError) {
                                    500 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "서버 내부적인 오류입니다. 관리자에게 문의하세요.",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                    else -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "정의되지 않은 서버 오류입니다. 관리자에게 문의하세요. ",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()

                                        Log.e(
                                            "INVALID STATUS ERROR",
                                            "status : ${serverError}" +
                                                    "body : 정의되지 않은 에러코드입니다."
                                        )
                                    }
                                }
                            }
                        },

                        // redirect code 에 대한 행위 정의 //
                        { redirect ->
                            if (redirect.toString().startsWith("3"))
                                when (redirect) {
                                    // TODO : about redirect
                                }
                        }
                    )
                )

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private class SignUpCallBack(
        success: () -> Unit,
        vararg failures: Consumer<Int>
    ) : Response<SignUpResponse>(success, *failures)

    companion object {
        private const val WRONG_PASSWORD = "패스워드가 잘못되었습니다."
        private const val WRONG_ID = "아이디가 잘못되었습니다."
        private const val WRONG_MBTI = "없는 MBTI 가 입력되었습니다."
        private const val SIGNUP_SUCCESS = "회원가입이 완료되었습니다."
        private const val SIGNUP_FAILURE = "회원가입이 실패했습니다."
    }
}