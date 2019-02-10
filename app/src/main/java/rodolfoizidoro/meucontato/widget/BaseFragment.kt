package rodolfoizidoro.meucontato.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rodolfoizidoro.meucontato.util.inflate

/**
 * Created by Burhanuddin on 2/21/2018.
 */
abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getLayoutId() == 0) {
            throw RuntimeException("Invalid Layout ID")
        }
        return container?.inflate(getLayoutId())
    }

}
