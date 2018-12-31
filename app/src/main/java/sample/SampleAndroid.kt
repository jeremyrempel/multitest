package sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import test.api.PhotoResponse
import test.presentation.PhotoActions
import test.presentation.PhotoPresenter
import test.presentation.PhotoView
import kotlin.properties.Delegates

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}


class MainActivity : AppCompatActivity(), PhotoView {

    override var isUpdating: Boolean by Delegates.observable(false) { _, _, isLoading ->
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            button.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            button.visibility = View.VISIBLE
        }
    }

    private val actions: PhotoActions by lazy {
        PhotoPresenter(Dispatchers.Main, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sample().checkMe()
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            actions.onRequestData()
        }
    }

    override fun onUpdate(data: PhotoResponse) {
        text.text = data.toString()
    }

    override fun showError(error: Throwable) {
        text.text = error.toString()
    }
}