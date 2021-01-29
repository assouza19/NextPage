package com.br.nextpage.di.koin

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.br.nextpage.data.dataSource.*
import com.br.nextpage.data.repository.BookRepositoryImpl
import com.br.nextpage.data.repository.LoginRepositoryImpl
import com.br.nextpage.data.repository.UserRepositoryImpl
import com.br.nextpage.domain.repository.BookRepository
import com.br.nextpage.domain.repository.LoginRepository
import com.br.nextpage.domain.repository.UserRepository
import com.br.nextpage.domain.usecase.GetBookDetailUseCase
import com.br.nextpage.domain.usecase.GetBooksUseCase
import com.br.nextpage.domain.usecase.GetTokenUseCase
import com.br.nextpage.presentation.viewmodel.BookDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
//    viewModel { HomeViewModel(get(), Dispatchers.Main, get(), get(), get()) }
//    viewModel { FavoriteViewModel(Dispatchers.Main, get(), get(), get()) }
    viewModel { BookDetailViewModel(getBookDetailUseCase = get()) }
//    viewModel {
//        LoginViewModel(
//            repository = get(),
//            userRepository = get(),
//            get(),
//            Dispatchers.Main
//        )
//    }

    factory {
        GetBookDetailUseCase(repository = get(), userRepository = get())
    }

    factory {
        GetTokenUseCase(userRepository = get())
    }

    factory {
        GetBooksUseCase(repository = get(), userRepository = get())
    }

    factory<BookRepository> {
        BookRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    factory<BookLocalDataSource> { BookLocalDataSourceImpl() }
    factory<UserLocalDataSource> { UserLocalDataSourceImpl(preferences = get()) }
    factory<UserDataSource> { UserDataSourceImpl(api = get()) }
    factory<BookRemoteDataSource> { BookRemoteDataSourceImpl(api = get()) }

    factory<UserRepository> { UserRepositoryImpl(get(), localDataSource = get()) }
    factory<LoginRepository> { LoginRepositoryImpl(get()) }
    factory<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
}