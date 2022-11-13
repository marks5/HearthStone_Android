package me.gabriel.hearthstone

import junit.framework.TestCase.assertEquals
import me.gabriel.hearthstone.data.hearthstone.entity.databaseEntity
import me.gabriel.hearthstone.utils.BaseUnitTest
import org.junit.Test

class HearthStoneLocalMapperShould : BaseUnitTest() {

    private val mapperEntity = databaseEntity.mapperToDomainModel()

    @Test
    fun keepSameId() {
        assertEquals(mapperEntity.cardId, databaseEntity.cardId)
    }

    @Test
    fun keepSameName() {
        assertEquals(mapperEntity.name, databaseEntity.name)
    }

    @Test
    fun keepSameCardSet() {
        assertEquals(mapperEntity.cardSet, databaseEntity.cardSet)
    }

    @Test
    fun keepSameArtist() {
        assertEquals(mapperEntity.artist, databaseEntity.artist)
    }

    @Test
    fun keepSameHealth() {
        assertEquals(mapperEntity.health, databaseEntity.health)
    }

    @Test
    fun keepSameAttack() {
        assertEquals(mapperEntity.attack, databaseEntity.attack)
    }

    @Test
    fun keepSameRarity() {
        assertEquals(mapperEntity.rarity, databaseEntity.rarity)
    }

    @Test
    fun keepSameRace() {
        assertEquals(mapperEntity.race, databaseEntity.race)
    }

    @Test
    fun keepSameImg() {
        assertEquals(mapperEntity.img, databaseEntity.img)
    }

    @Test
    fun keepSameText() {
        assertEquals(mapperEntity.text, databaseEntity.text)
    }

    @Test
    fun keepSameFlavor() {
        assertEquals(mapperEntity.flavor, databaseEntity.flavor)
    }

    @Test
    fun keepSameCollectible() {
        assertEquals(mapperEntity.collectible, databaseEntity.collectible)
    }

    @Test
    fun keepSamePlayerClass() {
        assertEquals(mapperEntity.playerClass, databaseEntity.playerClass)
    }

    @Test
    fun keepSameCost() {
        assertEquals(mapperEntity.cost, databaseEntity.cost)
    }

    @Test
    fun keepSameLocale() {
        assertEquals(mapperEntity.locale, databaseEntity.locale)
    }

    @Test
    fun keepSameDbfId() {
        assertEquals(mapperEntity.dbfId, databaseEntity.dbfId)
    }
}