package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {

private final String name;
static Map<String,Category> sameInstanceForSameName = new HashMap<>();

    private Category(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }


    public static Category of (String name)  {
        if (name == null){
            throw new IllegalArgumentException("Category name can't be null");
        }
        name = name.substring(0,1).toUpperCase()+name.substring(1);
        //return new Category(name);
        if (sameInstanceForSameName.containsKey(name)){
            return sameInstanceForSameName.get(name);
        }

        Category oddCat = new Category(name);
        sameInstanceForSameName.put(name, oddCat);//Om namnet redan finns i map, retunera den befintliga
        return oddCat;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

