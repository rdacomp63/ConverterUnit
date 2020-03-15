package org.company;

public interface IContainer {
    void Add(String KeyA, String KeyB, Float Value);

    void Delete(String KeyA, String KeyB);

    Float Find(String KeyA, String KeyB);

    Integer Size();

    void Clear();
}