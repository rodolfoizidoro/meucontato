package rodolfoizidoro.meucontato.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toHhMm() : String {
    return SimpleDateFormat("HH:mm", pt_BR).format(this)
}
