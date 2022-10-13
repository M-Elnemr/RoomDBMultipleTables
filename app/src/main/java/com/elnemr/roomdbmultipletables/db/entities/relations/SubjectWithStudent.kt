package com.elnemr.roomdbmultipletables.db.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.elnemr.roomdbmultipletables.db.entities.Student
import com.elnemr.roomdbmultipletables.db.entities.Subject

// relationship class between two tables Subject and Student (Add Subject PrimaryKey into Student Table)
data class SubjectWithStudent(
    // provide School class properties with (@Embedded)
    @Embedded val subject: Subject,

    @Relation(
        parentColumn = "subjectName", //(primaryKey in Subject table)
        entityColumn = "studentName", //(primaryKey in Student table)
        associateBy = Junction(StudentSubjectCrossRef::class) // Relationship Cross Class
    )
    val student: List<Student>
)