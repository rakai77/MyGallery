package com.example.mygallery.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.mygallery.data.source.Resource
import com.example.mygallery.data.source.local.room.UserDao
import com.example.mygallery.data.source.local.room.toDomain
import com.example.mygallery.domain.model.User
import com.example.mygallery.domain.model.toEntity
import com.example.mygallery.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<User>> {
        return flow {
            val users = dao.getUserByEmailAndPassword(email, password).first()
            if (users.isNotEmpty()) {
                emit(Resource.Success(users.first().toDomain()))
            } else {
                emit(Resource.Error(-1, "Your credential not valid, please check your email and password!"))
            }
        }.catch { e ->
            emit(Resource.Error(-1, e.message ?: "Something wrong."))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun register(user: User): Flow<Resource<User>> {
        return flow<Resource<User>> {
            dao.insertUser(user.toEntity())
            emit(Resource.Success(user))
        }.catch { e ->
            when (e) {
                is SQLiteConstraintException -> {
                    emit(Resource.Error(-1, "Email already registered in apps, use another email!"))
                }
                else -> {
                    emit(Resource.Error(-1, e.message ?: "Something wrong"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}