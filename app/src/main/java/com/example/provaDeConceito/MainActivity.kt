package com.example.provaDeConceito

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var pontos = 0
    val tempoEmMilissegundos : Long = 300000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        listViewPalavrasReservadas.adapter = arrayAdapter

        pontua()

        editTextPalavraReservada.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                if(listaPalavrasReservadas.contains(palavraReservada(charSequence.toString(), identificada = false))){
                    arrayAdapter.add(charSequence.toString())
                    listaPalavrasReservadas.find {
                        it.palavra == charSequence.toString()
                    }?.identificada = true

                    editTextPalavraReservada.text.clear()
                    pontos++
                    pontua()
                }
            }
        })

        val timer = object: CountDownTimer(tempoEmMilissegundos, 1000){
            override fun onFinish() {
                val dialog = AlertDialog.Builder(this@MainActivity)
                    .setTitle("fim de jogo")
                    .setMessage("Pontuação final: $pontos")
                    .setPositiveButton("reiniciar") { _, _ ->
                        arrayAdapter.clear()
                        this.start()
                    }
                    .setCancelable(false)

                dialog.show()
            }

            override fun onTick(millisUntilFinished: Long) {
                val minutos = ((millisUntilFinished/1000) / 60)
                val segundos = (millisUntilFinished/1000 % 60)
                textViewCronometro.text = "$minutos:$segundos"
            }
        }

        timer.start()
    }

    private fun pontua() {
        textViewPontuacao.text = "pontuação: $pontos"
    }
}

data class palavraReservada(
    val palavra: String,
    var identificada: Boolean = false
)

val listaPalavrasReservadas = mutableListOf(
    palavraReservada("abstract"),
    palavraReservada("assert"),
    palavraReservada("boolean"),
    palavraReservada("break"),
    palavraReservada("byte"),
    palavraReservada("case"),
    palavraReservada("catch"),
    palavraReservada("char"),
    palavraReservada("class"),
    palavraReservada("continue"),
    palavraReservada("const"),
    palavraReservada("default"),
    palavraReservada("do"),
    palavraReservada("double"),
    palavraReservada("else"),
    palavraReservada("enum"),
    palavraReservada("exports"),
    palavraReservada("extends"),
    palavraReservada("final"),
    palavraReservada("finally"),
    palavraReservada("float"),
    palavraReservada("for"),
    palavraReservada("goto"),
    palavraReservada("if"),
    palavraReservada("implements"),
    palavraReservada("import"),
    palavraReservada("instanceof"),
    palavraReservada("int"),
    palavraReservada("interface"),
    palavraReservada("long"),
    palavraReservada("module"),
    palavraReservada("native"),
    palavraReservada("new"),
    palavraReservada("package"),
    palavraReservada("private"),
    palavraReservada("protected"),
    palavraReservada("public"),
    palavraReservada("requires"),
    palavraReservada("return"),
    palavraReservada("short"),
    palavraReservada("static"),
    palavraReservada("strictfp"),
    palavraReservada("super"),
    palavraReservada("switch"),
    palavraReservada("synchronized"),
    palavraReservada("this"),
    palavraReservada("throw"),
    palavraReservada("throws"),
    palavraReservada("transient"),
    palavraReservada("try"),
    palavraReservada("var"),
    palavraReservada("void"),
    palavraReservada("volatile"),
    palavraReservada("while")
)