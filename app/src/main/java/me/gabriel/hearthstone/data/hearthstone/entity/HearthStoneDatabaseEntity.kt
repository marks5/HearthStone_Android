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

val databaseEntity = HearthStoneDatabaseEntity(
    cardId = "VAN_CS2_168",
    dbfId = 68395,
    name = "Murloc Raider",
    cardSet = "Vanilla",
    type = "Minion",
    faction = "Alliance",
    rarity = "Free",
    cost = 1,
    attack = 2,
    health = 1,
    text = "Whenever a Murloc is summoned, gain +1 Attack.",
    flavor = "Mrrraggglhlhghghlgh, mrgaaag blarrghlgaahahl mrgggg glhalhah a bghhll graggmgmg Garrosh mglhlhlh mrghlhlhl!!",
    artist = "Dan Scott",
    collectible = true,
    race = "Murloc",
    playerClass = "Neutral",
    img = "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/e648ad01e0e00be3822c2ad8f7d62dea8d1df50b15e0212ea7da716232f99009.png",
    locale = "enUS"
)