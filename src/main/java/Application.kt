import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import java.lang.Integer.parseInt
import java.util.logging.Level

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Too many argunents, need url and integer number")
        System.exit(0)
    }
    val count = try {
        parseInt(args[1])
    } catch (e: Exception) {
        throw e
    }
    val c = Checker(count, args[0])
    c.loader()
}


class Checker(val i: Int, val Url: String) {
    init {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").level = Level.OFF
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog")
    }

    fun loader() {
        val webClient = WebClient(BrowserVersion.CHROME)
        (1..i).forEach {
            try {
                checker(webClient)
            } catch (e: Exception) {
                println(e)
            }
        }
        webClient.close()
    }

    private fun checker(vc: WebClient) {
        val page: HtmlPage = vc.getPage(Url)
        println(page.asXml())
        vc.close()
    }
}