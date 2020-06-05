package com.hawksjamesf.map.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hawksjamesf.map.model.LBS

@Database(entities = [LBS::class], version = 1)
abstract class LbsDb : RoomDatabase() {
    abstract fun lbsDao(): LbsDao

    companion object {
        private var instance: LbsDb? = null
        @Synchronized
        fun get(context: Context): LbsDb {
            if (instance == null) {
                instance = Room.databaseBuilder(context, LbsDb::class.java, "LbsDb")
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                Log.d("", "onCreate")
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                Log.d("", "onOpen")
                            }

                        }).build()
            }
            return instance!!
        }
    }
}