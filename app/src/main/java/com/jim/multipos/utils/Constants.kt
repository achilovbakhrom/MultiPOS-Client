package com.jim.multipos.utils

import java.text.FieldPosition

const val BASE_URL = "http://192.168.0.106:8081"
const val TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ0aHBYYUJBR051eVlnRVlPU200Mm9Ud3cyMnFnQm1hZmhUWk02bUdOLTB3In0.eyJqdGkiOiJhMTcxODk5ZS01ZmI5LTRiN2UtOWE1OC1kNzhmYjBjODY2NDAiLCJleHAiOjE1NDIxOTY5ODQsIm5iZiI6MCwiaWF0IjoxNTQyMTk2OTI0LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvbWFzdGVyIiwiYXVkIjoidG9rZW4tY2xpZW50Iiwic3ViIjoiNWU3YWIwODItMmFhZC00YjU2LWJkODItM2NkYTllNDk4OWNiIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidG9rZW4tY2xpZW50IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZjQzZTg5OGItZTdjNS00NjFhLTg0NGQtZjRkMWI4MGRjN2UwIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6W10sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJkYXlicmVha2VyXzk1QG1haWwucnUiLCJlbWFpbCI6ImRheWJyZWFrZXJfOTVAbWFpbC5ydSJ9.AdSX5zLUpLAZpX1PxIe63WWjeSVmhav9_K2MBSPgTrARIGfy2uVd6GMSSawB3HtD8aRto7bnwPD6nsCunvlqUPQBoPkDozm-AxhYle5m6w5M81-BoGQ0HxHHr7JbzlHVgquKRw3xYr40qxgeCqUyQ4Va24yjwlT6lt0ynZ6zwv9HreUINWXPwlg6q9hYEtvGiF4d50C760Hgtgww4a9kSOEI0YZgEt34T87c3MGh7hQ2jdVMftcB_aE8Yx-m1zTL3IRWIx2VvpBiP7jlgLMm7Nb-tPrUFApMEMT_0BcfeL4JtF1G2T5dHDxOdYo77RTTUa74028IYTY-Kt8GylVtJA"


enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}

enum class FragmentCommunicationOperations(val operation: String) {
    ITEM_SELECTED("ITEM_SELECTED"),
    REFRESH_LIST("REFRESH_LIST"),
    ADD_NEW_ITEM("ADD_NEW_ITEM"),
    ITEM_ADDED("ITEM_ADDED"),
    CANCEL("CANCEL"),
    ITEM_DELETED("ITEM_DELETED"),
    ITEM_EDITED("ITEM_EDITED"),
    DELIVER_DATA("DELIVER_DATA")
}

enum class CompanyEditCreate(val operation: String){
    ADD_CONTACT("add_contact"),
    EDIT_CONTACT("edit_contact"),
    SAVE_CONTACT("save_contact"),
    CLOSE_CONTACT("close_contact"),
    SET_CONTACT("set_contact"),
    DELETE_CONTACT("delete_contact")
}

class Gender{
    companion object {
        var genders = arrayListOf("Male", "Female")
        fun getByPosition(position: Int): String{
            return genders[position]
        }
    }
}