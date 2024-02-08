package com.example.myapplication.api.number

import com.example.myapplication.api.API
import com.example.myapplication.api.ApiRequestType
import com.example.myapplication.api.ApiValue

class NumberApi {
    class GetNumberData(number: String) : API( ApiValue(number, ApiRequestType.Get))
    object GetRandomNumberData : API( ApiValue("random/math", ApiRequestType.Get))
}