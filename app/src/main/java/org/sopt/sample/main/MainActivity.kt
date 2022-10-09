package org.sopt.sample.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, HomeFragment())
            // TODO : .commitNow() 와 차이? 동기/비동기?
            .commit()

        binding.bnvHome.setOnItemSelectedListener {
            BottomMenu
                .from(it.title.toString().uppercase())
                .changeFragment(supportFragmentManager)
            true
        }

    }

    enum class BottomMenu() {
        HOME {
            override fun changeFragment(supportFragmentManager: FragmentManager) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main, HomeFragment())
                    .commit()
            }
        },
        SEARCH {
            override fun changeFragment(supportFragmentManager: FragmentManager) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main, SearchFragment())
                    .commit()
            }
        },
        GALLERY {
            override fun changeFragment(supportFragmentManager: FragmentManager) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main, GalleryFragment())
                    .commit()
            }
        }
        ;

        abstract fun changeFragment(supportFragmentManager: FragmentManager)

        companion object {
            fun from(title: String): BottomMenu {
                return when (title) {
                    "HOME" -> HOME
                    "GALLERY" -> GALLERY
                    "SEARCH" -> SEARCH
                    else -> {
                        throw java.lang.IllegalArgumentException("inValid Id")
                    }
                }
            }
        }
    }
}