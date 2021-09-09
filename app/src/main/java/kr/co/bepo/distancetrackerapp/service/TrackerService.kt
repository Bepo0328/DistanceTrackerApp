package kr.co.bepo.distancetrackerapp.service


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.AndroidEntryPoint
import kr.co.bepo.distancetrackerapp.util.Constants.ACTION_SERVICE_START
import kr.co.bepo.distancetrackerapp.util.Constants.ACTION_SERVICE_STOP
import kr.co.bepo.distancetrackerapp.util.Constants.NOTIFICATION_CHANNEL_ID
import kr.co.bepo.distancetrackerapp.util.Constants.NOTIFICATION_CHANNEL_NAME
import kr.co.bepo.distancetrackerapp.util.Constants.NOTIFICATION_ID
import javax.inject.Inject

@AndroidEntryPoint
class TrackerService : LifecycleService() {

    @Inject
    lateinit var notification: NotificationCompat.Builder

    @Inject
    lateinit var notificationManager: NotificationManager

    companion object {
        val started = MutableLiveData<Boolean>()
    }

    private fun setInitialValues() {
        started.postValue(false)
    }

    override fun onCreate() {
        setInitialValues()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_SERVICE_START -> {
                    started.postValue(true)
                    startForegroundService()
                }
                ACTION_SERVICE_STOP -> {
                    started.postValue(false)
                }
                else -> {
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService() {
        createNotificationChannel()
        startForeground(
            NOTIFICATION_ID,
            notification.build()
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}