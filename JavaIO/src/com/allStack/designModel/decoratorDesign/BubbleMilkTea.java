package com.allStack.designModel.decoratorDesign;

/**
 * @author wang
 * @create 2023-2023-18-16:36
 */
public class BubbleMilkTea implements Milk{
    @Override
    public int cost() {
        description();
        return 10;
    }

    @Override
    public String description() {
        return "bubble milk tea";
    }
}
