package com.allStack.designModel.decoratorDesign;

/**
 * @author wang
 * @create 2023-2023-18-16:11
 */
public abstract class DecoatorDesign {
    public static void main(String[] args) {
        Milk milk = new BubbleMilkTea();
        milk = new Pearl(milk);
        milk = new Ormosia(milk);
        milk = new CocoNata(milk);
        System.out.println("the milk tea is :"+ milk.description());
        System.out.println("cost:"+milk.cost());

    }

}
