package com.elnemr.roomdbmultipletables.db.entities.relations

import androidx.room.Entity

// table for (many to many) relationship between student and subject
// requires additional relationship classes
@Entity(primaryKeys = ["studentName", "subjectName"])
data class StudentSubjectCrossRef(
    val studentName: String,
    val subjectName: String
)