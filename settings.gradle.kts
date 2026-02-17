pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "sqldelight-postgres-textsearch"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            val vSqlDelight = "2.2.1"
            plugin("kotlin", "org.jetbrains.kotlin.jvm").version("2.2.21")
            plugin("sqldelight", "app.cash.sqldelight").version(vSqlDelight)
            plugin("flyway", "org.flywaydb.flyway").version("10.1.0")
            library("sqldelight-jdbc-driver", "app.cash.sqldelight:jdbc-driver:$vSqlDelight")
            library("sqldelight-postgresql-dialect", "app.cash.sqldelight:postgresql-dialect:$vSqlDelight")
            library("postgresql-jdbc-driver", "org.postgresql:postgresql:42.5.4")
            library("flyway-database-postgresql", "org.flywaydb:flyway-database-postgresql:10.1.0")
            library("google-truth", "com.google.truth:truth:1.4.2")
        }
    }
}
