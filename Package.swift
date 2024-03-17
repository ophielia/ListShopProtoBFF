// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/ophielia/ListShopProtoBFF/com/listshop/proto/bff/allshared-kmmbridge/0.1.3/allshared-kmmbridge-0.1.3.zip"
let remoteKotlinChecksum = "bbd248ee7cf4f58b870afe61232db4975c64cec1d9971bca51cd7ce0f16a57bf"
let packageName = "allshared"
// END KMMBRIDGE BLOCK

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)