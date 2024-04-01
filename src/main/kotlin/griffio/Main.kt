package griffio

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import griffio.queries.Sample

import org.postgresql.ds.PGSimpleDataSource

private fun getSqlDriver() = PGSimpleDataSource().apply {
    setURL("jdbc:postgresql://localhost:5432/textsearches")
    applicationName = "App Main"
}.asJdbcDriver()

fun main() {
    val driver = getSqlDriver()
    val sample = Sample(driver)

    sample.pgWebQueries.bodySearchable("neutrino & sun").executeAsList().also(::println)
    sample.pgWebQueries.titleBodySearchable("neutrino | sun").executeAsList().also(::println)
    sample.pgWebQueries.textSearchable("neutrino | gravity").executeAsList().also(::println)
}
