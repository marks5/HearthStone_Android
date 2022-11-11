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
        attack = attack,
        cost = cost,
        playerClass = playerClass ?: empty(),
        health = health
    )
}
