package com.example.diaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.diaryapp.databinding.ActivityAddNoteBinding
import com.example.diaryapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db:NoteDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title =binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0,title, content)

           GlobalScope.launch(Dispatchers.Main) {
               db.insertNoteAsync(note)
               finish()
               Toast.makeText(this@AddNoteActivity,"Note Saved", Toast.LENGTH_SHORT).show()
           }
        }
    }
}