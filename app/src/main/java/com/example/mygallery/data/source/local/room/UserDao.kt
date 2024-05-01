package com.example.mygallery.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE email = :email AND password =:password")
    fun getUserByEmailAndPassword(email: String, password: String): Flow<List<UserEntity>>
}
