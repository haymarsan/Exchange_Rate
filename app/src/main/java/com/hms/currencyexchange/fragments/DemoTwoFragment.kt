

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hms.currencyexchange.R


class DemoTwoFragment : Fragment() {

    companion object{
        fun newFragment() : DemoTwoFragment{
            return DemoTwoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_demo_two, container, false)
        return view
    }
}