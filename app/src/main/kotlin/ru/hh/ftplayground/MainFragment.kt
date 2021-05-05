package ru.hh.ftplayground

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.hh.ftplayground.extensions.setupWithNavController


internal class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        view?.let { setupBottomNavigationBar(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar(view)
        }

        view.findViewById<Button>(R.id.fragment_main__button__open_debug_panel).setOnClickListener {
            findNavController().navigate(R.id.DebugPanel)
        }
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar(view: View) {
        val navGraphIds = listOf(
            R.navigation.first__nav_graph,
            R.navigation.second__nav_graph,
            R.navigation.third__nav_graph
        )

        // Setup the bottom navigation view with a list of navigation graphs
        view.findViewById<BottomNavigationView>(R.id.fragment_main__bottom_navigation)
            .setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = childFragmentManager,
                containerId = R.id.fragment_main__nav_host_container,
                intent = requireActivity().intent
            )
    }

}