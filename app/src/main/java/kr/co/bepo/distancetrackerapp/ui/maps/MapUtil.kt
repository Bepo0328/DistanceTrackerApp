package kr.co.bepo.distancetrackerapp.ui.maps

import android.icu.util.LocaleData
import android.util.Log
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.util.*

object MapUtil {

    fun setCameraPosition(location: LatLng): CameraPosition {
        return CameraPosition.Builder()
            .target(location)
            .zoom(18f)
            .build()
    }

    fun calculateElapsedTime(startTime: Long, stopTime: Long): String {
        val elapsedTime = stopTime - startTime

        return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(elapsedTime)
    }

    fun calculateTheDistance(locationList: MutableList<LatLng>): String {
        if (locationList.size > 1) {
            val meters =
                SphericalUtil.computeDistanceBetween(locationList.first(), locationList.last())
            val kilometers = meters / 1_000
            return DecimalFormat("#.##").format(kilometers)
        }
        return "0.00"
    }
}