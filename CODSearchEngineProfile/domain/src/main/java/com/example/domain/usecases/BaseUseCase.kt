package com.example.domain.usecases

import io.reactivex.Single

interface BaseUseCase<T> {
    fun execute(): Single<T>
}
