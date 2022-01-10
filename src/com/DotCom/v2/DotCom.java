package com.DotCom.v2;

import java.util.ArrayList;

public class DotCom {                                                                                                   //DotCom的实例变量：
    private ArrayList<String> locationCells;                                                                            //————保存位置的ArrayList
    private String name;                                                                                                //————DotCom的名称

    public void setLocationCells(ArrayList<String> loc) {                                                               //更新DotCom位置的setter方法
        locationCells = loc;
    }

    public void setName(String n) {                                                                                     //基本的setter方法
        name = n;
    }

    public String checkYourself(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput);                                                                   //使用到indexOf()方法！如果玩家猜中的话，这个方法会返回它的位置。如果没有的话会反回-1
        if (index >= 0) {
            locationCells.remove(index);                                                                                //删除被猜中的元素

            if (locationCells.isEmpty()) {                                                                              //用这个方法来判别是否击沉
                result = "kill";
                System.out.println("Ouch! You sunk " + name + " :(");                                                   //列出击沉信息
            } else {
                result = "hit";
            }//close if
        }//close if
        return result;                                                                                                  //返回状态

    }//close method
}//close class
