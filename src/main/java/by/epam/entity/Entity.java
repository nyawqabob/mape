package by.epam.entity;

import java.io.Serializable;

public abstract class Entity implements Cloneable, Serializable {

    public abstract int getId();

    public abstract String getName();

    public abstract void setName(String name);

    public abstract void setId(int id);
}
