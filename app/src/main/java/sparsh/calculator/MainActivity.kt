package sparsh.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput :TextView? = null
    var lastNumeric : Boolean = false
    var lastDecimal :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput=findViewById(R.id.tvInput)
    }

    fun onDigit(view: View)
    {
        tvInput?.append((view as Button).text)
        lastNumeric=true
        lastDecimal=false
    }

    fun onClear(view: View)
    {
        tvInput?.text= ""
    }

    fun onDecimalPoint(view: View)
    {
        if(lastNumeric && !lastDecimal)
        {
            tvInput?.append(".")
            lastNumeric=false
            lastDecimal=true
        }
    }

    fun onOperator(view: View)
    {
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString()))
            {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDecimal = false
            }
        }
    }

    fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-"))
            false
        else{
            value.contains("+") || value.contains("/")
                    ||value.contains("*") || value.contains("-")
        }
    }

    fun onEqual(view: View)
    {
        if(lastNumeric)
        {
            var tvValue = tvInput?.text.toString()
            var prifix =  ""
            try {
                if(tvValue.startsWith("-"))
                {
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-"))
                {
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prifix.isNotEmpty())
                    {
                        one = prifix + one
                    }
                    var result = one.toDouble() - two.toDouble()
                    tvInput?.text = removeZeroAfterDecimal(result.toString())
                }

                else if(tvValue.contains("+"))
                {
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prifix.isNotEmpty())
                    {
                        one = prifix + one
                    }
                    var result = one.toDouble() + two.toDouble()
                    tvInput?.text = removeZeroAfterDecimal(result.toString())
                }
                else if(tvValue.contains("*"))
                {
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prifix.isNotEmpty())
                    {
                        one = prifix + one
                    }
                    var result = one.toDouble() * two.toDouble()
                    tvInput?.text = removeZeroAfterDecimal(result.toString())
                }
                else if(tvValue.contains("/"))
                {
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prifix.isNotEmpty())
                    {
                        one = prifix + one
                    }
                    var result = one.toDouble() / two.toDouble()
                    tvInput?.text = removeZeroAfterDecimal(result.toString())
                }


            }
            catch (e:ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDecimal(result: String) : String
    {
        var value = result
        if(result.contains(".0"))
        {
            value = result.substring(0,result.length-2)
        }
        return value
    }
}