package com.example.core_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.api.models.CheckAuthCodeResponseBody

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM access_token")
    fun getUserToken(): CheckAuthCodeResponseBody

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAccessToken(accessToken: CheckAuthCodeResponseBody)
}