package com.jsc.calendarstydy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.util.*

class CalendarAdapter(calendarList: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val HEADER_TYPE: Int = 0
    private val EMPTY_TYPE: Int = 1
    private val DAY_TYPE: Int = 2
    private val mCalendarList = calendarList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // 날짜 타입
        return if (viewType === HEADER_TYPE) {
            val viewHolder =
                HeaderViewHolder(inflater.inflate(R.layout.item_calendar_header, parent, false))
            val params =
                viewHolder.itemView.getLayoutParams() as StaggeredGridLayoutManager.LayoutParams
            params.isFullSpan = true //Span을 하나로 통합하기
            viewHolder.itemView.setLayoutParams(params)
            viewHolder

            //비어있는 일자 타입
        } else if (viewType === EMPTY_TYPE) {
            EmptyViewHolder(inflater.inflate(R.layout.item_day_empty, parent, false))
        } else {
            DayViewHolder(inflater.inflate(R.layout.item_day, parent, false))
        }

    }

    override fun getItemCount(): Int {
        if(mCalendarList != null){
            return mCalendarList.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        //날짜 타입 꾸미기
        if(viewType==HEADER_TYPE){
            val holder = holder as HeaderViewHolder
            val item = mCalendarList.get(position)
            val model = CalendarHeader()

            if(item is Long){
                model.setHeader(item)
            }
            holder.bind(model)
        }

        //비어있는 날짜
        else if(viewType == EMPTY_TYPE){
            val holder = holder as EmptyViewHolder
            val model = EmptyDay()
            holder.bind(model)
        }

        //일자 타입 꾸미기
        else if(viewType==DAY_TYPE){
            val holder = holder as DayViewHolder
            val item = mCalendarList.get(position)
            val model = Day()
            if(item is Calendar){
                //model에 캘린더 값을 넣어 몇일인지 데이터 넣기
                model.setCalendar(item)
            }
            //model의 데이터를 view에 표현하기
            holder.bind(model)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = mCalendarList[position]
        return if (item is Long) {
            HEADER_TYPE //날짜 타입
        } else if (item is String) {
            EMPTY_TYPE // 비어있는 일자 타입
        } else {
            DAY_TYPE // 일자 타입
        }
    }

    private class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //날짜 타입 ViewHolder
        var itemHeaderTitle: TextView? = null
        fun initView(v: View) {
            itemHeaderTitle = v.findViewById(R.id.item_header_title)
        }

        fun bind(model: CalendarHeader) {

            // 일자 값 가져오기
            val header: String = (model).getHeader()

            // header에 표시하기, ex : 2018년 8월
            itemHeaderTitle!!.text = header
        }

        init {
            initView(itemView)
        }
    }


    private class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initView(v: View?) {}
        fun bind(model: EmptyDay) {}

        // 비어있는 요일 타입 ViewHolder
        init {
            initView(itemView)
        }
    }

    // TODO : item_day와 매칭
    private class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 요일 입 ViewHolder
        var itemDay: TextView? = null
        fun initView(v: View) {
            itemDay = v.findViewById(R.id.item_day)
        }

        fun bind(model: Day) {

            // 일자 값 가져오기
            val day: String = (model).day as String

            // 일자 값 View에 보이게하기
            itemDay!!.text = day
        }

        init {
            initView(itemView)
        }
    }
}