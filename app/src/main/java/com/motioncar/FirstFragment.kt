package com.motioncar

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController
import androidx.transition.ChangeBounds
import androidx.transition.Explode
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.motioncar.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    var isFlag = false
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z, true
        )
        returnTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z, false
        )
        exitTransition = Explode()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playPauseCircle.setOnClickListener {
            isFlag = !isFlag
            val params = it.layoutParams as FrameLayout.LayoutParams
            var changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeBounds.setPathMotion(MaterialArcMotion())
            TransitionManager.beginDelayedTransition(binding.root, changeBounds)
            if (isFlag) {
                params.gravity = Gravity.TOP or Gravity.START

            } else {
                params.gravity = Gravity.BOTTOM or Gravity.END
            }
            binding.playPauseCircle.layoutParams = params

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}