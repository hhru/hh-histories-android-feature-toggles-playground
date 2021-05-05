package ru.hh.android.features.second.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.features.second.experiment.SecondFeatureExperiment


@Module
@InstallIn(SingletonComponent::class)
internal class SecondFeatureExperimentModule {

    @Provides
    @IntoSet
    fun providesExperiment(): Experiment = SecondFeatureExperiment()

}