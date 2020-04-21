package highestaim.com.weather.utils

import android.content.Context
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import highestaim.com.pixomaticprojectweather.R

fun EditText.clearText() {
    this.setText("")
}


fun <R> RecyclerView.initRecyclerView(context: Context?, adapter: R, isVertical: Boolean = true) {
    context?.let {
        this.layoutManager = LinearLayoutManager(
            context,
            if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL,
            false
        )
        this.adapter = adapter as RecyclerView.Adapter<*>
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showIf(condition: Boolean) = if (condition) show() else hide()

inline fun <T : View> T.onClick(crossinline func: T.() -> Unit) {
    setOnClickListener { func() }
}

fun Fragment.replaceFragment(fragment: Fragment, addBackStack: Boolean) {
    if (addBackStack) {
        activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
            ?.replace(R.id.fragmentContainer, fragment)
            ?.commitAllowingStateLoss()
    } else {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, fragment)
            ?.commitAllowingStateLoss()
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, addBackStack: Boolean) {
    if (addBackStack) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commitAllowingStateLoss()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commitAllowingStateLoss()
    }
}

class SimpleTimer<T>(millis: Long, val func: (T?) -> Unit) : CountDownTimer(millis, millis) {
    private var param: T? = null
    override fun onFinish() = func(param)

    override fun onTick(millisUntilFinished: Long) {}

    fun restart(withParam: T? = null) {
        param = withParam
        cancel()
        start()
    }

}

inline fun <T : EditText> T.onTextChanged(crossinline func: (orEmpty: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            func(s?.toString().orEmpty())
        }
    })
}
