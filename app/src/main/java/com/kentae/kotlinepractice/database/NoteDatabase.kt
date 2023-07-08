package com.kentae.kotlinepractice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kentae.kotlinepractice.models.NoteDao
import com.kentae.kotlinepractice.models.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun doa(): NoteDao
}

// If we add/delete a new field in Entity class, then we must change our versionNumber
