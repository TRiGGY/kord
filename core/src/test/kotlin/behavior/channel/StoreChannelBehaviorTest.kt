package dev.kord.core.behavior.channel

import equality.GuildChannelEqualityTest
import mockKord

@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
internal class StoreChannelBehaviorTest : GuildChannelEqualityTest<StoreChannelBehavior> by GuildChannelEqualityTest({ id, guildId ->
    val kord = mockKord()
    StoreChannelBehavior(id = id, guildId = guildId, kord = kord)
})