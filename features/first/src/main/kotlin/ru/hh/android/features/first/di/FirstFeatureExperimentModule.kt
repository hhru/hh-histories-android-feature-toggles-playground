package ru.hh.android.features.first.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.features.first.experiment.FirstFeatureExperiment


@Module
@InstallIn(SingletonComponent::class)
internal class FirstFeatureExperimentModule {

    @Provides
    @IntoSet
    fun providesExperiment(): Experiment = FirstFeatureExperiment()

}