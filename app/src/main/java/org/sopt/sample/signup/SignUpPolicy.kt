package org.sopt.sample.signup

import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.common.Mbti

class SignUpPolicy(val idMin: Int, val idMax: Int, val pwMin: Int, val pwMax: Int) {

    fun passwordInvalid(binding: ActivitySignUpBinding) =
        binding.idSucceed() && !binding.passwordSucceed()

    fun idInvalid(binding: ActivitySignUpBinding) = !binding.idSucceed()

    fun mbtiInvalid(binding: ActivitySignUpBinding) =
        !Mbti.contains(binding.etSignupMbti.text.toString())

    private fun ActivitySignUpBinding.idSucceed() =
        this.etSignupId.text.length in idMin..idMax

    private fun ActivitySignUpBinding.passwordSucceed() =
        this.etSignupPassword.text.length in pwMin..pwMax
}