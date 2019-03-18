package com.example.ui.entity

data class Coordinate(
        var x: Float = 0F,
        var y: Float = 0F
) {
    override fun equals(other: Any?): Boolean {
        if (other is Coordinate)
            return other.x == other.y

        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }
}