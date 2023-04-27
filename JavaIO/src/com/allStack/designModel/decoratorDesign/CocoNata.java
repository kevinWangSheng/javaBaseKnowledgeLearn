package com.allStack.designModel.decoratorDesign;

/**
 * @author wang
 * @create 2023-2023-18-16:38
 */
public class CocoNata implements Milk{
    private Milk milk;
    public CocoNata(Milk milk){
        this.milk = milk;
    }
    @Override
    public int cost() {
        description();
        return 2+milk.cost();
    }

    @Override
    public String description() {
        return milk.description()+" add coco nata";
    }
}
