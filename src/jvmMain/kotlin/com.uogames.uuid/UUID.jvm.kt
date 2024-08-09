package com.uogames.uuid


typealias OUUID = java.util.UUID

actual class UUID(
    val uuid: OUUID
) : AbstractUUID() {

    actual companion object : StaticUUID() {

        override fun randomUUID(): AbstractUUID = UUID(OUUID.randomUUID())

        override fun nameUUIDFromBytes(byteArray: ByteArray): AbstractUUID =
            UUID(OUUID.nameUUIDFromBytes(byteArray))

        override fun fromString(var0: String):AbstractUUID = UUID(OUUID.fromString(var0))

    }

    override fun getLeastSignificantBits() = uuid.leastSignificantBits

    override fun getMostSignificantBits() = uuid.mostSignificantBits

    override fun version(): Int = uuid.version()

    override fun variant(): Int = uuid.variant()

    override fun timestamp(): Long = uuid.timestamp()

    override fun clockSequence(): Int = uuid.clockSequence()

    override fun node(): Long = uuid.node()

    override fun toString(): String = uuid.toString()

    override fun hashCode(): Int = uuid.hashCode()

    override fun equals(other: Any?): Boolean = uuid == other

    override fun compareTo(other: AbstractUUID): Int = uuid.compareTo((other as UUID).uuid)

}