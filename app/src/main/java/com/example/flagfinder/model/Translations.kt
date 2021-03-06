package com.example.flagfinder.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Translations(

	@field:SerializedName("br")
	val br: String? = null,

	@field:SerializedName("de")
	val de: String? = null,

	@field:SerializedName("pt")
	val pt: String? = null,

	@field:SerializedName("ja")
	val ja: String? = null,

	@field:SerializedName("hr")
	val hr: String? = null,

	@field:SerializedName("it")
	val it: String? = null,

	@field:SerializedName("fa")
	val fa: String? = null,

	@field:SerializedName("fr")
	val fr: String? = null,

	@field:SerializedName("es")
	val es: String? = null,

	@field:SerializedName("nl")
	val nl: String? = null
) : Parcelable