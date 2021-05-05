package ru.hh.android.debug_panel.di

import ru.hh.android.debug_panel.ui.DebugPanelViewModel
import toothpick.config.Module
import toothpick.ktp.binding.bind


internal class DebugPanelModule : Module() {

    init {
        bind<DebugPanelViewModel>().singleton()
    }

}