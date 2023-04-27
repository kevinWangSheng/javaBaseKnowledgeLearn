package com.allStack.designModel.decoratorDesign;

/**
 * @author wang
 * @create 2023-2023-18-16:41
 */
public class Ormosia implements Milk{
    private Milk milk;
    public Ormosia(Milk milk){
        this.milk = milk;
    }

    @Override
    public int cost() {
        description();
        return 3+ milk.cost();
    }

    @Override
    public String description() {

        return milk.description() + " add Ormosia";
    }
}
