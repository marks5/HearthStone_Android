package me.gabriel.hearthstone.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.gabriel.hearthstone.data.hearthstone.HearthStoneRepository
import me.gabriel.hearthstone.data.hearthstone.RealHearthStoneRepository
import me.gabriel.hearthstone.data.hearthstone.source.local.HearthStoneLocalDataSource
import me.gabriel.hearthstone.data.hearthstone.source.local.RealHearthStoneLocalDataSource
import me.gabriel.hearthstone.data.hearthstone.source.remote.HearthStoneRemoteDataSource
import me.gabriel.hearthstone.data.hearthstone.source.remote.RealHearthStoneRemoteDataSource
import me.gabriel.hearthstone.domain.HearthStoneListUseCase
import me.gabriel.hearthstone.domain.RealHearthStoneListUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HearthStoneModule {

    @Singleton
    @Binds
    abstract fun bindHearthStoneRemoteDataSourceReal(
        realHearthStoneRemoteDataSource: RealHearthStoneRemoteDataSource
    ): HearthStoneRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindHearthStoneLocalDataSourceReal(
        realHearthStoneLocalDataSource: RealHearthStoneLocalDataSource
    ): HearthStoneLocalDataSource

    @Singleton
    @Binds
    abstract fun bindHeathStoneRepositoryReal(
        realHearthStoneRepository: RealHearthStoneRepository
    ): HearthStoneRepository

    @Singleton
    @Binds
    abstract fun bindHearthStoneListUseCase(
        useCase: RealHearthStoneListUseCase
    ): HearthStoneListUseCase
}
