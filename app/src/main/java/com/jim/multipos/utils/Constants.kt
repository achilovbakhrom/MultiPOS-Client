package com.jim.multipos.utils

const val BASE_URL = "http://192.168.1.56:8081"
const val TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ0MVp4VVo2NzE0N0IxNWVnektNQXJCQnFRSDZtb0FVc01VeG1ZR2cwMkhJIn0.eyJqdGkiOiJjZjkxY2NjZS0wNDkxLTRmNDAtOWE0OC04YTUyMDUzYTdjMjgiLCJleHAiOjE1NDAzNjI2NTAsIm5iZiI6MCwiaWF0IjoxNTQwMzYyNTkwLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvbWFzdGVyIiwiYXVkIjoidG9rZW4tY2xpZW50Iiwic3ViIjoiY2M2ODUxYTEtMTU3MS00ZWU4LWEwMjItZmZiYjZiNzNkOWJmIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidG9rZW4tY2xpZW50IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZDc5OTlkZjctODRjNC00MzE2LThhMjctN2YxYzIwMGYwMWIzIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6W10sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInByZWZlcnJlZF91c2VybmFtZSI6ImRheWJyZWFrZXJfOTVAbWFpbC5ydSIsImVtYWlsIjoiZGF5YnJlYWtlcl85NUBtYWlsLnJ1In0.m-j46pXeB3gqdi3gCCCUgRLeMrWeRxMdL1pqW_T7VhNVwOgxjooIVxg-f4vMpz5fV8LK3Ut46IPASbb-P0DtlJfY6-xVY3oKaCXG_iyI3y-Fx9c68K7xvo5uEa7brw6sT8yl_5MRDk-2NnDYTyPMZxhPRb9ZyK4Q2QeniqepxWQGz2Hp_rkwKr3y9G78McC-ckZMxhQoLm-eBvHK8CFQ4DldOCNakZd3UJmwXJFuNQbVOhPkwIbmrnfLCvqbZBALnrh16_KRL9KMuNdctJ7tkTPXVajfyh3OIpwPVHpezngO1BC_0Ssird1QNsk6pq6xSK7h6QVFUqmAYw06A_rsPA"


enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}


enum class FragmentCommunicationOperations(val operation: String) {
    ITEM_SELECTED("ITEM_SELECTED"),
    REFRESH_LIST("REFRESH_LIST"),
    ADD_NEW_ITEM("ADD_NEW_ITEM"),
    CANCEL("CANCEL"),
    ITEM_DELETED("ITEM_DELETED"),
    ITEM_EDITED("ITEM_EDITED")
}