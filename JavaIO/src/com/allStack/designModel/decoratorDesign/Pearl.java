package com.allStack.designModel.decoratorDesign;

/**
 * @author wang
 * @create 2023-2023-18-16:40
 */
public class Pearl implements Milk{
    private Milk milk;

    public Pearl(Milk milk){
        this.milk = milk;
    }

    @Override
    public int cost() {
        return 1+milk.cost();
    }

    @Override
    public String description() {
        return milk.description()+" add pearl";
    }
}
