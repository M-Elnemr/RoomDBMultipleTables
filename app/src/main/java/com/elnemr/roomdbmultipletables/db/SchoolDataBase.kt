package com.elnemr.roomdbmultipletables.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elnemr.roomdbmultipletables.db.dao.SchoolDao
import com.elnemr.roomdbmultipletables.db.entities.Director
import com.elnemr.roomdbmultipletables.db.entities.School
import com.elnemr.roomdbmultipletables.db.entities.Student
import com.elnemr.roomdbmultipletables.db.entities.Subject
import com.elnemr.roomdbmultipletables.db.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [Director::class, School::class, Student::class, Subject::class, StudentSubjectCrossRef::class],
    version = 1
)
abstract class SchoolDataBase : RoomDatabase() {

    abstract val schoolDao: SchoolDao

    companion object {
        // it's better to provide the database with hilt - I just keep it simple as much as i could
        @Volatile // when a change accrue to INSTANCE at any thread it became immediately visible to other threads
        private var INSTANCE: SchoolDataBase? = null

        fun getInstance(context: Context): SchoolDataBase {
            // lock this(SchoolDataBase) to make sure that it executes on a single thread
            // so no other thread can access database class in the time while we are in the next block
            // to make sure that we have a singleton Database and prevent more than one thread to create a Database instance
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDataBase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}