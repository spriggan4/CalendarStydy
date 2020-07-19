package com.jsc.calendarstydy

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_tab.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    val TAG = "CALENDAR"
//    var mCenterPosition by Delegates.notNull<Int>()
//    private var mCurrentTime by Delegates.notNull<Long>()
    var mCalendarList = ArrayList<Any>()

//    var textView: TextView? = null
//    var recyclerView: RecyclerView? = null
//    private var madapter: CalendarAdapter? = null
//    private var manager: StaggeredGridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setCalendarList()
        setRecycler()
    }

    private fun setCalendarList() {
        val cal = GregorianCalendar()//오늘 날짜

        val calendarList = ArrayList<Any>()

        for (i in -300 until 300) {
            try {
                val calendar =
                    GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1)
                calendarList.add(calendar.timeInMillis)

                val dayOfWeek =
                    calendar.get(Calendar.DAY_OF_WEEK) - 1//해당 월에 시작하는 요일. -1을 하면 빈칸 구할 수 있음.
                val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) // 해당 월의 마지막 요일

                for (j in 0 until dayOfWeek) {
                    calendarList.add(Keys().EMPTY) // 비어있는 일자 타입
                }
                for (j in 1..max) {
                    calendarList.add(
                        GregorianCalendar(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            j
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        mCalendarList = calendarList
    }

    private fun setRecycler() {
        if (mCalendarList == null) {
            Log.w(TAG, "No Query, not initializing RecyclerView");
        }

        calendar.layoutManager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        calendar.adapter = CalendarAdapter(mCalendarList)
    }
}