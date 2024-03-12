package co.touchlab.kmmbridgekickstart


fun initAppInfo(version: String,
                build: String,
                baseUrl: String): AppInfo {

    return AppInfo(
        version = version,
        build = build,
        baseUrl = baseUrl
    )
}

data class AppInfo(
    val version: String,
    val build: String,
    val baseUrl: String
)



