package com.elnemr.roomdbmultipletables.db.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.elnemr.roomdbmultipletables.db.entities.Student
import com.elnemr.roomdbmultipletables.db.entities.Subject

// relationship class between two tables Subject and Director (Add Student PrimaryKey into Subject Table)
data class StudentWithSubject(
    // provide Student class properties with (@Embedded)
    @Embedded val student: Student,

    @Relation(
        parentColumn = "studentName", //(primaryKey in Student table)
        entityColumn = "subjectName", //(primaryKey in Subject table)
        associateBy = Junction(StudentSubjectCrossRef::class)// Relationship Cross Class
    )
    val subject: List<Subject>
)