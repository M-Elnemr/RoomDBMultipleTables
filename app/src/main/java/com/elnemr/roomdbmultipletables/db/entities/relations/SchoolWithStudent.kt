package com.elnemr.roomdbmultipletables.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.elnemr.roomdbmultipletables.db.entities.School
import com.elnemr.roomdbmultipletables.db.entities.Student


data class SchoolWithStudent(
    @Embedded val school: School,

    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val student: List<Student>
)