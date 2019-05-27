package com.example.smacktest.Services

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.android.volley.Request.Method.*
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.smacktest.Utilities.URL_REGISTER
import org.json.JSONObject

object AuthService {

   fun registerUser(context: Context, email: String, password: String, complete: (Boolean) ->Unit){

       val jsonbody = JSONObject()
       jsonbody.put("email", email)
       jsonbody.put("password", password)
       val requestBody = jsonbody.toString()

       val registerRequest = object: StringRequest(Method.POST, URL_REGISTER, Response.Listener {response ->
            println(response)
           complete(true)
       }, Response.ErrorListener {error ->
           Log.d("Error","Could not register user: $error")
           complete(false)
       }){
           override fun getBodyContentType(): String {
               return "application/json; charset=utf-8"
           }

           override fun getBody(): ByteArray {
               return requestBody.toByteArray()
           }
       }
        Volley.newRequestQueue(context).add(registerRequest)
   }
}