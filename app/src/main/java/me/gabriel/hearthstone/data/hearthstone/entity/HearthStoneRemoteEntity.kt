package me.gabriel.hearthstone.data.hearthstone.entity

import me.gabriel.hearthstone.extension.empty

data class HearthStoneRemoteEntity(
    val cardId: String?,
    val dbfId: Int?,
    val img: String?,
    val name: String?,
    val flavor: String?,
    val text: String?,
    val artist: String?,
    val locale: String?,
    val collectible: Boolean?,
    val cardSet: String?,
    val type: String?,
    val faction: String?,
    val race: String?,
    val rarity: String?,
    val attack: Int?,
    val cost: Int?,
    val playerClass: String?,
    val health: Int?
) {
    fun mapperToDatabaseEntity() = HearthStoneDatabaseEntity(
        cardId = cardId ?: empty(),
        dbfId = dbfId ?: 0,
        img = img ?: empty(),
        name = name ?: empty(),
        flavor = flavor ?: empty(),
        text = text ?: empty(),
        artist = artist ?: empty(),
        locale = locale ?: empty(),
        collectible = collectible ?: false,
        cardSet = cardSet ?: empty(),
        type = type ?: empty(),
        faction = faction ?: empty(),
        race = race ?: empty(),
        rarity = rarity ?: empty(),
        attack = attack ?: 0,
        cost = cost ?: 0,
        playerClass = playerClass ?: empty(),
        health = health ?: 0
    )
}

val remoteEntity = HearthStoneRemoteEntity(
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
