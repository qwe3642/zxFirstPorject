package com.developproject.refexample;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 混合排序
 */
public class SortOfMix {
    public SortOfMix() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Comparator<Object> com=Collator.getInstance(java.util.Locale.CHINA);
        //	 myComparator com=new myComparator();
        //Collator.getInstance(java.util.Locale.CHINESE)

        ArrayList<String> newArray=  new ArrayList<String>();
        newArray.add("发车");
        newArray.add("ab12");
        newArray.add("ab21");
        newArray.add("过安");
        newArray.add("吧新");
        newArray.add("啊州");
        newArray.add("test");
        newArray.add("pp");
        newArray.add("？23");
        newArray.add(".23");
        newArray.add("33");
        newArray.add("23");
        newArray.add("33");
        CEComplexComparator  com=new CEComplexComparator();
        Collections.sort(newArray, com);

        for(String i:newArray){
            System.out.print(i+" ");
        }
    }
}
class CEComplexComparator implements Comparator<String> {
    public int compare(String str1, String str2) {
        char[] c={str1.toLowerCase().charAt(0),str2.toLowerCase().charAt(0)};//首字母
        String[] str={str1.substring(0, 1),str2.substring(0, 1)};
        int type[]={1,1};
        for(int i=0;i<2;i++)
        {
            if(str[i].matches("[\\u4e00-\\u9fbb]+"))//中文字符
                type[i]=2;
            else if(c[i]>='a' && c[i]<='z')
                type[i]=1;
            else if(c[i]>='1' && c[i]<='9')
                type[i]=3;
            else
                type[i]=4;
        }
        if(type[0]==2 && type[1]==2)
            return Collator.getInstance(java.util.Locale.CHINESE).compare(str1, str2);
        if(type[0]==type[1]) //同一类
            return str1.compareTo(str2);
        return type[0]-type[1];
    }
}
