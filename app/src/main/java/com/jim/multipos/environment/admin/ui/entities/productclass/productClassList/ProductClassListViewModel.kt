package com.jim.multipos.environment.admin.ui.entities.productclass.productClassList

import com.jim.multipos.core.SingleListViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.ProductClass
import javax.inject.Inject

class ProductClassListViewModel @Inject constructor(dataManager: DataManager): SingleListViewModel<ProductClass>(dataManager) {


    override fun onViewCreated() {
        data.value = mutableListOf()
        page.set(0)
        load()
    }

    override fun load() {

        val tenant = "nKc505P"
        val token = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ0MVp4VVo2NzE0N0IxNWVnektNQXJCQnFRSDZtb0FVc01VeG1ZR2cwMkhJIn0.eyJqdGkiOiIwY2QyYzEyOS0wNzQ0LTQ0YTAtYTk4Ny1mOGI3Yjc0ZDkzMDYiLCJleHAiOjE1MzcyNjk1MDEsIm5iZiI6MCwiaWF0IjoxNTM3MjY5NDQxLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvbWFzdGVyIiwiYXVkIjoidG9rZW4tY2xpZW50Iiwic3ViIjoiNjYzMWU1ZTctZjFlNS00MmQ0LWEzMjYtMzljOGJkNjA2OGEwIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidG9rZW4tY2xpZW50IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiOTYxYjExNDctZGFiOS00NGE1LWFhN2YtYmJiYzY3ZDhhYzJhIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6W10sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInByZWZlcnJlZF91c2VybmFtZSI6ImRheWJyZWFrZXJfOTVAbWFpbC5ydSIsImVtYWlsIjoiZGF5YnJlYWtlcl85NUBtYWlsLnJ1In0.Lob0lZqNKvvG6_IzaxOe40IfiFsjt-n7uNl9RNgWNUPSrY8VlacOv5h6w3X1wR0Pu9qr4YKS1-OujaU6-CQwxADYNtrLlQwyFXL64BcIccYCZTrqZuppCv0McQTTjKCh4eLCkfcIzJ6D4-_QCbkkNbpgJug4n9oAE8OSqSYbp8KR2DHXSXd9C6mG7oxcJ94iB-uSOyG5Hs_xd_l1dR1yMBL9iKrE-XKD3ohRJvixGQwKgM97gmbu8j1rOvzFKWtc_YokHLyDTORsbrkGTv4VJYUcwQ-kKHL1ykZFlsh_hnjPpqCYenuBeleXHnUB8eWN5Lyb5qR_0oZ0Og93HZqmYA"

        compositeDisposable.add(
            mDataManager
                .getProductClassList(page.get()!!, 20, token, tenant)
                .subscribe({

                    val tempData = mutableListOf<ProductClass>()
                    tempData.addAll(data.value!!)
                    tempData.addAll(it.data!!)
                    data.value = tempData

                }, {
                    it.printStackTrace()
                })

        )
    }


}