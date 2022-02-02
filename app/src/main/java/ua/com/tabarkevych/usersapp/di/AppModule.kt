package ua.com.tabarkevych.usersapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.com.tabarkevych.usersapp.common.Constants
import ua.com.tabarkevych.usersapp.data.remote.UsersAppApi
import ua.com.tabarkevych.usersapp.data.repository.UserInfoRepositoryImpl
import ua.com.tabarkevych.usersapp.data.repository.UsersRepositoryImpl
import ua.com.tabarkevych.usersapp.domain.repository.UserInfoRepository
import ua.com.tabarkevych.usersapp.domain.repository.UsersRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserApi(): UsersAppApi {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersAppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(api: UsersAppApi):UsersRepository{
        return UsersRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserInfoRepository(api: UsersAppApi):UserInfoRepository{
        return UserInfoRepositoryImpl(api)
    }

}