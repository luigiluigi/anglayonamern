package com.example.flagfinder.presentation.locationmap.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.appolica.interactiveinfowindow.InfoWindow
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.flagfinder.R
import com.example.flagfinder.base.BaseActivity
import com.example.flagfinder.base.CustomDrawableFactory
import com.example.flagfinder.base.CustomDrawableFactory.customDrawable
import com.example.flagfinder.base.SVGBuilder
import com.example.flagfinder.model.FlagResponseItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*

class LocationMapActivity:BaseActivity() ,LocationMapPresenter.LocationMapMvpView,OnMapReadyCallback{


    companion object{

        const val FLAG_MODEL = "FLAG_MODEL"

            fun getIntent(context:Context,flagModel:FlagResponseItem):Intent{
                var intent = Intent(context,LocationMapActivity::class.java)
                var bundle = Bundle()
                bundle.putParcelable(FLAG_MODEL,flagModel)
                intent.putExtras(bundle)
                return intent
            }
    }


    lateinit var mFlagItem:FlagResponseItem
    private lateinit var mMap: GoogleMap
    lateinit var mPresenter:LocationMapPresenter



    override val ActivityLayout: Int
        get() = R.layout.activity_maps

    override fun initViews() {
        mFlagItem = intent.getParcelableExtra(FLAG_MODEL)!!
        mPresenter = LocationMapPresenter(this,this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)





    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        mPresenter.setFlagResponseItem(mFlagItem)

    }

    override fun moveToSelectedFlag(location: LatLng,imageURl: String) {

        markerCreator(location, this, imageURl, "Country")
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(1f).build()

        //Zoom in and animate the camera.
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        mPresenter.displayDetails()
    }

    override fun displayDetails(item: FlagResponseItem) {
         flag_name.text = item.name
         tv_native_name.text = "/${item.nativeName}/"
         tv_region.text = item.region
        tv_latlng.text = "${item!!.latlng!![0]!!},${item!!.latlng!![1]!!}"
        tv_populution.text = item!!.population!!.toString()
        tv_time_zone.text = item.timezones!![0]!!
        SVGBuilder.requestSVGbuilder(this).diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(Uri.parse(item.flag))
            .into(iv_flag)
    }

    private fun markerCreator(
        position: LatLng,
        context: Context,
        imageURl: String,
        snippet: String
    ): Params {
        var marker: Marker = mMap.addMarker(
            MarkerOptions().position(position).icon(
                BitmapDescriptorFactory.fromBitmap(
                     customDrawable(imageURl, context)
                )
            ).snippet(snippet)
        )

        val offSetX = resources.getDimension(R.dimen.offSetX)
        val offSetY = resources.getDimension(R.dimen.offSetY)
        val markerSpec = InfoWindow.MarkerSpecification(offSetX.toInt(), offSetY.toInt())



        return Params(marker, markerSpec)
    }

    data class Params(
        val marker: Marker,
        val markSpec: InfoWindow.MarkerSpecification
    )
}