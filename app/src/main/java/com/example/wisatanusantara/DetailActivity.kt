package com.example.wisatanusantara

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }

    private var currentArticle: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        currentArticle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_ARTICLE, Article::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ARTICLE)
        }


        if (currentArticle != null) {
            findViewById<ImageView>(R.id.img_detail_photo).setImageResource(currentArticle!!.imageResId)
            findViewById<TextView>(R.id.tv_detail_title).text = currentArticle!!.title
            findViewById<TextView>(R.id.tv_detail_description).text = currentArticle!!.description

            supportActionBar?.title = currentArticle!!.title
        } else {

        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                shareArticle()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareArticle() {
        if (currentArticle != null) {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT,
                    "Ayo kunjungi ${currentArticle!!.title}! Destinasi di Indonesia. Detail: ${currentArticle!!.overview}")
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan informasi wisata"))
        }
    }
}