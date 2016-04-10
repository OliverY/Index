package com.example.yxj.demoindex;

/**
 * Created by yxj on 16/4/5.
 */
public class Person implements Comparable<Person>{

    public String id;

    public String name;

    public PinyinIndex pinyinIndex;

    public int age;

    public Person() {
    }

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
        this.pinyinIndex = generateIndex(name);
    }

    private PinyinIndex generateIndex(String name){
        return new PinyinIndex(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", firstLetter='" + pinyinIndex.getFirstLetter() + '\'' +
                ", wholeWord='" + pinyinIndex.getWholePinyin() + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person another) {
        if(pinyinIndex != null && another.pinyinIndex != null){
            if(pinyinIndex.getFirstLetter().equals("#")){
                return 1;
            }
            if(another.pinyinIndex.getFirstLetter().equals("#")){
                return -1;
            }
            String origin = pinyinIndex.getWholePinyin();
            String newOne = another.pinyinIndex.getWholePinyin();
            return origin.compareTo(newOne);
        }
        return 0;
    }
}
