import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bangkit.sortsavvy.R

class VideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_video, container, false)

        // Implementasi YouTube Player di sini
        // Contoh:
        // val youTubePlayerView = rootView.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        // youTubePlayerView.initialize(API_KEY, object : YouTubePlayer.OnInitializedListener {
        //     override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        //         player?.cueVideo("VIDEO_ID")
        //     }
        //     override fun onInitializationFailure(provider: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        //         // Handle failure
        //     }
        // })

        return rootView
    }

    companion object {
        fun newInstance() = VideoFragment()
    }
}
