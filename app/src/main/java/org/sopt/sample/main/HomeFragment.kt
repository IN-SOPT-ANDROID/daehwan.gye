package org.sopt.sample.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.R
import org.sopt.sample.common.FragmentTemplate
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment : FragmentTemplate() {
    private var _binding: FragmentHomeBinding? = null

    private val mockItemList = listOf<HomeListItem>(
        HomeListItem.of(name = "CatchMe", body = "Nunu-Lee", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "FILL-IN", body = "daehwan.gye", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "hihi", body = "daehwan2yo", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "babo", body = "you", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "Hacker", body = "NUNU_LEE", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "BeMe", body = "Nunu-Lee", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "BeMe", body = "Nunu-Lee", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "BeMe", body = "Nunu-Lee", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "BeMe", body = "Nunu-Lee", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "BeMe", body = "Nunu-Lee", imageNo = R.drawable.myface ),
        HomeListItem.of(name = "BeMe", body = "Nunu-Lee", imageNo = R.drawable.myface )
    )

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParrent: Boolean
    ) {
        _binding = FragmentHomeBinding.inflate(inflater, container, attachToParrent)
    }

    override fun getBinding() = requireNotNull(_binding)

    override fun removeBinding() {
        _binding = null;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeListAdapter = HomeListAdapter(requireContext())
        homeListAdapter.setList(mockItemList)
        getBinding().rvHome.adapter = homeListAdapter

    }
}