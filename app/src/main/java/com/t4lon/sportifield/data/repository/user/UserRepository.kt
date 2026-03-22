package com.t4lon.sportifield.data.repository.user

import com.t4lon.sportifield.data.entity.UserRemote
import com.t4lon.sportifield.data.model.User
import com.t4lon.sportifield.data.repository.BaseFirestoreRepository
import com.t4lon.sportifield.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor()
    : BaseFirestoreRepository<UserRemote>("users") {

    fun getUserProfile(userId: String): Flow<User?> {
        return getDocument(userId, UserRemote::class.java).map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val userRemote = resource.data
                    User(
                        uid = userId,
                        name = userRemote.name,
                    )
                }
                is Resource.Error -> null
                is Resource.Loading -> null
            }
        }
    }
}