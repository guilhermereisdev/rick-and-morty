package com.guilhermereisapps.rickandmortykotlin.di

import com.guilhermereisapps.rickandmortykotlin.data.local.dao.CharacterDao
import com.guilhermereisapps.rickandmortykotlin.data.remote.RemoteService
import com.guilhermereisapps.rickandmortykotlin.data.repository.CharactersRepositoryImpl
import com.guilhermereisapps.rickandmortykotlin.domain.repository.ICharactersRepository
import com.guilhermereisapps.rickandmortykotlin.domain.usecase.CharactersUseCaseImpl
import com.guilhermereisapps.rickandmortykotlin.domain.usecase.ICharactersUseCase
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterdetailsviewmodel.CharacterDetailsViewModel
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel.CharactersListViewModel
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.characterslistviewmodel.ICharactersListViewModel
import com.guilhermereisapps.rickandmortykotlin.presentation.viewmodel.filtersviewmodel.FiltersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ICharactersRepository> {
        CharactersRepositoryImpl(get<RemoteService>(), get<CharacterDao>())
    }

    single<ICharactersUseCase> { CharactersUseCaseImpl(get<ICharactersRepository>()) }

    viewModel { CharactersListViewModel(get<ICharactersUseCase>()) }
    viewModel { CharacterDetailsViewModel(get<ICharactersUseCase>()) }
    viewModel { FiltersViewModel() }
}
