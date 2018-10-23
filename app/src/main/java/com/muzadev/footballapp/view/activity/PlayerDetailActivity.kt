package com.muzadev.footballapp.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Player
import com.muzadev.footballapp.util.Const
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*


class PlayerDetailActivity : AppCompatActivity() {
    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        player = intent.getSerializableExtra(Const.player) as Player
        playerDetailTB.title = player.strPlayer
        playerDetailTB.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        setSupportActionBar(playerDetailTB)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindData()
    }

    private fun bindData() {
        Picasso.get().load(player.strFanart1)
                .fit()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imgFanArt)

        tvWeight.text = player.strWeight
        tvHeight.text = player.strHeight
        playerPosition.text = player.strPosition
        playerDescripton.text = player.strDescriptionEN
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
