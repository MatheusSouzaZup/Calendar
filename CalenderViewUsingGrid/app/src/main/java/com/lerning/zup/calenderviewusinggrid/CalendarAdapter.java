package com.lerning.zup.calenderviewusinggrid;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ZUP on 29/11/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    List<Day> mList;

    public CalendarAdapter(Calendar calendar) {
        mList = getMonthConfigurated(calendar);
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_calendar, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CalendarViewHolder holder, final int position) {
        Day day = mList.get(position);
        if (day == null){
            return;
        }
        holder.onBind(day);
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : mList.size();
    }

    private MyCalendar setUpValidyDate(Calendar calendar) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setFirstDay(calendar.get(Calendar.DAY_OF_WEEK));
        myCalendar.setMonthSize(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        myCalendar.setUsedDays(getUsedDays());
        return myCalendar;
    }

    private List<Day> getMonthConfigurated(Calendar calendar) {
        MyCalendar myCalendar = setUpValidyDate(calendar);
        List<Day> mList = new ArrayList<>();
        fillWithEmptyDay(myCalendar, mList);
        createUsefulDays(myCalendar, mList);
        return mList;
    }

    private void createUsefulDays(MyCalendar myCalendar, List<Day> mList) {
        for(int i = 1; i <= myCalendar.getMonthSize(); i++) {
            boolean isUsedDay = myCalendar.getUsedDays().contains(i);
            mList.add(new Day(i + "", isUsedDay));
        }
    }

    private void fillWithEmptyDay(MyCalendar myCalendar, List<Day> mList) {
        for (int i = 0; i < myCalendar.getFirstDay() -1; i++){
            mList.add(null);
        }
    }

    private List<Integer> getUsedDays() {
        List<Integer> mList = new ArrayList<>();
        mList.add(1);
        mList.add(5);
        mList.add(9);
        mList.add(13);
        mList.add(21);
        return mList;
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDay;
        ImageView mIvDot;

        public CalendarViewHolder(View itemView) {
            super(itemView);
            mTvDay = itemView.findViewById(R.id.calendar_day);
            mIvDot = itemView.findViewById(R.id.calendar_dot);
        }

        public void onBind(final Day day) {
            mTvDay.setText(day.getDay());
            if (day.isUsedDay()) {
                enable(day);
            }
        }

        private void enable(final Day day) {
            mIvDot.setVisibility(View.VISIBLE);
            mTvDay.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorBlack));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), day.getDay(), Toast.LENGTH_SHORT).show();
                    //onCalenderListener.onClickItemDay();
                }
            });
        }
    }

    public interface OnCalenderListener {
        void onClickItemDay(MyCalendar myCalendar);
    }
}
