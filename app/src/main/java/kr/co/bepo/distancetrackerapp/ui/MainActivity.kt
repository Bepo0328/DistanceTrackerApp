package kr.co.bepo.distancetrackerapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kr.co.bepo.distancetrackerapp.R
import kr.co.bepo.distancetrackerapp.databinding.ActivityMainBinding
import kr.co.bepo.distancetrackerapp.util.Permissions.hasLocationPermission

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (hasLocationPermission(this)) {
            navController.navigate(R.id.action_permissionFragment_to_mapsFragment)
        }
    }
}