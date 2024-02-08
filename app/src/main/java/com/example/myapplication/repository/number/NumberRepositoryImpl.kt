package com.example.myapplication.repository.number

import com.example.myapplication.api.DataResult
import com.example.myapplication.mapper.NumberDtoMapper
import com.example.myapplication.network.number.NumberDataFetcher

class NumberRepositoryImpl(
    private val fetcher: NumberDataFetcher,
    private val numberMapper: NumberDtoMapper
) : NumberRepository {

    override fun getNumberFact(number: String, completionHandler: NumberResultStatus) {
        fetcher.getNumberData(number) { result ->
            when (result) {
                is DataResult.Failure -> completionHandler(NumberStatus.StatusError(result.data.message))
                is DataResult.FailureHttp -> completionHandler(NumberStatus.StatusErrorHttp(result.error))
                is DataResult.Success -> {
                    val model = numberMapper.mapToModel(result.data)
                    completionHandler(NumberStatus.StatusSuccess(model))
                }
            }
        }
    }

    override fun getRandomNumberFact(completionHandler: NumberResultStatus) {
        fetcher.getRandomNumberData { result ->
            when (result) {
                is DataResult.Failure -> completionHandler(NumberStatus.StatusError(result.data.message))
                is DataResult.FailureHttp -> completionHandler(NumberStatus.StatusErrorHttp(result.error))
                is DataResult.Success -> {
                    val model = numberMapper.mapToModel(result.data)
                    completionHandler(NumberStatus.StatusSuccess(model))
                }
            }
        }
    }


}