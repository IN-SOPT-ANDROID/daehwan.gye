package org.sopt.sample.main.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.ApiPool
import org.sopt.sample.common.FragmentTemplate
import org.sopt.sample.databinding.FragmentGalleryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryFragment : FragmentTemplate<FragmentGalleryBinding>() {
    private val reqresApi = ApiPool.reqresApi

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ) {
        _binding = FragmentGalleryBinding.inflate(inflater, container, attachToParent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val galleryListAdapter = GalleryListAdapter(requireContext())

        reqresApi.getProfiles(1).enqueue(
            object : Callback<ReqresResponse> {
                override fun onResponse(
                    call: Call<ReqresResponse>,
                    response: Response<ReqresResponse>
                ) {
                    val a = GalleryListAdapter(requireContext())

                    response.body().let {
                        a.setList(
                            it?.let {
                                it.data.map {
                                    GalleryElement(
                                        "${it.lastName} ${it.firstName}",
                                        it.email,
                                        it.avatar
                                    )
                                }
                            } ?: emptyList()
                        )
                    }

                    getBinding().rvGallery.adapter = a
                    Log.e("Success", "e")
                }

                override fun onFailure(call: Call<ReqresResponse>, t: Throwable) {
                    // blah blah
                    Log.e("Error", "e")
                }
            }
        )
    }

    override fun getBinding(): FragmentGalleryBinding =
        requireNotNull(_binding) as FragmentGalleryBinding
}