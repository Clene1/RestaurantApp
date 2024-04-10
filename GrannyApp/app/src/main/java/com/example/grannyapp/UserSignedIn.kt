package com.example.grannyapp

/*object UserSignedIn {
    var arrayUsers=ArrayList<User>()
    fun appaendToList(u:ArrayList<User>){
        arrayUsers.addAll(u)
    }
}*/


class UserSignedIn private constructor() {

    var arrayUsers=ArrayList<User>()
    var count = 0
    fun appaendToList(u:ArrayList<User>){
        arrayUsers.addAll(u)
    }
    fun IncreaseCount(){
        count++
    }
    companion object {
        @Volatile private var instance: UserSignedIn? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserSignedIn().also { instance = it }
            }
    }
}