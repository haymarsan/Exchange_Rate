import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hms.currencyexchange.R
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl
import kotlinx.android.synthetic.main.fragment_demo_two.*
import kotlinx.android.synthetic.main.fragment_demo_two.view.*
import kotlinx.android.synthetic.main.fragment_favourite_currency.view.*


class CurrencyCalculatorFragment : Fragment() {

    lateinit var mViewModel: ExchangeRateViewModel

    private var currencyType = "USD"



    private lateinit var mAmount:EditText

    private lateinit var mMMK:EditText

    var currencyList = ArrayList<RateVO>()

    var usdRate = 0.0
    var sgdRate = 0.0
    var eurRate = 0.0
    var thbRate = 0.0


    companion object {
        fun newFragment(): CurrencyCalculatorFragment {
            return CurrencyCalculatorFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_demo_two, container, false)

        //val currencyList = arrayOf("USD", "EUR", "SGD", "THB")

        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)

        view.progressCalculate.visibility = View.VISIBLE
        mViewModel.getExchangeRate().observe(this, Observer {
            val data = it
            Log.d("Data Set", data.description)

            view.progressCalculate.visibility = View.GONE
           // mSpinner = view.findViewById(R.id.spnCurrency)
            mAmount = view.findViewById(R.id.etAmount)
            mMMK = view.findViewById(R.id.etMMK)

            //etAmount.isEnabled = true
            //etMMK.isEnabled = true


            /*val spinnerArrayAdapter = ArrayAdapter<String>(
                context!!, android.R.layout.simple_spinner_item,
                currencyList
            )

            spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout
                    .simple_spinner_dropdown_item
            )
            view.spnCurrency.adapter = spinnerArrayAdapter*/



            for ((key, value) in it.rates) {

                if (isFavouriteCurrency(key, value.toDouble()))
                    currencyList.add(RateVO(key, value))

            }

        })


        view.spnCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currencyType = view!!.spnCurrency.selectedItem.toString()

                when(currencyType){
                    "USD" -> {
                        mMMK.setText(calculateCurrency(mAmount.text.toString().toDouble(), usdRate).toString())
                    }
                    "EUR" -> {
                        mMMK.setText(calculateCurrency(mAmount.text.toString().toDouble(), eurRate).toString())
                    }
                    "SGD" -> {
                        mMMK.setText(calculateCurrency(mAmount.text.toString().toDouble(), sgdRate).toString())
                    }
                    "THB" -> {
                        mMMK.setText(calculateCurrency(mAmount.text.toString().toDouble(), thbRate).toString())
                    }
                }

            }

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun calculateCurrency(amount: Double, rate: Double): Double = amount * rate


    private fun isFavouriteCurrency(currency: String, value: Double): Boolean {
        var isCurrency = false

        when (currency) {
            "USD" -> {
                usdRate = value
                isCurrency = true
            }
            "EUR" -> {
                eurRate = value
                isCurrency = true
            }
            "SGD" -> {
                sgdRate = value
                isCurrency = true
            }
            "THB" -> {
                thbRate = value
                isCurrency = true
            }
            else -> isCurrency = false

        }

        return isCurrency


    }
}