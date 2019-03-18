package com.example.ui.entity

data class Stroke(
        var startPoint: Coordinate,
        var endPoint: Coordinate,
        var displayedTime: Int = 0
)