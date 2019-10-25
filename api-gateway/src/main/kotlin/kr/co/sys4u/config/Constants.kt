package kr.co.sys4u.config

class Constants {
    companion object{
        /* response header의 key - alert message를 위한 용도 */
        const val X_MESSAGE = "X-MESSAGE"
        const val X_FORWARD_URL = "X-Forward-Url"
        const val ACCESS_TOKEN = "ACCESS-TOKEN"
        const val REFRESH_TOKEN = "REFRESH-TOKEN"


        /* term */
        const val TERM_30_MINUTES = 1000 * 60 * 30
        const val TERM_2_WEEKS = 1000 * 60 * 60 * 24 * 14
    }
}