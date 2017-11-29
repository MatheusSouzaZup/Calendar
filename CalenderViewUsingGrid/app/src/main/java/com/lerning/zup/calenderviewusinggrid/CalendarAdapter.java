package com.lerning.zup.calenderviewusinggrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ZUP on 29/11/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    private LayoutInflater mLayoutInflater;
    Context mContext;
    List<Day> mList;
    private OnCalenderListener onCalenderListener;

    public CalendarAdapter(List<Day> list, OnCalenderListener calenderListener) {
        mList = list;
        onCalenderListener = calenderListener;
    }
    public CalendarAdapter(List<Day> list) {
        mList = list;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.item_day_calendar, parent, false);
        mContext = parent.getContext();
        CalendarViewHolder calendarViewHolder = new CalendarViewHolder(view);
        return calendarViewHolder;
    }

    @Override
    public void onBindViewHolder(final CalendarViewHolder holder, final int position) {
        if (mList.get(position) != null) {
            holder.onBind(mList.get(position));
        } else {
            return;
        }

    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay;
        ImageView ivDot;

        public CalendarViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.calendar_day);
            ivDot = itemView.findViewById(R.id.calendar_dot);
        }

        public void onBind(final Day day) {
            if (day.isUsedDay()) {
                ivDot.setVisibility(View.VISIBLE);
                tvDay.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                tvDay.setText(day.getDay());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, day.getDay(), Toast.LENGTH_SHORT).show();
                        //onCalenderListener.onClickItemDay();
                    }
                });
            } else {
                tvDay.setTextColor(mContext.getResources().getColor(R.color.colorGrayLight));
                tvDay.setText(day.getDay());
            }
        }
    }

    public interface OnCalenderListener {
        void onClickItemDay(MyCalendar myCalendar);
    }
}
