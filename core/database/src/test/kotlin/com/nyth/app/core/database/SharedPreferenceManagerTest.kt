package com.nyth.app.core.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.testing.data.DummyObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SharedPreferenceManagerTest {

    private lateinit var context: Context
    private lateinit var sharedPrefManager: SharedPreferenceManager

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPrefManager = SharedPreferenceManager(context)
    }

    @Test
    fun testStringSetGet() {
        sharedPrefManager["test_key"] = "hello"
        val result: String? = sharedPrefManager["test_key"]
        assertEquals("hello", result)
    }

    @Test
    fun testIntSetGet() {
        sharedPrefManager["int_key"] = 42
        val result: Int? = sharedPrefManager["int_key"]
        assertEquals(42, result)
    }

    @Test
    fun testBooleanSetGet() {
        sharedPrefManager["bool_key"] = true
        val result: Boolean? = sharedPrefManager["bool_key"]
        assertEquals(true, result)
    }

    @Test
    fun testFloatSetGet() {
        sharedPrefManager["float_key"] = 3.14f
        val result: Float? = sharedPrefManager["float_key"]
        assertEquals(3.14f, result)
    }

    @Test
    fun testLongSetGet() {
        sharedPrefManager["long_key"] = 123456789L
        val result: Long? = sharedPrefManager["long_key"]
        assertEquals(123456789L, result)
    }

    @Test
    fun testDoubleSetGet() {
        sharedPrefManager["double_key"] = 9.99
        val result: Double? = sharedPrefManager["double_key"]
        assertEquals(9.99, result)
    }

    @Test
    fun testObjectSetGet() {
        val dummy = DummyObject("John", 30)
        sharedPrefManager["obj_key"] = dummy
        val result: DummyObject? = sharedPrefManager["obj_key"]
        assertEquals(dummy, result)
    }

    @Test
    fun testClearAll() {
        sharedPrefManager["key1"] = "value"
        sharedPrefManager["key2"] = 123
        sharedPrefManager.clear()
        val result1: String? = sharedPrefManager["key1"]
        val result2: Int? = sharedPrefManager["key2"]
        assertNull(result1)
        assertNull(result2)
    }

    @Test
    fun testAccessTokenProperty() {
        sharedPrefManager.accessToken = "abc123"
        assertEquals("abc123", sharedPrefManager.accessToken)
    }

    @Test
    fun testSelectedCityProperty() {
        sharedPrefManager.selectedCity = "New York"
        assertEquals("New York", sharedPrefManager.selectedCity)
    }
}