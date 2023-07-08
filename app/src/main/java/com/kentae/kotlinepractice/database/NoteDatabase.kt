package com.kentae.kotlinepractice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezatpanah.roomdatabase_youtube.db.NoteDao
import com.ezatpanah.roomdatabase_youtube.db.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun doa(): NoteDao
}

// If we add/delete a new field in Entity class, then we must change our versionNumber
