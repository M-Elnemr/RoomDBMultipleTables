package com.elnemr.roomdbmultipletables.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.elnemr.roomdbmultipletables.db.entities.Director
import com.elnemr.roomdbmultipletables.db.entities.School

// relationship class between two tables School and Director (Add School PrimaryKey into Director Table)
data class SchoolWithDirector(
    // provide School class properties with (@Embedded)
    @Embedded val school: School,

    @Relation(
        parentColumn = "schoolName", //(primaryKey in School table)
        entityColumn = "schoolName" //(foreignKey in School table)
    )
    val director: Director
)