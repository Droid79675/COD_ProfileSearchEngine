package com.codsearchengineprofile.domain.models

data class ProfileDomainModel (
    var platform: String,
    var username: String,
    var level: Int,
    val accuracy: Double,
    val wins: Int,
    val losses: Int,
    val kdRatio: Double,
    val headshots: Int,
    val score: Long
)
