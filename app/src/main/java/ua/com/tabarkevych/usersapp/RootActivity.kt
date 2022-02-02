package ua.com.tabarkevych.usersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ua.com.tabarkevych.usersapp.databinding.ActivityRootBinding

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_UsersApp)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}