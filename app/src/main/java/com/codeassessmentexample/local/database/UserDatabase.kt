package com.codeassessmentexample.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codeassessmentexample.local.dao.UserDAO
import com.codeassessmentexample.local.model.UserEntity
import com.codeassessmentexample.local.utils.DBConstants
import com.codeassessmentexample.local.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [UserEntity::class,],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDAO() : UserDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return  INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): UserDatabase {
           return Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DBConstants.DB_NAME
            ).build()
        }
    }
}