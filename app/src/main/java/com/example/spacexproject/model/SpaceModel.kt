package com.example.spacexproject.model

import javax.inject.Inject

data class SpaceModel @Inject constructor (
    var capsule_serial : String,
    var capsule_id : String)


