package hhhcom.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var operand1: Double? = null
    private var operand2: Double? = null
    private var operator: String? = null
    private var isOperatorClicked = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val display = findViewById<TextView>(R.id.display)

        val btnAC = findViewById<Button>(R.id.btnAC)
        val btnEquals = findViewById<Button>(R.id.btnEquals)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btnDot = findViewById<Button>(R.id.btnDot)

        fun updateDisplay(text: String) {
            display.text = text
        }

        fun handleNumberClick(number: String) {
            if (isOperatorClicked) {
                updateDisplay(number)
                isOperatorClicked = false
            } else {
                val currentText = display.text.toString()
                updateDisplay(currentText + number)
            }
        }

        btnAC.setOnClickListener {
            display.text = ""
            operand1 = null
            operand2 = null
            operator = null
        }

        btnEquals.setOnClickListener {
            operand2 = display.text.toString().toDoubleOrNull()
            if (operand1 != null && operand2 != null && operator != null) {
                val result = when (operator) {
                    "+" -> operand1!! + operand2!!
                    "-" -> operand1!! - operand2!!
                    "*" -> operand1!! * operand2!!
                    "/" -> operand1!! / operand2!!
                    else -> 0.0
                }
                updateDisplay(result.toString())
                operand1 = result
                operand2 = null
                operator = null
            }
        }

        val operatorClickListener = { op: String ->
            operand1 = display.text.toString().toDoubleOrNull()
            operator = op
            isOperatorClicked = true
        }

        btnDiv.setOnClickListener { operatorClickListener("/") }
        btnMul.setOnClickListener { operatorClickListener("*") }
        btnMinus.setOnClickListener { operatorClickListener("-") }
        btnPlus.setOnClickListener { operatorClickListener("+") }

        val numberClickListener = { number: String ->
            handleNumberClick(number)
        }

        btn0.setOnClickListener { numberClickListener("0") }
        btn1.setOnClickListener { numberClickListener("1") }
        btn2.setOnClickListener { numberClickListener("2") }
        btn3.setOnClickListener { numberClickListener("3") }
        btn4.setOnClickListener { numberClickListener("4") }
        btn5.setOnClickListener { numberClickListener("5") }
        btn6.setOnClickListener { numberClickListener("6") }
        btn7.setOnClickListener { numberClickListener("7") }
        btn8.setOnClickListener { numberClickListener("8") }
        btn9.setOnClickListener { numberClickListener("9") }
        btnDot.setOnClickListener { handleNumberClick(".") }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
