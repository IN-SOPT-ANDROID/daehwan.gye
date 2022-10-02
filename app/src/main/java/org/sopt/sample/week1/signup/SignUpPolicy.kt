package org.sopt.sample.week1.signup

import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpPolicy(val idMin: Int, val idMax: Int, val pwMin: Int, val pwMax: Int) {

    fun passwordInvalid(binding: ActivitySignUpBinding) =
        binding.idSucceed() && !binding.passwordSucceed()

    fun idInvalid(binding: ActivitySignUpBinding) = !binding.idSucceed()

    private fun ActivitySignUpBinding.idSucceed() =
        this.signupEtId.text.length in idMin..idMax

    private fun ActivitySignUpBinding.passwordSucceed() =
        this.signupEtPassword.text.length in pwMin..pwMax
}