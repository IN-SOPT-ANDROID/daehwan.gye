package org.sopt.sample.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.ApiPool
import org.sopt.sample.Response
import org.sopt.sample.databinding.ActivityLoginBinding
import org.sopt.sample.mypage.MyPageActivity
import org.sopt.sample.signup.SignUpActivity
import java.util.function.Consumer

class LoginActivity : AppCompatActivity() {
    private val loginApi = ApiPool.loginApi

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // click signUp
        binding.btnSignUp.setOnClickListener {
            val toSignUpView = Intent(this, SignUpActivity::class.java)
            startActivity(toSignUpView)
        }

        // click login
        val function: (v: View) -> Unit = {

            loginApi.login(
                LoginRequest(
                    binding.etIdInsertion.text.toString(),
                    binding.etPasswordInsertion.text.toString()
                )
                // call back
            ).enqueue(
                LoginCallBack(

                    // 200 success 에 대한 행위 정의 //
                    {
                        // 200 alert
                        Toast.makeText(this.applicationContext, "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT)
                            .show()

                        val toMyPageView = Intent(this, MyPageActivity::class.java)

                        startActivity(toMyPageView)
                        finish()
                    },

                    // TODO : 코드 중복 해결해야함
                    // -> 공통부분 추상화 예정

                    // Client error code 에 대한 행위 정의 //
                    { clientError ->
                        if (clientError.toString().startsWith("4")) {
                            when (clientError) {
                                400 -> {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "올바르지 못한 형태의 값이 입력되었습니다.",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                403 -> {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "로그인 정보가 올바르지 못합니다. ",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                404 -> {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "올바르지 못한 주소로의 요청입니다. 관리자에게 문의하세요. ",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                else -> {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "정의되지 않은 클라이언트 오류입니다. 관리자에게 문의하세요. ",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
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
                                        this@LoginActivity,
                                        "서버 내부적인 오류입니다. 관리자에게 문의하세요.",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                                else -> {
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
        }
        binding.btnLogin.setOnClickListener(function)
    }

    private class LoginCallBack(
        success: () -> Unit,
        vararg failures: Consumer<Int>
    ) : Response<LoginResponse>(success, *failures)
}