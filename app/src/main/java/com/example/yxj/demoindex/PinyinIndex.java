package com.example.yxj.demoindex;

import android.util.Log;

import com.github.promeg.pinyinhelper.Pinyin;

/**
 * Created by yxj on 16/4/6.
 */
public class PinyinIndex {

    public String[] subPinyin = null;

    PinyinIndex(String name){
        init(name);
    }

    private void init(String name){
        subPinyin = new String[name.length()*2];
        for(int i=name.length()-1;i>=0;i--){
            char character = name.charAt(i);
            if(Pinyin.isChinese(character)){
                subPinyin[i] = Pinyin.toPinyin(character);
                subPinyin[i+name.length()] = subPinyin[i].substring(0,1);
                generateSubPinyin(i);
                generateSubPinyin(i+name.length());
            }else{
                if(Character.isDigit(character)){// 是数字
                    subPinyin[i] = "#";
//                    subPinyin[i] = String.valueOf(character);
                    subPinyin[i+name.length()] = subPinyin[i].substring(0, 1);
                }else if(Character.isLetter(character)){// 是拼音
                    subPinyin[i] = String.valueOf(character).toUpperCase();
                    subPinyin[i+name.length()] = subPinyin[i].substring(0, 1);
                }else{// 是符号
                    subPinyin[i] = "#";
                    subPinyin[i+name.length()] = subPinyin[i].substring(0,1);
                }
                generateSubPinyin(i);
                generateSubPinyin(i+name.length());
            }
        }

        for (String str:subPinyin) {
            Log.e("pinyin", str);
        }
    }

    private void generateSubPinyin(int i){
        if(i<subPinyin.length-1 && i!=subPinyin.length/2-1){
            subPinyin[i] = subPinyin[i]+subPinyin[i+1];
        }
    }

    /**
     * 是否匹配
     * @param pinyin
     * @return
     */
    public boolean matches(String pinyin){
        for(String index:subPinyin){
            if(index!=null && index.startsWith(pinyin)){
                return true;
            }
        }
        return false;
    }

    public String getWholePinyin(){
        return subPinyin[0];
    }

    public String getFirstLetter(){
        return String.valueOf(subPinyin[0].charAt(0));
    }

}

