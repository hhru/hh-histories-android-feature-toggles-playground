package ru.hh.ftplayground.di

import android.content.Context
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@dagger.Module
@InstallIn(SingletonComponent::class)
internal class RootModule {

    @Provides
    fun provideApplicationContext(@ApplicationContext applicationContext: Context): Context {
        return applicationContext
    }

}