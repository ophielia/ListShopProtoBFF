// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/ophielia/ListShopProtoBFF/com/listshop/proto/bff/allshared-kmmbridge/0.1.6/allshared-kmmbridge-0.1.6.zip"
let remoteKotlinChecksum = "8ce4389884d130870cfe9036a840e46a6ab55b34d5fc8fbb07a0291d914ff511"
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