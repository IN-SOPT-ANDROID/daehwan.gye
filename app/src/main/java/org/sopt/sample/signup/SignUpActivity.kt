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
                        // Client error code ??? ?????? ?????? ?????? //
                        { clientError ->
                            if (clientError.toString().startsWith("4")) {
                                when (clientError) {
                                    400 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "???????????? ?????? ????????? ?????? ?????????????????????.",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                    404 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "???????????? ?????? ???????????? ???????????????. ??????????????? ???????????????. ",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                    else -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "???????????? ?????? ??????????????? ???????????????. ??????????????? ???????????????. ",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()

                                        Log.e(
                                            "INVALID STATUS ERROR",
                                            "status : ${clientError}" +
                                                    "body : ???????????? ?????? ?????????????????????."
                                        )
                                    }
                                }
                            }
                        },

                        // server error code ??? ?????? ?????? ?????? //
                        { serverError ->
                            if (serverError.toString().startsWith("5")) {
                                when (serverError) {
                                    500 -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "?????? ???????????? ???????????????. ??????????????? ???????????????.",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                    else -> {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            "???????????? ?????? ?????? ???????????????. ??????????????? ???????????????. ",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()

                                        Log.e(
                                            "INVALID STATUS ERROR",
                                            "status : ${serverError}" +
                                                    "body : ???????????? ?????? ?????????????????????."
                                        )
                                    }
                                }
                            }
                        },

                        // redirect code ??? ?????? ?????? ?????? //
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
        private const val WRONG_PASSWORD = "??????????????? ?????????????????????."
        private const val WRONG_ID = "???????????? ?????????????????????."
        private const val WRONG_MBTI = "?????? MBTI ??? ?????????????????????."
        private const val SIGNUP_SUCCESS = "??????????????? ?????????????????????."
        private const val SIGNUP_FAILURE = "??????????????? ??????????????????."
    }
}