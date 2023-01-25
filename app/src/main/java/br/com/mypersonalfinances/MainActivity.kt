package br.com.mypersonalfinances


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.mypersonalfinances.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setupWithNavController(findNavController(R.id.my_nav_host_fragment))

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.my_nav_host_fragment))
    }
}

