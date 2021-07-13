package com.example.flagfinder.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionalBlocsItem(

	@field:SerializedName("otherNames")
	val otherNames: List<String?>? = null,

	@field:SerializedName("acronym")
	val acronym: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("otherAcronyms")
	val otherAcronyms: List<String?>? = null
) : Parcelable