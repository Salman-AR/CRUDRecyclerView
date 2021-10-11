package com.example.crudrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var inputNIS : EditText
    private lateinit var inputNama : EditText
    private lateinit var jkLakiLaki : RadioButton
    private lateinit var jkPerempuan : RadioButton
    private lateinit var btnTambah : Button
    private lateinit var recyclerView : RecyclerView
    private lateinit var recyclerAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputNIS = findViewById(R.id.txtInputNIS)
        inputNama = findViewById(R.id.txtInputNama)
        jkLakiLaki = findViewById(R.id.rbLakiLaki)
        jkPerempuan = findViewById(R.id.rbPerempuan)
        btnTambah = findViewById(R.id.btnTambah)
        recyclerView = findViewById(R.id.listData)
        //membuat variable kosong tipe mutabelist utk simpan data array
        // data array disimpan di data class SiswaData
        val data = mutableListOf<SiswaData>()
        viewManager = LinearLayoutManager(this)
        recyclerAdapter = SiswaAdapter(data)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = viewManager
        //setOnClickListener untuk tombol tambahData
        btnTambah.setOnClickListener {
            //memebuat variable penyimpan data
            val nis = inputNIS.text.toString()
            val nama = inputNama.text.toString()
            var jekel = ""
            if (jkLakiLaki.isChecked){
                jekel="Laki Laki"
            }else if(jkPerempuan.isChecked){
                jekel="Perempuan"
            }else{
                Toast.makeText(this,"Pilih Jenis Kelamin",Toast.LENGTH_LONG).show()
            }
            val siswa = SiswaData(nis, nama, jekel)
            if(nis.isNullOrEmpty()){
                Toast.makeText(this,"Masukan NIS",Toast.LENGTH_LONG).show()
            }else if (nama.isNullOrEmpty()){
                Toast.makeText(this,"Masukan Nama",Toast.LENGTH_LONG).show()
            }else if (jekel.isNullOrEmpty()){
                Toast.makeText(this,"Pilih Jenis Kelamin",Toast.LENGTH_LONG).show()
            }else{
                data.add(siswa)
                inputNIS.setText("")
                inputNama.setText("")
                val rGroup = findViewById<RadioGroup>(R.id.radioGroup)
                rGroup.clearCheck()
            }
            recyclerAdapter.notifyDataSetChanged()

        }
    }
}