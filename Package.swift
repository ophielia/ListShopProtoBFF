// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/ophielia/ListShopProtoBFF/com/listshop/proto/bff/allshared-kmmbridge/0.1.4/allshared-kmmbridge-0.1.4.zip"
let remoteKotlinChecksum = "fe5faac7a45d22998acdf2f590ef63bb9399a621a0e65584bb036c89340a12d6"
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