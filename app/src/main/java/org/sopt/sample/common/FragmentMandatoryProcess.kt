package org.sopt.sample.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

// 모든 Fragment 에서 하위 메서드들을 구현해 binding 에 대한 처리를 Mandatory 하게 명시하기 위함
interface FragmentMandatoryProcess<out FragmentType> {
    fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    )

    fun getBinding(): ViewBinding
    fun removeBinding()
}