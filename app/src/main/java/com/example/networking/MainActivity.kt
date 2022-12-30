package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.reflect.Executable
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var data=""

        //volley code
        //volley queue
        val queue=Volley.newRequestQueue(this)
        //volley request

        val request=StringRequest(Request.Method.GET,"https://www.tutorialspoint.com/json/data.json",Response.Listener {
            val txt:TextView=findViewById(R.id.textView)
            txt.text=it.toString()
        },Response.ErrorListener {
            data=it.toString()
        })
        queue.add(request)




    }
    fun click(view: View){
        var data=""
        val th=Thread(Runnable {
            val url_string="https://www.tutorialspoint.com/json/data.json";
            val url: URL =URL(url_string)

            try{
                val connection:HttpURLConnection=url.openConnection() as HttpURLConnection
                connection.connect()

                val reader=BufferedReader(InputStreamReader(connection.inputStream))
                var line:String?=""
                while (true){
                    line=reader.readLine()
                    data+=line
                    if(line==null)
                        break
                }
                Log.d("JSON Text",data)
            }catch (e:Exception){
                Log.d("message",e.toString())
            }
        })
        th.start()


    }
}