package me.gabriel.hearthstone

import junit.framework.TestCase.assertEquals
import me.gabriel.hearthstone.data.hearthstone.entity.remoteEntity
import me.gabriel.hearthstone.utils.BaseUnitTest
import org.junit.Test

class HearthStoneRemoteMapperShould : BaseUnitTest() {

    private val mapperEntity = remoteEntity.mapperToDatabaseEntity()

    @Test
    fun keepSameId() {
        assertEquals(mapperEntity.cardId, remoteEntity.cardId)
    }

    @Test
    fun keepSameName() {
        assertEquals(mapperEntity.name, remoteEntity.name)
    }

    @Test
    fun keepSameCardSet() {
        assertEquals(mapperEntity.cardSet, remoteEntity.cardSet)
    }

    @Test
    fun keepSameArtist() {
        assertEquals(mapperEntity.artist, remoteEntity.artist)
    }

    @Test
    fun keepSameHealth() {
        assertEquals(mapperEntity.health, remoteEntity.health)
    }

    @Test
    fun keepSameAttack() {
        assertEquals(mapperEntity.attack, remoteEntity.attack)
    }

    @Test
    fun keepSameRarity() {
        assertEquals(mapperEntity.rarity, remoteEntity.rarity)
    }

    @Test
    fun keepSameRace() {
        assertEquals(mapperEntity.race, remoteEntity.race)
    }

    @Test
    fun keepSameImg() {
        assertEquals(mapperEntity.img, remoteEntity.img)
    }

    @Test
    fun keepSameText() {
        assertEquals(mapperEntity.text, remoteEntity.text)
    }

    @Test
    fun keepSameFlavor() {
        assertEquals(mapperEntity.flavor, remoteEntity.flavor)
    }

    @Test
    fun keepSameCollectible() {
        assertEquals(mapperEntity.collectible, remoteEntity.collectible)
    }

    @Test
    fun keepSamePlayerClass() {
        assertEquals(mapperEntity.playerClass, remoteEntity.playerClass)
    }

    @Test
    fun keepSameCost() {
        assertEquals(mapperEntity.cost, remoteEntity.cost)
    }

    @Test
    fun keepSameLocale() {
        assertEquals(mapperEntity.locale, remoteEntity.locale)
    }

    @Test
    fun keepSameDbfId() {
        assertEquals(mapperEntity.dbfId, remoteEntity.dbfId)
    }
}