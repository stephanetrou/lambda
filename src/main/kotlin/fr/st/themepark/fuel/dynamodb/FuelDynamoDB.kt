package fr.st.themepark.fuel.dynamodb

import com.amazonaws.services.dynamodbv2.document.Item
import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.result.Result
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader


fun Request.responseItem(handler: (Request, Response, Result<Item, FuelError>) -> Unit) {
    response(itemDeserializer(), handler)
}

fun Request.responseItem(handler: Handler<Item>) = response(itemDeserializer(), handler)
fun Request.responseItem() = response(itemDeserializer())

fun itemDeserializer() = object : ResponseDeserializable<Item>  {

    override fun deserialize(content: String): Item? = Item.fromJSON(content)

    override fun deserialize(reader: Reader): Item? = deserialize(reader.readText())

    override fun deserialize(bytes: ByteArray): Item? = deserialize(bytes.contentToString())

    override fun deserialize(inputStream: InputStream) : Item? = deserialize(InputStreamReader(inputStream, "UTF-8"))

}
