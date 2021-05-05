package ru.hh.android.features.third.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.features.third.experiment.ThirdFeatureExperiment


@Module
@InstallIn(SingletonComponent::class)
internal class ThirdFeatureExperimentModule {

    @Provides
    @IntoSet
    fun providesExperiment(): Experiment = ThirdFeatureExperiment()

}