package org.sopt.sample.main

import android.view.LayoutInflater
import android.view.ViewGroup
import org.sopt.sample.common.FragmentTemplate
import org.sopt.sample.databinding.FragmentGalleryBinding

class GalleryFragment : FragmentTemplate<FragmentGalleryBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) {
        _binding = FragmentGalleryBinding.inflate(inflater, container, attachToParent)
    }

    override fun getBinding(): FragmentGalleryBinding =
        requireNotNull(_binding) as FragmentGalleryBinding
}