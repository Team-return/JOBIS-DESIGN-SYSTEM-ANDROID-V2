package team.returm.jobisdesignsystemv2.toast

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import team.returm.jobisdesignsystemv2.R
import team.returm.jobisdesignsystemv2.foundation.JobisIcon

object JobisToast {
    fun create(
        context: Context,
        message: String,
        duration: Int = Toast.LENGTH_SHORT,
        @DrawableRes drawable: Int = JobisIcon.CheckCircle,
    ): Toast {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(
            R.layout.jobis_toast,
            LinearLayout(context).findViewById(R.id.ll_jobis_toast),
        )

        view.findViewById<TextView>(R.id.tv_toast_message).text = message
        view.findViewById<ImageView>(R.id.img_toast_icon).setImageResource(drawable)

        return Toast(context).apply {
            this.duration = duration
            this.view = view
        }
    }
}
