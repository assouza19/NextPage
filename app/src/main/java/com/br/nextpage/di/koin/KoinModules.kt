package com.br.nextpage.di.koin

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.br.nextpage.data.dataSource.BookLocalDataSource
import com.br.nextpage.data.dataSource.BookLocalDataSourceImpl
import com.br.nextpage.data.dataSource.BookRemoteDataSource
import com.br.nextpage.data.dataSource.BookRemoteDataSourceImpl
import com.br.nextpage.data.repository.BookRepositoryImpl
import com.br.nextpage.data.repository.LoginRepositoryImpl
import com.br.nextpage.data.repository.UserRepositoryImpl
import com.br.nextpage.domain.repository.BookRepository
import com.br.nextpage.domain.repository.LoginRepository
import com.br.nextpage.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), Dispatchers.Main, get(), get(), get()) }
    viewModel { FavoriteViewModel(Dispatchers.Main, get(), get(), get()) }
    viewModel { BookDetailsViewModel() }
    viewModel {
        LoginViewModel(
            repository = get(),
            userRepository = get(),
            get(),
            Dispatchers.Main
        )
    }

    factory<BookRepository> {
        BookRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    factory<BookLocalDataSource> { BookLocalDataSourceImpl() }
    factory<BookRemoteDataSource> { BookRemoteDataSourceImpl(api = get()) }

    factory { BooksRetrofit() }
    factory { UsersRetrofit() }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
}