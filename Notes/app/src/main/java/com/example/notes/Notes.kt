package com.example.notes

import java.util.UUID

data class Notes (
    val title: String,
    var text: String,
    val id: UUID = UUID.randomUUID()
)