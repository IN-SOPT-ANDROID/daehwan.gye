package org.sopt.sample.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 *  반복되는 binding 생성, 제거 과정을 놓치지 않고 수행하기 위해 Template Pattern 활용
 *  - getBinding() 을 구현하도록하여 컴파일 단계에서 해당 값에 선언을 명시하도록 한다.
 *  - Fragment 생명주기를 고려해, view 를 지우지 않아 메모리에 방치되는 행위를 막는다.
 */
abstract class FragmentTemplate<FragmentType> : Fragment(), FragmentMandatoryProcess<FragmentType> {
    protected var _binding: ViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // _binding 에 값을 넣지 않으면 컴파일 오류 발생
        initBinding(inflater, container, false)

        return (getBinding() as ViewBinding).root
    }

    override fun onDestroyView() {
        removeBinding()
        super.onDestroyView()
    }

    override fun removeBinding() {
        _binding = null;
    }
}