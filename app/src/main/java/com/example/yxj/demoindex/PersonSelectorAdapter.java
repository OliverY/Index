package com.example.yxj.demoindex;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yxj on 16/4/5.
 */
public class PersonSelectorAdapter extends BaseAdapter implements Indexer{

    private Context mContext;
    private List<Person> persons;

    private HashMap<Character,Integer> map = new HashMap<>();

    public PersonSelectorAdapter(Context context,List<Person> persons){
        mContext = context;
        this.persons = persons;
        for(int i=0;i<persons.size();i++){
            Person person = persons.get(i);
            String firstLetter = person.pinyinIndex.getFirstLetter();
            if(!map.containsKey(firstLetter.charAt(0))){
                map.put(firstLetter.charAt(0),i);
            }
        }
//        Log.e("map",map.toString());
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
        }
        TextView tv_index = (TextView) convertView.findViewById(R.id.tv_index);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);

        Person person = persons.get(position);
        tv_index.setText(person.pinyinIndex.getFirstLetter());
        tv_name.setText(person.name);
        if(map.containsValue(position)){
            // 是第一个
            tv_index.setVisibility(View.VISIBLE);
        }else{
            // 不是第一个
            tv_index.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public int getStartPositionOfSection(char section) {
        if(map.containsKey(section)){
            return map.get(section);
        }else{
            return -1;
        }
    }

    public String getIndicatorByItemPosition(int position){
        return persons.get(position).pinyinIndex.getFirstLetter();
    }

    public void refresh(List<Person> filterPersonList) {
        persons = filterPersonList;
        notifyDataSetChanged();
    }
}
