package fr.st.themepark

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.ItemUtils
import com.amazonaws.services.dynamodbv2.model.PutItemRequest

fun saveItem(item : Item) {
    val putItemRequest = PutItemRequest("wait_time", ItemUtils.toAttributeValues(item))
    val client = AmazonDynamoDBAsyncClientBuilder.standard().build()
    client.putItem(putItemRequest)
}