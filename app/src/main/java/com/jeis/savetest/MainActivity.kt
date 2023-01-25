package com.jeis.savetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var sum=0
    var pos=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sum,posの変数にファイルの数字を入れる
        DataLoad()

        //変数の中身をレイアウトに反映
        LayoutReflesh()

        //Moveボタンが押されたときの処理
        findViewById<Button>(R.id.btn_Move).setOnClickListener{
            //金額、マスを計算
            sum+=300
            pos=(sum-sum%500)/500

            //レイアウトを更新
            LayoutReflesh()
            //ファイルに保存
            DataSave()
        }

    }

    fun LayoutReflesh(){
        findViewById<TextView>(R.id.txtPosition).text=pos.toString()+"マス目"
        findViewById<TextView>(R.id.txt_Price).text=sum.toString()+"円"
    }

    fun DataSave(){
        openFileOutput("sum",   MODE_PRIVATE).bufferedWriter().use{
            it.write(sum.toString())
        }
        openFileOutput("position",   MODE_PRIVATE).bufferedWriter().use{
            it.write(pos.toString())
        }
    }

    fun DataLoad(){

        var data=""
        openFileInput("sum").bufferedReader().forEachLine {
            data+=it
        }
        sum=data.toInt()

        data=""
        openFileInput("position").bufferedReader().forEachLine {
            data+=it
        }
        pos=data.toInt()

    }

}