package sample

import android.app.Application
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import com.github.jeremyrempel.unsplash.api.PhotoResponse
import com.github.jeremyrempel.unsplash.presentation.PhotoActions
import com.github.jeremyrempel.unsplash.presentation.PhotoPresenter
import com.github.jeremyrempel.unsplash.presentation.PhotoView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), PhotoView {

    override var isUpdating: Boolean by Delegates.observable(false) { _, _, isLoading ->
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            button.visibility = View.GONE
            imageView.visibility = View.GONE
            text.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            button.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE
            text.visibility = View.VISIBLE
        }
    }

    private val actions: PhotoActions by lazy {
        PhotoPresenter(Dispatchers.Main, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            actions.onRequestData()
        }
    }

    override fun onUpdate(data: PhotoResponse) {
        text.text = data.description

        Glide
            .with(this)
            .load(data.urls.thumb)
            .into(imageView)
    }

    override fun showError(error: Throwable) {
        text.text = error.toString()
    }
}