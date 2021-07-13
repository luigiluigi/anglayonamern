package com.example.flagfinder.base

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import com.bumptech.glide.GenericRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.load.resource.file.FileToStreamDecoder
import com.bumptech.glide.request.GenericRequest
import com.caverock.androidsvg.SVG
import java.io.InputStream

object SVGBuilder {

    fun requestSVGbuilder(context:Context):GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable>{
       val  requestBuilder  =
           Glide.with(context)
            .using(
                Glide.buildStreamModelLoader(Uri::class.java, context),
                InputStream::class.java
            )
            .from(Uri::class.java)
            .`as`(SVG::class.java)
            .transcode(SvgDrawableTranscoder(), PictureDrawable::class.java)
            .sourceEncoder(StreamEncoder())
            .cacheDecoder(FileToStreamDecoder(SvgDecoder()))
            .decoder(SvgDecoder())
            .listener(SvgSoftwareLayerSetter<Uri>())
        return requestBuilder
    }
}