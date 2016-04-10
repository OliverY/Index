package com.example.yxj.demoindex;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AlphabetIndexer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SectionBar.OnIndexSelected {

    private SectionBar bar;
    private PersonSelectorAdapter adapter;
    private ListView lv;
    private EditText et_search;
    private TextView tv_empty;
    private TextView tv_indicator;

    /**
     * 全部的列表
     */
    private List<Person> personList;
    private TextView tv_letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personList = setPersons();

        lv = (ListView) findViewById(R.id.lv);
        tv_empty = (TextView) findViewById(R.id.tv_empty);
        bar = (SectionBar) findViewById(R.id.bar);
        adapter = new PersonSelectorAdapter(this,personList);
        lv.setAdapter(adapter);
        lv.setEmptyView(tv_empty);
        bar.setOnIndexSelected(this);

        et_search = (EditText) findViewById(R.id.et_search);
        et_search.addTextChangedListener(watcher);

        tv_letter = (TextView) findViewById(R.id.tv_letter);
        tv_indicator = (TextView) findViewById(R.id.tv_indicator);

//        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                tv_indicator.setText(adapter.getIndicatorByItemPosition(firstVisibleItem));
//            }
//        });
    }

    private List<Person> setPersons(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("#","##严"));
        personList.add(new Person("#","严1"));
        personList.add(new Person("#","1严"));
        personList.add(new Person("A","啊"));
        personList.add(new Person("A","啊雅"));
        personList.add(new Person("D", "丁春秋"));
        personList.add(new Person("B", "白岩松"));
        personList.add(new Person("F","范仲淹"));
        personList.add(new Person("F","方世玉"));
        personList.add(new Person("G","关咏荷"));
        personList.add(new Person("F","方大同"));
        personList.add(new Person("H", "黄宏"));
        personList.add(new Person("H", "黄海波"));
        personList.add(new Person("H", "韩寒"));
        personList.add(new Person("J","蒋介石"));
        personList.add(new Person("K", "金宇彬"));
        Collections.sort(personList);
        Log.e("personList",personList.toString());
        return personList;
    }

    @Override
    public void select(char index) {
        int i = adapter.getStartPositionOfSection(index);
        showFloatView(index);
        if(i >= 0){
            lv.setSelection(i);
        }else{

        }
    }

    @Override
    public void up() {
        tv_letter.setVisibility(View.GONE);
    }

    private void showFloatView(char letter){
        tv_letter.setText(letter+"");
        tv_letter.setVisibility(View.VISIBLE);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchPersons(s.toString());
        }
    };

    private void searchPersons(String keyWord){
        keyWord = keyWord.toUpperCase();
        List<Person> filterPersonList = new ArrayList<Person>();
        if(isChinese(keyWord)){
            for(Person person:personList){
                if(person.name.contains(keyWord)){
                    filterPersonList.add(person);
                }
            }
        }else{
            for(Person person:personList){
                if(person.pinyinIndex.matches(keyWord)){
                    filterPersonList.add(person);
                }
            }
        }
        adapter.refresh(filterPersonList);
    }

    private boolean isChinese(String keyword){
        for (int i=0;i<keyword.length();i++){
            if(Pinyin.isChinese(keyword.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
