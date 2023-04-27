package com.allStack.designModel.decoratorDesign;

/**
 * @author wang
 * @create 2023-2023-18-16:37
 */
public class TaroMilkTea implements Milk{
    @Override
    public int cost() {
        description();
        return 15;
    }

    @Override
    public String description() {
        return "taro milk tea";
    }
}
