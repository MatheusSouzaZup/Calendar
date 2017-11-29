package com.lerning.zup.calenderviewusinggrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ZUP on 29/11/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    private LayoutInflater mLayoutInflater;
    private MyCalendar mCalendar;
    Context context;
    private Integer mDays;
    List<Integer> usedDays;
    private OnCalenderListener onCalenderListener;
    public CalendarAdapter(MyCalendar myCalendar, OnCalenderListener calenderListener) {
        mCalendar = myCalendar;
        mDays = 1;
        usedDays = myCalendar.getUsedDays();
        onCalenderListener = calenderListener;
    }
    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.item_day_calendar,parent,false);
        context = parent.getContext();
        CalendarViewHolder calendarViewHolder = new CalendarViewHolder(view);
        return calendarViewHolder;
    }

    @Override
    public void onBindViewHolder(final CalendarViewHolder holder, final int position) {
        if(mCalendar.getFirstDay()-1 <= position) {
                if (usedDays.contains(mDays)) {
                    holder.ivDot.setVisibility(View.VISIBLE);
                    holder.tvDay.setTextColor(context.getResources().getColor(R.color.colorBlack));
                    holder.tvDay.setText(mDays.toString());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
                            onCalenderListener.onClickItemDay();
                        }
                    });
                } else {
                    holder.tvDay.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    holder.tvDay.setText(mDays.toString());
                }

            mDays++;
        }
        else{
            return;
        }

    }

    @Override
    public int getItemCount() {
        if(mCalendar == null)
        return 0;
        return mCalendar.getMonthSize() + mCalendar.getFirstDay() -1;
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay;
        ImageView ivDot;
        public CalendarViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.calendar_day);
            ivDot = itemView.findViewById(R.id.calendar_dot);
        }

        public void onBind() {

        }
    }

    public interface OnCalenderListener {
        void onClickItemDay(MyCalendar myCalendar);
    }
}
