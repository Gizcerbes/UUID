package com.uogames.uuid

expect val UUID : StaticUUID

abstract class AbstractUUID : Comparable<AbstractUUID> {

    abstract fun getLeastSignificantBits() : Long

    abstract fun getMostSignificantBits():Long

    abstract fun version(): Int

    abstract fun variant(): Int

    abstract fun timestamp(): Long

    abstract fun clockSequence(): Int

    abstract fun node(): Long

    abstract override fun toString(): String

    abstract override fun hashCode(): Int

    abstract override fun equals(other: Any?): Boolean

}

abstract class StaticUUID{

    abstract fun randomUUID(): AbstractUUID

    abstract fun nameUUIDFromBytes(byteArray: ByteArray) : AbstractUUID

    abstract fun fromString(var0: String): AbstractUUID

}


