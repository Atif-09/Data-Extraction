package com.example.dataextraction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import com.example.dataextraction.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.output.movementMethod = ScrollingMovementMethod()


binding.extract.setOnClickListener {
    CoroutineScope(Dispatchers.IO).launch {
        if (binding.textInput.text!!.isNotEmpty()){
            val text = getTextFromUrl(binding.textInput.text.toString())

            withContext(Dispatchers.Main){
                binding.output.text = text

            }
        }


    }
}

    }

    private fun getTextFromUrl(url: String):String {
        val document = Jsoup.connect(url).get()
        val elementBody = document.body()
        Log.d("Data", "onCreate: $elementBody")

        return elementBody.text()
    }
}