package com.listshop.bff.repositories

import com.listshop.bff.data.model.UserInfo
import com.listshop.bff.db.UserInfoEntity
import kotlinx.datetime.Clock


internal class SessionInfoRepository(
    private val listShopDatabase: ListShopDatabase
) {

    fun getUserInfo(): UserInfoEntity? {
        val userInfoList = listShopDatabase.db.userSessionDefinitionQueries.selectAllUserInfos()
            .executeAsList()
        if (userInfoList.size > 0 ) {
            return userInfoList.get(0)
        }
        return null
    }

    fun createUserInfo(): UserInfoEntity? {
        val now = Clock.System.now()

        listShopDatabase.db.userSessionDefinitionQueries
            .insertIntoUserInfo(null,
                null,
                now.toString(),
                now.toString(),
                null
            )
        return getUserInfo()
    }

    fun updateUserInfo(userInfo: UserInfo) {
        listShopDatabase.db.userSessionDefinitionQueries
            .updateUserInfo(
                userInfo.userName,
                userInfo.userToken,
                userInfo.userCreated,
                userInfo.userLastSeen,
                userInfo.userLastSignedIn
            )
    }

}
