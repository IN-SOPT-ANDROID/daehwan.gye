package org.sopt.sample.main

import android.view.LayoutInflater
import android.view.ViewGroup
import org.sopt.sample.common.FragmentTemplate
import org.sopt.sample.databinding.FragmentSearchBinding

class SearchFragment : FragmentTemplate<FragmentSearchBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) {
        _binding = FragmentSearchBinding.inflate(inflater, container, attachToParent)
    }

    override fun getBinding(): FragmentSearchBinding =
        requireNotNull(_binding) as FragmentSearchBinding
}