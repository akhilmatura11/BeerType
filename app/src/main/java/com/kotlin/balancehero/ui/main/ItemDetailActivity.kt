package com.kotlin.balancehero.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.kotlin.balancehero.R
import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {
    lateinit var beer: Beers
    var checked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beer = intent.getParcelableExtra("beerItem")!!
        checked = beer.checkbox
        bindViews()
    }

    private fun bindViews() {
        val binding: ActivityItemDetailBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_item_detail)
        binding.beer = beer
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Glide.with(this)
            .load(beer.image_url)
            .into(binding.imageDetail)

        binding.checkboxDetail.setOnCheckedChangeListener { buttonView, checked ->
            this.checked = checked
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("ActivityResult", beer)
        intent.putExtra("checkedResult", checked)
        setResult(RESULT_OK, intent)
        super.onBackPressed()
    }
}