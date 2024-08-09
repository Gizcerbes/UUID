package com.uogames.uuid

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.toCValues
import platform.Foundation.NSUUID


actual class UUID(
    val uuid: NSUUID
) : AbstractUUID() {

    private val ust = uuid.UUIDString.replace("-", "")
    private val leastSigBits = pgetLeastSignificantBits()
    private val mostSigBits = pgetMostSignificantBits()

    actual companion object : StaticUUID() {

        override fun randomUUID(): AbstractUUID =
            UUID(NSUUID.new()!!)


        @OptIn(ExperimentalForeignApi::class)
        override fun nameUUIDFromBytes(byteArray: ByteArray): AbstractUUID =
            UUID(NSUUID(byteArray.toUByteArray().toCValues().getPointer(MemScope())))


        override fun fromString(var0: String): AbstractUUID =
            UUID(NSUUID(var0))


    }

    private fun pgetLeastSignificantBits(): Long{
        val least = ust.substring(16, 32)
        return least.toULong(16).toLong()
    }

    override fun getLeastSignificantBits(): Long  = leastSigBits

    private fun pgetMostSignificantBits(): Long {
        val least = ust.substring(0, 16)
        return least.toULong(16).toLong()
    }

    override fun getMostSignificantBits(): Long  = mostSigBits

    override fun version(): Int {
        return (this.mostSigBits shr 12 and 15L).toInt()
    }

    override fun variant(): Int {
        return (this.leastSigBits ushr (64L - (this.leastSigBits ushr 62)).toInt() and (this.leastSigBits shr 63)).toInt()
    }

    override fun timestamp(): Long {
        if (this.version() != 1) {
            throw Exception("Not a time-based UUID")
        } else {
            return (this.mostSigBits and 4095L) shl 48 or ((this.mostSigBits shr 16 and 65535L) shl 32) or (this.mostSigBits ushr 32)
        }
    }

    override fun clockSequence(): Int {
        if (this.version() != 1) {
            throw Exception("Not a time-based UUID")
        } else {
            return ((this.leastSigBits and 4611404543450677248L) ushr 48).toInt()
        }
    }

    override fun node(): Long {
        if (this.version() != 1) {
            throw Exception("Not a time-based UUID")
        } else {
            return this.leastSigBits and 281474976710655L
        }
    }

    override fun toString(): String {
        return uuid.toString().lowercase()
    }

    override fun hashCode(): Int {
        val r = this.mostSigBits xor this.leastSigBits
        return (r xor (r ushr 32)).toInt()
    }

    override fun equals(other: Any?): Boolean {
        if (null != other && other == UUID::class) {
            val var2: UUID = other as UUID
            return this.mostSigBits == var2.mostSigBits && this.leastSigBits == var2.leastSigBits
        } else {
            return false
        }
    }

    override fun compareTo(other: AbstractUUID): Int {
        val var2 = when{
            this.mostSigBits > other.getMostSignificantBits() -> 1
            this.mostSigBits < other.getMostSignificantBits() -> -1
            else -> 0
        }
        return if (var2 != 0) var2 else when{
            this.leastSigBits > other.getLeastSignificantBits() -> 1
            this.leastSigBits < other.getLeastSignificantBits() -> -1
            else -> 0
        }
    }


}