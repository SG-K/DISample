package com.example.koinsample.di

import com.example.koinsample.reposearch.viewmodels.GitHubSearchViewmodel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { GitHubSearchViewmodel(get()) }
}