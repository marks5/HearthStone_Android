package me.gabriel.hearthstone.data.hearthstone.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.gabriel.hearthstone.domain.HearthStoneDomainModel

@Entity(tableName = "hearthstone_table")
data class HearthStoneDatabaseEntity(
    val cardId: String,
    @PrimaryKey val dbfId: Int,
    val img: String,
    val name: String,
    val flavor: String,
    val text: String,
    val artist: String,
    val locale: String,
    val collectible: Boolean,
    val cardSet: String,
    val type: String,
    val faction: String,
    val race: String,
    val rarity: String,
    val attack: Int?,
    val cost: Int?,
    val playerClass: String,
    val health: Int?
) {
    fun mapperToDomainModel(): HearthStoneDomainModel {
        return HearthStoneDomainModel(
            cardId,
            dbfId,
            img,
            name,
            flavor,
            text,
            artist,
            locale,
            collectible,
            cardSet,
            type,
            faction,
            race,
            rarity,
            attack,
            cost,
            playerClass,
            health
        )
    }
}