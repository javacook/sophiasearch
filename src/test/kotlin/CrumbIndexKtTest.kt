import de.kotlincook.sophiasearch.CrumbIndexResult.Dist
import de.kotlincook.sophiasearch.CrumbIndexResult.None
import de.kotlincook.sophiasearch.crumbIndexOf
import org.junit.Assert.assertEquals
import org.junit.Test


class CrumbIndexKtTest {

    @Test
    fun casePrefix() {
        val actual = "This is an example".crumbIndexOf("This")
        assertEquals(Dist(0), actual)
    }

    @Test
    fun caseSpread() {
        val actual = "This is an example".crumbIndexOf("iinm")
        assertEquals(Dist(11), actual)
    }

    @Test
    fun caseNone() {
        val actual = "This is an example".crumbIndexOf("none")
        assertEquals(None, actual)
    }
}