package com.gitlab.hopebaron.websocket.entity

import kotlinx.serialization.*
import kotlinx.serialization.internal.IntDescriptor

@Serializable
data class Message(
        val id: Snowflake,
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake? = null,
        val author: User,
        val member: PartialGuildMember? = null,
        val content: String,
        val timestamp: String,
        @SerialName("edited_timestamp")
        val editedTimestamp: String? = null,
        val tts: Boolean,
        @SerialName("mention_everyone")
        val mentionEveryone: Boolean,
        val mentions: List<OptionallyMemberUser>,
        @SerialName("mention_roles")
        val mentionRoles: List<Role>,
        val attachments: List<Attachment>,
        val embeds: List<Embed>,
        val reactions: List<Reaction>? = null,
        val nonce: String? = null,
        val pinned: Boolean,
        @SerialName("webhook_id")
        val webhookId: Snowflake? = null,
        val type: MessageType,
        val activity: MessageActivity? = null,
        val application: MessageApplication? = null
)

@Serializable
data class Attachment(
        val id: Snowflake,
        val fileName: String,
        val size: Int,
        val url: String,
        @SerialName("proxy_url")
        val proxyUrl: String,
        val height: Int? = null,
        val width: Int? = null
)

@Serializable
data class Embed(
        val title: String? = null,
        val type: String? = null,
        val description: String? = null,
        val url: String? = null,
        val timestamp: String,
        val color: Int,
        val footer: Footer? = null,
        val image: Image? = null,
        val thumbnail: Thumbnail? = null,
        val video: Video? = null,
        val provider: Provider? = null,
        val author: Author? = null,
        val fields: List<Field>? = null
) {
    @Serializable
    data class Footer(
            val text: String,
            @SerialName("icon_url")
            val iconUrl: String? = null,
            @SerialName("proxy_icon_url")
            val proxyIconUrl: String? = null
    )

    @Serializable
    data class Image(
            val url: String? = null,
            @SerialName("proxy_url")
            val proxyUrl: String? = null,
            val height: Int? = null,
            val width: Int? = null
    )

    @Serializable
    data class Thumbnail(
            val url: String? = null,
            @SerialName("proxy_url")
            val proxyUrl: String? = null,
            val height: Int? = null,
            val width: Int? = null
    )

    @Serializable
    data class Video(val url: String? = null, val height: Int? = null, val width: Int? = null)

    @Serializable
    data class Provider(val name: String? = null, val url: String? = null)

    @Serializable
    data class Author(
            val name: String? = null,
            val url: String? = null,
            @SerialName("icon_url")
            val iconUrl: String? = null,
            @SerialName("proxy_icon_url")
            val proxyIconUrl: String? = null
    )

    @Serializable
    data class Field(val name: String, val value: String, val inline: Boolean? = null)
}

@Serializable
data class Reaction(
        val count: Int,
        val me: Boolean,
        val emoji: Emoji
)

@Serializable
data class MessageActivity(val type: Int, @SerialName("party_id") val partyId: Snowflake? = null)

@Serializable
data class MessageApplication(
        val id: Snowflake,
        @SerialName("cover_image")
        val coverImage: String? = null,
        val description: String,
        val icon: String,
        val name: String
)

@Serializable
data class DeletedMessage(
        val id: Snowflake,
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake? = null
)

@Serializable
data class BulkDeleteData(
        val ids: List<String>,
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake? = null
)

@Serializable
data class MessageReaction(
        @SerialName("user_id")
        val userId: Snowflake,
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("message_id")
        val messageId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake? = null,
        val emoji: PartialEmoji
)

@Serializable
data class PartialEmoji(
        val id: Snowflake,
        val name: String
)

@Serializable
data class AllRemovedMessageReactions(
        @SerialName("channel_id")
        val channelId: Snowflake,
        @SerialName("message_id")
        val messageId: Snowflake,
        @SerialName("guild_id")
        val guildId: Snowflake? = null
)

@Serializable(with = MessageType.MessageTypeSerializer::class)
enum class MessageType(val code: Int) {
    Default(0),
    RecipientAdd(1),
    RecipientRemove(2),
    Call(3),
    ChannelNameChange(4),
    ChannelIconChange(5),
    ChannelPinnedMessage(6),
    GuildMemberJoin(7);

    @Serializer(forClass = MessageType::class)
    companion object MessageTypeSerializer : KSerializer<MessageType> {

        override val descriptor: SerialDescriptor
            get() = IntDescriptor.withName("type")

        override fun deserialize(decoder: Decoder): MessageType {
            val code = decoder.decodeInt()
            return values().first { it.code == code }
        }

        override fun serialize(encoder: Encoder, obj: MessageType) {
            encoder.encodeInt(obj.code)
        }
    }
}
