package br.com.fundatec.fundatecheroes.login.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT * from  userTable")
    fun getUser(): List<UserEntity>

    @Query("SELECT date from userTable")
    fun getUserDate(): Date?

    @Query("DELETE from userTable")
    fun clearCache()
}