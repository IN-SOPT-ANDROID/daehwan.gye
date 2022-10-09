package org.sopt.sample.main

import android.view.LayoutInflater
import android.view.ViewGroup
import org.sopt.sample.common.FragmentTemplate
import org.sopt.sample.databinding.FragmentSearchBinding

class SearchFragment : FragmentTemplate() {
    private var _binding: FragmentSearchBinding? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) {
        _binding = FragmentSearchBinding.inflate(inflater, container, attachToParent)
    }

    override fun getBinding() = requireNotNull(_binding)

    override fun removeBinding() {
        _binding = null;
    }
}