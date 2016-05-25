package io.mediachain.copycat

import io.atomix.catalyst.serializer.Serializer

object Serializers {
  val klasses = List(
    classOf[GetBlock],
    classOf[Block],
    classOf[Entry]
  )
  def register(serializer: Serializer) {
    klasses.foreach(serializer.register(_))
  }
}