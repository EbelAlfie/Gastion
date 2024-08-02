package com.example.gastion.data.service

import retrofit2.http.Body
import retrofit2.http.POST

interface MemberService {
  @POST("{url}")
  fun <req, res>login(
    @Body request: req
  ): res
}