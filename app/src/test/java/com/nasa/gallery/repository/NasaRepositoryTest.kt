package com.nasa.gallery.repository

import com.nasa.gallery.model.ImageDataModel
import com.nasa.gallery.network.APIInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class NasaRepositoryTest {
    @Mock
    lateinit var apiInterface: APIInterface

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_GetImagesWithEmptyList_ExpectedError() = runTest {
        Mockito.`when`(apiInterface.getImages()).thenReturn(Response.success(emptyList()))

        val sut = NasaRepository(apiInterface)
        val result = sut.getImages()
        assertEquals(true, result is com.nasa.gallery.utils.Result.Error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_GetImages_ExpectedError() = runTest {
        Mockito.`when`(apiInterface.getImages()).thenReturn(Response.error(500, "Something Went Wrong!!".toResponseBody()))

        val sut = NasaRepository(apiInterface)
        val result = sut.getImages()
        assertEquals(true, result is com.nasa.gallery.utils.Result.Error)
        assertEquals("Something Went Wrong!!", (result as com.nasa.gallery.utils.Result.Error).exception.message)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_GetImages_ExpectedImageDataList() = runTest {
        Mockito.`when`(apiInterface.getImages()).thenReturn(Response.success(getMockedImageList()))

        val sut = NasaRepository(apiInterface)
        val result = sut.getImages()
        assertEquals(true, result is com.nasa.gallery.utils.Result.Success)
        assertEquals(2, (result as com.nasa.gallery.utils.Result.Success).data.size)
        assertEquals("Starburst Galaxy M94 from Hubble", result.data[0].title)
    }

    private fun getMockedImageList(): List<ImageDataModel> {
        val imageDataModel1 = ImageDataModel(
            copyright = "ESA/HubbleNASA",
            description = "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library",
            hdUrl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
            title = "Starburst Galaxy M94 from Hubble",
            url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg"
        )
        val imageDataModel2 = ImageDataModel(
            copyright = "Steve Mazlin",
            description = "Is this what will become of our Sun? Quite possibly.  The first hint of our Sun's future was discovered inadvertently in 1764. At that time, Charles Messier was compiling a list of diffuse objects not to be confused with comets. The 27th object on Messier's list, now known as M27 or the Dumbbell Nebula, is a planetary nebula, the type of nebula our Sun will produce when nuclear fusion stops in its core. M27 is one of the brightest planetary nebulae on the sky, and can be seen toward the constellation of the Fox (Vulpecula) with binoculars. It takes light about 1000 years to reach us from M27, featured here in colors emitted by hydrogen and oxygen. Understanding the physics and significance of M27 was well beyond 18th century science. Even today, many things remain mysterious about bipolar planetary nebula like M27, including the physical mechanism that expels a low-mass star's gaseous outer-envelope, leaving an X-ray hot white dwarf.   APOD across world languages: Arabic, Catalan, Chinese (Beijing), Chinese (Taiwan), Croatian, Czech, Dutch, Farsi, French, French, German, Hebrew, Indonesian, Japanese, Korean, Montenegrin, Polish, Russian, Serbian, Slovenian,  Spanish and Ukrainian",
            hdUrl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
            title = "M27: The Dumbbell Nebula",
            url = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg"
        )
        return listOf(imageDataModel1, imageDataModel2)
    }
}