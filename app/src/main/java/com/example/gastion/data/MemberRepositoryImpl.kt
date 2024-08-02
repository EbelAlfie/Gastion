package com.example.gastion.data

import com.example.gastion.data.model.UserRequest
import com.example.gastion.data.model.UserResponse
import com.example.gastion.data.service.MemberService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This repository handle user authentication and their data flow
 */
class MemberRepositoryImpl @Inject constructor(
  private val memberService: MemberService
): MemberRepository {
  override suspend fun login(loginRequest: UserRequest): Flow<UserResponse> {
    return flow {
      try {
        val response = memberService.login(loginRequest)

      } catch (e: Throwable) {

      }
    }
  }
}