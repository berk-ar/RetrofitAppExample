package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.retrofitapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSozler()

        binding.nextButton.setOnClickListener {
            getSozler() //butona tıklandığı zaman yeni veriyi getirir.
        }
    }

    private fun getSozler(){
        yuklemeBar(true)

        GlobalScope.launch {
            try {
                val gelenVeri = RetrofitInstance.alintiSozlerApi.getRandomSoz()
                runOnUiThread {
                    yuklemeBar(false)
                    gelenVeri.body()?.first()?.let {
                        gorselNesneler(it) //gelen verileri gorsel nesneler üzerinde gösterir.
                    }
                }
            }catch (e:Exception){
                yuklemeBar(false)
                Toast.makeText(applicationContext,"HATA",Toast.LENGTH_SHORT).show() //internet kopması durumda hata mesajı verir.
            }
        }
    }

    private fun gorselNesneler(soz : AlintiSozlerModel){
        binding.sozlerText.text = soz.q
        binding.yazarText.text = soz.a
    }

    private fun yuklemeBar(yuklendiMi : Boolean){
        if (yuklendiMi){
            binding.progressBar.visibility = View.VISIBLE
            binding.nextButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.nextButton.visibility = View.VISIBLE
        }
    }
}